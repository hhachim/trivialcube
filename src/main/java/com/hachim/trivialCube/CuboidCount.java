package com.hachim.trivialCube;

/**
 *
 * @author hachim
 */
public class CuboidCount extends Cuboid {
    
    public CuboidCount(int[] dimensionIndices, String[] headerRowParts)  throws Exception  {
        super(dimensionIndices, headerRowParts);
    }
    
    /**
     * Traitement d'une ligne de la table d'entrée
     * pour mettre à jour les sums
     * @param row 
     */
    public void processRow(String[] rowParts) {
        Row r = new Row(rowParts);
        String attributeName = r.getValuesAtIndexes(_dimensionIndices);
        //long sum = Long.valueOf(r.getValueAtIndex(_aggregateDimensionIndice));
                
        if (this._attributes.containsKey(attributeName)) {  
            long oldValue = this._attributes.get(attributeName).getValue();
            this._attributes.get(attributeName).setValue(1+oldValue);            
        } else {            
            this._attributes.put(attributeName,new Attribute(attributeName,1));
        }
    }
    
}
