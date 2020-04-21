/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.model;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author Laci
 */
public class Foglalas {
    private int id;
    private DateFormat startIdopont = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private DateFormat endIdopont = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private int asztalId;
    private String nev;
    private boolean active;

    public Foglalas(int id, int asztalId, String nev, boolean active) {
        this.id = id;
        this.asztalId = asztalId;
        this.nev = nev;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public int getAsztalId() {
        return asztalId;
    }

    public DateFormat getEndIdopont() {
        return endIdopont;
    }

    public String getNev() {
        return nev;
    }

    public DateFormat getStartIdopont() {
        return startIdopont;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setAsztalId(int asztalId) {
        this.asztalId = asztalId;
    }

    public void setEndIdopont(DateFormat endIdopont) {
        this.endIdopont = endIdopont;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public void setStartIdopont(DateFormat startIdopont) {
        this.startIdopont = startIdopont;
    }
    
}
