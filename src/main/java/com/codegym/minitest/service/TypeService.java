package com.codegym.minitest.service;

import com.codegym.minitest.model.Tour;
import com.codegym.minitest.model.Type;
import com.codegym.minitest.repository.ITypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TypeService implements ITypeService{
    @Autowired
    ITypeRepository iTypeRepository;
    @Override
    public Page<Type> findAll(int pageIndex, int pageSize) {
        return null;
    }

    @Override
    public Iterable<Type> findAll() {
        return iTypeRepository.findAll() ;
    }

    @Override
    public void save(Type type) {
        iTypeRepository.save(type);
    }

    @Override
    public Optional<Type> findById(long id) {
        return iTypeRepository.findById(id);
    }

    @Override
    public void remove(long id) {
        iTypeRepository.deleteById(id);
    }

    @Override
    public Page<Tour> findByName(String name, int pageIndex, int pageSize) {
        return null;
    }

    @Override
    public Page<Tour> findTourByDestinationIsContaining(Pageable pageable, String des) {
        return null;
    }
}
