package peng.zhi.liu.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peng.zhi.liu.dto.UserPageDTO;
import peng.zhi.liu.dto.UpdateUserDTO;
import peng.zhi.liu.entity.User;
import peng.zhi.liu.mapper.UserMapper;
import peng.zhi.liu.result.PageResult;
import peng.zhi.liu.service.UserService;
import peng.zhi.liu.vo.UserInfoVO;
import peng.zhi.liu.vo.UserPageVO;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
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
    public void deleteUserService(Long userId) {
        userMapper.deleteUserMapper(userId);
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
                .build();
        userMapper.modifyUserMapper(user);
    }
}
