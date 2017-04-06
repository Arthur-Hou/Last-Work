package com.extr.domain.user;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Group implements Serializable {

	private static final long serialVersionUID = -166573023634513538L;
	private int groupId;
	private String groupName;
	private int userId;
	private boolean defaultt;
	public boolean isDefaultt() {
		return defaultt;
	}
	public void setDefaultt(boolean defaultt) {
		this.defaultt = defaultt;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
}

/*	private static final long serialVersionUID = -166573023634513538L;
	private int id;
	private String name;
	private int group_level_id;
	private int parent;
	private List<Group> children;

	public List<Group> getChildren() {
		return children;
	}

	public void setChildren(List<Group> children) {
		this.children = children;
	}

	public int getParent() {
		return parent;
	}

	public void setParent(int parent) {
		this.parent = parent;
	}

	public int getGroup_level_id() {
		return group_level_id;
	}

	public void setGroup_level_id(int group_level_id) {
		this.group_level_id = group_level_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
*/