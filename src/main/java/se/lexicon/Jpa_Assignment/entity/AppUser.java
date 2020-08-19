package se.lexicon.Jpa_Assignment.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class AppUser {
    //Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;

    //Constructor 1
    public AppUser(int id, String firstName, String lastName, String email) {
        this.id = id;
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
    }

    //Constructor 2
    public AppUser(String firstName, String lastName, String email)
    {
        this(0,firstName,lastName,email);
    }

    //Empty constructor
    public AppUser() { }

    //Getters & Setters (sans setID)
    public int getId() { return id; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    //Eqyals & Hashcode (Makes Equals compare Hashcode and not memory adress)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUser appUser = (AppUser) o;
        return id == appUser.id &&
                firstName.equals(appUser.firstName) &&
                lastName.equals(appUser.lastName) &&
                email.equals(appUser.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email);
    }

    //ToString override
    @Override
    public String toString() {
        return "AppUser{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
