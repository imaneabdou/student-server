package fr.student.server.web.rest;

import fr.student.server.domain.Student;
import fr.student.server.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.TestPropertySource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class StudentResourceIT {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    @Transactional
    void createStudent() throws Exception {
        //Given
        int databaseSizeBeforeCreate = studentRepository.findAll().size();

        //When
        Student newStudent = new Student("Lorenzo", 24);
        studentRepository.save(newStudent);

        //Then
        List<Student> allStudents = studentRepository.findAll();
        assertThat(allStudents).hasSize(databaseSizeBeforeCreate + 1);
        assertThat(allStudents).contains(newStudent);
    }

    @Test
    @Transactional
    void getStudent() throws Exception {
        //Given
        Student createdStudent = studentRepository.save(new Student("Charles", 17));

        //When
        Student retrievedStudent = studentRepository.findById(createdStudent.getId()).orElse(null);

        //Then
        assertThat(retrievedStudent).isNotNull();
        assertThat(retrievedStudent.getName()).isEqualTo("Charles");
        assertThat(retrievedStudent.getAge()).isEqualTo(17);
    }

    @Test
    @Transactional
    void updateStudent() throws Exception {
        //Given
        Student createdStudent = studentRepository.save(new Student("Bruce Jenner", 56));

        //When
        createdStudent.setName("Caitlyn Jenner");
        studentRepository.save(createdStudent);

        //Then
        Student updatedStudent = studentRepository.findById(createdStudent.getId()).orElse(null);
        assertThat(updatedStudent).isNotNull();
        assertThat(updatedStudent.getName()).isEqualTo("Caitlyn Jenner");
        assertThat(updatedStudent.getId()).isEqualTo(createdStudent.getId());
        assertThat(updatedStudent.getAge()).isEqualTo(56);
    }

    @Test
    @Transactional
    void deleteStudent() throws Exception {
        //Given
        Student studentToDelete = studentRepository.save(new Student("Samra", 27));
        int databaseSizeBeforeDelete = studentRepository.findAll().size();

        //When
        studentRepository.deleteById(studentToDelete.getId());

        //Then
        List<Student> allStudents = studentRepository.findAll();
        assertThat(allStudents).hasSize(databaseSizeBeforeDelete - 1);
        assertThat(allStudents).doesNotContain(studentToDelete);
    }
}
