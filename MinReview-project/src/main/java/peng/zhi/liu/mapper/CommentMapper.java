package peng.zhi.liu.mapper;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import peng.zhi.liu.dto.CommentPageDTO;
import peng.zhi.liu.vo.CommentPageVO;

//评论Mapper接口
@Mapper
public interface CommentMapper {
    //评论分页查询
    Page<CommentPageVO> commentPageMapper(CommentPageDTO commentPageDTO);
    //删除评论
    void deleteCommentMapper(Long id);
    //用户评论分页查询
    Page<CommentPageVO> userCommentPageMapper(CommentPageDTO commentPageDTO);
}