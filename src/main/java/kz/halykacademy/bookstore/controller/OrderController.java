package kz.halykacademy.bookstore.controller;

import kz.halykacademy.bookstore.dto.OrderCreateDTO;
import kz.halykacademy.bookstore.dto.OrderDTO;
import kz.halykacademy.bookstore.dto.UserDTO;
import kz.halykacademy.bookstore.entity.Order;
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
    public ResponseEntity<List<OrderDTO>> getAllOrders(){
        List<OrderDTO> list = orderService.getOrderList();
        return ResponseEntity.ok().body(list);
    }
    @GetMapping(value = "/order/{orderId}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable("orderId") Long orderId){
        OrderDTO orderDTO = orderService.getOrderById(orderId);
        return ResponseEntity.ok().body(orderDTO);
    }

    @PostMapping(value = "/order/create")
    public ResponseEntity createNewOrder(@RequestBody OrderCreateDTO orderDTO){
        orderService.createOrder(orderDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/order/update")
    public ResponseEntity updateOrder(@RequestBody OrderDTO orderDTO){
        orderService.updateOrder(orderDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/order/delete/{orderId}")
    public ResponseEntity deleteOrderById(@PathVariable("orderId") Long orderId){
        orderService.deleteOrder(orderId);
        return ResponseEntity.ok().build();
    }

}
