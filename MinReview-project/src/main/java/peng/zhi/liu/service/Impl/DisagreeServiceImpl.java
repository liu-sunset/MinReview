package peng.zhi.liu.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import peng.zhi.liu.dto.DisagreeDTO;
import peng.zhi.liu.entity.Disagree;
import peng.zhi.liu.mapper.DisagreeMapper;
import peng.zhi.liu.mapper.DishMapper;
import peng.zhi.liu.service.DisagreeService;
import peng.zhi.liu.vo.DishDetailVO;

import java.time.LocalDateTime;

// 点踩服务实现类
@Service
public class DisagreeServiceImpl implements DisagreeService {
    @Autowired
    private DisagreeMapper disagreeMapper;
    @Autowired
    private DishMapper dishMapper;
    // 点踩菜品
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void dislikeDishService(DisagreeDTO disagreeDTO) {
        Disagree disagree = new Disagree();
        disagree.setDishId(disagreeDTO.getDishId());
        disagree.setUserId(disagreeDTO.getUserId());
        disagree.setCreateTime(LocalDateTime.now());
        disagree.setUpdateTime(LocalDateTime.now());
        disagreeMapper.addDisagreeMapper(disagree);
        DishDetailVO dishDetail = dishMapper.getDishDetailMapper(disagreeDTO.getDishId());
        Integer dislikeCount = dishDetail.getDislikeCount();
        if(dislikeCount==null||dislikeCount==0){
            dishMapper.updateDishDislikeCountMapper(1,disagreeDTO.getDishId());
        }else{
            dishMapper.updateDishDislikeCountMapper(dislikeCount+1,disagreeDTO.getDishId());
        }
    }

    // 取消点踩菜品
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void cancelDislikeDishService(Long dishId, Long userId) {
        disagreeMapper.deleteDisagreeMapper(dishId, userId);
        DishDetailVO dishDetail = dishMapper.getDishDetailMapper(dishId);
        Integer dislikeCount = dishDetail.getDislikeCount();
        if(dislikeCount==null||dislikeCount==0){
            dishMapper.updateDishDislikeCountMapper(0,dishId);
        }else{
            dishMapper.updateDishDislikeCountMapper(dislikeCount-1,dishId);
        }
    }
}