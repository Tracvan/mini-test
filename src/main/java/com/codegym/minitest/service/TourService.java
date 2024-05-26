package com.codegym.minitest.service;

import com.codegym.minitest.model.Tour;
import com.codegym.minitest.repository.ITourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.NoSuchElementException;
import java.util.Optional;
@Service
public class TourService implements ITourService{
    @Autowired
    private ITourRepository iTourRepository;

    @Override
    public Page<Tour> findAll(int pageIndex, int pageSize) {
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        return iTourRepository.findAll(pageable);
    }

    @Override
    public Iterable<Tour> findAll() {
        return iTourRepository.findAll();
    }

    @Override
    public void save(Tour tour) throws DataIntegrityViolationException{
            iTourRepository.save(tour);
    }

    @Override
    public Optional<Tour> findById(long id) throws NoSuchElementException {
        return iTourRepository.findById(id);
    }

    @Override
    public void remove(long id) {
        iTourRepository.deleteById(id);
    }

    @Override
    public Page<Tour> findByName(String name, int pageIndex, int pageSize) {
        return null;
    }

    @Override
    public Page<Tour> findTourByDestinationIsContaining(Pageable pageable, String des) {
        return iTourRepository.findTourByDestinationIsContaining(pageable, des);
    }
}
