package com.cafe.managerassignment.service;

import com.cafe.managerassignment.model.Order;
import com.cafe.managerassignment.model.OrderStatus;
import com.cafe.managerassignment.model.ProductInOrder;
import com.cafe.managerassignment.model.Table;
import com.cafe.managerassignment.repo.TableRepository;
import com.cafe.managerassignment.repo.ProductInOrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TableService {
    private TableRepository tableRepository;


    public TableService(TableRepository tableRepository ) {
        this.tableRepository = tableRepository;
    }

    public List<Table> findAll() {
        List<Table> tables = tableRepository.findAll();
        List<Table> sortedOrders = tables.stream().sorted().collect(Collectors.toList());
        return sortedOrders;
    }

    public Table findById(Long id) {
        return tableRepository.findById(id).orElseThrow(IllegalStateException::new);
    }

    public void save(Table table) {
        tableRepository.save(table);
    }

    public void deleteById(Long id) {
        tableRepository.deleteById(id);
    }

    public List<Table> findByWaiter_Id(Long waiterId) {
        List<Table> tables = tableRepository.findByWaiter_Id(waiterId);

        return tables;
    }

}
