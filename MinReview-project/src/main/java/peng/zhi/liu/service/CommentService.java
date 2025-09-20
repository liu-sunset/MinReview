package peng.zhi.liu.service;

import org.apache.logging.log4j.message.LoggerNameAwareMessage;
import peng.zhi.liu.dto.AddCommentDTO;
import peng.zhi.liu.dto.CommentPageDTO;
import peng.zhi.liu.result.PageResult;
import peng.zhi.liu.vo.CommentPageVO;

import java.lang.invoke.CallSite;
import java.util.List;

//评论服务接口
public interface CommentService {
    //评论分页查询
    public PageResult<CommentPageVO> commentPageService(CommentPageDTO commentPageDTO);
    //管理员删除评论
    public void deleteCommentService(List<Long> ids);
    //用户删除评论
    public void userDeleteCommentService(Long id);
    //用户评论分页查询
    public PageResult<CommentPageVO> userCommentPageService(CommentPageDTO commentPageDTO);
    //添加评论
    public void addCommentService(AddCommentDTO addCommentDTO);
}