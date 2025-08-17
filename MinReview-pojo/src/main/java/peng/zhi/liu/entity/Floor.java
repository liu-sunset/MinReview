package peng.zhi.liu.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 楼层实体类
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Floor {
    private Long id;
    private String name;
    private Long canteenId;
    private Integer floorNumber;
    private String description;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}