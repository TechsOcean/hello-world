package com.chandan.hibernateentitymappings.mysql;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "college")
@Data
@NoArgsConstructor
@ToString
public class College {

    @Id
//    @SequenceGenerator(name = "college_sequence", sequenceName = "college_sequence")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;


}
