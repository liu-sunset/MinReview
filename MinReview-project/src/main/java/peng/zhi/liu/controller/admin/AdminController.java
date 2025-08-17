package peng.zhi.liu.controller.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import peng.zhi.liu.dto.AdminLoginDTO;
import peng.zhi.liu.dto.AdminPageDTO;
import peng.zhi.liu.dto.AddAdminDTO;
import peng.zhi.liu.result.PageResult;
import peng.zhi.liu.result.Result;
import peng.zhi.liu.service.AdminService;
import peng.zhi.liu.vo.AdminLoginVO;
import peng.zhi.liu.vo.AdminPageVO;

@Slf4j
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;
    //todo:test
    @GetMapping("/admin/list")
    public Result adminPageController(AdminPageDTO adminPageDTO){
        log.info("管理员分页查询参数:{}",adminPageDTO);
        PageResult<AdminPageVO> pageResult = adminService.adminPageService(adminPageDTO);
        return Result.success(pageResult);
    }

    
    @PostMapping("/login")
    public Result adminLoginController(@RequestBody AdminLoginDTO adminLoginDTO){
        log.info("管理员登录信息:{}",adminLoginDTO);
        AdminLoginVO adminLoginVO = adminService.empLoginService(adminLoginDTO);
        return Result.success(adminLoginVO);
    }

    // todo:test
    @PostMapping("/admin")
    public Result addAdminController(@RequestBody AddAdminDTO addAdminDTO){
        log.info("添加管理员信息:{}",addAdminDTO);
        adminService.addAdminService(addAdminDTO);
        return Result.success();
    }

    // todo:test
    @PutMapping("/admin/{adminId}")
    public Result updateAdminNameController(@PathVariable Long adminId, @RequestBody String name){
        log.info("更新管理员名称,管理员id:{},新名称:{}",adminId,name);
        adminService.updateAdminNameService(adminId, name);
        return Result.success();
    }

    // todo:test
    @DeleteMapping("/admin/{adminId}")
    public Result deleteAdminController(@PathVariable Long adminId){
        log.info("删除管理员,管理员id:{}",adminId);
        adminService.deleteAdminService(adminId);
        return Result.success();
    }

    // todo:test
    @PostMapping("/admin/status/{adminId}")
    public Result updateAdminStatusController(@PathVariable Long adminId, @RequestBody Integer status){
        log.info("更新管理员状态,管理员id:{},新状态:{}",adminId,status);
        adminService.updateAdminStatusService(adminId, status);
        return Result.success();
    }
}
