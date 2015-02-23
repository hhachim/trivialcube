package com.hachim.trivialCube;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        Cube c = new Cube("table.txt", 9);
        c.run("sum",3);
    }
}
