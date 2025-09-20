package peng.zhi.liu.controller.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import peng.zhi.liu.annotation.JwtInspect;
import peng.zhi.liu.annotation.OperationLog;
import peng.zhi.liu.dto.AddCampusDTO;
import peng.zhi.liu.dto.CampusPageDTO;
import peng.zhi.liu.enums.OperationTypeEnum;
import peng.zhi.liu.result.PageResult;
import peng.zhi.liu.result.Result;
import peng.zhi.liu.service.CampusService;
import peng.zhi.liu.vo.CampusDetailVO;
import peng.zhi.liu.vo.CampusPageVO;


@Slf4j
@RestController("adminCampusController")
@RequestMapping("/admin/campus")
public class CampusController {
    @Autowired
    private CampusService campusService;
    @JwtInspect
    @GetMapping("/list")
    @OperationLog(OperationTypeEnum.select)
    public Result campusPageController(CampusPageDTO compusPageDTO){
        log.info("校区分页查询参数:{}",compusPageDTO);
        PageResult<CampusPageVO> campusPageVOPageResult = campusService.campusPageService(compusPageDTO);
        return Result.success(campusPageVOPageResult);
    }

    @JwtInspect
    @PostMapping
    @OperationLog(OperationTypeEnum.insert)
    public Result addCampusController(@RequestBody AddCampusDTO addCampusDTO){
        log.info("添加校区:{}",addCampusDTO);
        campusService.addCampusService(addCampusDTO);
        return Result.success();
    }

    @JwtInspect
    @PutMapping("/{campusId}")
    @OperationLog(OperationTypeEnum.update)
    public Result modifyCampusController(@PathVariable Long campusId,@RequestBody AddCampusDTO addCampusDTO){
        log.info("修改ID是{}的校区信息为{}",campusId,addCampusDTO);
        campusService.modifyCampusService(campusId,addCampusDTO);
        return Result.success();
    }

    @JwtInspect
    @DeleteMapping("/{campusId}")
    @OperationLog(OperationTypeEnum.delete)
    public Result deleteCampusController(@PathVariable Long campusId){
        log.info("删除ID是{}的校区",campusId);
        campusService.deleteCampusService(campusId);
        return Result.success();
    }

    @JwtInspect
    @PutMapping("/status/{campusId}")
    @OperationLog(OperationTypeEnum.update)
    public Result updateCampusStatusController(@PathVariable Long campusId,@RequestParam Integer status){
        log.info("修改ID是{}的校区状态为{}",campusId,status);
        campusService.updateCampusStatusService(campusId,status);
        return Result.success();
    }

    @JwtInspect
    @GetMapping("/{id}")
    @OperationLog(OperationTypeEnum.select)
    public Result selectCampusDetailByIdController(@PathVariable Long id){
        log.info("查询回显，id:{}",id);
        CampusDetailVO campusDetailVO = campusService.selectCampusDetailByIdService(id);
        return  Result.success(campusDetailVO);
    }
}
