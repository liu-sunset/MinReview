package peng.zhi.liu.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CanteenPageVO {
    private Long id;
    private String name;
    private Long campusId;
    private String campusName;
    private String address;
    private String openTime;
    private String closeTime;
    private Integer status;
    private Integer floorCount;
}