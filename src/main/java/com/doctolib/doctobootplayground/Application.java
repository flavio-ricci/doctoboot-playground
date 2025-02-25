package com.doctolib.doctobootplayground;

import com.doctolib.doctoboot.autoconfigure.DoctoBootApplication;
import org.springframework.boot.SpringApplication;

@DoctoBootApplication(serviceName = "doctoboot-playground")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
