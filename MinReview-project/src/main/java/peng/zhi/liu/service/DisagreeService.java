package peng.zhi.liu.service;

import peng.zhi.liu.dto.DisagreeDTO;

public interface DisagreeService {
    // 点踩菜品
    public void dislikeDishService(DisagreeDTO disagreeDTO);
    // 取消点踩菜品
    public void cancelDislikeDishService(Long dishId, Long userId);
}