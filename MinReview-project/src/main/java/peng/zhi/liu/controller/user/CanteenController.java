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
import peng.zhi.liu.service.CanteenService;
import peng.zhi.liu.vo.CanteenVO;

import java.util.List;

@Slf4j
@RestController("userCanteenController")
@RequestMapping("/user")
public class CanteenController {
    @Autowired
    private CanteenService canteenService;
    @JwtInspect
    @GetMapping("/canteen/list/{campusId}")
    @OperationLog(OperationTypeEnum.select)
    public Result getCanteenListByCampusId(@PathVariable Long campusId) {
        log.info("根据校区ID获取食堂列表,校区id: {}", campusId);
        List<CanteenVO> canteenList = canteenService.getCanteenListByCampusIdService(campusId);
        return Result.success(canteenList);
    }
}
