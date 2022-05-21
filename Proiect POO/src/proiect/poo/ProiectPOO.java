package proiect.poo;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class ProiectPOO {

    public static void main(String[] args) {

        Scanner input= new Scanner(System.in);
        Masa mese []= new Masa[15];
        ArrayList<Rezervare> rezervari = new ArrayList<Rezervare>();

        System.out.println("Bine ati venit la Impinge Tava!");
        
        mese[0] = new Masa(2, "interior");
        mese[1] = new Masa(4, "interior");
        mese[2] = new Masa(8, "interior");
        mese[3] = new Masa(4, "interior");
        mese[4] = new Masa(6, "interior");
        mese[5] = new Masa(2, "interior");
        mese[6] = new Masa(2, "terasa");
        System.out.println(Masa.getNrMese());
        
        new Rezervare(new Persoana("Rusanda", "Valentin", "0755012345"), 3, LocalDate.parse("2022-07-23"), LocalTime.parse("15:30"), mese[1], new PreComanda());
        
        while(true){
            System.out.println("Selectati optiunea dorita:");
            System.out.println("1. Rezervare\n2. Afiseaza rezervarile curente\n3. Anuleaza rezervare\n4. Iesire");
            int actiune;
            actiune=input.nextInt();
            switch (actiune) {
                case 1:
                    Rezervare A= new Rezervare();
                    if(!A.rezerva(mese)){
                        break;
                    }
                    rezervari.add(A);
                    break;
                case 2:
                    System.out.println();
                    afisareRezervari(rezervari);
                    break;
                case 3:
                    if (rezervari.size() == 0){
                        System.out.println("Nu exista rezervari.");
                        break;
                    }
                    System.out.println("\nIntroduceti numarul rezervarii pe care doriti sa o anulati:");
                    afisareRezervari(rezervari);
                    int rez = input.nextInt() - 1;
                    if (rezervari.get(rez) == null){
                        System.out.println("Rezervarea nu exista.");
                        break;
                    }
                    rezervari.remove(rez);
                    System.out.println("Rezervarea a fost anulata cu succes!");
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Optiunea nu exista!");
                    break;
            }
            System.out.println("\nDoriti sa efectuati alta operatiune? (y/n)");
            switch (input.next()){
                case "y":
                    break;
                default:
                    return;
            }
            System.out.println();
        }
    }
    
    public static void afisareRezervari(ArrayList<Rezervare> rezervari){
        if (rezervari.size() == 0){
            System.out.println("Nu exista rezervari.");
            return;
        }
        for(int i=0;i<rezervari.size();i++){
            System.out.print("Rezervarea " + (i + 1) + ":");
            rezervari.get(i).afisare();
        }
    }
}    
