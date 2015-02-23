/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hachim.trivialCube;

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
    public Cuboid(int[] dimensionIndices, String headerRow) {
        this._dimensionIndices = dimensionIndices;
        this._attributes = new HashMap<String, Attribute>();

        this._name = generateName(headerRow);
        System.out.println("Calcuting cuboid " + _name + " (columns = " + Arrays.toString(_dimensionIndices) + ")");
    }

    public void write() {

        for (Map.Entry<String, Attribute> entry : _attributes.entrySet()) {
            System.out.println(entry.getValue().toString());
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
    private String generateName(String headerRow) {
        Row r = new Row(headerRow);
        return r.getValuesAtIndexes(_dimensionIndices);
    }

    abstract void processRow(String row);

}
