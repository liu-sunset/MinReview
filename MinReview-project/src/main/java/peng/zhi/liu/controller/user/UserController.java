package peng.zhi.liu.controller.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import peng.zhi.liu.result.Result;
import peng.zhi.liu.service.UserService;
import peng.zhi.liu.service.CampusService;
import peng.zhi.liu.service.FloorService;
import peng.zhi.liu.service.CanteenService;
import peng.zhi.liu.vo.CanteenVO;
import peng.zhi.liu.vo.UserInfoVO;
import peng.zhi.liu.vo.CampusPageVO;
import peng.zhi.liu.vo.FloorVO;
import peng.zhi.liu.dto.UpdateUserDTO;

import java.util.List;

//用户控制器
@Slf4j
@RestController("userUserController")
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private CanteenService canteenService;

    //todo:test
    @GetMapping("/personInfo")
    public Result getUserInfo(@RequestParam Long id) {
        log.info("获取用户信息,用户id: {}", id);
        UserInfoVO userInfoVO = userService.getUserInfoByIdService(id);
        return Result.success(userInfoVO);
    }

    //todo:test
    @PutMapping("/personInfo")
    public Result updateUserInfo(@RequestBody UpdateUserDTO updateUserDTO) {
        log.info("更新用户信息: {}", updateUserDTO);
        userService.updateUserInfoService(updateUserDTO);
        return Result.success();
    }

    //todo:test
    @DeleteMapping("/personInfo")
    public Result deleteUser(@RequestParam Long id) {
        log.info("注销账号,用户id: {}", id);
        userService.deleteUserService(id);
        return Result.success();
    }
}