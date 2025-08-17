package peng.zhi.liu.service;

import peng.zhi.liu.dto.AddFloorDTO;
import peng.zhi.liu.dto.FloorPageDTO;
import peng.zhi.liu.result.PageResult;
import peng.zhi.liu.vo.FloorPageVO;

//楼层服务接口
public interface FloorService {
    //添加楼层
    void addFloorService(AddFloorDTO addFloorDTO);
    //楼层分页查询
    PageResult<FloorPageVO> floorPageService(FloorPageDTO floorPageDTO);
    //删除楼层
    void deleteFloorService(Long floorId);
}