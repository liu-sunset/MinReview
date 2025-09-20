package peng.zhi.liu.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import net.sf.jsqlparser.statement.comment.Comment;
import org.apache.logging.log4j.message.LoggerNameAwareMessage;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import peng.zhi.liu.controller.user.CommentController;
import peng.zhi.liu.dto.AddCommentDTO;
import peng.zhi.liu.dto.CommentPageDTO;
import peng.zhi.liu.entity.Dish;
import peng.zhi.liu.entity.UserComment;
import peng.zhi.liu.mapper.CommentMapper;
import peng.zhi.liu.mapper.DishMapper;
import peng.zhi.liu.result.PageResult;
import peng.zhi.liu.service.CommentService;
import peng.zhi.liu.utils.BaseContext;
import peng.zhi.liu.vo.CommentPageVO;
import peng.zhi.liu.vo.DishDetailVO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//评论服务实现类
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private DishMapper dishMapper;
    //评论分页查询
    @Override
    public PageResult<CommentPageVO> commentPageService(CommentPageDTO commentPageDTO) {
        PageHelper.startPage(commentPageDTO.getPage(), commentPageDTO.getPageSize());
        Page<CommentPageVO> page = commentMapper.commentPageMapper(commentPageDTO);
        return new PageResult<>(page.getTotal(), page.getResult());
    }

    //删除评论
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteCommentService(List<Long> ids) {
        for (int i = 0; i < ids.size(); i++) {
            Long id = ids.get(i);
            UserComment userComment = commentMapper.selectByIdMapper(id);
            Dish dish = new Dish();
            dish.setUpdateTime(LocalDateTime.now());
            dish.setId(userComment.getDishId());
            Dish dish1 = dishMapper.getDishByIdMapper(userComment.getDishId());
            dish.setCommentCount(dish1.getCommentCount()-1);
            dishMapper.updateDishMapper(dish);
        }
        commentMapper.deleteCommentMapper(ids);


    }

    @Override
    public void userDeleteCommentService(Long id) {
        ArrayList<Long> ids = new ArrayList<>();
        ids.add(id);
        commentMapper.deleteCommentMapper(ids);
            UserComment userComment = commentMapper.selectByIdMapper(id);
            Dish dish = new Dish();
            dish.setUpdateTime(LocalDateTime.now());
            dish.setId(userComment.getDishId());
            Dish dish1 = dishMapper.getDishByIdMapper(userComment.getDishId());
            dish.setCommentCount(dish1.getCommentCount()-1);
            dishMapper.updateDishMapper(dish);
    }

    @Override
    public PageResult<CommentPageVO> userCommentPageService(CommentPageDTO commentPageDTO) {
        PageHelper.startPage(commentPageDTO.getPage(), commentPageDTO.getPageSize());
        Page<CommentPageVO> page = commentMapper.userCommentPageMapper(commentPageDTO);
        return new PageResult<>(page.getTotal(), page.getResult());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addCommentService(AddCommentDTO addCommentDTO) {
        UserComment userComment = new UserComment();
        BeanUtils.copyProperties(addCommentDTO,userComment);
        userComment.setCreateTime(LocalDateTime.now());
        userComment.setUpdateTime(LocalDateTime.now());
        commentMapper.addCommentMapper(userComment);
        Dish dish = new Dish();
        DishDetailVO detailVO = dishMapper.getDishDetailMapper(addCommentDTO.getDishId());
        dish.setId(addCommentDTO.getDishId());
        dish.setCommentCount(detailVO.getCommentCount()+1);
        dish.setUpdateTime(LocalDateTime.now());
        dishMapper.updateDishMapper(dish);
    }
}