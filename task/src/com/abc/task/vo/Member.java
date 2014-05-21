package com.abc.task.vo;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.abc.task.enums.MemberStatus;


public class Member {
	private int id;
	private String name;
	private String email;
	private String mobile;
	private MemberStatus status;
	@JsonIgnore
	private String password;

	public Member() {
	}

	public Member(String name, String password, String email,MemberStatus status) {
		this.name = name;
		this.password = password;
		this.email = email;
		this.status = status;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public MemberStatus getStatus() {
		return status;
	}

	public void setStatus(MemberStatus status) {
		this.status = status;
	}
}
