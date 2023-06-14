package com.chandan.hibernateentitymappings.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@NoArgsConstructor
@ToString
@Entity
@Table(name = "student_details")
public class StudentDetails {

    @Id
    @SequenceGenerator(name = "student_details_id_sequence", sequenceName = "student_details_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_details_id_sequence")
    private int detail_id;

    @Column(name = "COLLEGE")
    private String college;

    @Column(name = "NO_OF_PROBLEMS_SOLVED")
    @Min(message = "Field Value must be between 1 and 20", value = 1L)
    @Max(20)
    private int noOfProblemsSolved;

    public StudentDetails(String college, int noOfProblemsSolved) {
        this.college = college;
        this.noOfProblemsSolved = noOfProblemsSolved;
    }

}
