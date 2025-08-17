package peng.zhi.liu.controller.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import peng.zhi.liu.dto.CommentPageDTO;
import peng.zhi.liu.result.PageResult;
import peng.zhi.liu.result.Result;
import peng.zhi.liu.service.CommentService;
import peng.zhi.liu.vo.CommentPageVO;

//评论管理Controller
@Slf4j
@RestController
@RequestMapping("/admin/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    //todo:test
    @GetMapping("/list")
    public Result commentPageController(CommentPageDTO commentPageDTO) {
        log.info("评论分页查询参数: {}", commentPageDTO);
        PageResult<CommentPageVO> pageResult = commentService.commentPageService(commentPageDTO);
        return Result.success(pageResult);
    }

    //todo:test
    @DeleteMapping("/{commentId}")
    public Result deleteCommentController(@PathVariable Long commentId) {
        log.info("删除评论ID: {}", commentId);
        commentService.deleteCommentService(commentId);
        return Result.success();
    }
}