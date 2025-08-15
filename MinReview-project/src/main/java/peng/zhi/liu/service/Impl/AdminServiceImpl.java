package peng.zhi.liu.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import peng.zhi.liu.constant.AdminConstant;
import peng.zhi.liu.dto.AdminLoginDTO;
import peng.zhi.liu.entity.Admin;
import peng.zhi.liu.exception.AdminException;
import peng.zhi.liu.mapper.AdminMapper;
import peng.zhi.liu.property.JWTProperty;
import peng.zhi.liu.service.AdminService;
import peng.zhi.liu.utils.JWTUtils;
import peng.zhi.liu.vo.AdminLoginVO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private JWTProperty jwtProperty;
    //员工登录
    @Override
    public AdminLoginVO empLoginService(AdminLoginDTO adminLoginDTO) {
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
        AdminLoginVO adminLoginVO = AdminLoginVO.builder()
                .token(token)
                .id(adminList.get(0).getId())
                .name(adminList.get(0).getName())
                .username(adminList.get(0).getUsername())
                .build();
        return adminLoginVO;
    }
}
