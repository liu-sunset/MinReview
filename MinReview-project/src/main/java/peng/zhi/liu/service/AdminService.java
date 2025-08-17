package peng.zhi.liu.service;

import peng.zhi.liu.dto.AddAdminDTO;
import peng.zhi.liu.dto.AdminLoginDTO;
import peng.zhi.liu.dto.AdminPageDTO;
import peng.zhi.liu.result.PageResult;
import peng.zhi.liu.vo.AdminLoginVO;
import peng.zhi.liu.vo.AdminPageVO;

public interface AdminService {
    //员工登录
    public AdminLoginVO empLoginService(AdminLoginDTO employeeLoginDTO);
    //管理员分页查询
    public PageResult<AdminPageVO> adminPageService(AdminPageDTO adminPageDTO);
    // 添加管理员
    public void addAdminService(AddAdminDTO addAdminDTO);
    // 更新管理员名称
    public void updateAdminNameService(Long adminId, String name);
    // 删除管理员
    public void deleteAdminService(Long adminId);
    // 更新管理员状态
    public void updateAdminStatusService(Long adminId, Integer status);
}
