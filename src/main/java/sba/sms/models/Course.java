package sba.sms.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Course is a POJO, configured as a persistent class that represents (or maps to) a table
 * name 'course' in the database. A Course object contains fields that represent course
 * information and a mapping of 'courses' that indicate an inverse or referencing side
 * of the relationship. Implement Lombok annotations to eliminate boilerplate code.
 */

@Entity
@Table(name = "course")
@Data // Lombok annotation for getters, setters, equals, and hashCode
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Integer id;

    @Column(name = "name", nullable = false, length = 100)
    String name;

    @Column(name = "instructor", nullable = false, length = 100)
    String instructor;

    @ManyToMany(mappedBy = "courses")
    Set<Student> students = new LinkedHashSet<>();

    // Constructor excluding collections for convenience
    public Course(String name, String instructor) {
        this.name = name;
        this.instructor = instructor;
    }

    // toString excluding collections to avoid infinite loops
    @Override
    public String toString() {
        return "Course{id=" + id + ", name='" + name + '\'' + ", instructor='" + instructor + "'}";
    }
}