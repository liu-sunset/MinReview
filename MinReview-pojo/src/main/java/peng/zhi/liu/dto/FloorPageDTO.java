package peng.zhi.liu.dto;

import lombok.Data;

/**
 * 楼层分页查询DTO
 */
@Data
public class FloorPageDTO {
    private Integer page;
    private Integer pageSize;
    private Long canteenId;
    private String keyWord;
}