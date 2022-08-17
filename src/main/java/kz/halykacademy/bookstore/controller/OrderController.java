package kz.halykacademy.bookstore.controller;

import kz.halykacademy.bookstore.dto.OrderDTO;
import kz.halykacademy.bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @GetMapping(value = "/all")
    public List<OrderDTO> getAllOrders(){
        return orderService.getOrderList();
    }
    @GetMapping(value = "/order/{orderId}")
    public OrderDTO getOrderById(@PathVariable("orderId") Long orderId){
        return orderService.getOrderById(orderId);
    }

    @PostMapping(value = "/order/create")
    public void createNewOrder(@RequestBody OrderDTO orderDTO){
        orderService.createOrder(orderDTO);
    }

    @PutMapping(value = "/order/update")
    public void updateOrder(@RequestBody OrderDTO orderDTO){
        orderService.updateOrder(orderDTO);
    }

    @DeleteMapping(value = "/order/delete/{orderId}")
    public void deleteOrderById(@PathVariable("orderId") Long orderId){
        orderService.deleteOrder(orderId);
    }

}
