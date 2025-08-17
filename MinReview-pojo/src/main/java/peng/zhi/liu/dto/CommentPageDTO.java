package peng.zhi.liu.dto;

import lombok.Data;

//评论分页查询DTO
@Data
public class CommentPageDTO {
    private Integer page;
    private Integer pageSize;
    private Long dishId;
}