package com.srelife.jenkins.practice; // El paquete debe coincidir con la ruta de carpetas

public class WebApp {
    public static String getGreeting() {
        return "Hello Devops People this's jenkis!";
    }

    public static void main(String[] args) {
        System.out.println(getGreeting());
    }
}

