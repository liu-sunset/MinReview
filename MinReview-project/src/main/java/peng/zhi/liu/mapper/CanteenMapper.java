package peng.zhi.liu.mapper;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import peng.zhi.liu.dto.CanteenPageDTO;
import peng.zhi.liu.entity.canteen;
import peng.zhi.liu.vo.CanteenPageVO;
import peng.zhi.liu.vo.CanteenVO;
import java.util.List;

@Mapper
public interface CanteenMapper {
    //餐厅分页查询
    Page<CanteenPageVO> canteenPageMapper(CanteenPageDTO canteenPageDTO);
    //添加餐厅
    void addCanteenMapper(canteen canteen);
    //更新餐厅
    void modifyCanteenMapper(canteen canteen);
    //删除餐厅
    void deleteCanteenMapper(Long id);
    //根据校区ID获取食堂列表
    List<CanteenVO> getCanteenListByCampusIdMapper(Long campusId);
}
