package peng.zhi.liu.mapper;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import peng.zhi.liu.dto.DishPageDTO;
import peng.zhi.liu.entity.Dish;
import peng.zhi.liu.vo.DishDetailVO;
import peng.zhi.liu.vo.DishPageVO;

//菜品Mapper接口
@Mapper
public interface DishMapper {
    //菜品分页查询
    Page<DishPageVO> dishPageMapper(DishPageDTO dishPageDTO);
    //添加菜品
    void addDishMapper(Dish dish);
    //根据id查询菜品
    Dish getDishByIdMapper(Long id);
    //更新菜品
    void updateDishMapper(Dish dish);
    //删除菜品
    void deleteDishMapper(Long id);
    //获取菜品详情
    DishDetailVO getDishDetailMapper(Long dishId);
}