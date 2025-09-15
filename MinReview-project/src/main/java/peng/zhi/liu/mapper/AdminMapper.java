package peng.zhi.liu.mapper;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.logging.log4j.message.LoggerNameAwareMessage;
import peng.zhi.liu.dto.AdminPageDTO;
import peng.zhi.liu.entity.Admin;
import peng.zhi.liu.vo.AdminPageVO;

import java.util.List;

@Mapper
public interface AdminMapper {
    //根据条件查询管理员
    public List<Admin> selectAdmin(Admin admin);
    //管理员分页查询
    public Page<AdminPageVO> adminPageMapper(AdminPageDTO adminPageDTO);
    // 添加管理员
    public void addAdmin(Admin admin);
    // 更新管理员名称
    public void updateAdminMapper(Admin admin);
    // 删除管理员
    public void deleteAdmin(List<Long> ids);
}
