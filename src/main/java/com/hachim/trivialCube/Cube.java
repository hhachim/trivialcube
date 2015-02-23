/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hachim.trivialCube;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author hachim
 */
public class Cube {

    protected String _headerRow;
    protected ArrayList _dimensions;
    protected String[] _rows;
    protected int _aggregateColumnIndice;

    public Cube(String filePath, int nbRows) {
        //lecture du header et des rows
        getFileContent(filePath, nbRows);

        //calcul la liste des _dimensions
        generateDimCombinations();
        
        displayDimensionIndices();
    }
    
    private void displayDimensionIndices(){
         for (int i = 0; i < _dimensions.size(); i++) {
            System.out.print("Dimension : " + (i+1) +" => ");            
            int[] currentDim = (int[]) _dimensions.get(i);
            System.out.println(Arrays.toString(currentDim));
         }
    }

    private void generateDimCombinations() {
        String[] headerParts = _headerRow.split(",");       
        System.out.println("Calculating all dimensions indices for length="+headerParts.length);
        _dimensions = Combination.generate(headerParts.length);
    }

    private void getFileContent(String filePath, int nbRows) {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(filePath).getFile());

        _rows = new String[nbRows - 1];//on ne compte pas le header

        try {
            Scanner scanner = new Scanner(file);
            _headerRow = scanner.nextLine();
            //System.out.println(_headerRow);
            //System.exit(0);
            int i = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                //result.append(line).append("\n");
                _rows[i] = line;
                i += 1;
            }

            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(String aggregateFunction, int aggregateColumnIndice) {
        _aggregateColumnIndice = aggregateColumnIndice;
        run(aggregateFunction);
    }

    public void run(String aggregateFunction) {
        Cuboid c = null;
        //-1 pour ne pas calculer la dernière dimension
        //qui n'est rien d'autre que la table d'entrée
        int NbCuboids = _dimensions.size() - 1;
        for (int i = 0; i < NbCuboids; i++) {
            int[] currentDim = (int[]) _dimensions.get(i);

            if (aggregateFunction.equals("count")) {
                c = new CuboidCount(currentDim, _headerRow);
            } else if (aggregateFunction.equals("sum")) {
                c = new CuboidSum(currentDim, _headerRow, _aggregateColumnIndice);
            }
            
            if (c != null) {
                int j;
                for (j = 0; j < _rows.length; j++) {
                    c.processRow(_rows[j]);
                }

                c.write();
                //  freeCuboide(c);
            }
        }
    }

    public void run() {
        run("count");
    }
}
