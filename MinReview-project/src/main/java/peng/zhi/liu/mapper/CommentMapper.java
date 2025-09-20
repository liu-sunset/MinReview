package peng.zhi.liu.mapper;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.logging.log4j.message.LoggerNameAwareMessage;
import peng.zhi.liu.dto.CommentPageDTO;
import peng.zhi.liu.entity.UserComment;
import peng.zhi.liu.vo.CommentPageVO;

import java.util.List;

//评论Mapper接口
@Mapper
public interface CommentMapper {
    //评论分页查询
    Page<CommentPageVO> commentPageMapper(CommentPageDTO commentPageDTO);
    //删除评论
    void deleteCommentMapper(List<Long> ids);
    //用户评论分页查询
    Page<CommentPageVO> userCommentPageMapper(CommentPageDTO commentPageDTO);
    //添加用户评论
    public void addCommentMapper(UserComment userComment);
    //修改用户的评论头像为默认头像
    public void modifyCommentAvatarMapper(Long id,String avatarUrl);
    //批量修改用户评论的名字
    public void modifyCommentNameMapper(Long id,String userName);
    //根据ID查询评论详细信息
    public UserComment selectByIdMapper(Long id);
}