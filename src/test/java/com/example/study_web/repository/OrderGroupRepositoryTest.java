package com.example.study_web.repository;

import com.example.study_web.model.entity.OrderGroup;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Commit;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Commit
public class OrderGroupRepositoryTest {
    @Autowired
    private OrderGroupRepository orderGroupRepository;

    @Test
    public void create(){
        OrderGroup orderGroup = new OrderGroup();

//        orderGroup.setUserId(3L);
        orderGroup.setStatus("test");
        orderGroup.setOrderType("일괄배송");
        orderGroup.setRevAddress("서울특별시 test");
        orderGroup.setPaymentType("계좌이체");
        orderGroup.setTotalPrice(15000000);
        orderGroup.setTotalQuantity(3);
        orderGroup.setCreatedAt(LocalDateTime.now());
        orderGroup.setCreatedBy("test01");

        OrderGroup newOrderGroup = orderGroupRepository.save(orderGroup);
        System.out.println("new orderGroup info : " + newOrderGroup);
    }

    @Test
    public void read(){
        Optional<OrderGroup> orderGroup = orderGroupRepository.findById(1L);
        System.out.println("orderGroup info : " + orderGroup);
    }

    @Test
    public void update(){

        Optional<OrderGroup> partner = orderGroupRepository.findById(1L);

        partner.ifPresent(selectOrderGroup -> {
            selectOrderGroup.setRevName("윤호연");
            selectOrderGroup.setUpdatedAt(LocalDateTime.now());
            selectOrderGroup.setUpdatedBy("updateTest");
            orderGroupRepository.save(selectOrderGroup);
        });
    }

    @Test
    public void delete(){
        Optional<OrderGroup> partner = orderGroupRepository.findById(3L);
        partner.ifPresent(selectOrderGroup -> {
            orderGroupRepository.delete(selectOrderGroup);
        });
    }
}
