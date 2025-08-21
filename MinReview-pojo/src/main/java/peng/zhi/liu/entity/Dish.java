package peng.zhi.liu.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

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
    private Long canteenId;
    private Long campusId;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}