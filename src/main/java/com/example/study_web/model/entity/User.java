package com.example.study_web.model.entity;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor // 모든 필드의 값을 파라미터로 받는 생성자를 만들어줌
@NoArgsConstructor  // 파라미터가 없는 기본 생성자를 생성해줌 /생성자를 빈 값으로 초기화
@Entity // JPA 객체 맵핑
@ToString(exclude = "orderGroupList")
@Builder
@Accessors(chain = true)       // 일일이 setMethod를 여러 줄로 생성할 필요 없이 Chain형태로 이어서 원하는 setMethod를 생성할 수 있다.
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

    @CreatedDate
    private LocalDateTime createdAt;

    @CreatedBy
    private String createdBy;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @LastModifiedBy
    private String updatedBy;

    // User 1 : N OrderGroup
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<OrderGroup> orderGroupList;
}

