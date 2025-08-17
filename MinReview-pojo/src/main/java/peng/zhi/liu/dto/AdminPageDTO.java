package peng.zhi.liu.dto;

import lombok.Data;

//管理员分页查询DTO
@Data
public class AdminPageDTO {
    private Integer page;
    private Integer pageSize;
    private String name;
}