/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hachim.trivialCube;

import com.google.common.base.Joiner;

/**
 *
 * @author hachim
 */
public class Row {
    protected String[] _rowParts;
    public Row(String row) {
        _rowParts = row.split(",");     
    }
    
    public String getValueAtIndex(int index) {
        return _rowParts[index];
    }
    
    public String getValuesAtIndexes(int[] indexes) {
        String[] values = new String[indexes.length];
        
        for(int i=0; i < indexes.length; i++) {
            values[i] = _rowParts[indexes[i]];
        }        
        String value = Joiner.on(",").join(values);
        return value;
    }
}
