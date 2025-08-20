package peng.zhi.liu.controller.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import peng.zhi.liu.dto.CommentPageDTO;
import peng.zhi.liu.result.PageResult;
import peng.zhi.liu.result.Result;
import peng.zhi.liu.service.CommentService;
import peng.zhi.liu.vo.CommentPageVO;


@Slf4j
@RestController("userCommentController")
@RequestMapping("/user/comment")
public class CommentController {
    
    @Autowired
    private CommentService commentService;

    //todo:test
    @GetMapping("/list")
    public Result getCommentListController(CommentPageDTO commentPageDTO) {
        log.info("获取菜品评论列表参数: {}", commentPageDTO);
        // 调用服务层获取评论列表
        PageResult<CommentPageVO> pageResult = commentService.userCommentPageService(commentPageDTO);
        return Result.success(pageResult);
    }
}
