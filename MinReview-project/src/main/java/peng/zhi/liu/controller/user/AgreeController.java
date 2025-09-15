package peng.zhi.liu.controller.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import peng.zhi.liu.annotation.JwtInspect;
import peng.zhi.liu.annotation.OperationLog;
import peng.zhi.liu.dto.AgreeDTO;
import peng.zhi.liu.enums.OperationTypeEnum;
import peng.zhi.liu.result.Result;
import peng.zhi.liu.service.AgreeService;

@Slf4j
@RestController("userAgreeController")
@RequestMapping("/user")
public class AgreeController {
    @Autowired
    private AgreeService agreeService;

    @JwtInspect
    @PostMapping("/like")
    @OperationLog(OperationTypeEnum.insert)
    public Result likeDish(@RequestBody AgreeDTO agreeDTO) {
        log.info("用户点赞菜品,参数: {}", agreeDTO);
        agreeService.likeDishService(agreeDTO);
        return Result.success();
    }

    @JwtInspect
    @DeleteMapping("/like/{dishId}/{userId}")
    @OperationLog(OperationTypeEnum.delete)
    public Result cancelLikeDish(@PathVariable Long dishId, @PathVariable Long userId) {
        log.info("用户取消点赞菜品,菜品id: {}, 用户id: {}", dishId, userId);
        agreeService.cancelLikeDishService(dishId, userId);
        return Result.success();
    }
}
