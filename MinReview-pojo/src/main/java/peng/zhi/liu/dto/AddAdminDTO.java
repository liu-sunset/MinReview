package peng.zhi.liu.dto;

import lombok.Data;

// 添加管理员DTO
@Data
public class AddAdminDTO {
    private String username;
    private String password;
    private String name;
}