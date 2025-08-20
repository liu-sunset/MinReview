package peng.zhi.liu.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peng.zhi.liu.dto.DisagreeDTO;
import peng.zhi.liu.entity.Disagree;
import peng.zhi.liu.mapper.DisagreeMapper;
import peng.zhi.liu.service.DisagreeService;
import java.time.LocalDateTime;

// 点踩服务实现类
@Service
public class DisagreeServiceImpl implements DisagreeService {
    @Autowired
    private DisagreeMapper disagreeMapper;

    // 点踩菜品
    @Override
    public void dislikeDishService(DisagreeDTO disagreeDTO) {
        Disagree disagree = new Disagree();
        disagree.setDishId(disagreeDTO.getDishId());
        disagree.setUserId(disagreeDTO.getUserId());
        disagree.setCreateTime(LocalDateTime.now());
        disagree.setUpdateTime(LocalDateTime.now());
        disagreeMapper.addDisagreeMapper(disagree);
    }

    // 取消点踩菜品
    @Override
    public void cancelDislikeDishService(Long dishId, Long userId) {
        disagreeMapper.deleteDisagreeMapper(dishId, userId);
    }
}