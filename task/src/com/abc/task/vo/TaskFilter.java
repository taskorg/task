package com.abc.task.vo;

public class TaskFilter {
	private int id;
	private String name;
	private String classPath;
	private String defaultParams;
	private String message;
	private String demo;
	private String description;

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

	public String getClassPath() {
		return classPath;
	}

	public void setClassPath(String classPath) {
		this.classPath = classPath;
	}

	public String getDefaultParams() {
		return defaultParams;
	}

	public void setDefaultParams(String defaultParams) {
		this.defaultParams = defaultParams;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDemo() {
		return demo;
	}

	public void setDemo(String demo) {
		this.demo = demo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
