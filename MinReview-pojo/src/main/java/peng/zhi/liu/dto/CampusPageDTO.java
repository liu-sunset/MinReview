package peng.zhi.liu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CampusPageDTO {
    private Integer page;
    private Integer pageSize;
    private String keyWord;
}
