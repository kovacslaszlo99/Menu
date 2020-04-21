/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.model;

/**
 *
 * @author Laci
 */
public class Etel {
    private int id;
    private String nev;
    private int ar;

    public Etel(int id, String nev, int ar) {
        this.id = id;
        this.nev = nev;
        this.ar = ar;
    }

    public int getId() {
        return id;
    }

    public int getAr() {
        return ar;
    }

    public String getNev() {
        return nev;
    }

    public void setAr(int ar) {
        this.ar = ar;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }
    
    
}
