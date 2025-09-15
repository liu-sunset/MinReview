package peng.zhi.liu.mapper;

import org.apache.ibatis.annotations.Mapper;
import peng.zhi.liu.entity.OperationLog;

@Mapper
public interface OperationLogMapper {
    //新增日志
    public void addOperationLogMapper(OperationLog operationLog);
}
