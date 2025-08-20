package peng.zhi.liu.service;

import peng.zhi.liu.dto.AgreeDTO;

public interface AgreeService {
    // 点赞菜品
    public void likeDishService(AgreeDTO agreeDTO);
    // 取消点赞菜品
    public void cancelLikeDishService(Long dishId, Long userId);
}