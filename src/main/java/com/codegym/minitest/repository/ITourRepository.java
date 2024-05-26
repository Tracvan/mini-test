package com.codegym.minitest.repository;

import com.codegym.minitest.model.Tour;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITourRepository extends JpaRepository<Tour, Long> {
    Page<Tour> findTourByDestinationIsContaining(Pageable pageable, String destination);

}