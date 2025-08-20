package peng.zhi.liu.dto;

import lombok.Data;

// 点踩数据传输对象
@Data
public class DisagreeDTO {
    // 菜品id
    private Long dishId;
    // 用户id
    private Long userId;
}