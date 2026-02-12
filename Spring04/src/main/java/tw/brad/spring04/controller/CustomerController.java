package tw.brad.spring04.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import tw.brad.spring04.dto.CustomerDto;
import tw.brad.spring04.dto.OrderDetailDto;
import tw.brad.spring04.dto.OrderDto;
import tw.brad.spring04.entity.Customer;
import tw.brad.spring04.entity.Order;
import tw.brad.spring04.entity.OrderDetail;
import tw.brad.spring04.repo.CustomerRepo;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerRepo customerRepo;

    @Operation(summary = "查詢客戶訂單", description = "OK!Keep Going")
    @GetMapping("/v1/{id}")
    public ResponseEntity<Customer> test1(@Parameter(description="Enter CustomerID")@PathVariable String id){
        return ResponseEntity.ok(customerRepo.findById(id).orElse(null));
    }

    @GetMapping("/v2/{id}")
    public ResponseEntity<Customer> test2(@PathVariable String id){
        // Optional<Customer> opt = customerRepo.findByCustomerID(id);
        // if (opt.isPresent()) {
        //     Customer customer = opt.get();
        // }

        
        Customer customer = customerRepo.findByCustomerID(id).orElse(null);    

        // customerRepo.findByCustomerID(id).orElse(new Customer());
        // customerRepo.findByCustomerID(id).orElseGet(Customer::new);
        // customerRepo.findByCustomerID(id).orElseThrow(() -> new IllegalArgumentException("xxx"));

        return ResponseEntity.ok(customer);
    }

    @GetMapping("/v3/{id}")
    public ResponseEntity<CustomerDto> test3(@PathVariable String id){
        Customer c = customerRepo.findById(id).orElse(null);
        //-----------------
        List<Order> orders = c.getOrders();

        ArrayList<OrderDto> orderlList = new ArrayList<>();
        for(Order o : orders) {
            List<OrderDetail> details = o.getOrderdetails();

            ArrayList<OrderDetailDto> detailDtos = new ArrayList<>();
            for (OrderDetail d : details){
                detailDtos.add(new OrderDetailDto(d.getUnitPrice(), d.getQuantity(), d.getProduct().getProductName()));
            }
            
            orderlList.add(new OrderDto(o.getOrderid(), o.getOrderDate(), detailDtos));
        }

        //-------------------
        CustomerDto cDto = new CustomerDto(
                c.getCustomerid(), c.getCompanyName(), orderlList);

        return ResponseEntity.ok(cDto);
    }

    @GetMapping("/v4/{id}")
    public ResponseEntity<CustomerDto> test4(@PathVariable String id){
        Optional<Customer> opt = customerRepo.findById(id);
        if (opt.isPresent()) {
            Customer c = opt.get();
            CustomerDto cDto = toCustomerDto(c);
            return ResponseEntity.ok(cDto);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/v5/{id}")
    public ResponseEntity<CustomerDto> test5(@PathVariable String id){
        return customerRepo.findById(id).map(this::toCustomerDto)
                    .map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
                    // orElseGet(): isEmpty => delay
                    // orElse() => right now
    }

    private CustomerDto toCustomerDto(Customer c) {
        return new CustomerDto(
                c.getCustomerid(),
                c.getCompanyName(),
                c.getOrders().stream().map(this::toOrderDto).toList()
        );
    }

    private OrderDto toOrderDto(Order o) {
        return new OrderDto(
                o.getOrderid(), 
                o.getOrderDate(),
                o.getOrderdetails().stream().map(this::toOrderDetailDto).toList()
                );
    }

    private OrderDetailDto toOrderDetailDto(OrderDetail od) {
        return new OrderDetailDto(
                od.getUnitPrice(), 
                od.getQuantity(), 
                od.getProduct().getProductName());
    }

    
}
