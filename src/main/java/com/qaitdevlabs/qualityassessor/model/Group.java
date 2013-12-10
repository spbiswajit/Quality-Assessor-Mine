package com.qaitdevlabs.qualityassessor.model;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Group  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long groupId;
	private String groupName;
	private String groupDescription;
	private Set<User> users = new HashSet<User>(0);
	private Set<Role> roles = new HashSet<Role>(0);
	
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	public Long getGroupId() {
		return groupId;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupDescription(String groupDescription) {
		this.groupDescription = groupDescription;
	}
	public String getGroupDescription() {
		return groupDescription;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	public Set<User> getUsers() {
		return users;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public Set<Role> getRoles() {
		return roles;
	}

}
