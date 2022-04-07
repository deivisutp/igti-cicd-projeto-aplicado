package com.example.demo.student;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void itShouldCheckIfEmailExists() {
        //given
        Student student = new Student(
                "Deivis",
                "deivisutp@gmail.com",
                Gender.MALE
        );
        underTest.save(student);

        //when
        boolean exists = underTest.selectExistsEmail("deivisutp@gmail.com");

        //then
        assertThat(exists).isTrue();
    }

    @Test
    void itShouldCheckIfStudenttExistsEmailDoesNotExists() {
        //given
        Student student = new Student(
                "Deivis",
                "deivisutp2@gmail.com",
                Gender.MALE
        );
        underTest.save(student);

        //when
        boolean exists = underTest.selectExistsEmail("deivisutp@gmail.com");

        //then
        assertThat(exists).isFalse();
    }
}