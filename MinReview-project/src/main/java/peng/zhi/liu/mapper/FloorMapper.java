package peng.zhi.liu.mapper;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import peng.zhi.liu.dto.FloorPageDTO;
import peng.zhi.liu.entity.Floor;
import peng.zhi.liu.vo.FloorPageVO;
import peng.zhi.liu.vo.FloorVO;
import java.util.List;

//楼层Mapper接口
@Mapper
public interface FloorMapper {
    //添加楼层
    void addFloorMapper(Floor floor);
    //楼层分页查询
    Page<FloorPageVO> floorPageMapper(FloorPageDTO floorPageDTO);
    //删除楼层
    void deleteFloorMapper(Long floorId);
    //根据食堂ID获取楼层列表
    List<FloorVO> getFloorListByCanteenIdMapper(Long canteenId);
    //获取根据餐厅ID和楼层号获取楼层
    public Floor selectFloorMapper(Long canteenId,Integer floorNumber);
}