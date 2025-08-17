package peng.zhi.liu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CanteenPageDTO {
    private Integer page;
    private Integer pageSize;
    private String keyWord;
    private Long campusId;
}