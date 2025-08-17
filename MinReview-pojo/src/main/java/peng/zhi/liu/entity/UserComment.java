package peng.zhi.liu.entity;

import lombok.Data;
import java.time.LocalDateTime;

//评论实体类
@Data
public class UserComment {
    private Long id;
    private Long userId;
    private Long dishId;
    private String content;
    private Long replyTo;
    private Integer status;
    private Integer likeCount;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}