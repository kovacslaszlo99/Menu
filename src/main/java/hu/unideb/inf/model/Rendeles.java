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
public class Rendeles {
    private int id;
    private int fogalasId;
    private int etelId;
    private int asztalId;
    private int mennyiseg;

    public Rendeles(int id, int fogalasId, int etelId, int asztalId,int mennyiseg) {
        this.id = id;
        this.fogalasId = fogalasId;
        this.etelId = etelId;
        this.asztalId = asztalId;
        this.mennyiseg = mennyiseg;
    }

    public int getAsztalId() {
        return asztalId;
    }

    public int getEtelId() {
        return etelId;
    }

    public int getFogalasId() {
        return fogalasId;
    }

    public void setAsztalId(int asztalId) {
        this.asztalId = asztalId;
    }

    public void setEtelId(int etelId) {
        this.etelId = etelId;
    }

    public int getId() {
        return id;
    }

    public void setFogalasId(int fogalasId) {
        this.fogalasId = fogalasId;
    }
    public int getMennyiseg() {
        return mennyiseg;
    }
    public void setMennyiseg(int mennyiseg) {
        this.mennyiseg = mennyiseg;
    }
    
    
}
