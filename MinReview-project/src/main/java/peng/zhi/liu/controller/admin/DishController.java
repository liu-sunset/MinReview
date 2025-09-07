package peng.zhi.liu.controller.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import peng.zhi.liu.annotation.JwtInspect;
import peng.zhi.liu.dto.AddDishDTO;
import peng.zhi.liu.dto.DishPageDTO;
import peng.zhi.liu.dto.UpdateDishDTO;
import peng.zhi.liu.result.PageResult;
import peng.zhi.liu.result.Result;
import peng.zhi.liu.service.DishService;
import peng.zhi.liu.vo.DishPageVO;

//菜品管理Controller
@Slf4j
@RestController("adminDishController")
@RequestMapping("/admin/dish")
public class DishController {
    @Autowired
    private DishService dishService;
    @JwtInspect
    @GetMapping("/list")
    public Result dishPageController(DishPageDTO dishPageDTO) {
        log.info("菜品分页查询参数: {}", dishPageDTO);
        PageResult<DishPageVO> pageResult = dishService.dishPageService(dishPageDTO);
        return Result.success(pageResult);
    }

    @JwtInspect
    @PostMapping
    public Result addDishController(@RequestBody AddDishDTO addDishDTO) {
        log.info("添加菜品参数: {}", addDishDTO);
        dishService.addDishService(addDishDTO);
        return Result.success();
    }

    @JwtInspect
    @PutMapping("/{dishId}")
    public Result updateDishController(@PathVariable Long dishId, @RequestBody UpdateDishDTO updateDishDTO) {
        log.info("更新菜品参数: dishId={}, updateDishDTO={}", dishId, updateDishDTO);
        //设置菜品id
        updateDishDTO.setId(dishId);
        //调用service层方法
        dishService.updateDishService(updateDishDTO);
        return Result.success();
    }

    @JwtInspect
    @DeleteMapping("/{dishId}")
    public Result deleteDishController(@PathVariable Long dishId) {
        log.info("删除菜品参数: dishId={}", dishId);
        //调用service层方法
        dishService.deleteDishService(dishId);
        return Result.success();
    }

    @JwtInspect
    @PutMapping("/status/{dishId}")
    public Result updateDishStatusController(@PathVariable Long dishId, @RequestParam Integer status){
        log.info("修改菜品ID是{}的状态为{}",dishId,status);
        dishService.updateDishStatusService(dishId,status);
        return Result.success();
    }
}