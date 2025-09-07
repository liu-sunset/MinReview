package peng.zhi.liu.controller.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import peng.zhi.liu.annotation.JwtInspect;
import peng.zhi.liu.dto.UserPageDTO;
import peng.zhi.liu.result.PageResult;
import peng.zhi.liu.result.Result;
import peng.zhi.liu.service.UserService;
import peng.zhi.liu.vo.UserPageVO;

@Slf4j
@RestController("adminUserController")
@RequestMapping("/admin/user")
public class UserController {
    @Autowired
    private UserService userService;

    @JwtInspect
    @GetMapping("/list")
    public Result userPageController(UserPageDTO userPageDTO){
        log.info("用户分页查询参数：{}",userPageDTO);
        PageResult<UserPageVO> pageResult = userService.userPageService(userPageDTO);
        return Result.success(pageResult);
    }

    @JwtInspect
    @PutMapping("/status/{userId}")
    public Result modifyUserStatusController(@PathVariable Long userId,@RequestParam Integer status){
        log.info("修改用户ID是{}的用户状态为{}",userId,status);
        userService.modifyUserStatusService(userId,status);
        return Result.success();
    }

    @JwtInspect
    @DeleteMapping("/{userId}")
    public Result deleteUserController(@PathVariable Long userId){
        log.info("删除的用户ID是{}",userId);
        userService.deleteUserService(userId);
        return Result.success();
    }
}
