package com.santos.demo.rest;

import com.santos.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> theStudents;

    //define @PostConstruct to load the student data....only once!

    @PostConstruct
    public void loadData(){

        theStudents = new ArrayList<>();

        theStudents.add(new Student("Santos" , "Bista"));
        theStudents.add(new Student("Claudio" , "Echheveri" ));
        theStudents.add(new Student("Erling" , "Haaland"));
    }

    //define endpoints for "/students" --> return a list of students
    @GetMapping("/students")
    public List<Student> getStudents(){

        return theStudents;
    }

    //define another endpoint fo "/students/{studentID}" --> return student at index
    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId){

        //just index into the list...keeping it simple for now

        //check the studentId again list size

        if( (studentId>= theStudents.size())  ||  (studentId<0) ){
            throw new StudentNotFoundException("Student id not found - " + studentId);
        }
        return theStudents.get(studentId);
    }


}
