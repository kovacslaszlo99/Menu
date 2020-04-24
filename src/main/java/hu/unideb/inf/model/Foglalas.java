/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.model;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

/**
 *
 * @author Laci
 */
public class Foglalas {
    private int id;
    private LocalDateTime startIdopont;
    private LocalDateTime endIdopont;
    private int asztalId;
    private String nev;
    private boolean active;

    public Foglalas(int id, LocalDateTime startIdopont, LocalDateTime endIdopont, int asztalId, String nev, boolean active) {
        this.id = id;
        this.startIdopont = startIdopont;
        this.endIdopont = endIdopont;
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

    public LocalDateTime getEndIdopont() {
        return endIdopont;
    }

    public String getNev() {
        return nev;
    }

    public LocalDateTime getStartIdopont() {
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

    public void setNev(String nev) {
        this.nev = nev;
    }

    public void setEndIdopont(LocalDateTime endIdopont) {
        this.endIdopont = endIdopont;
    }

    public void setStartIdopont(LocalDateTime startIdopont) {
        this.startIdopont = startIdopont;
    }
    
    
}
