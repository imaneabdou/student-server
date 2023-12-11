package fr.student.server.web.rest;

import java.util.List;
import fr.student.server.domain.Student;
import fr.student.server.service.StudentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class StudentResource {

    public final StudentService studentService;

    public StudentResource(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public List<Student> getAllStudents(){
        return studentService.findAll();
    }

    @GetMapping("/students/{id}")
    public Student getStudent(@PathVariable Integer id){
        return studentService.findById(id);
    }

    @PostMapping("/students")
    public Student createStudent(@RequestBody Student student){
        return studentService.createStudent(student);
    }

    @PutMapping("/students")
    public Student updateStudent(@RequestBody Student student){
        return studentService.updateStudent(student);
    }

    @DeleteMapping("/students/{id}")
        public String deleteStudent(@PathVariable Integer id){
        boolean hasDeleteSucceeded = studentService.deleteStudent(id);
        return hasDeleteSucceeded ? "Student with id " +id+ " succesffully deleted !"
                : "Delete failed. Make sure that the student with id " +id+ " exists.";
    }
}