package ch.benedict.m223.lektion4.springjpademo.entity;

import jakarta.persistence.*;

/**
 * O/R Mapper = Object Relational Mapper.
 * "Mappt mein Java-Object auf relationale Datenbank."
 *
 * import jakarta.persistence.Entity;
 * -> Spring Data JPA (Jakarta Persistence API)
 */
@Entity
@Table(name="student") // spricht MySql Tabelle 'student' an
public class StudentEntity {

    @Id //PRIMARY KEY
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="vorname")
    private String vorname;

    @Column(name="nachname")
    private String nachname;

    @Column(name="geburtstag")
    private String geburtstag;

    public StudentEntity(){}

    public StudentEntity(String vorname, String nachname, String geburtstag) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.geburtstag = geburtstag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getGeburtstag() {
        return geburtstag;
    }

    public void setGeburtstag(String geburtstag) {
        this.geburtstag = geburtstag;
    }

    @Override
    public String toString() {
        return "StudentEntity{" +
                "id=" + id +
                ", vorname='" + vorname + '\'' +
                ", nachname='" + nachname + '\'' +
                ", geburtstag='" + geburtstag + '\'' +
                '}';
    }

}
