package com.example.study_web.repository;

import com.example.study_web.model.entity.Item;
import com.example.study_web.model.entity.OrderDetail;
import com.example.study_web.model.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        user.setAccount("양정호");
        user.setEmail("test04@gmail.com");
        user.setPhoneNumber("010-4444-4444");
        user.setCreateAt(LocalDateTime.now());
        user.setCreateBy("test01");

        User newUser = userRepository.save(user);
        System.out.println("new user info : " + newUser);
    }

    @Test
    public void read(){
//        Optional<User> user = userRepository.findById(3L);
//        System.out.println("new user info : " + user);
        Optional<User> user = userRepository.findByAccount("윤호연");
        user.ifPresent(selectUser -> {
            // 선택된 유저 의 getOrderDetailList 정보
            // stream() -> 추가된 컬렉션의 저장 요소를 하나씩 참조해서 람다식으로 처리할 수 있도록 해주는 반복자
            selectUser.getOrderDetailList().stream().forEach(detail -> {
                Item item = detail.getItem();
                System.out.println(item);
            });
        });
    }

    @Test
    public void userTotalShoppingPriceOutput() {

        // 입력 창 대신 사용
        String account = "김태훈";

        Optional<User> user = userRepository.findByAccount(account);

        if(user.isPresent()){
            user.ifPresent(selectUser -> {
                // 해당 고객의 쇼핑내역 몇건인지
                // selectUser.getOrderDetailList().stream().count() long타입이길래 int로 타입 변환
                int count = (int) selectUser.getOrderDetailList().stream().count();

                // 콘솔 출력 화면
                System.out.println(account + "님의 쇼핑내역은 " + count + "건이고, 토탈 " + userTotalShoppingPrice(account) + "원을 구매하셨습니다.");
            });

            // 고객 정보가 없을 경우 출력 화면
        } else {
            System.out.println(account + "님은 없는 정보 입니다.");
        }
    }

    // 한 고객의 총 구매 가격 계산 함수
    @Test
    public int userTotalShoppingPrice(String account) {
        Optional<User> user = userRepository.findByAccount(account);

        // 합계를 위한 품목 당 가격 배열 선언
        ArrayList<Integer> sumPriceList = new ArrayList<Integer>();

        // 해당 고객의 총 구매 가격 합계 초기값(0원) 설정
        int totalShoppingPrice = 0;

        // 해당 고객의 구매이력에서 item 테이블의 price 값 추출해 배열에 저장
        user.ifPresent(selectUser -> {
            selectUser.getOrderDetailList().stream().forEach(detail -> {
                int price = detail.getItem().getPrice();
                sumPriceList.add(price);
            });
        });

        // 품목 당 가격 배열의 값을 합산
        for(int i = 0; i < sumPriceList.size(); i++){
            totalShoppingPrice += sumPriceList.get(i);
        }

        // 합계 값 리턴
        return totalShoppingPrice;

    }

    // 전체 조회
    @Test
    public void allUserSearch(){

        List<User> users = userRepository.findAll(Sort.by(Sort.Direction.ASC,"id"));
        for(User user : users){
            System.out.println(user.getId() + " " + user.getAccount() + " " + userTotalShoppingPrice(user.getAccount()) + "원");
        }
    }

    // TOP 3 구매순위 조회
    @Test
    public void top3Shopping(){

        // Top 구매력 3인 예시
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
