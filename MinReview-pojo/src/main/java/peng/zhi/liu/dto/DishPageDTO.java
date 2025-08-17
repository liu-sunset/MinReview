package peng.zhi.liu.dto;

import lombok.Data;

//菜品分页查询DTO
@Data
public class DishPageDTO {
    private Integer page;
    private Integer pageSize;
    private String keyword;
    private Long floorId;
    private Long categoryId;
}