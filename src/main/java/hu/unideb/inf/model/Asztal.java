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
public class Asztal {
    private int id;
    private int ferohely;

    public Asztal(int id, int ferohely) {
        this.id = id;
        this.ferohely = ferohely;
    }

    public int getId() {
        return id;
    }

    public int getFerohely() {
        return ferohely;
    }
}
