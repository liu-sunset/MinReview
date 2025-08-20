package peng.zhi.liu.mapper;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import peng.zhi.liu.dto.CampusPageDTO;
import peng.zhi.liu.entity.Campus;
import peng.zhi.liu.service.CampusService;
import peng.zhi.liu.vo.CampusPageVO;

import java.util.List;

@Mapper
public interface CampusMapper {
    //校区的分页查询
    public Page<CampusPageVO> campusPageMapper(CampusPageDTO compusPageDTO);
    //添加校区
    public void addCampusMapper(Campus campus);
    //修改校区
    public void modifyCampusMapper(Campus campus);
    //删除校区
    public void deleteCampusMapper(Long id);
    
    //获取校区列表
    public List<CampusPageVO> getCampusListMapper();
}
