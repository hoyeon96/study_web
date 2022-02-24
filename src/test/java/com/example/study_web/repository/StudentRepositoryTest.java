package com.example.study_web.repository;


import com.example.study_web.model.entity.Student;
import com.example.study_web.model.entity.User;
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
public class StudentRepositoryTest {
    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void create() {
        Student student = new Student();

        student.setName("윤호연");
        student.setAge(27);
        student.setMajor("E-Business학과");
        student.setSubMajor("경영학과");
        student.setCreateAt(LocalDateTime.now());
        student.setCreateBy("연습test");

        Student newStudent = studentRepository.save(student);
        System.out.println("new student info : " + newStudent);
    }

    @Test
    public void read(){
        Optional<Student> student = studentRepository.findById(1L);
        System.out.println("new student info : " + student);
    }

    @Test
    public void update(){
        // Optional<T>는 null이 올 수 있는 값을 감싸는 Wrapper 클래스

        Optional<Student> student = studentRepository.findById(1L);

        // ifPresent -> 위의 Optional~ 객체의 값이 존재할 경우에만 실행될 로직을 람다식으로
        student.ifPresent(selectStudent -> {
            selectStudent.setSubMajor("SWP");
            selectStudent.setUpdateAt(LocalDateTime.now());
            selectStudent.setUpdateBy("updateTest");
            studentRepository.save(selectStudent);
        });
    }

    @Test
    public void delete(){
        Optional<Student> student = studentRepository.findById(1L);
        student.ifPresent(selectUser-> {
            studentRepository.delete(selectUser);
        });
    }

}
