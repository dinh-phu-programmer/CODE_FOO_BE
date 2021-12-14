package com.codefoo.app.service.common;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.codefoo.app.exception.specific.ObjectNotFoundException;
import com.codefoo.app.model.rootclass.RootClass;

public interface CommonService<T extends RootClass, ID extends Number> {
	T save(T object);

	T update(T object, ID id) throws ObjectNotFoundException;

	T delete(T object);

	T delete(ID id) throws ObjectNotFoundException;

	List<T> findAll();

	T findById(ID id) throws ObjectNotFoundException;

	Page<T> findAllWithPage(int page, int size, Optional<String> sortBy);
}
