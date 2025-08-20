package peng.zhi.liu.service.Impl;

import com.aliyun.oss.model.CnameConfiguration;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peng.zhi.liu.dto.AddCanteenDTO;
import peng.zhi.liu.dto.CanteenPageDTO;
import peng.zhi.liu.entity.canteen;
import peng.zhi.liu.mapper.CanteenMapper;
import peng.zhi.liu.result.PageResult;
import peng.zhi.liu.service.CanteenService;
import peng.zhi.liu.vo.CanteenPageVO;
import peng.zhi.liu.vo.CanteenVO;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CanteenServiceImpl implements CanteenService {
    @Autowired
    private CanteenMapper canteenMapper;

    @Override
    public PageResult<CanteenPageVO> canteenPageService(CanteenPageDTO canteenPageDTO) {
        PageHelper.startPage(canteenPageDTO.getPage(), canteenPageDTO.getPageSize());
        Page<CanteenPageVO> page = canteenMapper.canteenPageMapper(canteenPageDTO);
        return new PageResult<>(page.getTotal(), page.getResult());
    }

    @Override
    public void addCanteenService(AddCanteenDTO addCanteenDTO) {
        canteen canteen = new canteen();
        BeanUtils.copyProperties(addCanteenDTO, canteen);
        canteen.setCreateTime(LocalDateTime.now());
        canteen.setUpdateTime(LocalDateTime.now());
        canteenMapper.addCanteenMapper(canteen);
    }

    @Override
    public void modifyCanteenService(Long id, AddCanteenDTO addCanteenDTO) {
        canteen canteen = new canteen();
        BeanUtils.copyProperties(addCanteenDTO, canteen);
        canteen.setId(id);
        canteen.setUpdateTime(LocalDateTime.now());
        canteenMapper.modifyCanteenMapper(canteen);
    }

    @Override
    public void deleteCanteenService(Long id) {
        canteenMapper.deleteCanteenMapper(id);
    }
    
    //根据校区ID获取食堂列表
    @Override
    public List<CanteenVO> getCanteenListByCampusIdService(Long campusId) {
        return canteenMapper.getCanteenListByCampusIdMapper(campusId);
    }

    @Override
    public void updateCanteenStatusService(Long id, Integer status) {
        canteen canteen = new canteen();
        canteen.setStatus(status);
        canteen.setId(id);
        canteen.setUpdateTime(LocalDateTime.now());
        canteenMapper.modifyCanteenMapper(canteen);
    }
}
