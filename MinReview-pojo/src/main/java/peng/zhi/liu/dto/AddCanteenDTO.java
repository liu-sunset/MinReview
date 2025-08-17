package peng.zhi.liu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddCanteenDTO {
    private String name;
    private Long campusId;
    private String address;
    private String description;
}