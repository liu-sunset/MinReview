package peng.zhi.liu.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CampusPageVO {
    private Long id;
    private String name;
    private String address;
    private Integer status;
}
