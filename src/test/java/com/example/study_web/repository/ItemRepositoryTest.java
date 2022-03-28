package com.example.study_web.repository;

import com.example.study_web.model.entity.Item;
import com.example.study_web.model.entity.Partner;
import com.example.study_web.model.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Commit;

import java.time.LocalDateTime;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Commit
public class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void create(){
        Item item = new Item();

//        item.setPartnerId(4L);
        item.setName("GARDE");
        item.setStatus("UNREGISTERED");
        item.setTitle("백팩");
        item.setPrice(250000);
        item.setCreatedAt(LocalDateTime.now());
        item.setCreatedBy("test01");

        Item newItem = itemRepository.save(item);
        Assertions.assertNotNull(newItem);
    }

    @Test
    public void read(){
        Optional<Item> item = itemRepository.findByName("ALTAR");
        System.out.println("item info : " + item);
        Assertions.assertTrue(item.isPresent());
    }

}
