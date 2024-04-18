package ch.benedict.m223.lektion4.springjpademo.dao;

import ch.benedict.m223.lektion4.springjpademo.entity.StudentEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Bean/Component
 * Stellt sicher, dass nur eine Instanz exisitert.
 * Stichwort: Singleton (Design Patterns)
 */
@Repository
public class StudentDAOImplementation implements StudentDAO{

    private EntityManager entityManager;

    /**
     * Dependency Injection via Constructor
     * @param entityManager
     */
    @Autowired
    public StudentDAOImplementation(EntityManager entityManager) {
        System.out.println("init StudentDAOImplementation(" + entityManager);
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void saveStudent(StudentEntity student) {
        entityManager.persist(student);
    }

    @Override
    @Transactional
    public void deleteStudent(StudentEntity student) {
        entityManager.remove(student);
    }

    @Override
    public StudentEntity getStudentById(int key) {
        return entityManager.find(StudentEntity.class, key);
    }

    @Transactional
    public void updateStudent(StudentEntity studentUpdate){entityManager.merge(studentUpdate);};

    @Transactional
    public void deleteTableContents(){
        // delete contents
        entityManager.createQuery("DELETE FROM StudentEntity s").executeUpdate();
    }

    @Transactional
    public void resetAutoIncrement(){
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        String sql = "ALTER TABLE student AUTO_INCREMENT = 1";
        entityManager.createNativeQuery(sql).executeUpdate();
        transaction.commit();
    }

    @Transactional
    public List<StudentEntity> getAllStudentViaQuery(){
        String jpql = "SELECT s FROM StudentEntity s";
        TypedQuery<StudentEntity> query = entityManager.createQuery(jpql, StudentEntity.class);
//        for(StudentEntity dao : query.getResultList()){
//            System.out.println(dao);
//        }
        return query.getResultList();

    }

    /**
     * maybe for future use
     */
    public void resetAutoIncrementByGBT() {
        // Begin a transaction
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        // Create a native SQL query to reset auto_increment
        String sql = "ALTER TABLE student AUTO_INCREMENT = 1";

        // Execute the native SQL query
        entityManager.createNativeQuery(sql).executeUpdate();

        // Commit the transaction
        transaction.commit();
    }

}
