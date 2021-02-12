package com.chamanthi.travelSecurity.entity;

import java.util.List;

import javax.persistence.*;

@Entity
public class Role extends BaseIdEntity {

	private String name;

	@ManyToMany(fetch = FetchType.EAGER,cascade = { CascadeType.ALL })
	@JoinTable(name = "permission_role", joinColumns = {
			@JoinColumn(name = "role_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "permission_id", referencedColumnName = "id") })
	private List<Permission> permissions;


	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	@ManyToMany(mappedBy = "roles")
	private List<User> users;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

}