package peng.zhi.liu.mapper;

import org.apache.ibatis.annotations.Mapper;
import peng.zhi.liu.entity.Agree;

// 点赞Mapper接口
@Mapper
public interface AgreeMapper {
    // 添加点赞记录
    void addAgreeMapper(Agree agree);
    // 删除点赞记录
    void deleteAgreeMapper(Long dishId, Long userId);
}