package com.ssm.chapter3.dao;

import java.util.List;

import com.ssm.chapter3.pojo.Role;

public interface RoleDao {

	public int insertRole(Role role);
	public int delectRole(int id);
	public int updateRole(Role role);
	public Role getRole(int id);
	public List<Role> findRoles(String name);
	
}
