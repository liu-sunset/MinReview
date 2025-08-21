package peng.zhi.liu.controller.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import peng.zhi.liu.dto.UserPageDTO;
import peng.zhi.liu.result.PageResult;
import peng.zhi.liu.result.Result;
import peng.zhi.liu.service.UserService;

@Slf4j
@RestController("adminUserController")
@RequestMapping("/admin/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public Result userPageController(UserPageDTO userPageDTO){
        log.info("用户分页查询参数：{}",userPageDTO);
        PageResult pageResult = userService.userPageService(userPageDTO);
        return Result.success(pageResult);
    }

    @PutMapping("/status/{userId}")
    public Result modifyUserStatusController(@PathVariable Long userId,@RequestParam Integer status){
        log.info("修改用户ID是{}的用户状态为{}",userId,status);
        userService.modifyUserStatusService(userId,status);
        return Result.success();
    }

    @DeleteMapping("/{userId}")
    public Result deleteUserController(@PathVariable Long userId){
        log.info("删除的用户ID是{}",userId);
        userService.deleteUserService(userId);
        return Result.success();
    }
}
