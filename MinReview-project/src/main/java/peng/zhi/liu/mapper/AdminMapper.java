package peng.zhi.liu.mapper;

import org.apache.ibatis.annotations.Mapper;
import peng.zhi.liu.entity.Admin;

import java.util.List;

@Mapper
public interface AdminMapper {
    //根据条件查询管理员
    public List<Admin> selectAdmin(Admin admin);
}
