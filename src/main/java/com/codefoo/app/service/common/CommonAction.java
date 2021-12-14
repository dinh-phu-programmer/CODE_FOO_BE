package com.codefoo.app.service.common;

import static com.codefoo.app.constant.ExceptionMessageConstant.OBJECT_NOT_FOUND_EXCEPTION;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.codefoo.app.exception.specific.ObjectNotFoundException;
import com.codefoo.app.model.rootclass.RootClass;

public abstract class CommonAction<T extends RootClass, ID extends Number, E extends JpaRepository> {

	protected E jpaRepository;

	public CommonAction(E jpaRepository) {
		this.jpaRepository = jpaRepository;
	}

	public T save(T object) {
		return (T) jpaRepository.save(object);
	}

	public T update(T object, ID id) throws ObjectNotFoundException {
		T current = null;

		current = findById(id);

		Integer currentID = current.getId();

		current = object;

		current.setId(currentID);

		return (T) jpaRepository.save(current);
	}

	public T delete(T object) {
		this.jpaRepository.delete(object);
		return object;
	}

	public T delete(ID id) throws ObjectNotFoundException {
		T current = null;

		current = findById(id);

		this.jpaRepository.deleteById(id);
		return current;
	}

	public List<T> findAll() {
		return this.jpaRepository.findAll();
	}

	public T findById(ID id) throws ObjectNotFoundException {

		Optional<T> obj = this.jpaRepository.findById(id);

		if (!obj.isPresent()) {
			throw new ObjectNotFoundException(OBJECT_NOT_FOUND_EXCEPTION + id, T.classType);
		}

		return obj.get();

	}

	public Page<T> findAllWithPage(int page, int size, Optional<String> sortBy) {
		String sort = sortBy.orElse("id");
		Pageable pageable = PageRequest.of(page, size, Sort.by(sort).descending());

		Page<T> obj = this.jpaRepository.findAll(pageable);
		return obj;
	}
}
