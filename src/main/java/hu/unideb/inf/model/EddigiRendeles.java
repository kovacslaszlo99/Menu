package hu.unideb.inf.model;

public class EddigiRendeles {
    private String etelnev;
    private int mennyiseg;
    private int osszeg;

    public EddigiRendeles(String etelnev, int mennyiseg, int osszeg) {
        this.etelnev = etelnev;
        this.mennyiseg = mennyiseg;
        this.osszeg = osszeg;
    }

    public String getEtelnev() {
        return etelnev;
    }

    public void setEtelnev(String etelnev) {
        this.etelnev = etelnev;
    }

    public int getMennyiseg() {
        return mennyiseg;
    }

    public void setMennyiseg(int mennyiseg) {
        this.mennyiseg = mennyiseg;
    }

    public int getOsszeg() {
        return osszeg;
    }

    public void setOsszeg(int osszeg) {
        this.osszeg = osszeg;
    }
}
