package peng.zhi.liu.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peng.zhi.liu.constant.MessageConstant;
import peng.zhi.liu.dto.AddFloorDTO;
import peng.zhi.liu.dto.FloorPageDTO;
import peng.zhi.liu.entity.Floor;
import peng.zhi.liu.exception.OrdinaryException;
import peng.zhi.liu.mapper.FloorMapper;
import peng.zhi.liu.result.PageResult;
import peng.zhi.liu.service.FloorService;
import peng.zhi.liu.vo.FloorPageVO;
import peng.zhi.liu.vo.FloorVO;

import java.time.LocalDateTime;
import java.util.List;

//楼层服务实现类
@Service
public class FloorServiceImpl implements FloorService {
    @Autowired
    private FloorMapper floorMapper;

    //添加楼层
    @Override
    public void addFloorService(AddFloorDTO addFloorDTO) {
        //判断是否已经存在此楼层
        Floor floor1 = floorMapper.selectFloorMapper(addFloorDTO.getCanteenId(), addFloorDTO.getFloorNumber());
        if (floor1!=null){
            throw new OrdinaryException(MessageConstant.FLOOR_EXIST);
        }
        Floor floor = new Floor();
        BeanUtils.copyProperties(addFloorDTO, floor);
        floor.setCreateTime(LocalDateTime.now());
        floor.setUpdateTime(LocalDateTime.now());
        floorMapper.addFloorMapper(floor);
    }

    //楼层分页查询
    @Override
    public PageResult<FloorPageVO> floorPageService(FloorPageDTO floorPageDTO) {
        PageHelper.startPage(floorPageDTO.getPage(), floorPageDTO.getPageSize());
        Page<FloorPageVO> page = floorMapper.floorPageMapper(floorPageDTO);
        return new PageResult<>(page.getTotal(), page.getResult());
    }

    //删除楼层
    @Override
    public void deleteFloorService(Long floorId) {
        floorMapper.deleteFloorMapper(floorId);
    }
    
    //根据食堂ID获取楼层列表
    @Override
    public List<FloorVO> getFloorListByCanteenIdService(Long canteenId) {
        return floorMapper.getFloorListByCanteenIdMapper(canteenId);
    }
}