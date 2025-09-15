package peng.zhi.liu.service;

import jakarta.servlet.http.HttpServletRequest;
import peng.zhi.liu.dto.ModifyUserPasswordDTO;
import peng.zhi.liu.dto.UserLoginDTO;
import peng.zhi.liu.dto.UserPageDTO;
import peng.zhi.liu.result.PageResult;
import peng.zhi.liu.vo.UserInfoVO;
import peng.zhi.liu.dto.UpdateUserDTO;
import peng.zhi.liu.vo.UserLoginVO;
import peng.zhi.liu.vo.UserPageVO;

import java.util.List;

public interface UserService {
    //用户分页查询
    public PageResult<UserPageVO> userPageService(UserPageDTO userPageDTO);
    //修改用户状态
    public void modifyUserStatusService(Long userId,Integer status);
    //管理员删除用户
    public void deleteUserService(List<Long> ids) throws Exception;
    //用户注销账号
    public void deleteUserByUserService(Long id) throws Exception;
    //根据id获取用户信息
    public UserInfoVO getUserInfoByIdService(Long id);
    //更新用户信息
    public void updateUserInfoService(UpdateUserDTO updateUserDTO);
    //用户登录
    public UserLoginVO userLoginService(UserLoginDTO userLoginDTO,HttpServletRequest httpServletRequest);
    //用户注册
    public void userRegisterService(UserLoginDTO userLoginDTO,HttpServletRequest httpServletRequest);
    //修改用户密码
    public void updateUserPasswordService(Long userId, ModifyUserPasswordDTO modifyUserPasswordDTO);
    //用户登出
    public void userLoginoutService(HttpServletRequest httpServletRequest);
}
