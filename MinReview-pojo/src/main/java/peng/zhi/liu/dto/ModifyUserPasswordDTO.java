package peng.zhi.liu.dto;

import lombok.Data;

@Data
public class ModifyUserPasswordDTO {
    private String oldPassword;
    private String newPassword;
}
