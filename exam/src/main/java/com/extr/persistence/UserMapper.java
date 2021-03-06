package com.extr.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.extr.domain.user.Department;
import com.extr.domain.user.Group;
import com.extr.domain.user.Role;
import com.extr.domain.user.User;
import com.extr.util.Page;

public interface UserMapper {

	/**
	 * 根据用户名称查询用户基本信息
	 * 
	 * @param user_name
	 * @return
	 */
	public User getUserByName(String username);

	/**
	 * 添加user并返回该记录的主键
	 * 
	 * @param user
	 * @return
	 */
	public int insertUser(User user);

	/**
	 * 更新user基本信息(包括更新password,fullname)
	 * 
	 * @param user
	 */
	public void updateUser(@Param("user") User user, @Param("oldpassword") String oldpassword);

	/**
	 * 查询某角色的所有用户
	 * 
	 * @param role_id
	 * @return
	 */
	public List<User> getUserListByRoleId(@Param("roleId") int roleId, @Param("page") Page<User> page);

	/**
	 * 给用户授权一种角色
	 * 
	 * 
	 * @param userId
	 *            roleId
	 */
	public void grantUserRole(@Param("userId") int userId, @Param("roleId") int roleId);

	/**
	 * 获取
	 * 
	 * @param userId
	 * @param page
	 * @return
	 */
	public List<Group> getGroupListByUserId(@Param("userId") int userId, @Param("page") Page<Group> page);

	/**
	 * 添加一个分组
	 * 
	 * @param group
	 */
	public void addGroup(@Param("group") Group group);

	/**
	 * 为学员分配一个用户组
	 * 
	 * @param userId
	 * @param groupId
	 */
	public void addUserGroup(@Param("userId") int userId, @Param("groupId") int groupId);

	/**
	 * 更新分组
	 * 
	 * @param groupId
	 * @param groupName
	 */
	public void updateGroup(@Param("groupId") int groupId, @Param("groupName") String groupName);

	/**
	 * 获取所有的角色
	 * 
	 * @return
	 */
	public List<Role> getRoleList();

	/**
	 * 更新用户状态
	 * 
	 * @param idList
	 * @param enabled
	 */
	public void changeUserStatus(@Param("array") List<Integer> idList, @Param("enabled") boolean enabled);

	/**
	 * 删除分组
	 * 
	 * @param groupId
	 */
	public void deleteGroup(int groupId);
	
	/**
	 * 删除分组
	 * @param userId
	 * @param groupId
	 * @param managerId 只能删除自己管理的分组中的数据
	 */
	public void deleteUserGroup(@Param("userId") int userId,@Param("groupId") int groupId,@Param("managerId") int managerId);

	/**
	 * 根据groupid和关键字来查询用户
	 * 
	 * @param groupId
	 *            0 则为全部用户
	 * @param searchStr
	 * @param page
	 * @return
	 */
	public List<User> getUserListByGroupIdAndParams(@Param("groupId") int groupId, @Param("authority") String authority,
			@Param("searchStr") String searchStr, @Param("page") Page<User> page);
	
	/**
	 * 根据用户名清单获取用户
	 * @param nameList
	 * @return
	 */
	public List<User> getUserByNames(@Param("array") String[] nameList,@Param("roleId") int roleId);
	
	/**
	 * 批量添加用户到分组
	 * @param idList
	 * @param groupId
	 */
	public void addUsers2Group(@Param("array") List<Integer> idList,@Param("groupId") int groupId);
	
	/**
	 * 获取所有部门信息
	 * @param page
	 * @return
	 */
	public List<Department> getDepList(@Param("page") Page<Department> page);
	
	/**
	 * 新增部门
	 * @param dep
	 */
	public void addDep(Department dep);
	
	/**
	 * 更新部门
	 * @param dep
	 */
	public void updateDep(Department dep);
	
	/**
	 * 删除一个部门
	 * @param depId
	 */
	public void deleteDep(int depId);
	
	/**
	 * 为用户添加一个部门
	 * @param userId
	 * @param depId
	 */
	public void addUser2Dep(@Param("userId") int userId,@Param("depId") int depId);
	
	/**
	 * 删除用户的部门信息
	 * @param userId
	 */
	public void deleteUser2Dep(int userId);
	
	/**
	 * 获取分组中的用户
	 * @param idList
	 * @param user
	 * @return
	 */
	public List<User> getUserListByGroupIdList(@Param("array") List<Integer> idList,@Param("page") Page<User> user);
}


/*import com.extr.domain.user.Role;
import com.extr.domain.user.User;
import com.extr.util.Page;

public interface UserMapper {

	*//**
	 * 添加user并返回该记录的主键
	 * 
	 * @param user
	 * @return
	 *//*
	public int insertUser(User user);

	*//**
	 * 更新user基本信息(包括更新password,fullname)
	 * 
	 * @param user
	 *//*
	public void updateUser(@Param("user") User user, @Param("oldpassword") String oldpassword);

	*//**
	 * 根据ID删除某个用户的记录
	 * 
	 * @param user_id
	 *//*
	public void deleteUser(int user_id);

	*//**
	 * 根据ID查询用户基本信息
	 * 
	 * @param user_id
	 * @return
	 *//*
	public User getUserById(int user_id);

	*//**
	 * 根据用户名称查询用户基本信息
	 * 
	 * @param user_name
	 * @return
	 *//*
	public User getUserByName(String username);

	*//**
	 * 获取用户列表
	 * 
	 * @return
	 *//*
	public List<User> getAllUserList(@Param("page") Page<User> page);

	*//**
	 * 插入角色
	 * 
	 * @param role
	 * @return
	 *//*
	public int insertRole(Role role);

	*//**
	 * 更新角色
	 * 
	 * @param role
	 *//*
	public void updateRole(Role role);

	*//**
	 * 删除角色
	 * 
	 * @param role_id
	 *//*
	public void deleteRole(int role_id);

	*//**
	 * 获取角色列表
	 * 
	 * @return
	 *//*
	public List<Role> getAllRoleList(@Param("page") Page<Role> page);

	*//**
	 * 给用户授权一种角色
	 * 
	 * 
	 * @param user_id
	 *            role_id
	 *//*
	public void grantUserRole(@Param("user_id") int user_id, @Param("role_id") int role_id);

	*//**
	 * 查询某角色的所有用户
	 * 
	 * @param role_id
	 * @return
	 *//*
	public List<User> getUserListByRoleId(@Param("role_id") int role_id, @Param("page") Page<User> page);

	*//**
	 * 查询用户的角色列表
	 * 
	 * @param user_id
	 * @return
	 *//*
	public List<Role> getRoleListByUserId(@Param("user_id") int user_id, @Param("page") Page<Role> page);

	*//**
	 * 删除user的role
	 * 
	 * @param user_id
	 *//*
	public void deleteUserRoleByUserId(@Param("user_id") int user_id);

}
*/