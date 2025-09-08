package peng.zhi.liu.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import peng.zhi.liu.constant.AdminConstant;
import peng.zhi.liu.constant.MessageConstant;
import peng.zhi.liu.controller.CaptchaController;
import peng.zhi.liu.dto.AdminLoginDTO;
import peng.zhi.liu.dto.AdminPageDTO;
import peng.zhi.liu.dto.AddAdminDTO;
import peng.zhi.liu.dto.ModifyAdminPasswordDTO;
import peng.zhi.liu.entity.Admin;
import peng.zhi.liu.exception.AdminException;
import peng.zhi.liu.mapper.AdminMapper;
import peng.zhi.liu.property.JWTProperty;
import peng.zhi.liu.result.PageResult;
import peng.zhi.liu.service.AdminService;
import peng.zhi.liu.utils.BaseContext;
import peng.zhi.liu.utils.JWTUtils;
import peng.zhi.liu.vo.AdminLoginVO;
import peng.zhi.liu.vo.AdminPageVO;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private JWTProperty jwtProperty;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private CaptchaController captchaController;
    //管理员分页查询
    @Override
    public PageResult<AdminPageVO> adminPageService(AdminPageDTO adminPageDTO) {
        PageHelper.startPage(adminPageDTO.getPage(), adminPageDTO.getPageSize());
        Page<AdminPageVO> page = adminMapper.adminPageMapper(adminPageDTO);
        PageResult<AdminPageVO> pageResult = new PageResult<>();
        pageResult.setTotal(page.getTotal());
        pageResult.setList(page.getResult());
        return pageResult;
    }

    // 添加管理员
    @Override
    public void addAdminService(AddAdminDTO addAdminDTO) {
        // 1. 检查用户名是否已存在
        Admin adminCheck = new Admin();
        adminCheck.setUsername(addAdminDTO.getUsername());
        List<Admin> adminList = adminMapper.selectAdmin(adminCheck);
        if (!adminList.isEmpty()) {
            throw new AdminException(AdminConstant.ADMIN_USERNAME_EXIST);
        }

        // 2. 封装Admin实体类
        Admin admin = new Admin();
        admin.setUsername(addAdminDTO.getUsername());
        // 密码MD5加密
        String password = DigestUtils.md5DigestAsHex(addAdminDTO.getPassword().getBytes());
        admin.setPassword(password);
        admin.setName(addAdminDTO.getName());
        // 设置默认状态为启用
        admin.setStatus(AdminConstant.ADMIN_IS_OPEN);
        // 设置时间
        LocalDateTime now = LocalDateTime.now();
        admin.setCreateTime(now);
        admin.setUpdateTime(now);

        // 3. 调用mapper插入数据
        adminMapper.addAdmin(admin);
    }

    //员工登录
    @Override
    public AdminLoginVO empLoginService(AdminLoginDTO adminLoginDTO,HttpServletRequest httpServletRequest) {
        //检验图片验证码
        if (!captchaController.verifyCaptcha(adminLoginDTO.getCaptcha(),httpServletRequest)){
            throw new AdminException(MessageConstant.INDENTITY_FALSE);
        }
        String username = adminLoginDTO.getUsername();
        String password = adminLoginDTO.getPassword();
        Admin admin = new Admin();
        admin.setUsername(username);
        List<Admin> adminList = adminMapper.selectAdmin(admin);

        //如果没有此用户
        if(adminList.isEmpty()){
            throw new AdminException(AdminConstant.ADMIN_NOT_EXSIT);
        }

        //密码md5加密
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        if(!password.equals(adminList.get(0).getPassword())){
            throw new AdminException(AdminConstant.ADMIN_PASSWORD_ERROR);
        }

        if (Objects.equals(adminList.get(0).getStatus(), AdminConstant.ADMIN_IS_CLOSE)){
            throw new AdminException(AdminConstant.ADMIN_IS_CLOSE);
        }
        //构建token
        Map<String, Object> map = new HashMap<>();
        map.put(AdminConstant.ADMIN_ID_INTERCEPTER,adminList.get(0).getId());
        String token = JWTUtils.createJWT(jwtProperty.getSecretKey(), jwtProperty.getTtlTime(), map);

        AdminLoginVO adminLoginVO = new AdminLoginVO();
        adminLoginVO.setId(adminList.get(0).getId());
        adminLoginVO.setUsername(adminList.get(0).getUsername());
        adminLoginVO.setName(adminList.get(0).getName());
        adminLoginVO.setToken(token);
        return adminLoginVO;
    }

    // 更新管理员名称
    @Override
    public void updateAdminNameService(Long adminId, String name) {
        // 1. 检查管理员是否存在
        Admin adminCheck = new Admin();
        adminCheck.setId(adminId);
        List<Admin> adminList = adminMapper.selectAdmin(adminCheck);
        if (adminList.isEmpty()) {
            throw new AdminException(AdminConstant.ADMIN_NOT_EXSIT);
        }

        // 2. 封装Admin实体类
        Admin admin = new Admin();
        admin.setId(adminId);
        admin.setName(name);
        // 设置更新时间
        admin.setUpdateTime(LocalDateTime.now());

        // 3. 调用mapper更新数据
        adminMapper.updateAdminMapper(admin);
    }

    // 删除管理员
    @Override
    public void deleteAdminService(Long adminId) {
        // 1. 检查管理员是否存在
        Admin adminCheck = new Admin();
        adminCheck.setId(adminId);
        List<Admin> adminList = adminMapper.selectAdmin(adminCheck);
        if (adminList.isEmpty()) {
            throw new AdminException(AdminConstant.ADMIN_NOT_EXSIT);
        }

        // 2. 调用mapper删除数据
        adminMapper.deleteAdmin(adminId);
    }

    // 更新管理员状态
    @Override
    public void updateAdminStatusService(Long adminId, Integer status) {
        // 1. 检查管理员是否存在
        Admin adminCheck = new Admin();
        adminCheck.setId(adminId);
        List<Admin> adminList = adminMapper.selectAdmin(adminCheck);
        if (adminList.isEmpty()) {
            throw new AdminException(AdminConstant.ADMIN_NOT_EXSIT);
        }

        // 2. 封装Admin实体类
        Admin admin = new Admin();
        admin.setId(adminId);
        admin.setStatus(status);
        // 设置更新时间
        admin.setUpdateTime(LocalDateTime.now());

        // 3. 调用mapper更新数据
        adminMapper.updateAdminMapper(admin);
    }

    @Override
    public void updateAdminPasswordController(Long adminId, ModifyAdminPasswordDTO modifyAdminPasswordDTO) {
        Admin admin = new Admin();
        admin.setId(adminId);
        String oldPassword = DigestUtils.md5DigestAsHex(modifyAdminPasswordDTO.getOldPassword().getBytes());
        admin.setPassword(oldPassword);
        List<Admin> adminList = adminMapper.selectAdmin(admin);
        if(adminList==null||adminList.isEmpty()){
            throw new AdminException(AdminConstant.ADMIN_NOT_EXSIT);
        }

        String newPassword = DigestUtils.md5DigestAsHex(modifyAdminPasswordDTO.getNewPassword().getBytes());
        admin.setPassword(newPassword);
        admin.setUpdateTime(LocalDateTime.now());
        adminMapper.updateAdminMapper(admin);
    }

    @Override
    public void adminLoginoutService(HttpServletRequest httpServletRequest) {
        //获取用户的token
        String authorization = httpServletRequest.getHeader("Authorization");
        String token = authorization.substring(7);
        Long ID = -BaseContext.getId();
        stringRedisTemplate.opsForValue().set(ID.toString(),token,jwtProperty.getTtlTime()/1000, TimeUnit.SECONDS);
    }
}
