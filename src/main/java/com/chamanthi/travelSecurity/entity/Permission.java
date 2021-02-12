package com.chamanthi.travelSecurity.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class Permission extends BaseIdEntity {

	private String name;

	public String getName() {
		return name;
	}

	public List<Role> getRole() {
		return role;
	}

	public void setRole(List<Role> role) {
		this.role = role;
	}

	@ManyToMany(mappedBy = "permissions")
	private List<Role> role;

	public void setName(String name) {
		this.name = name;
	}
}
