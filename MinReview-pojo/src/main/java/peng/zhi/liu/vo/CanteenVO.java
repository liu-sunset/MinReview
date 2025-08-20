package peng.zhi.liu.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 食堂VO类
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CanteenVO {
    private Long id;
    private String name;
    private Long campusId;
    private String campusName;
    private String address;
    private Integer status;
    private Integer floorCount;
}