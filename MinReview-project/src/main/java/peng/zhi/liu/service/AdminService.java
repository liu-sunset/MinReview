package peng.zhi.liu.service;

import peng.zhi.liu.dto.AdminLoginDTO;
import peng.zhi.liu.vo.AdminLoginVO;

public interface AdminService {
    //员工登录
    public AdminLoginVO empLoginService(AdminLoginDTO employeeLoginDTO);
}
