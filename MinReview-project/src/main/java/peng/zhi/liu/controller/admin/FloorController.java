package peng.zhi.liu.controller.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import peng.zhi.liu.dto.AddFloorDTO;
import peng.zhi.liu.dto.FloorPageDTO;
import peng.zhi.liu.result.PageResult;
import peng.zhi.liu.result.Result;
import peng.zhi.liu.service.FloorService;
import peng.zhi.liu.vo.FloorPageVO;

//楼层管理Controller
@Slf4j
@RestController
@RequestMapping("/admin/floor")
public class FloorController {
    @Autowired
    private FloorService floorService;

    //todo:test
    @PostMapping
    public Result addFloorController(@RequestBody AddFloorDTO addFloorDTO) {
        log.info("添加楼层: {}", addFloorDTO);
        floorService.addFloorService(addFloorDTO);
        return Result.success();
    }

    //todo:test
    @GetMapping("/list")
    public Result floorPageController(FloorPageDTO floorPageDTO) {
        log.info("楼层分页查询参数: {}", floorPageDTO);
        PageResult<FloorPageVO> pageResult = floorService.floorPageService(floorPageDTO);
        return Result.success(pageResult);
    }

    //todo:test
    @DeleteMapping("/{floorId}")
    public Result deleteFloorController(@PathVariable Long floorId) {
        log.info("删除楼层, id: {}", floorId);
        floorService.deleteFloorService(floorId);
        return Result.success();
    }
}