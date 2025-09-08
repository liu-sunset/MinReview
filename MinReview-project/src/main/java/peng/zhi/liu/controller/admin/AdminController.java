package peng.zhi.liu.controller.admin;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import peng.zhi.liu.annotation.JwtInspect;
import peng.zhi.liu.dto.*;
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

    @JwtInspect
    @GetMapping("/admin/list")
    public Result adminPageController(AdminPageDTO adminPageDTO){
        log.info("管理员分页查询参数:{}",adminPageDTO);
        PageResult<AdminPageVO> pageResult = adminService.adminPageService(adminPageDTO);
        return Result.success(pageResult);
    }


    @PostMapping("/login")
    public Result adminLoginController(@RequestBody AdminLoginDTO adminLoginDTO,HttpServletRequest httpServletRequest){
        log.info("管理员登录信息:{}",adminLoginDTO);
        AdminLoginVO adminLoginVO = adminService.empLoginService(adminLoginDTO,httpServletRequest);
        return Result.success(adminLoginVO);
    }

    @JwtInspect
    @PostMapping("/admin")
    public Result addAdminController(@RequestBody AddAdminDTO addAdminDTO){
        log.info("添加管理员信息:{}",addAdminDTO);
        adminService.addAdminService(addAdminDTO);
        return Result.success();
    }

    @JwtInspect
    @PutMapping("/admin/name/{adminId}")
    public Result updateAdminNameController(@PathVariable Long adminId, @RequestBody ModifyAdminNameDTO modifyAdminNameDTO){
        log.info("更新管理员名称,管理员id:{},新名称:{}",adminId,modifyAdminNameDTO.getName());
        adminService.updateAdminNameService(adminId, modifyAdminNameDTO.getName());
        return Result.success();
    }

    @JwtInspect
    @DeleteMapping("/admin/{adminId}")
    public Result deleteAdminController(@PathVariable Long adminId){
        log.info("删除管理员,管理员id:{}",adminId);
        adminService.deleteAdminService(adminId);
        return Result.success();
    }

    @JwtInspect
    @PutMapping("/admin/status/{adminId}/{status}")
    public Result updateAdminStatusController(@PathVariable Long adminId, @PathVariable Integer status){
        log.info("更新管理员状态,管理员id:{},新状态:{}",adminId,status);
        adminService.updateAdminStatusService(adminId, status);
        return Result.success();
    }

    @JwtInspect
    @PutMapping("/admin/password/{adminId}")
    public Result updateAdminPasswordController(@PathVariable Long adminId, @RequestBody ModifyAdminPasswordDTO modifyAdminPasswordDTO){
        log.info("修改管理员密码，管理员ID:{},修密码：{}，新密码:{}",adminId,modifyAdminPasswordDTO.getOldPassword(),modifyAdminPasswordDTO.getNewPassword());
        adminService.updateAdminPasswordController(adminId,modifyAdminPasswordDTO);
        return Result.success();
    }

    @JwtInspect
    @PostMapping("/logout")
    public Result userLogoutController(HttpServletRequest httpServletRequest){
        log.info("管理员登出");
        adminService.adminLoginoutService(httpServletRequest);
        return Result.success();
    }
}
