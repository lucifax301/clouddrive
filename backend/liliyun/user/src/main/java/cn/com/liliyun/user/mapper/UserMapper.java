package cn.com.liliyun.user.mapper;

import java.util.List;
import java.util.Map;

import cn.com.liliyun.common.annotation.DBRoute;
import cn.com.liliyun.user.model.RoleUser;
import cn.com.liliyun.user.model.User;
import cn.com.liliyun.user.vo.UserVo;

@DBRoute("MRG")
public interface UserMapper {

	int deleteByPrimaryKey(User user);

    int insert(User user);

    int insertSelective(User user);

    User selectByPrimaryKey(User user);

    int updateByPrimaryKeySelective(User user);

    int updateByPrimaryKey(User user);
    
    User selectOne(User user);
    
    UserVo selectAppUser(User user);
    
    List <User> selectList(User user);
    
    int updatePwdByusername(User user);
    
    int insertBatch(Map <String,Object> params);
    
    public List<User> selectRoleUser(User user);
    
    public List<User> selectMutiUser(Map params);
    
    //public User selectOneWithRole(User user);
    
    //public List<User> selectListWithRole(User user);
    
    public List<User> listRoleUser(Map<String, Object> params);
	
	public List<User> listNotRoleUser(Map<String, Object> params);
	
	public int insertRoleUser(RoleUser roleUser);
	
	public int delRoleUser(RoleUser roleUser);
	
	public List<Integer> listRoleUserIds(RoleUser roleUser);
	
	public int getRoleUserTotal(RoleUser roleUser);
	
	public List<Map> listRoleUserCount(Map<String, Object> params);
}