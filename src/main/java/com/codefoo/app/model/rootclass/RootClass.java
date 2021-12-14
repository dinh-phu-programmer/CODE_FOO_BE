package com.codefoo.app.model.rootclass;

public abstract class RootClass {
	public static final String classType="rootclass";
	
	protected Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public RootClass() {

	}

}
