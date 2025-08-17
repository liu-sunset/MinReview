package peng.zhi.liu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddCampusDTO {
    private String name;
    private String address;
    private String description;
}
