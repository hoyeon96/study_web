package com.example.study_web.repository;

import com.example.study_web.model.entity.Category;
import com.example.study_web.model.entity.Partner;
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
public class PartnerRepositoryTest {
    @Autowired
    private PartnerRepository partnerRepository;

    @Test
    public void create(){
        Partner partner = new Partner();

//        partner.setCategoryId(2L);
        partner.setName("뉴발란스");
        partner.setStatus("test");
        partner.setAddress("서울특별시 test");
        partner.setCallCenter("02-000-5555");
        partner.setCreatedAt(LocalDateTime.now());
        partner.setCreatedBy("test01");

        Partner newPartner = partnerRepository.save(partner);
        System.out.println("new category info : " + newPartner);
    }

    @Test
    public void read(){
        Optional<Partner> partner = partnerRepository.findById(1L);
        System.out.println("partner info : " + partner);
    }

    @Test
    // 파트너사 전체 조회
    // ID NAME
    public void allRead(){
        List<Partner> partners = partnerRepository.findAll();
        for(Partner partner : partners){
            System.out.println(partner.getId() + " " + partner.getName());
        }
    }

    @Test
    public void update(){

        Optional<Partner> partner = partnerRepository.findById(1L);

        partner.ifPresent(selectPartner -> {
            selectPartner.setCallCenter("000-000-0000");
            selectPartner.setBusinessNumber("000-000-0000");
            selectPartner.setCeoName("이재용");
            selectPartner.setUpdatedAt(LocalDateTime.now());
            selectPartner.setUpdatedBy("updateTest");
            partnerRepository.save(selectPartner);
        });
    }

    @Test
    public void delete(){
        Optional<Partner> partner = partnerRepository.findById(8L);
        partner.ifPresent(selectPartner -> {
            partnerRepository.delete(selectPartner);
        });
    }
}
