package peng.zhi.liu.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import peng.zhi.liu.dto.AgreeDTO;
import peng.zhi.liu.entity.Agree;
import peng.zhi.liu.mapper.AgreeMapper;
import peng.zhi.liu.mapper.DishMapper;
import peng.zhi.liu.service.AgreeService;
import peng.zhi.liu.service.DishService;
import peng.zhi.liu.vo.DishDetailVO;

import java.time.LocalDateTime;

// 点赞服务实现类
@Service
public class AgreeServiceImpl implements AgreeService {
    @Autowired
    private AgreeMapper agreeMapper;
    @Autowired
    private DishMapper dishMapper;

    // 点赞菜品
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void likeDishService(AgreeDTO agreeDTO) {
        Agree agree = new Agree();
        agree.setDishId(agreeDTO.getDishId());
        agree.setUserId(agreeDTO.getUserId());
        agree.setCreateTime(LocalDateTime.now());
        agree.setUpdateTime(LocalDateTime.now());
        agreeMapper.addAgreeMapper(agree);
        DishDetailVO dishDetail = dishMapper.getDishDetailMapper(agreeDTO.getDishId());
        Integer likeCount = dishDetail.getLikeCount();
        if (likeCount==null||likeCount==0){
            dishMapper.updateDishLikeCountMapper(1,agreeDTO.getDishId());
        }else{
            dishMapper.updateDishLikeCountMapper(likeCount+1,agreeDTO.getDishId());
        }
    }

    // 取消点赞菜品
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void cancelLikeDishService(Long dishId, Long userId) {
        agreeMapper.deleteAgreeMapper(dishId, userId);
        DishDetailVO dishDetail = dishMapper.getDishDetailMapper(dishId);
        Integer likeCount = dishDetail.getLikeCount();
        if (likeCount==null||likeCount==0){
            dishMapper.updateDishLikeCountMapper(0,dishId);
        }else{
            dishMapper.updateDishLikeCountMapper(likeCount-1,dishId);
        }
    }
}