package peng.zhi.liu.entity;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

//菜品实体类
@Data
public class Dish {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String imageUrl;
    private Long categoryId;
    private Long floorId;
    private Integer status;
    private Integer likeCount;
    private Integer dislikeCount;
    private Integer commentCount;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}