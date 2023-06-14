package com.chandan.hibernateentitymappings.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "student", uniqueConstraints = {
        @UniqueConstraint(name = "unique_constraints_email", columnNames = {"email"})
})
public class Student {

    @Id
    @SequenceGenerator(name = "student_sequence", sequenceName = "student_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_sequence")
    private int student_id;

    @NotBlank(message = "lastname can not be null or blank")
    private String lastname;

    @NotNull(message = "firstname can not be null or blank")
    private String firstname;

    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            flags = Pattern.Flag.CASE_INSENSITIVE, message = "Email must contain @ and domain")
    private String email;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(
            name = "detail_id", referencedColumnName = "detail_id"
    )
    private StudentDetails studentDetails;

    public Student(String firstname, String lastname, String email, StudentDetails studentDetails) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.studentDetails = studentDetails;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + student_id +
                ", first_name='" + firstname + '\'' +
                ", last_name='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", studentDetails=" + studentDetails +
                '}';
    }
}
