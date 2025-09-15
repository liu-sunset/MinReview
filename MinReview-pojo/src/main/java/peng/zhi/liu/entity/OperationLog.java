package peng.zhi.liu.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OperationLog {
    private Long id;
    private Long userId;
    private Long adminId;
    private String operationType;
    private String requestUrl;
    private String requestMethod;
    private String requestParams;
    private String responseData;
    private Integer status;
    private String errorMsg;
    private Long executeTime;
    private LocalDateTime createTime;
}
