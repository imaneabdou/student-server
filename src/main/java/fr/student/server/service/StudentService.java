package fr.student.server.service;

import fr.student.server.domain.Student;
import fr.student.server.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    public final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> findAll(){
        return studentRepository.findAll();
    }

    public Student findById(Integer id){
        return studentRepository.findById(id).orElse(null);
    }

    public Student createStudent(Student studentToCreate){
        return studentRepository.save(studentToCreate);
    }

    public Student updateStudent(Student student) {
        Student studentToUpdate = findById(student.getId());
        if (studentToUpdate != null) {
            studentToUpdate.setName(student.getName());
            studentToUpdate.setAge(student.getAge());
            return studentRepository.save(studentToUpdate);
        }
        return null;
    }

    public boolean deleteStudent(Integer id) {
        Student studentToDelete = findById(id);
        if (studentToDelete != null) {
            studentRepository.delete(studentToDelete);
            return true;
        }
        return false;
    }
}