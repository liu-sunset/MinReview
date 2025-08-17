package peng.zhi.liu.vo;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

//菜品分页查询VO
@Data
public class DishPageVO {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String imageUrl;
    private Long categoryId;
    private String categoryName;
    private Long floorId;
    private String floorName;
    private Integer likeCount;
    private Integer dislikeCount;
    private Integer commentCount;
    private Integer status;
    private LocalDateTime updateTime;
}