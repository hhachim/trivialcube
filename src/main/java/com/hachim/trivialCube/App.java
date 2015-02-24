package com.hachim.trivialCube;

/**
 * Hello world!
 *
 */
public class App {
    public String inputFile;
    public static void main(String[] args) {
        System.out.println("Computing cube starts...");                
        String file = System.getProperty("file");
        String func = System.getProperty("func");
        
        System.out.println("Aggragate function used : " + func);
        
        if(func.equals("sum")) {            
            int column =  Integer.parseInt(System.getProperty("column"));
            System.out.println("Sum column id : " + column);
            System.out.println("Input file path : " + file);
            Cube c = new Cube(file, 9);                        
            c.run("sum",column);
        }        
    }
}
