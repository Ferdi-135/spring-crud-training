package ch.benedict.m223.lektion4.springjpademo.controller;

import ch.benedict.m223.lektion4.springjpademo.dao.StudentDAOImplementation;
import ch.benedict.m223.lektion4.springjpademo.dto.StudentPOJO;
import ch.benedict.m223.lektion4.springjpademo.entity.StudentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class StudentController {

    private StudentDAOImplementation sdi;

    /**
     * Kommuniziert an Spring Container, dass ich hier
     * die StudentDAOImplementation.java hier verwenden möchte.
     * @param sdi
     */
    @Autowired
    public StudentController(StudentDAOImplementation sdi) {
        System.out.println("init student controller");
        this.sdi = sdi;
    }

    @RequestMapping("create_student")
    public StudentEntity createStudent(@RequestBody StudentPOJO requestStudent){
        System.out.println("CONTROLLER: received [" + requestStudent + "]");
        StudentEntity studentEntity = new StudentEntity(
          requestStudent.getVorname(),
          requestStudent.getNachname(),
                requestStudent.getGeburtstag()
        );
        System.out.println("CONTROLLER: storing [" + studentEntity + "]...");
        sdi.saveStudent(studentEntity);
        return studentEntity;
    }

    @RequestMapping("read_student/{id}")
    public StudentEntity getStudent(@PathVariable int id){
        StudentEntity student = sdi.getStudentById(id);
        return student;
    }

    @RequestMapping("read_student_simple/{id}")
    public StudentEntity getStudentSimple(@PathVariable int id){
        return sdi.getStudentById(id);
    }

    @RequestMapping("update_student/{id}")
    public StudentEntity updateStudent(@RequestBody StudentPOJO student, @PathVariable int id) throws InterruptedException {
        StudentEntity oldStudentData = sdi.getStudentById(id);
        StudentEntity studentUpdate = new StudentEntity(
                student.getVorname(),
                student.getNachname(),
                student.getGeburtstag());
        studentUpdate.setId(id);
        String summary = "updated student: " + oldStudentData + " to: " + studentUpdate;
        System.out.println("CONTROLLER: updating [" + summary + "]...");
        sdi.updateStudent(studentUpdate);
        return studentUpdate;
    }

    @RequestMapping("delete_student/{id}")
    public StudentEntity deleteStudent(@PathVariable int id){
        StudentEntity oldStudentData = sdi.getStudentById(id);
        System.out.println("CONTROLLER: deleted [" + oldStudentData + "]...");
        sdi.deleteStudent(oldStudentData);
        return oldStudentData;
    }

    @RequestMapping("reset-table")
    public String resetTable(){
        sdi.deleteTableContents();
        return "{}";
    }

    @RequestMapping("read-table")
    public List<StudentEntity> readTable(){
        return sdi.getAllStudentViaQuery();
    }





}
