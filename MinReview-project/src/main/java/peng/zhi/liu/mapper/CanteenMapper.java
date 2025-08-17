package peng.zhi.liu.mapper;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import peng.zhi.liu.dto.CanteenPageDTO;
import peng.zhi.liu.entity.canteen;
import peng.zhi.liu.vo.CanteenPageVO;

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
}
