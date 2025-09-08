package peng.zhi.liu.controller.user;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import peng.zhi.liu.annotation.JwtInspect;
import peng.zhi.liu.dto.ModifyUserPasswordDTO;
import peng.zhi.liu.dto.UserLoginDTO;
import peng.zhi.liu.result.Result;
import peng.zhi.liu.service.UserService;
import peng.zhi.liu.vo.UserInfoVO;
import peng.zhi.liu.dto.UpdateUserDTO;
import peng.zhi.liu.vo.UserLoginVO;


//用户控制器
@Slf4j
@RestController("userUserController")
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result userLoginController(@RequestBody UserLoginDTO userLoginDTO,HttpServletRequest httpServletRequest){
        log.info("用户登录信息:{}",userLoginDTO);
        UserLoginVO userLoginVO = userService.userLoginService(userLoginDTO,httpServletRequest);
        return Result.success(userLoginVO);
    }

    @PostMapping("/register")
    public Result userRegisterController(@RequestBody UserLoginDTO userLoginDTO,HttpServletRequest httpServletRequest){
        log.info("用户注册信息:{}",userLoginDTO);
        userService.userRegisterService(userLoginDTO,httpServletRequest);
        return Result.success();
    }

    @JwtInspect
    @GetMapping("/personInfo")
    public Result getUserInfo(@RequestParam Long id) {
        log.info("获取用户信息,用户id: {}", id);
        UserInfoVO userInfoVO = userService.getUserInfoByIdService(id);
        return Result.success(userInfoVO);
    }

    @JwtInspect
    @PutMapping("/personInfo")
    public Result updateUserInfo(@RequestBody UpdateUserDTO updateUserDTO) {
        log.info("更新用户信息: {}", updateUserDTO);
        userService.updateUserInfoService(updateUserDTO);
        return Result.success();
    }

    @JwtInspect
    @DeleteMapping("/personInfo")
    public Result deleteUser(@RequestParam Long id) {
        log.info("注销账号,用户id: {}", id);
        userService.deleteUserService(id);
        return Result.success();
    }

    @JwtInspect
    @PostMapping("/password/{userId}")
    public Result modifyUserPasswordController(@PathVariable Long userId,@RequestBody ModifyUserPasswordDTO modifyUserPasswordDTO){
        log.info("用户修改密码，用户ID：{}，密码信息：{}",userId, modifyUserPasswordDTO);
        userService.updateUserPasswordService(userId, modifyUserPasswordDTO);
        return Result.success();
    }


    @JwtInspect
    @PostMapping("/logout")
    public Result userLogoutController(HttpServletRequest httpServletRequest){
        log.info("用户登出");
        userService.userLoginoutService(httpServletRequest);
        return Result.success();
    }
}