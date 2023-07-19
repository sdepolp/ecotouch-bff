package cl.ecotouch.bff.controller;

import cl.ecotouch.bff.dto.LocationRequestDTO;
import cl.ecotouch.bff.dto.OrderRequestDto;
import cl.ecotouch.bff.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value="/v1/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping(value="/get-orders")
    public ResponseEntity<?> getOrders(@RequestBody() OrderRequestDto orderRequestDto) throws Exception {
        return ResponseEntity.ok().body(orderService.getDailyOrders(orderRequestDto));
    }

    @GetMapping(value="{username}")
    public ResponseEntity<?> getOrdersByUser(@PathVariable String username) throws Exception {
        return ResponseEntity.ok().body(orderService.getOrdersByUser(username));
    }
}
