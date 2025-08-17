package peng.zhi.liu.dto;

import lombok.Data;

//添加楼层DTO
@Data
public class AddFloorDTO {
    private String name;
    private Long canteenId;
    private Integer floorNumber;
    private String description;
}