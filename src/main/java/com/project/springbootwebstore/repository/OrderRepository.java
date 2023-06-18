package com.project.springbootwebstore.repository;

import com.project.springbootwebstore.entity.users.Order;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends PagingAndSortingRepository<Order,Long> {
}
