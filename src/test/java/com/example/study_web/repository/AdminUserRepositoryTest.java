package com.example.study_web.repository;

import com.example.study_web.model.entity.AdminUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Commit;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Commit
public class AdminUserRepositoryTest {
    @Autowired
    private AdminUserRepository adminUserRepository;

    @Test
    public void create(){
        AdminUser adminUser = new AdminUser();

        adminUser.setAccount("master2");
        adminUser.setPassword("javahard");
        adminUser.setRole("관리자");
        adminUser.setStatus("test");
        adminUser.setCreatedAt(LocalDateTime.now());
        adminUser.setCreatedBy("test01");

        AdminUser newAdminUser = adminUserRepository.save(adminUser);
        System.out.println("new AdminUser info : " + newAdminUser);
    }

    @Test
    public void read(){
        Optional<AdminUser> adminUser = adminUserRepository.findById(1L);
        System.out.println("AdminUser info : " + adminUser);
    }

    @Test
    public void update(){
        Optional<AdminUser> adminUser = adminUserRepository.findById(1L);
        adminUser.ifPresent(selectAdminUser -> {
            selectAdminUser.setPassword("rhksflwkdlqslek");
            selectAdminUser.setPasswordUpdatedAt(LocalDateTime.now());
            selectAdminUser.setUpdatedAt(LocalDateTime.now());
            selectAdminUser.setUpdatedBy("system");
        });
    }

    @Test
    public void delete(){
        Optional<AdminUser> adminUser = adminUserRepository.findById(2L);
        adminUser.ifPresent(selectAdminUser -> {
            adminUserRepository.delete(selectAdminUser);
        });
    }
}
