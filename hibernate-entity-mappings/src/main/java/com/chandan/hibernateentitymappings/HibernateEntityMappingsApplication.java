package com.chandan.hibernateentitymappings;

import com.chandan.hibernateentitymappings.config.PostgreSQLConfig;
import com.chandan.hibernateentitymappings.entities.Student;
import com.chandan.hibernateentitymappings.entities.StudentDetails;
import com.chandan.hibernateentitymappings.repository.StudentDetailRepository;
import com.chandan.hibernateentitymappings.repository.StudentRepository;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

@EnableConfigurationProperties(PostgreSQLConfig.class)
@EnableRabbit
@SpringBootApplication
public class HibernateEntityMappingsApplication {

    private static final Logger log = LogManager.getLogger(HibernateEntityMappingsApplication.class);

    private static HikariDataSource hikariDataSource;

    @Autowired
    public HibernateEntityMappingsApplication(HikariDataSource hikariDataSource) {
        HibernateEntityMappingsApplication.hikariDataSource = hikariDataSource;
    }

    public static void main(String[] args) {
       /* AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext();
        StudentDemoRepository studentDemoRepository =
                applicationContext.getBean(StudentDemoRepository.class);
        studentDemoRepository.save(new Student("Hello", "Hi", "chandansinghcse1@gmail.com",
                new StudentDetails("Public Inter College", 20)));
        Student student = studentDemoRepository.findStudentById(Long.valueOf("29"));
        System.out.println(student);*/
        SpringApplication springApplication = new SpringApplication(HibernateEntityMappingsApplication.class);
        Map<String, Object> defProperties = new HashMap<>();
        defProperties.put("spring.profiles.default", "prod");
        springApplication.setDefaultProperties(defProperties);
        springApplication.run(args);

        System.out.println(" ========================== ");
        System.out.println(hikariDataSource.getDataSourceProperties());
        System.out.println(" ========================== ");

  /*      ConfigurableApplicationContext applicationContext =
                SpringApplication.run(HibernateEntityMappingsApplication.class, args);
        applicationContext.se*/
    }

    //@Bean
    public CommandLineRunner run(StudentRepository studentRepository, StudentDetailRepository detailRepository) {
        return args -> {
            log.info(" Saving students ");

            Student student1 = new Student("Chandan", "Singh", "chandan.singh@verifone.com", new StudentDetails("APJ Abdul Kalam University", 10));
            studentRepository.save(student1);
            Student student2 = new Student("Nandan", "Singh", "nandan.singh@verifone.com", new StudentDetails("Delhi University", 15));
            studentRepository.save(student2);
            Student student3 = new Student("Abhishek", "Singh", "abhishek.singh@verifone.com", new StudentDetails("UP Prayagraj", 20));
            studentRepository.save(student3);

            log.info("Student found with findAll()");
            studentRepository.findAll().forEach(student -> {
                log.info("Customer.toString() " + student.toString());
            });

            log.info(" Fetch Student by last name");
            studentRepository.findByLastname("Rajput").forEach(student -> {
                log.info(" Found student with last name Rajput " + student.getFirstname() + " " + student.getStudent_id());
            });

            log.info(" Thanks ");
        };

    }

}
