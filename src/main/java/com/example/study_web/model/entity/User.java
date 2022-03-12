package com.example.study_web.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor // 모든 필드의 값을 파라미터로 받는 생성자를 만들어줌
@NoArgsConstructor  // 파라미터가 없는 기본 생성자를 생성해줌 /생성자를 빈 값으로 초기화
@Entity // JPA 객체 맵핑
@ToString(exclude = "orderGroupList")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String account;

    private String password;

    private String status;

    private String email;

    // @Column(name = "phone_number")
    private String phoneNumber;

    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime updatedAt;

    private String updatedBy;

    // 1:N 매칭 되는 1이 지칭하는 테이블 작업
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<OrderGroup> orderGroupList;
}

