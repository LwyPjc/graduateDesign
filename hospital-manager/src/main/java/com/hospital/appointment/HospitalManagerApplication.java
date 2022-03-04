package com.hospital.appointment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class HospitalManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(HospitalManagerApplication.class, args);
    }

}
