package com.hachim.trivialCube;

import java.util.ArrayList;

/**
 *
 * @author hachim
 */
public class Combination {

    public static String[] dims;
    public static int dimIndice = 0;

    // print all subsets that take k of the remaining elements, with given prefix 
    public static void generate(String prefix, String elements, int k) {
        if (k == 0) {
            //System.out.print(prefix);
            dims[dimIndice] = prefix;
            dimIndice++;

            return;
        }
        for (int i = 0; i < elements.length(); i++) {
            generate(prefix + elements.charAt(i), elements.substring(i + 1), k - 1);
        }
    }

    public static ArrayList generate(int nbColumns) {
        self:
        dims = new String[1000];
        String elements = "0123456789";
        for (int i = 0; i <= nbColumns; i++) {
            generate("", elements.substring(0, nbColumns), i);
        }

        ArrayList al = new ArrayList();
        for (int i = 0; i < dims.length; i++) {
            if (dims[i] != null && !dims[i].isEmpty()) {
                String dim = dims[i];
                int[] dimColumns = new int[dim.length()];
                for (int j = 0; j < dim.length(); j++) {
                    dimColumns[j] = Character.getNumericValue(dim.charAt(j));
                }
               
                al.add(dimColumns);
            }
        }
        return al;
    }
}
