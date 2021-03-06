package com.hachim.trivialCube;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author hachim
 */
abstract class Cuboid {

    /**
     * Nom du cuboid, généré automatiquement à l'aide de l'entête et de la
     * position des colonnes du cuboid. Exemple vendeur-date
     */
    protected String _name;
    protected Map<String, Attribute> _attributes;
    protected String _ouputFile;
    public PrintWriter writer;

    /**
     * Posititions des colonnes pour le cuboid Exemple (0,2) pour les colonnes
     * vendeur et date
     */
    protected int[] _dimensionIndices;

    /**
     * Pour définir un cuboid, on indique les positions des colonnes
     * caractérisant ce cuboid, ainsi que la ligne d'entête (servant à générer
     * un nom pour le cuboid), et en fin, la fonction d'aggrégation à utiliser
     * (count ou sum)
     *
     * @param dimensionIndices position des colonnes du cuboid
     * @param headerRow ligne d'entête
     */
    public Cuboid(int[] dimensionIndices, String[] headerRowParts) throws Exception {
        this._dimensionIndices = dimensionIndices;
        this._attributes = new HashMap<String, Attribute>();
        this._name = generateName(headerRowParts);
    }

    public void write(String outputDirectory) {
        try {
            this._ouputFile = outputDirectory + "/cuboid_" + this._name;
            writer = new PrintWriter(this._ouputFile, "UTF-8");
            System.out.println("Calculating cuboid " + _name + " (columns = " 
                    + Arrays.toString(_dimensionIndices) + ")");
            for (Map.Entry<String, Attribute> entry : _attributes.entrySet()) {
                writer.println(entry.getValue().toString());
            }
            writer.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public long getAttributValue(String attributeName) {
        return this._attributes.get(attributeName).getValue();
    }

    /**
     * Nom du cuboid
     *
     * @return
     */
    public String getName() {
        return this._name;
    }

    /**
     * Met à jour le nom du cuboid
     *
     * @param name
     */
    public void setName(String name) {
        this._name = name;
    }

    /**
     * Génération automatique du nom du cuboid en fonction de la position des
     * colonnes (dimensionIndices) et de la ligne d'entête (headerRow)
     *
     * @param dimensionIndices
     * @param headerRow
     * @return name
     */
    private String generateName(String[] headerRowParts) {
        Row r = new Row(headerRowParts);
        return r.getValuesAtIndexes(_dimensionIndices);
    }

    abstract void processRow(String[] rowParts);

}
