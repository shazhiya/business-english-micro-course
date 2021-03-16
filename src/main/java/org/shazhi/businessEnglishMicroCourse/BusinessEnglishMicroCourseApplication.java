package org.shazhi.businessEnglishMicroCourse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJpaRepositories
@EnableJpaAuditing
@EnableTransactionManagement
@EntityScan(basePackages = "org.shazhi.businessEnglishMicroCourse.entity")
public class BusinessEnglishMicroCourseApplication {

    public static void main(String[] args) {
        SpringApplication.run(BusinessEnglishMicroCourseApplication.class, args);
    }

}
