package com.project.springbootwebstore.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.springbootwebstore.dto.OrderDto;
import com.project.springbootwebstore.entity.order.DeliveryDetails;
import com.project.springbootwebstore.entity.order.Order;
import com.project.springbootwebstore.entity.users.User;
import com.project.springbootwebstore.entity.order.UserOrderInfo;
import com.project.springbootwebstore.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;
    private final ProductService productService;

    @Autowired
    public OrderService(OrderRepository orderRepository, UserService userService, ProductService productService) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.productService = productService;
    }

    public Order saveOrder(OrderDto orderDto, UserDetails user){
        UserOrderInfo userOrderInfo = new UserOrderInfo(orderDto.firstName(), orderDto.lastName(), orderDto.email(), orderDto.phone());
        DeliveryDetails deliveryDetails = new DeliveryDetails(orderDto.city(),orderDto.street(),orderDto.building(), orderDto.apartmentNo(), LocalDate.parse(orderDto.date()));
        User user1 = userService.getUserByUsername(user.getUsername()).get();
//        List<Product> orderedProducts = productService.getAllProductsById(orderDto.getOrderedProducts().stream().map(cartItem -> (long) cartItem.getId()).toList());


        ObjectMapper objectMapper = new ObjectMapper();
        String json = "";
        try {
            json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(orderDto.orderedProducts());
        } catch(Exception e) {
            e.printStackTrace();
        }
        Order order = new Order(user1, LocalDateTime.now(),deliveryDetails,userOrderInfo,json);
        return orderRepository.save(order);

    }

}
