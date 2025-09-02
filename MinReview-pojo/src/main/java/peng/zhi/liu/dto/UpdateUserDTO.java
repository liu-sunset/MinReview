package peng.zhi.liu.dto;

import lombok.Data;

//更新用户信息DTO类
@Data
public class UpdateUserDTO {
    private Long id;
    private String name;
    private String phone;
    private String avatarUrl;
    private Integer gender;
}