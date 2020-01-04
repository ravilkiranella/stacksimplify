package com.stacksimplify.restservicesdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stacksimplify.restservicesdemo.entity.Order;

public interface OrderRepository extends JpaRepository<Order,Long>{

}
