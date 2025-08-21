package peng.zhi.liu.controller.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import peng.zhi.liu.dto.DishPageDTO;
import peng.zhi.liu.result.PageResult;
import peng.zhi.liu.result.Result;
import peng.zhi.liu.service.DishService;
import peng.zhi.liu.vo.DishDetailVO;
import peng.zhi.liu.vo.DishPageVO;

@Slf4j
@RestController("userDishController")
@RequestMapping("/user")
public class DishController {
    @Autowired
    private DishService dishService;

    @GetMapping("/dish/list")
    public Result dishPageController(DishPageDTO dishPageDTO){
        log.info("用户调用菜品分页查询参数:{}",dishPageDTO);
        PageResult<DishPageVO> pageResult = dishService.dishPageService(dishPageDTO);
        return Result.success(pageResult);
    }

    @GetMapping("/dish/detail/{dishId}")
    public Result getDishDetailController(@PathVariable Long dishId){
        log.info("用户调用获取菜品详情接口,菜品id: {}", dishId);
        DishDetailVO dishDetailVO = dishService.getDishDetailService(dishId);
        return Result.success(dishDetailVO);
    }
}
