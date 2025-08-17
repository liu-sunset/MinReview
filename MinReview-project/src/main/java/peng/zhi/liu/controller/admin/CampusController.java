package peng.zhi.liu.controller.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import peng.zhi.liu.dto.AddCampusDTO;
import peng.zhi.liu.dto.CampusPageDTO;
import peng.zhi.liu.result.PageResult;
import peng.zhi.liu.result.Result;
import peng.zhi.liu.service.CampusService;
import peng.zhi.liu.vo.CampusPageVO;

@Slf4j
@RestController
@RequestMapping("/admin/campus")
public class CampusController {
    @Autowired
    private CampusService campusService;
    // todo:测试
    @GetMapping("/list")
    public Result campusPageController(CampusPageDTO compusPageDTO){
        log.info("校区分页查询参数:{}",compusPageDTO);
        PageResult<CampusPageVO> campusPageVOPageResult = campusService.campusPageService(compusPageDTO);
        return Result.success(campusPageVOPageResult);
    }

    // todo:test
    @PostMapping
    public Result addCampusController(@RequestBody AddCampusDTO addCampusDTO){
        log.info("添加校区:{}",addCampusDTO);
        campusService.addCampusService(addCampusDTO);
        return Result.success();
    }

    //todo:test
    @PutMapping("/{campusId}")
    public Result modifyCampusController(@PathVariable Long campusId,@RequestBody AddCampusDTO addCampusDTO){
        log.info("修改ID是{}的校区信息为{}",campusId,addCampusDTO);
        campusService.modifyCampusService(campusId,addCampusDTO);
        return Result.success();
    }

    //todo:test
    @DeleteMapping("/{campusId}")
    public void deleteCampusController(@PathVariable Long campusId){
        log.info("删除ID是{}的校区",campusId);
        campusService.deleteCampusService(campusId);
    }

    //todo:test
    @PostMapping("/status/{campusId}")
    public Result updateCampusStatusController(@PathVariable Long campusId,@RequestParam Integer status){
        log.info("修改ID是{}的校区状态为{}",campusId,status);
        campusService.updateCampusStatusService(campusId,status);
        return Result.success();
    }
}
