package com.doctolib.doctobootplayground.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.doctolib.doctobootplayground.repositories")
@Configuration
public class DatabaseConfiguration {}
