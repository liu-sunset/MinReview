package peng.zhi.liu.service;

import peng.zhi.liu.dto.AddDishDTO;
import peng.zhi.liu.dto.DishPageDTO;
import peng.zhi.liu.dto.UpdateDishDTO;
import peng.zhi.liu.result.PageResult;
import peng.zhi.liu.vo.DishPageVO;

public interface DishService {
    //菜品分页查询
    public PageResult<DishPageVO> dishPageService(DishPageDTO dishPageDTO);
    //添加菜品
    public void addDishService(AddDishDTO addDishDTO);
    //更新菜品
    public void updateDishService(UpdateDishDTO updateDishDTO);
    //删除菜品
    public void deleteDishService(Long dishId);
    //更新菜品状态
    public void updateDishStatusService(Long id,Integer status);
}
