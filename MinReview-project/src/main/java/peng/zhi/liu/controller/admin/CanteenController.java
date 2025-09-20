package peng.zhi.liu.controller.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import peng.zhi.liu.annotation.JwtInspect;
import peng.zhi.liu.annotation.OperationLog;
import peng.zhi.liu.dto.AddCanteenDTO;
import peng.zhi.liu.dto.CanteenPageDTO;
import peng.zhi.liu.enums.OperationTypeEnum;
import peng.zhi.liu.result.PageResult;
import peng.zhi.liu.result.Result;
import peng.zhi.liu.service.CanteenService;
import peng.zhi.liu.vo.CanteenDetailVO;
import peng.zhi.liu.vo.CanteenPageVO;
import peng.zhi.liu.vo.CanteenVO;

import java.util.List;

@Slf4j
@RestController("adminCanteenController")
@RequestMapping("/admin/canteen")
public class CanteenController {
    @Autowired
    private CanteenService canteenService;
    @JwtInspect
    @PostMapping
    @OperationLog(OperationTypeEnum.insert)
    public Result addCanteenController(@RequestBody AddCanteenDTO addCanteenDTO) {
        log.info("添加餐厅: {}", addCanteenDTO);
        canteenService.addCanteenService(addCanteenDTO);
        return Result.success();
    }
    @JwtInspect
    @GetMapping("/list")
    @OperationLog(OperationTypeEnum.select)
    public Result canteenPageController(CanteenPageDTO canteenPageDTO) {
        log.info("餐厅分页查询参数: {}", canteenPageDTO);
        PageResult<CanteenPageVO> pageResult = canteenService.canteenPageService(canteenPageDTO);
        return Result.success(pageResult);
    }
    @JwtInspect
    @PutMapping("/{canteenId}")
    @OperationLog(OperationTypeEnum.update)
    public Result modifyCanteenController(@PathVariable Long canteenId, @RequestBody AddCanteenDTO addCanteenDTO) {
        log.info("更新餐厅: id={}, 数据={}", canteenId, addCanteenDTO);
        canteenService.modifyCanteenService(canteenId, addCanteenDTO);
        return Result.success();
    }
    @JwtInspect
    @DeleteMapping("/{canteenId}")
    @OperationLog(OperationTypeEnum.delete)
    public Result deleteCanteenController(@PathVariable Long canteenId) {
        log.info("删除餐厅: id={}", canteenId);
        canteenService.deleteCanteenService(canteenId);
        return Result.success();
    }
    @JwtInspect
    @PutMapping("/status/{canteenId}")
    @OperationLog(OperationTypeEnum.update)
    public Result updateCanteenStatusController(@PathVariable Long canteenId,@RequestParam Integer status){
        log.info("更新餐厅状态,餐厅ID:{},餐厅状态:{}",canteenId,status);
        canteenService.updateCanteenStatusService(canteenId,status);
        return Result.success();
    }

    @JwtInspect
    @GetMapping("/{canteenId}")
    @OperationLog(OperationTypeEnum.select)
    public Result getCanteenDetailController(@PathVariable Long canteenId){
        log.info("餐厅的查询回显，id:{}",canteenId);
        CanteenDetailVO canteenDetailVO = canteenService.getCanteenDetailService(canteenId);
        return Result.success(canteenDetailVO);
    }
}
