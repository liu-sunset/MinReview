package peng.zhi.liu.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 楼层分页查询VO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FloorPageVO {
    private Long id;
    private String name;
    private Long canteenId;
    private String canteenName;
    private Integer floorNumber;
    private String description;
    private Integer dishCount;
}