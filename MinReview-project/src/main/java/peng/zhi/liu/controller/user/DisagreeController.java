package peng.zhi.liu.controller.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import peng.zhi.liu.dto.DisagreeDTO;
import peng.zhi.liu.result.Result;
import peng.zhi.liu.service.DisagreeService;

@Slf4j
@RestController("userDisagreeController")
@RequestMapping("/user")
public class DisagreeController {
    @Autowired
    private DisagreeService disagreeService;

    //todo:test
    @PostMapping("/dislike")
    public Result dislikeDish(@RequestBody DisagreeDTO disagreeDTO) {
        log.info("用户点踩菜品,参数: {}", disagreeDTO);
        disagreeService.dislikeDishService(disagreeDTO);
        return Result.success();
    }

    //todo:test
    @DeleteMapping("/dislike/{dishId}/{userId}")
    public Result cancelDislikeDish(@PathVariable Long dishId, @PathVariable Long userId) {
        log.info("用户取消点踩菜品,菜品id: {}, 用户id: {}", dishId, userId);
        disagreeService.cancelDislikeDishService(dishId, userId);
        return Result.success();
    }
}