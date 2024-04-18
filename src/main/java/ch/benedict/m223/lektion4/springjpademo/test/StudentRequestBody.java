package ch.benedict.m223.lektion4.springjpademo.test;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StudentRequestBody {

    @JsonProperty("vorname")
    String vorname;

    @JsonProperty("nachname")
    String nachname;

    @JsonProperty("geburtstag")
    String geburtstag;

    public StudentRequestBody(String vorname, String nachname, String geburtstag) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.geburtstag = geburtstag;
    }

    @Override
    public String toString() {
        return "StudentRequestBody{" +
                "vorname='" + vorname + '\'' +
                ", nachname='" + nachname + '\'' +
                ", geburtstag='" + geburtstag + '\'' +
                '}';
    }
}
