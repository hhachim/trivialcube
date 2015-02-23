/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hachim.trivialCube;

/**
 *
 * @author dev
 */
public class Attribute {    
    protected long _value = 0;
    protected String _name;
    
    public Attribute(String name) {
        this._name = name;        
    }
    
    public Attribute(String name, long value) {
        this(name);
        this._value = value;
    }
    
    public void setValue(long value) {
        this._value = value;
    }
    
    public String toString() {
        return this._name + ":" + this._value;
    }
    
    public long getValue() {
        return this._value;
    }    
    
}
