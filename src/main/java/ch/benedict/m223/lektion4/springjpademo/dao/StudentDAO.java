package ch.benedict.m223.lektion4.springjpademo.dao;

import ch.benedict.m223.lektion4.springjpademo.entity.StudentEntity;

/**
 * DAO: Data Access Object
 */
public interface StudentDAO {

    void saveStudent(StudentEntity student);

    void deleteStudent(StudentEntity student);

    StudentEntity getStudentById(int key);
}

