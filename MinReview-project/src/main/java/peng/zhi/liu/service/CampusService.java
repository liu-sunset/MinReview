package peng.zhi.liu.service;

import peng.zhi.liu.dto.AddCampusDTO;
import peng.zhi.liu.dto.CampusPageDTO;
import peng.zhi.liu.result.PageResult;
import peng.zhi.liu.vo.CampusPageVO;

public interface CampusService {
    //校区分页查询
    public PageResult<CampusPageVO> campusPageService(CampusPageDTO campusPageDTO);
    //添加校区
    public void addCampusService(AddCampusDTO addCampusDTO);
    //修改校区
    public void modifyCampusService(Long id,AddCampusDTO addCampusDTO);
    //删除校区
    public void deleteCampusService(Long id);
    //更新校区状态
    public void updateCampusStatusService(Long id,Integer status);
}
