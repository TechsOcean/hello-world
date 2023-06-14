package com.chandan.hibernateentitymappings.repository;

public interface IStudentRepository<T> {

    void save(T t);

    public T findStudentById(Long id);
}
