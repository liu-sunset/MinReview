package peng.zhi.liu.controller.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import peng.zhi.liu.dto.UserLoginDTO;
import peng.zhi.liu.result.Result;
import peng.zhi.liu.service.UserService;
import peng.zhi.liu.service.CanteenService;
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
    public Result userLoginController(@RequestBody UserLoginDTO userLoginDTO){
        log.info("用户登录信息:{}",userLoginDTO);
        UserLoginVO userLoginVO = userService.userLoginService(userLoginDTO);
        return Result.success(userLoginVO);
    }

    @PostMapping("/register")
    public Result userRegisterController(@RequestBody UserLoginDTO userLoginDTO){
        log.info("用户注册信息:{}",userLoginDTO);
        userService.userRegisterService(userLoginDTO);
        return Result.success();
    }
    @GetMapping("/personInfo")
    public Result getUserInfo(@RequestParam Long id) {
        log.info("获取用户信息,用户id: {}", id);
        UserInfoVO userInfoVO = userService.getUserInfoByIdService(id);
        return Result.success(userInfoVO);
    }

    @PutMapping("/personInfo")
    public Result updateUserInfo(@RequestBody UpdateUserDTO updateUserDTO) {
        log.info("更新用户信息: {}", updateUserDTO);
        userService.updateUserInfoService(updateUserDTO);
        return Result.success();
    }

    @DeleteMapping("/personInfo")
    public Result deleteUser(@RequestParam Long id) {
        log.info("注销账号,用户id: {}", id);
        userService.deleteUserService(id);
        return Result.success();
    }
}