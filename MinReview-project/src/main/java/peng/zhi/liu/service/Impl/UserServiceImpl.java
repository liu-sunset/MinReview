package peng.zhi.liu.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import net.sf.jsqlparser.statement.comment.Comment;
import opennlp.tools.namefind.TokenNameFinderEvaluationMonitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import peng.zhi.liu.constant.UserConstant;
import peng.zhi.liu.dto.UserLoginDTO;
import peng.zhi.liu.dto.UserPageDTO;
import peng.zhi.liu.dto.UpdateUserDTO;
import peng.zhi.liu.entity.User;
import peng.zhi.liu.entity.UserComment;
import peng.zhi.liu.exception.UserException;
import peng.zhi.liu.mapper.CommentMapper;
import peng.zhi.liu.mapper.UserMapper;
import peng.zhi.liu.property.JWTProperty;
import peng.zhi.liu.result.PageResult;
import peng.zhi.liu.result.Result;
import peng.zhi.liu.service.UserService;
import peng.zhi.liu.utils.JWTUtils;
import peng.zhi.liu.vo.UserInfoVO;
import peng.zhi.liu.vo.UserLoginVO;
import peng.zhi.liu.vo.UserPageVO;

import java.net.UnknownServiceException;
import java.time.LocalDateTime;
import java.util.HashMap;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private JWTProperty jwtProperty;
    //用户分页查询
    @Override
    public PageResult userPageService(UserPageDTO userPageDTO) {
        PageHelper.startPage(userPageDTO.getPage(),userPageDTO.getPageSize());
        Page<UserPageVO> page = userMapper.userPageMapper(userPageDTO);
        return new PageResult(page.getTotal(),page.getResult());
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
    public void deleteUserService(Long userId) {
        userMapper.deleteUserMapper(userId);
        commentMapper.deleteCommentMapper(userId);
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
                .name(updateUserDTO.getNickName())
                .avatarUrl(updateUserDTO.getAvatarUrl())
                .updateTime(LocalDateTime.now())
                .gender(updateUserDTO.getGender())
                .build();
        userMapper.modifyUserMapper(user);
    }

    @Override
    public UserLoginVO userLoginService(UserLoginDTO userLoginDTO) {
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
    public void userRegisterService(UserLoginDTO userLoginDTO) {
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
}
