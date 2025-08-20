package peng.zhi.liu.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peng.zhi.liu.dto.CommentPageDTO;
import peng.zhi.liu.mapper.CommentMapper;
import peng.zhi.liu.result.PageResult;
import peng.zhi.liu.service.CommentService;
import peng.zhi.liu.vo.CommentPageVO;

//评论服务实现类
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;

    //评论分页查询
    @Override
    public PageResult<CommentPageVO> commentPageService(CommentPageDTO commentPageDTO) {
        PageHelper.startPage(commentPageDTO.getPage(), commentPageDTO.getPageSize());
        Page<CommentPageVO> page = commentMapper.commentPageMapper(commentPageDTO);
        return new PageResult<>(page.getTotal(), page.getResult());
    }

    //删除评论
    @Override
    public void deleteCommentService(Long id) {
        commentMapper.deleteCommentMapper(id);
    }

    @Override
    public PageResult<CommentPageVO> userCommentPageService(CommentPageDTO commentPageDTO) {
        PageHelper.startPage(commentPageDTO.getPage(), commentPageDTO.getPageSize());
        Page<CommentPageVO> page = commentMapper.userCommentPageMapper(commentPageDTO);
        return new PageResult<>(page.getTotal(), page.getResult());
    }
}