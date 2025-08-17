package peng.zhi.liu.vo;

import lombok.Data;
import java.time.LocalDateTime;

//管理员分页查询VO
@Data
public class AdminPageVO {
    private Long id;
    private String username;
    private String name;
    private Integer status;
    private Integer gender;
    private LocalDateTime updateTime;
}