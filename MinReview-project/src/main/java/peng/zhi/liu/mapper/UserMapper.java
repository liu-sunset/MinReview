package peng.zhi.liu.mapper;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import peng.zhi.liu.dto.UserPageDTO;
import peng.zhi.liu.entity.User;
import peng.zhi.liu.vo.UserInfoVO;
import peng.zhi.liu.vo.UserPageVO;

import java.util.List;

@Mapper
public interface UserMapper {
    //用户分页查询
    public Page<UserPageVO> userPageMapper(UserPageDTO userPageDTO);
    //修改用户
    public void modifyUserMapper(User user);
    //删除用户
    public void deleteUserMapper(Long userId);
    //根据id获取用户信息
    public UserInfoVO getUserInfoById(Long id);
}
