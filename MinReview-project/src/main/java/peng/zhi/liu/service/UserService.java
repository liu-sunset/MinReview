package peng.zhi.liu.service;

import peng.zhi.liu.dto.UserPageDTO;
import peng.zhi.liu.result.PageResult;

import java.util.List;

public interface UserService {
    //用户分页查询
    public PageResult userPageService(UserPageDTO userPageDTO);
    //修改用户状态
    public void modifyUserStatusService(Long userId,Integer status);
    //删除用户
    public void deleteUserService(Long userId);
}
