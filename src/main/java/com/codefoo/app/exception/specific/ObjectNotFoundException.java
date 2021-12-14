package com.codefoo.app.exception.specific;

import com.codefoo.app.model.rootclass.RootClass;

public class ObjectNotFoundException extends Exception {

	public ObjectNotFoundException() {
		super();
	}

	public ObjectNotFoundException(String s) {
		super(s);
	}

	public ObjectNotFoundException(String s, RootClass customClass) {

	}

	public ObjectNotFoundException(String s, String customClass) {
		super(customClass + " " + s);

	}
}
