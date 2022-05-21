package proiect.poo;

public class PreComanda {
    
    private String produsMancare, produsBauturi, observatii;

    public PreComanda() {
        this.produsMancare = "";
        this.produsBauturi = "";
        this.observatii = "";
    }

    public PreComanda(String produsMancare, String produsBauturi, String observatii) {
        this.produsMancare = produsMancare;
        this.produsBauturi = produsBauturi;
        this.observatii = observatii;
    }

    
    public String getObservatii() {
        return observatii;
    }

    public void setObservatii(String observatii) {
        this.observatii = observatii;
    }

    public String getProdusBauturi() {
        return produsBauturi;
    }

    public void setProdusBauturi(String produsBauturi) {
        this.produsBauturi = produsBauturi;
    }

    public String getProdusMancare() {
        return produsMancare;
    }

    public void setProdusMancare(String produsMancare) {
        this.produsMancare = produsMancare;
    }
}