package peng.zhi.liu.controller.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import peng.zhi.liu.annotation.JwtInspect;
import peng.zhi.liu.dto.AddCommentDTO;
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
    @JwtInspect
    @GetMapping("/list")
    public Result getCommentListController(CommentPageDTO commentPageDTO) {
        log.info("获取菜品评论列表参数: {}", commentPageDTO);
        // 调用服务层获取评论列表
        PageResult<CommentPageVO> pageResult = commentService.userCommentPageService(commentPageDTO);
        return Result.success(pageResult);
    }

    @JwtInspect
    @PostMapping
    public Result addCommentController(@RequestBody AddCommentDTO addCommentDTO){
        log.info("用户添加评论，评论信息:{}",addCommentDTO);
        commentService.addCommentService(addCommentDTO);
        return Result.success();
    }

    @JwtInspect
    @DeleteMapping("/{commentId}")
    public Result deleteCommentController(@PathVariable Long commentId){
        log.info("用户删除评论，评论ID:{}",commentId);
        commentService.deleteCommentService(commentId);
        return Result.success();
    }
}
