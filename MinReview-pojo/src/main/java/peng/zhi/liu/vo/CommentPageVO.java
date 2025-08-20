package peng.zhi.liu.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

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
    private String name;
    private String avatarUrl;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}