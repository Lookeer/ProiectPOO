package proiect.poo;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.lang.Math;
import static java.time.temporal.ChronoUnit.MINUTES;

public class Rezervare {
    
    private Persoana persoana;
    private int nrPersoane;
    private LocalTime ora;
    private LocalDate data;
    private Masa masa;
    private PreComanda preComanda;
    Scanner input=new Scanner(System.in);
    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static Rezervare[] rezervari = new Rezervare[50];
    private static int nrRezervari = 0;

    public Rezervare(){
        this.persoana=new Persoana();
        this.nrPersoane=0;
        this.data=LocalDate.EPOCH;
        this.ora=LocalTime.MIN;
        this.masa=null;
        this.preComanda=null;
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

    public boolean rezerva(Masa mese[]){
        persoana.introducere();
        System.out.println("Introduceti:\nNumarul de persoane:");
        this.nrPersoane=input.nextInt();
        System.out.println("Data (zz/LL/aaaa):");
        this.data = LocalDate.parse(input.next(), format);
        System.out.println("Ora (hh:mm):");
        this.ora=LocalTime.parse(input.next());
        System.out.println("Mese valabile:");
        int valabile=0;
        int i, j;
        System.out.println(Masa.getNrMese());
        for(i=0;i<Masa.getNrMese();i++){
            if (nrPersoane > mese[i].getLocuri()) continue;
            for (j = 0; j < nrRezervari; j++){
                if (rezervari[j].getMasa() == mese[i]){
                    if (rezervari[j].getData().equals(this.data) && rezervari[j].getOra().until(this.ora, MINUTES) < Math.abs(60)){
                        break;
                    }
                }
            }
            if (j < nrRezervari) continue;
            mese[i].afisare();
            valabile++;
        }
        if(valabile==0){
            System.out.println("Niciuna.");
            return false;
        }
        System.out.println("Introduceti numarul mesei dorite: ");
        int m=input.nextInt() - 1;
        if(m>=Masa.getNrMese()){
            System.out.println("Masa inexistenta.");
            return false;
        }
        this.masa=mese[m];
        System.out.println("Rezervare completa!");
        rezervari[nrRezervari] = this;
        nrRezervari++;
        return true;
    }

    
    public void afisare(){
        System.out.println();
        persoana.afisare();
        System.out.println("Numarul de persoane: "+this.nrPersoane+"\nData: "+this.data.format(format)+"\nOra: "+this.ora);
        masa.afisare();
    }
    
    public void setPersoana(Persoana persoana) {
        this.persoana = persoana;
    }
    public Persoana getPersoana() {
        return persoana;
    }

    public void setnrPersoane(int nrPersoane) {
        this.nrPersoane = nrPersoane;
    }
    public int getnrPersoane() {
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

}