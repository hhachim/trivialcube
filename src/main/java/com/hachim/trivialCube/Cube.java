package com.hachim.trivialCube;

import au.com.bytecode.opencsv.CSVReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 *
 * @author hachim
 */
public class Cube {

    protected String[] _headerRowParts;
    protected ArrayList _dimensions;
    protected ArrayList _rows;
    protected int _aggregateColumnIndice;
    protected String _outputDirectory = "/tmp/cube";
    protected CubeOptions _options;

    public Cube(String[] args) {
        _options = new CubeOptions(args);
        _outputDirectory = _options.ouputDirectory;
        _aggregateColumnIndice = _options.columnValuesId;

        //lecture du header et des rows
        getFileContent(_options.inputFile);

        //calcul la liste des _dimensions
        if (_options.outputDimensions != null) {
            generateOutputDimensionsList(_options.outputDimensions);
        } else {
            generateDimCombinations();
        }

        displayDimensionIndices();
    }

    public CubeOptions getOptions() {
        return _options;
    }

    private void displayDimensionIndices() {
        for (int i = 0; i < _dimensions.size(); i++) {
            System.out.print("Dimension : " + (i + 1) + " => ");
            int[] currentDim = (int[]) _dimensions.get(i);
            System.out.println(Arrays.toString(currentDim));
        }
    }

    private void generateDimCombinations() {        
        System.out.println("Calculating all dimensions indices for length=" + _headerRowParts.length);
        _dimensions = Combination.generate(_headerRowParts.length);

        //on enleve la dernière dimension (il n'a pas besoin d'être calculée)
        //car elle est identique à la relation d'entrée (elle comprend toutes les colonnes)        
        _dimensions.remove(_dimensions.size() - 1);
    }

    private void generateOutputDimensionsList(String outputDimensionsStr) {
        String[] tmpDims = outputDimensionsStr.split(",");
        _dimensions = new ArrayList();
        for (int i = 0; i < tmpDims.length; i++) {
            String[] numberStrs = tmpDims[i].split("-");
            int[] numbers = new int[numberStrs.length];
            for (int j = 0; j < numberStrs.length; j++) {
                numbers[j] = Integer.parseInt(numberStrs[j]);
            }
            Arrays.sort(numbers);
            _dimensions.add(numbers);
        }
    }

    private void getFileContent(String filePath) {        
        CSVReader reader;
        try {
            _rows = new ArrayList();
            //@todo, ',' and '"' must be given in command line
            reader = new CSVReader(new FileReader(filePath), ',', '"');
            _headerRowParts = reader.readNext();
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                _rows.add(nextLine);                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            int nbCuboids = _dimensions.size();

            if (_options.aggregateFunction.equals("sum")) {
                runSum(nbCuboids);
            } else if (_options.aggregateFunction.equals("count")) {
                runCount(nbCuboids);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void runSum(int nbCuboids) {
        try {
            CuboidSum c = null;
            for (int i = 0; i < nbCuboids; i++) {
                System.out.print("Step " + (i + 1) + "/" + nbCuboids + "\t: ");
                int[] currentDim = (int[]) _dimensions.get(i);
                c = new CuboidSum(currentDim, _headerRowParts, _aggregateColumnIndice);
                computeRows(c);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void runCount(int nbCuboids) {
        try {
            CuboidCount c = null;
            for (int i = 0; i < nbCuboids; i++) {
                System.out.print("Step " + i + "/" + (nbCuboids - 1) + " : ");
                int[] currentDim = (int[]) _dimensions.get(i);
                c = new CuboidCount(currentDim, _headerRowParts);
                computeRows(c);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void computeRows(Cuboid c) {
        Iterator<String[]> it = _rows.iterator();
        while (it.hasNext()) {
            c.processRow(it.next());
        }
        c.write(this._outputDirectory);
        //c = null;
        //System.gc();
        //Runtime r=Runtime.getRuntime();
        //long mem1=r.freeMemory();
        //r.gc();
        //freeMemory();
    }
}
