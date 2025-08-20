package peng.zhi.liu.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peng.zhi.liu.dto.AgreeDTO;
import peng.zhi.liu.entity.Agree;
import peng.zhi.liu.mapper.AgreeMapper;
import peng.zhi.liu.service.AgreeService;
import java.time.LocalDateTime;

// 点赞服务实现类
@Service
public class AgreeServiceImpl implements AgreeService {
    @Autowired
    private AgreeMapper agreeMapper;

    // 点赞菜品
    @Override
    public void likeDishService(AgreeDTO agreeDTO) {
        Agree agree = new Agree();
        agree.setDishId(agreeDTO.getDishId());
        agree.setUserId(agreeDTO.getUserId());
        agree.setCreateTime(LocalDateTime.now());
        agree.setUpdateTime(LocalDateTime.now());
        agreeMapper.addAgreeMapper(agree);
    }

    // 取消点赞菜品
    @Override
    public void cancelLikeDishService(Long dishId, Long userId) {
        agreeMapper.deleteAgreeMapper(dishId, userId);
    }
}