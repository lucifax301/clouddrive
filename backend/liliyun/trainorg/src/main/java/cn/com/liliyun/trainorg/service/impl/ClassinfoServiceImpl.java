package cn.com.liliyun.trainorg.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.liliyun.common.dto.MapDTO;
import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.common.util.PageUtil;
import cn.com.liliyun.student.model.Student;
import cn.com.liliyun.student.service.StudentService;
import cn.com.liliyun.trainorg.mapper.ClassinfoMapper;
import cn.com.liliyun.trainorg.model.Classinfo;
import cn.com.liliyun.trainorg.service.ClassinfoService;

import com.github.pagehelper.PageInfo;

@Service
public class ClassinfoServiceImpl implements ClassinfoService {

	@Autowired
	private ClassinfoMapper classinfoMapper;
	@Autowired
	private StudentService studentService;
	
	@Override
	public ResultBean addClass(Classinfo classinfo) {
		classinfoMapper.insertSelective(classinfo);
		return new ResultBean();
	}

	@Override
	public ResultBean updateClass(Classinfo classinfo) {
		classinfoMapper.updateByPrimaryKeySelective(classinfo);
		return new ResultBean();
	}

	@Override
	public ResultBean updateStatus(Classinfo classinfo) {
		classinfoMapper.updateStatus(classinfo);
		return new ResultBean();
	}
	
	
	@Override
	public ResultBean deleteClass(Classinfo classinfo) {
		String [] ids = classinfo.getIds().split(",");
		for (String id : ids) {
			Student student=new Student();
			student.setClassid(Integer.parseInt(id));
			Integer sid= studentService.getClassStudentOne(student);
			if(sid!=null&&sid.intValue()>0){
				ResultBean rb=new ResultBean("班别已经被学员使用，不能删除");
				return rb;
			}
		}
		for (String id : ids) {
			classinfo.setId(Integer.parseInt(id));
			
			classinfoMapper.deleteByPrimaryKey(classinfo);
		}
		return new ResultBean();
	}
	
	@Override
	public void deleteClassByType(Classinfo classinfo){
		classinfoMapper.deleteByClassType(classinfo);
	}

	@Override
	public ResultBean selectList(Classinfo classinfo) {
		ResultBean rb = new ResultBean();
		PageUtil.startPage(classinfo);
		List<Classinfo> list = classinfoMapper.selectList(classinfo);
		rb.setResult(new PageInfo<>(list));
		return rb;
	}
	
	@Override
	public ResultBean selectListAll(Classinfo classinfo) {
		ResultBean rb = new ResultBean();
		List<Classinfo> list = classinfoMapper.selectList(classinfo);
		Map map=new HashMap();
		map.put("list", list);
		rb.setResult(map);
		return rb;
	}

	@Override
	public ResultBean selectOne(Classinfo classinfo) {
		ResultBean rb = new ResultBean();
		classinfo = classinfoMapper.selectByPrimaryKey(classinfo);
		rb.setResult(classinfo);
		return rb;
	}

	@Override
	public List<Classinfo> selectAllList(Classinfo classinfo) {
		List<Classinfo> list = classinfoMapper.selectList(classinfo);
		return list;
	}

	@Override
	public Map<Integer, MapDTO> getMap(Classinfo classinfo) {
		return classinfoMapper.getMap(classinfo);
	}

	@Override
	public void batchAddClass(Map classinfo) {
		classinfoMapper.batchinsert(classinfo);
		
	}
	@Override
	public void batchDelClass(Map classinfo) {
		classinfoMapper.batchdel(classinfo);
	}

	@Override
	public Classinfo get(Classinfo classinfo) {
		return classinfoMapper.selectByPrimaryKey(classinfo);
	}
	
}
