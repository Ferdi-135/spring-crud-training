package ch.benedict.m223.lektion4.springjpademo.dto;

public class StudentPOJO {

    private String vorname;
    private String nachname;
    private String geburtstag;

    public String getVorname() {
        return vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public String getGeburtstag() {
        return geburtstag;
    }

    @Override
    public String toString() {
        return "StudentPOJO{" +
                "vorname='" + vorname + '\'' +
                ", nachname='" + nachname + '\'' +
                ", geburtstag='" + geburtstag + '\'' +
                '}';
    }

}
