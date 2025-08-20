package peng.zhi.liu.service;

import peng.zhi.liu.dto.AddCanteenDTO;
import peng.zhi.liu.dto.CanteenPageDTO;
import peng.zhi.liu.result.PageResult;
import peng.zhi.liu.vo.CanteenPageVO;
import peng.zhi.liu.vo.CanteenVO;
import java.util.List;

public interface CanteenService {
    //餐厅分页查询
    PageResult<CanteenPageVO> canteenPageService(CanteenPageDTO canteenPageDTO);
    //添加餐厅
    void addCanteenService(AddCanteenDTO addCanteenDTO);
    //更新餐厅
    void modifyCanteenService(Long id, AddCanteenDTO addCanteenDTO);
    //删除餐厅
    void deleteCanteenService(Long id);
    //根据校区ID获取食堂列表
    List<CanteenVO> getCanteenListByCampusIdService(Long campusId);
    //更新餐厅状态
    public void updateCanteenStatusService(Long id,Integer status);
}
