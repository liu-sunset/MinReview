package peng.zhi.liu.vo;

import lombok.Data;
import java.time.LocalDateTime;

//评论分页查询VO
@Data
public class CommentPageVO {
    private Long id;
    private Long userId;
    private Long dishId;
    private String content;
    private Long replyTo;
    private Integer status;
    private Integer likeCount;
    private LocalDateTime updateTime;
}