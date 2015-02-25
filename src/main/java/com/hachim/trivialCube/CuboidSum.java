package com.hachim.trivialCube;

/**
 *
 * @author hachim
 */
public class CuboidSum extends Cuboid {
    /**
     * Colonne où il faut faire les aggrégations de sum
     */
    protected int _aggregateDimensionIndice;
    
    public CuboidSum(int[] dimensionIndices, String[] headerRowParts, int aggregateDimensionIndice)  throws Exception  {
        super(dimensionIndices, headerRowParts);
        this._aggregateDimensionIndice = aggregateDimensionIndice;
    }
    
    /**
     * Traitement d'une ligne de la table d'entrée
     * pour mettre à jour les sums
     * @param row 
     */
    public void processRow(String[] rowParts) {
        Row r = new Row(rowParts);
        String attributeName = r.getValuesAtIndexes(_dimensionIndices);
        long sum = Long.valueOf(r.getValueAtIndex(_aggregateDimensionIndice));
                
        if (this._attributes.containsKey(attributeName)) {            
            long oldValue = this._attributes.get(attributeName).getValue();
            this._attributes.get(attributeName).setValue(sum+oldValue);            
        } else {            
            this._attributes.put(attributeName,new Attribute(attributeName,sum));
        }
    }
    
}
