package com.example.study_web.repository;


import com.example.study_web.model.entity.Category;
import com.example.study_web.model.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Commit
public class CategoryRepositoryTest {
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void create() {
        Category category = new Category();

        category.setType("test1");
        category.setTitle("건강식품");
        category.setCreatedAt(LocalDateTime.now());
        category.setCreatedBy("");

        Category newCategory = categoryRepository.save(category);
        System.out.println("new category info : " + newCategory);
    }

    @Test
    public void read(){
        Optional<Category> category = categoryRepository.findById(1L);
        System.out.println("category info : " + category);
    }

    @Test
    // 카테고리 전체 조회
    // ID TYPE TITLE
    public void allRead(){
        List<Category> categorys = categoryRepository.findAll();
        for(Category category : categorys){
            System.out.println(category.getId() + " " + category.getType() + " " + category.getTitle());
        }
    }

    @Test
    public void update(){
        // Optional<T>는 null이 올 수 있는 값을 감싸는 Wrapper 클래스

        Optional<Category> category = categoryRepository.findById(4L);

        // ifPresent -> 위의 Optional~ 객체의 값이 존재할 경우에만 실행될 로직을 람다식으로
        category.ifPresent(selectCategory -> {
            selectCategory.setType("test2");
            selectCategory.setUpdatedAt(LocalDateTime.now());
            selectCategory.setUpdatedBy("updateTest");
            categoryRepository.save(selectCategory);
        });
    }

    @Test
    public void delete(){
        Optional<Category> category = categoryRepository.findById(1L);
        category.ifPresent(selectCategory-> {
            categoryRepository.delete(selectCategory);
        });
    }

}
