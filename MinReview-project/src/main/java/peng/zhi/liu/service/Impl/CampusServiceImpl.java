package peng.zhi.liu.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import peng.zhi.liu.dto.AddCampusDTO;
import peng.zhi.liu.dto.CampusPageDTO;
import peng.zhi.liu.entity.Campus;
import peng.zhi.liu.mapper.CampusMapper;
import peng.zhi.liu.result.PageResult;
import peng.zhi.liu.service.CampusService;
import peng.zhi.liu.vo.CampusPageVO;

import java.time.LocalDateTime;

@Service
public class CampusServiceImpl implements CampusService {
    @Autowired
    private CampusMapper campusMapper;
    @Override
    public PageResult<CampusPageVO> campusPageService(CampusPageDTO campusPageDTO) {
        PageHelper.startPage(campusPageDTO.getPage(), campusPageDTO.getPageSize());
        Page<CampusPageVO> page = campusMapper.campusPageMapper(campusPageDTO);
        return new PageResult<>(page.getTotal(),page.getResult());
    }

    @Override
    public void addCampusService(AddCampusDTO addCampusDTO) {
        Campus campus = new Campus();
        BeanUtils.copyProperties(addCampusDTO,campus);
        campus.setCreateTime(LocalDateTime.now());
        campus.setUpdateTime(LocalDateTime.now());
        campusMapper.addCampusMapper(campus);
    }

    @Override
    public void modifyCampusService(Long id, AddCampusDTO addCampusDTO) {
        Campus campus = new Campus();
        BeanUtils.copyProperties(addCampusDTO,campus);
        campus.setId(id);
        campus.setUpdateTime(LocalDateTime.now());
        campusMapper.modifyCampusMapper(campus);
    }

    @Override
    public void deleteCampusService(Long id) {
        campusMapper.deleteCampusMapper(id);
    }

    @Override
    public void updateCampusStatusService(Long id, Integer status) {
        Campus campus = new Campus();
        campus.setUpdateTime(LocalDateTime.now());
        campus.setId(id);
        campus.setStatus(status);
        campusMapper.modifyCampusMapper(campus);
    }
}
