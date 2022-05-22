package proiect.poo;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;
import static java.time.temporal.ChronoUnit.MINUTES;

public class Rezervare {
    
    private Persoana persoana;
    private int nrPersoane;
    private LocalTime ora;
    private LocalDate data;
    private Masa masa;
    private PreComanda preComanda;
    Scanner input=new Scanner(System.in);
    private static Rezervare[] rezervari = new Rezervare[50];
    private static int nrRezervari = 0;

    public Rezervare(){
        this.persoana=new Persoana();
        this.nrPersoane=0;
        this.data=LocalDate.EPOCH;
        this.ora=LocalTime.MIN;
        this.masa=null;
        this.preComanda=new PreComanda();
    }
    
    public Rezervare(Persoana persoana, int nrPersoane, LocalDate data, LocalTime ora, Masa masa, PreComanda preComanda) {
        this.persoana = persoana;
        this.nrPersoane = nrPersoane;
        this.data=data;
        this.ora = ora;
        this.masa = masa;
        this.preComanda = preComanda;
        rezervari[nrRezervari] = this;
        nrRezervari++;
    }
    
    public Rezervare(Persoana persoana, int nrPersoane, LocalDate data, LocalTime ora, Masa masa) {
        this.persoana = persoana;
        this.nrPersoane = nrPersoane;
        this.data=data;
        this.ora = ora;
        this.masa = masa;
        this.preComanda = new PreComanda();
        rezervari[nrRezervari] = this;
        nrRezervari++;
    }
    
    public void setParametri(Persoana persoana, int nrPersoane, LocalDate data, LocalTime ora, Masa masa) {
        this.persoana = persoana;
        this.nrPersoane = nrPersoane;
        this.data=data;
        this.ora = ora;
        this.masa = masa;
    }

    public boolean rezerva(Masa mese[], Meniu meniu){
        persoana.introducere();
        System.out.println("Numarul de persoane:");
        this.nrPersoane=input.nextInt();
        System.out.println("Data (zz/LL/aaaa):");
        this.data = LocalDate.parse(input.next(), ProiectPOO.format);
        System.out.println("Ora (hh:mm):");
        this.ora=LocalTime.parse(input.next());
        System.out.println("Mese valabile:");
        int nevalabile[] = new int[30];
        int nrNevalabile = 0;
        int nrValabile=0;
        int i, j;
        for(i=0;i<Masa.getNrMese();i++){
            if (nrPersoane > mese[i].getLocuri()) continue;
            for (j = 0; j < nrRezervari; j++){
                if (rezervari[j].getMasa() == mese[i]){
                    if (rezervari[j].getData().equals(this.data) && Math.abs(rezervari[j].getOra().until(this.ora, MINUTES)) < 60){
                        nevalabile[nrNevalabile] = i;
                        nrNevalabile++;
                        break;
                    }
                }
            }
            if (j < nrRezervari) continue;
            mese[i].afisare();
            nrValabile++;
        }
        
        if(nrValabile == 0){
            System.out.println("Niciuna.");
            return false;
        }
        
        System.out.println("Introduceti numarul mesei dorite: ");
        int m=input.nextInt() - 1;
        if (m >= Masa.getNrMese()){
            System.out.println("Masa inexistenta.");
            return false;
        }
        
        for (int x : nevalabile){
            if (m == x){
                System.out.println("Masa nu este valabila.");
                return false;
            }
        }
        this.masa = mese[m];
        System.out.println("Doriti sa pre-comandati ceva? (y/n)");
        switch (input.next()){
            case "y":
            case "Y":
                this.preComanda.initializare(meniu);
                break;
            default:
                break;
        }
        System.out.println("Rezervare completa!");
        rezervari[nrRezervari] = this;
        nrRezervari++;
        return true;
    }

    
    public void afisare(){
        System.out.println();
        persoana.afisare();
        System.out.println("Numarul de persoane: "+this.nrPersoane+"\nData: "+this.data.format(ProiectPOO.format)+"\nOra: "+this.ora);
        masa.afisare();
        if (!preComanda.isNull())
            preComanda.afisare();
    }
    
    public void setPersoana(Persoana persoana) {
        this.persoana = persoana;
    }
    public Persoana getPersoana() {
        return persoana;
    }

    public void setNrPersoane(int nrPersoane) {
        this.nrPersoane = nrPersoane;
    }
    public int getNrPersoane() {
        return nrPersoane;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
    public LocalDate getData() {
        return data;
    }

    public void setOra(LocalTime ora) {
        this.ora = ora;
    }
    public LocalTime getOra() {
        return ora;
    }

    public void setMasa(Masa masa) {
        this.masa = masa;
    }
    public Masa getMasa() {
        return masa;
    }

    public void setPreComanda(PreComanda preComanda) {
        this.preComanda = preComanda;
    }
    
    public PreComanda getPreComanda() {
        return preComanda;
    }
    
    public Masa[] verificaMese(Masa mese[]){
        Masa valabile[] = new Masa[7];
        int nrValabile = 0;
        int i, j;
        for(i=0;i<Masa.getNrMese();i++){
            if (nrPersoane > mese[i].getLocuri()) continue;
            for (j = 0; j < nrRezervari; j++){
                if (rezervari[j].getMasa() == mese[i]){
                    if (rezervari[j].getData().equals(this.data) && Math.abs(rezervari[j].getOra().until(this.ora, MINUTES)) < 60){
                        break;
                    }
                }
            }
            if (j < nrRezervari) continue;
            valabile[nrValabile] = mese[i];
            nrValabile++;
        }
        return valabile;
    }

}