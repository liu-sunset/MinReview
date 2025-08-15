package peng.zhi.liu.controller.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import peng.zhi.liu.dto.AdminLoginDTO;
import peng.zhi.liu.result.Result;
import peng.zhi.liu.service.AdminService;
import peng.zhi.liu.vo.AdminLoginVO;

@Slf4j
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;
    @PostMapping("/login")
    public Result adminLoginController(@RequestBody AdminLoginDTO adminLoginDTO){
        log.info("管理员登录信息:{}",adminLoginDTO);
        AdminLoginVO adminLoginVO = adminService.empLoginService(adminLoginDTO);
        return Result.success(adminLoginVO);
    }
}
