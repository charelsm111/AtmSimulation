package com.app.atmsimulation;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

public class Main implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) {
        Screen screen = new WelcomeScreen();
        screen.show();
    }
}
