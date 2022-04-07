package com.example.demo.student;

import com.example.demo.student.exception.BadRequestException;
import com.example.demo.student.exception.StudentNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public void addStudent(Student student) {
        //check if email is taken
        if (studentRepository.selectExistsEmail(student.getEmail())) {
            throw new BadRequestException("Email " + student.getEmail() + " already taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        //Check if student exists
        if (!studentRepository.existsById(studentId)) {
            throw new StudentNotFoundException(
                    "Student with id " + studentId + " does not exists"
            );
        }
        studentRepository.deleteById(studentId);
    }
}
