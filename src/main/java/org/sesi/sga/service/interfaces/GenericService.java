package org.sesi.sga.service.interfaces;

import java.util.List;

import org.sesi.sga.service.exceptions.DBException;

public interface GenericService<T> {
	T save(T entidade) throws DBException;
	Boolean update(T entidade) throws DBException;
	T getById(Integer id) throws DBException;
	List<T> getAll(String orderBy) throws DBException;
	Boolean removeById(Integer id) throws DBException;
}
