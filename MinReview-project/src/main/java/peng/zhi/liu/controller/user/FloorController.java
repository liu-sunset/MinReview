package peng.zhi.liu.controller.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import peng.zhi.liu.annotation.JwtInspect;
import peng.zhi.liu.annotation.OperationLog;
import peng.zhi.liu.enums.OperationTypeEnum;
import peng.zhi.liu.result.Result;
import peng.zhi.liu.service.FloorService;
import peng.zhi.liu.vo.FloorVO;

import java.util.List;

@Slf4j
@RestController("userFloorController")
@RequestMapping("/user")
public class FloorController {
    @Autowired
    private FloorService floorService;

    @JwtInspect
    @GetMapping("/floor/list/{canteenId}")
    @OperationLog(OperationTypeEnum.select)
    public Result getFloorListByCanteenId(@PathVariable Long canteenId) {
        log.info("根据食堂ID获取楼层列表,食堂ID: {}", canteenId);
        List<FloorVO> floorList = floorService.getFloorListByCanteenIdService(canteenId);
        return Result.success(floorList);
    }
}
