package org.example;

import org.openqa.selenium.chrome.ChromeDriver;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        ChromeDriver driver =new ChromeDriver();
        driver.get("https://one.co.il/");
    }
}