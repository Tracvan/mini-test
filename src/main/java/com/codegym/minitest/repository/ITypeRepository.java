package com.codegym.minitest.repository;

import com.codegym.minitest.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

public interface ITypeRepository extends JpaRepository<Type, Long> {
}
