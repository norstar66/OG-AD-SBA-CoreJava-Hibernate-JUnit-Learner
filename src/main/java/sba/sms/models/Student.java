package sba.sms.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;


/**
 * Student is a POJO, configured as a persistent class that represents (or maps to) a table
 * named 'student' in the database. A Student object contains fields that represent student
 * login credentials and a join table containing a registered student's email and course(s)
 * data. The Student class can be viewed as the owner of the bi-directional relationship.
 */
@Entity
@Table(name = "student")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Student {
    @Id
    @Column(name = "email", nullable = false, length = 50)
    String email;

    @Column(name = "name", nullable = false, length = 100)
    String name;

    @Column(name = "password", nullable = false, length = 100)
    String password;

    @ManyToMany
    @JoinTable(
            name = "student_course",
            joinColumns = @JoinColumn(name = "student_email"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    Set<Course> courses = new LinkedHashSet<>();

    // Constructor excluding collections for convenience
    public Student(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    // toString excluding collections to avoid infinite loops
    @Override
    public String toString() {
        return "Student{email='" + email + '\'' + ", name='" + name + '\'' + ", password='" + password + "'}";
    }
}


