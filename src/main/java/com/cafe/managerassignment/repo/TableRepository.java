package com.cafe.managerassignment.repo;

import com.cafe.managerassignment.model.Order;
import com.cafe.managerassignment.model.Table;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TableRepository extends CrudRepository<Table, Long> {
    List<Table> findByWaiter_Id(Integer id);
}
