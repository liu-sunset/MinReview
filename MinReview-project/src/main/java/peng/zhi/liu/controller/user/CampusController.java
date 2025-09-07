package peng.zhi.liu.controller.user;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import peng.zhi.liu.annotation.JwtInspect;
import peng.zhi.liu.result.Result;
import peng.zhi.liu.service.CampusService;
import peng.zhi.liu.vo.CampusPageVO;
import java.util.List;

@Slf4j
@RestController("userCampusController")
@RequestMapping("/user")
public class CampusController {
    @Autowired
    private CampusService campusService;


    @JwtInspect
    @GetMapping("/campus/list")
    public Result getCampusList() {
        log.info("获取校区列表");
        List<CampusPageVO> campusList = campusService.getCampusListService();
        return Result.success(campusList);
    }

}
