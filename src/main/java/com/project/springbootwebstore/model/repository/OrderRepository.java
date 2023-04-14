package com.project.springbootwebstore.model.repository;

import com.project.springbootwebstore.model.entity.users.Order;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends PagingAndSortingRepository<Order,Long> {
}
