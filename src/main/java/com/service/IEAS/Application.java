/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.IEAS;

import java.util.Date;
import java.util.TimeZone;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 *
 * @author Tanyong
 */
@SpringBootApplication
@EnableAsync
public class Application implements CommandLineRunner {

    @Value("${spring.profiles.active}")
    private String activeProfile;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(25);
        return executor;
    }

    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("i18n/messages");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setUseCodeAsDefaultMessage(true);
        return messageSource;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("|------------ Details ------------");
        System.out.println();
        System.out.println("|--- Environment : " + activeProfile);
        System.out.println();
        System.out.println("|--- Default Timezone : " + TimeZone.getDefault().getID() + " at Date : " + new Date());
        System.out.println();
        System.out.println("|---------------------------------");

    }
}
