package peng.zhi.liu.controller.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import peng.zhi.liu.dto.AddCanteenDTO;
import peng.zhi.liu.dto.CanteenPageDTO;
import peng.zhi.liu.result.PageResult;
import peng.zhi.liu.result.Result;
import peng.zhi.liu.service.CanteenService;
import peng.zhi.liu.vo.CanteenPageVO;

@Slf4j
@RestController("adminCanteenController")
@RequestMapping("/admin/canteen")
public class CanteenController {
    @Autowired
    private CanteenService canteenService;

    @PostMapping
    public Result addCanteenController(@RequestBody AddCanteenDTO addCanteenDTO) {
        log.info("添加餐厅: {}", addCanteenDTO);
        canteenService.addCanteenService(addCanteenDTO);
        return Result.success();
    }

    @GetMapping("/list")
    public Result canteenPageController(CanteenPageDTO canteenPageDTO) {
        log.info("餐厅分页查询参数: {}", canteenPageDTO);
        PageResult<CanteenPageVO> pageResult = canteenService.canteenPageService(canteenPageDTO);
        return Result.success(pageResult);
    }

    @PutMapping("/{canteenId}")
    public Result modifyCanteenController(@PathVariable Long canteenId, @RequestBody AddCanteenDTO addCanteenDTO) {
        log.info("更新餐厅: id={}, 数据={}", canteenId, addCanteenDTO);
        canteenService.modifyCanteenService(canteenId, addCanteenDTO);
        return Result.success();
    }

    @DeleteMapping("/{canteenId}")
    public Result deleteCanteenController(@PathVariable Long canteenId) {
        log.info("删除餐厅: id={}", canteenId);
        canteenService.deleteCanteenService(canteenId);
        return Result.success();
    }

    @PutMapping("/status/{canteenId}")
    public Result updateCanteenStatusController(@PathVariable Long canteenId,Integer status){
        log.info("更新餐厅状态,餐厅ID:{},餐厅状态:{}",canteenId,status);
        canteenService.updateCanteenStatusService(canteenId,status);
        return Result.success();
    }
}
