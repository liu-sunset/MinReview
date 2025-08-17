package peng.zhi.liu.mapper;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import peng.zhi.liu.dto.FloorPageDTO;
import peng.zhi.liu.entity.Floor;
import peng.zhi.liu.vo.FloorPageVO;

//楼层Mapper接口
@Mapper
public interface FloorMapper {
    //添加楼层
    void addFloorMapper(Floor floor);
    //楼层分页查询
    Page<FloorPageVO> floorPageMapper(FloorPageDTO floorPageDTO);
    //删除楼层
    void deleteFloorMapper(Long floorId);
}