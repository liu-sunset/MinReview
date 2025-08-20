package peng.zhi.liu.dto;

import lombok.Data;

@Data
public class ModifyAdminPasswordDTO {
    private String oldPassword;
    private String newPassword;
}
