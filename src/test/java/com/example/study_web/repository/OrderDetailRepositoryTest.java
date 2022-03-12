package com.example.study_web.repository;

import com.example.study_web.model.entity.OrderDetail;
import com.example.study_web.model.entity.Partner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Commit;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Commit
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    public void create(){
        OrderDetail orderDetail = new OrderDetail();
//        orderDetail.setOrderHistoryId(2L);
//        orderDetail.setItemId(4L);
        orderDetail.setStatus("test");
        orderDetail.setQuantity(1);
        orderDetail.setTotalPrice(5000000);
        orderDetail.setCreatedAt(LocalDateTime.now());
        orderDetail.setCreatedBy("test01");

        OrderDetail newOrderDetail = orderDetailRepository.save(orderDetail);
        Assertions.assertNotNull(newOrderDetail);
    }

    @Test
    public void read(){
        Optional<OrderDetail> orderDetail = orderDetailRepository.findById(1L);
        System.out.println("orderDetail info : " + orderDetail);
    }


    @Test
    public void update(){

        Optional<OrderDetail> orderDetail = orderDetailRepository.findById(1L);

        orderDetail.ifPresent(selectOrderDetail -> {
            selectOrderDetail.setArrivalDate(LocalDateTime.of(2022,3,17,12,00));
            selectOrderDetail.setUpdatedAt(LocalDateTime.now());
            selectOrderDetail.setUpdatedBy("updateTest");
            orderDetailRepository.save(selectOrderDetail);
        });
    }

    @Test
    public void delete(){
        Optional<OrderDetail> orderDetail = orderDetailRepository.findById(3L);
        orderDetail.ifPresent(selectOrderDetail -> {
            orderDetailRepository.delete(selectOrderDetail);
        });
    }
}
