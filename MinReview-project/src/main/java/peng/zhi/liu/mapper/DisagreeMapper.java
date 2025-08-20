package peng.zhi.liu.mapper;

import org.apache.ibatis.annotations.Mapper;
import peng.zhi.liu.entity.Disagree;

// 点踩Mapper接口
@Mapper
public interface DisagreeMapper {
    // 添加点踩记录
    void addDisagreeMapper(Disagree disagree);
    // 删除点踩记录
    void deleteDisagreeMapper(Long dishId, Long userId);
}