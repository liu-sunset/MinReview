package peng.zhi.liu.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peng.zhi.liu.dto.AddDishDTO;
import peng.zhi.liu.dto.DishPageDTO;
import peng.zhi.liu.dto.UpdateDishDTO;
import peng.zhi.liu.entity.Dish;
import peng.zhi.liu.mapper.DishMapper;
import peng.zhi.liu.result.PageResult;
import peng.zhi.liu.service.DishService;
import peng.zhi.liu.vo.DishPageVO;
import java.time.LocalDateTime;

//菜品服务实现类
@Service
public class DishServiceImpl implements DishService {
    @Autowired
    private DishMapper dishMapper;

    //菜品分页查询
    @Override
    public PageResult<DishPageVO> dishPageService(DishPageDTO dishPageDTO) {
        PageHelper.startPage(dishPageDTO.getPage(), dishPageDTO.getPageSize());
        Page<DishPageVO> page = dishMapper.dishPageMapper(dishPageDTO);
        return new PageResult<>(page.getTotal(), page.getResult());
    }
    //添加菜品
    @Override
    public void addDishService(AddDishDTO addDishDTO) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(addDishDTO, dish);
        dish.setCreateTime(LocalDateTime.now());
        dish.setUpdateTime(LocalDateTime.now());
        dishMapper.addDishMapper(dish);
    }
    
    //更新菜品
    @Override
    public void updateDishService(UpdateDishDTO updateDishDTO) {
        //根据id查询菜品
        Dish dish = dishMapper.getDishByIdMapper(updateDishDTO.getId());
        //复制属性
        BeanUtils.copyProperties(updateDishDTO, dish);
        //更新时间
        dish.setUpdateTime(LocalDateTime.now());
        //更新菜品
        dishMapper.updateDishMapper(dish);
    }
    //删除菜品
    @Override
    public void deleteDishService(Long dishId) {
        dishMapper.deleteDishMapper(dishId);
    }

    @Override
    public void updateDishStatusService(Long id, Integer status) {
        Dish dish = new Dish();
        dish.setId(id);
        dish.setStatus(status);
        dishMapper.updateDishMapper(dish);
    }
}