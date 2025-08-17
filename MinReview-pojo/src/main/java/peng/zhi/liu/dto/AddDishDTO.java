package peng.zhi.liu.dto;

import lombok.Data;
import java.math.BigDecimal;

//添加菜品DTO
@Data
public class AddDishDTO {
    private String name;
    private String description;
    private BigDecimal price;
    private String imageUrl;
    private Long categoryId;
    private Long floorId;
}