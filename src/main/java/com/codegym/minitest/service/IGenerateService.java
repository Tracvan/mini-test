package com.codegym.minitest.service;

import com.codegym.minitest.model.Tour;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface IGenerateService <T> {
    Page<T> findAll(int pageIndex, int pageSize);
    Iterable<T> findAll();
    void save(T t);
    Optional<T> findById(long id);
    void remove(long id);
    Page<Tour> findByName(String name, int pageIndex, int pageSize);

    Page<Tour> findTourByDestinationIsContaining(Pageable pageable, String des);
}

