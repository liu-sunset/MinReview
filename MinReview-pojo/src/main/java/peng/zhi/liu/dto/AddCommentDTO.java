package peng.zhi.liu.dto;

import lombok.Data;

@Data
public class AddCommentDTO {
    private Long dishId;
    private Long userId;
    private String content;
    private Long replyTo;
    private String avatarUrl;
    private String userName;
}
