package peng.zhi.liu.dto;

import lombok.Data;
import java.math.BigDecimal;

//更新菜品DTO类
@Data
public class UpdateDishDTO {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String imageUrl;
    private Long categoryId;
    private Long floorId;
    private Long canteenId;
}