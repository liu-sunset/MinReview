package peng.zhi.liu.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import peng.zhi.liu.constant.MessageConstant;
import peng.zhi.liu.constant.UserConstant;
import peng.zhi.liu.controller.CaptchaController;
import peng.zhi.liu.dto.ModifyUserPasswordDTO;
import peng.zhi.liu.dto.UserLoginDTO;
import peng.zhi.liu.dto.UserPageDTO;
import peng.zhi.liu.dto.UpdateUserDTO;
import peng.zhi.liu.entity.User;
import peng.zhi.liu.exception.AdminException;
import peng.zhi.liu.exception.UserException;
import peng.zhi.liu.mapper.CommentMapper;
import peng.zhi.liu.mapper.UserMapper;
import peng.zhi.liu.property.JWTProperty;
import peng.zhi.liu.result.PageResult;
import peng.zhi.liu.service.UserService;
import peng.zhi.liu.utils.AliyunOSSUtils;
import peng.zhi.liu.utils.BaseContext;
import peng.zhi.liu.utils.JWTUtils;
import peng.zhi.liu.vo.UserInfoVO;
import peng.zhi.liu.vo.UserLoginVO;
import peng.zhi.liu.vo.UserPageVO;
import org.springframework.data.redis.core.StringRedisTemplate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private JWTProperty jwtProperty;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private CaptchaController captchaController;
    @Autowired
    private AliyunOSSUtils aliyunOSSUtils;
    //用户分页查询
    @Override
    public PageResult<UserPageVO> userPageService(UserPageDTO userPageDTO) {
        PageHelper.startPage(userPageDTO.getPage(),userPageDTO.getPageSize());
        Page<UserPageVO> page = userMapper.userPageMapper(userPageDTO);
        return new PageResult<UserPageVO>(page.getTotal(),page.getResult());
    }

    //修改用户状态
    @Override
    public void modifyUserStatusService(Long userId, Integer status) {
        User user = User.builder()
                .id(userId)
                .status(status)
                .updateTime(LocalDateTime.now())
                .build();
        userMapper.modifyUserMapper(user);
    }

    //删除用户
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteUserService(List<Long> ids) throws Exception {
        List<String> paths = new ArrayList<>();
        for (int i = 0; i < ids.size(); i++) {
            User user = new User();
            user.setId(ids.get(i));
            List<User> userList = userMapper.getUser(user);
            paths.add(userList.get(0).getAvatarUrl());
        }
        //批量删除用户头像文件
        aliyunOSSUtils.deleteAmount(paths);
        //删除用户
        userMapper.deleteUserMapper(ids);
        //修改此用户的评论头像为默认头像
        for (int i = 0; i < ids.size(); i++) {
            Long id = ids.get(i);
            commentMapper.modifyCommentAvatarMapper(id,MessageConstant.DEFAULT_AVATAR);
        }
    }

    //用户注销账号
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteUserByUserService(Long id) throws Exception {
        User user = new User();
        user.setId(id);
        List<User> userList = userMapper.getUser(user);
        //删除OSS服务中的图片
        aliyunOSSUtils.deleteObject(userList.get(0).getAvatarUrl());
        //删除用户
        List<Long> ids = new ArrayList<>();
        ids.add(id);
        userMapper.deleteUserMapper(ids);
        //修改此用户的评论头像为默认头像
        commentMapper.modifyCommentAvatarMapper(id,MessageConstant.DEFAULT_AVATAR);
    }

    //根据id获取用户信息
    @Override
    public UserInfoVO getUserInfoByIdService(Long id) {
        return userMapper.getUserInfoById(id);
    }

    //更新用户信息
    @Override
    public void updateUserInfoService(UpdateUserDTO updateUserDTO) {
        User user = User.builder()
                .id(updateUserDTO.getId())
                .name(updateUserDTO.getName())
                .avatarUrl(updateUserDTO.getAvatarUrl())
                .phone(updateUserDTO.getPhone())
                .updateTime(LocalDateTime.now())
                .gender(updateUserDTO.getGender())
                .build();
        //查找是否存在同名情况
        User userTemp = userMapper.selectUserByName(user.getName());
        if(userTemp!=null){
            throw new UserException(UserConstant.USER_EXIST);
        }
        userMapper.modifyUserMapper(user);
    }

    @Override
    public UserLoginVO userLoginService(UserLoginDTO userLoginDTO,HttpServletRequest httpServletRequest) {
        //校验图形验证码
        if (!captchaController.verifyCaptcha(userLoginDTO.getCaptcha(),httpServletRequest)){
            throw new UserException(MessageConstant.INDENTITY_FALSE);
        }
        //查询数据库
        User user = userMapper.selectUserByName(userLoginDTO.getName());
        if(user==null){
            throw new UserException(UserConstant.USER_NOT_EXIST);
        }
        String password = DigestUtils.md5DigestAsHex(userLoginDTO.getPassword().getBytes());
        if(!password.equals(password)){
            throw new UserException(UserConstant.PASSWORD_ERROR);
        }
        UserInfoVO userInfoVO = UserInfoVO.builder().id(user.getId()).name(user.getName()).avatarUrl(user.getAvatarUrl())
                .phone(user.getPhone()).gender(user.getGender()).createTime(user.getCreateTime()).build();
        HashMap<String, Object> map = new HashMap<>();
        map.put(UserConstant.USER_ID_INTERCEPTER,user.getId());
        String token = JWTUtils.createJWT(jwtProperty.getSecretKey(), jwtProperty.getTtlTime(), map);
        return UserLoginVO.builder().token(token).userInfo(userInfoVO).build();
    }

    @Override
    public void userRegisterService(UserLoginDTO userLoginDTO,HttpServletRequest httpServletRequest) {
        //检验图片验证码
        if (!captchaController.verifyCaptcha(userLoginDTO.getCaptcha(),httpServletRequest)){
            throw new AdminException(MessageConstant.INDENTITY_FALSE);
        }
        //查询数据库
        User user = userMapper.selectUserByName(userLoginDTO.getName());
        if(user!=null){
            throw new UserException(UserConstant.USER_EXIST);
        }
        String password = DigestUtils.md5DigestAsHex(userLoginDTO.getPassword().getBytes());
        User user1 = new User();
        user1.setName(userLoginDTO.getName());
        user1.setPassword(password);
        user1.setCreateTime(LocalDateTime.now());
        user1.setUpdateTime(LocalDateTime.now());
        userMapper.addUserMapper(user1);
    }

    @Override
    public void updateUserPasswordService(Long userId, ModifyUserPasswordDTO modifyUserPasswordDTO) {
        User user = new User();
        user.setId(userId);
        String oldPassword = DigestUtils.md5DigestAsHex(modifyUserPasswordDTO.getOldPassword().getBytes());
        user.setPassword(oldPassword);
        List<User> userList = userMapper.getUser(user);
        if(userList==null||userList.isEmpty()){
            throw new UserException(UserConstant.USER_NOT_EXIST);
        }

        String newPassword = DigestUtils.md5DigestAsHex(modifyUserPasswordDTO.getNewPassword().getBytes());
        user.setPassword(newPassword);
        user.setUpdateTime(LocalDateTime.now());
        userMapper.modifyUserMapper(user);
    }

    @Override
    public void userLoginoutService(HttpServletRequest httpServletRequest) {
        //获取用户的token
        String authorization = httpServletRequest.getHeader("Authorization");
        String token = authorization.substring(7);
        stringRedisTemplate.opsForValue().set(BaseContext.getId().toString(),token,jwtProperty.getTtlTime()/1000, TimeUnit.SECONDS);
    }
}
