package com.hachim.trivialCube;

/**
 * TrivialCube : computing an OLAP Cube
 *
 */
public class App {

    public static void main(String[] args) {
        Cube c = new Cube(args);

        System.out.println("Computing cube starts...");
        System.out.println("Aggragate function used : " + c.getOptions().aggregateFunction);
        System.out.println("Input file path : " + c.getOptions().inputFile);
        System.out.println("Values column id : " + c.getOptions().columnValuesId);       
        c.run();
    }

}
