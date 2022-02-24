package com.example.study_web.repository;

import com.example.study_web.model.entity.Item;
import com.example.study_web.model.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Commit;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@DataJpaTest    //  Jpa를 test할 수 있게 도와주는 어노테이션
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)    // 실제 데이터베이스에 테스트하기 위해
@Commit
public class UserRepositoryTest {
    @Autowired  // 의존성 주입
    private UserRepository userRepository;

    @Test   // 이 메소드는 테스트 대상 메소드라고 선언
    public void create(){
        // String sql = insert ~~~;
        User user = new User();

        // user.setId(); -> Auto increment -> sql error
        user.setAccount("test07");
        user.setEmail("test07@gmail.com");
        user.setPhoneNumber("010-0007-0017");
        user.setCreateAt(LocalDateTime.now());
        user.setCreateBy("test07");

        User newUser = userRepository.save(user);
        System.out.println("new user info : " + newUser);
    }

    @Test
    public void read(){
//        Optional<User> user = userRepository.findById(3L);
//        System.out.println("new user info : " + user);
        Optional<User> user = userRepository.findByAccount("test02");
        user.ifPresent(selectUser -> {
            selectUser.getOrderDetailList().stream().forEach(detail -> {
                Item item = detail.getItem();
                System.out.println(item);
            });
        });
    }

    @Test
    public void userTotalShoppingPrice() {
        // 아이디 검색 -> 아이디만 얼마나 샀는지
        // 출력 : 18번 김태훈 고객님의 쇼핑내역은 3건이고, 토탈 1800000원을 구매하셨습니다.
        // 출력 : 20번 윤호연 고객님의 쇼핑내역은 0건이고, 토탈 0원을 구매하셨습니다.
        // 출력 : 50번 고객님은 없는 정보 입니다.
        // 전체 조회(findAll ->
        // 출력 :
        // 1 김태훈 10000
        // 2 윤호연 15000
        // Top 구매력 3인
        // 1등 5번의 윤호연 고객님
        // 2등 20번의 김태훈 고객님
        // 3등 18번의 이승현 고객님
    }

    @Test
    public void update(){
        Optional<User> user = userRepository.findById(3L);
        user.ifPresent(selectUser-> {
            selectUser.setAccount("aaaaa");
            selectUser.setUpdateAt(LocalDateTime.now());
            selectUser.setUpdateBy("updateTest");
            userRepository.save(selectUser);
        });
    }

    @Test
    public void delete(){
        Optional<User> user = userRepository.findById(3L);


        Assertions.assertTrue(user.isPresent());
        user.ifPresent(selectUser-> {
            userRepository.delete(selectUser);
        });

        Optional<User> deleteUser = userRepository.findById(3L);
        Assertions.assertFalse(deleteUser.isPresent());
    }
}
