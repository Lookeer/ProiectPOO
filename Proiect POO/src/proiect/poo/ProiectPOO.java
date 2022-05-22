package proiect.poo;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;
import java.time.format.DateTimeFormatter;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class ProiectPOO {
    
    public static DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    public static void main(String[] args) throws ParserConfigurationException {
        Scanner input= new Scanner(System.in);
        Masa mese []= new Masa[15];
        ArrayList<Rezervare> rezervari = new ArrayList<>();

        System.out.println("Bine ati venit la Impinge Tava!");
        
        mese[0] = new Masa(2, "interior");
        mese[1] = new Masa(4, "interior");
        mese[2] = new Masa(8, "interior");
        mese[3] = new Masa(4, "interior");
        mese[4] = new Masa(6, "interior");
        mese[5] = new Masa(2, "interior");
        mese[6] = new Masa(2, "terasa");
        
        initRezervari(mese);
        Meniu meniu = initMeniu();
        //new Rezervare(new Persoana("Rusanda", "Valentin", "0755012345"), 3, LocalDate.parse("2022-07-23"), LocalTime.parse("15:30"), mese[1], new PreComanda());
        
        while(true){
            System.out.println("Selectati optiunea dorita:");
            System.out.println("1. Rezervare\n2. Afiseaza rezervarile curente\n3. Anuleaza rezervare\n4. Iesire");
            int actiune;
            actiune=input.nextInt();
            switch (actiune) {
                case 1:
                    Rezervare A= new Rezervare();
                    if(!A.rezerva(mese, meniu)){
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
                    afisareRezervari(rezervari);
                    System.out.println("\nIntroduceti numarul rezervarii pe care doriti sa o anulati:");
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
                case "Y":
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
            System.out.print("-REZERVAREA " + (i + 1) + "-");
            rezervari.get(i).afisare();
            System.out.println();
        }
    }
    
    public static void initRezervari(Masa mese[]){
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(new File("src\\proiect\\poo\\Rezervari.xml"));
            
            NodeList list = doc.getDocumentElement().getElementsByTagName("rezervare");
            
            for (int i = 0; i < list.getLength(); i++){
                Element r = (Element) list.item(i);                                     // Rezervare
                Element p = (Element) r.getElementsByTagName("persoana").item(0);       // Persoana
                
                String nume = p.getElementsByTagName("nume").item(0).getTextContent();
                String prenume = p.getElementsByTagName("prenume").item(0).getTextContent();
                String nrTel = p.getElementsByTagName("nrTelefon").item(0).getTextContent();
                int nrPers = parseInt(r.getElementsByTagName("nrPersoane").item(0).getTextContent());
                LocalDate data = LocalDate.parse(r.getElementsByTagName("data").item(0).getTextContent(), format);
                LocalTime ora = LocalTime.parse(r.getElementsByTagName("ora").item(0).getTextContent());
                int idMasa = parseInt(r.getElementsByTagName("masa").item(0).getTextContent());
                
                new Rezervare(new Persoana(nume, prenume, nrTel), nrPers, data, ora, mese[idMasa]);
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
          e.printStackTrace();
        }
    }
    
    public static Meniu initMeniu(){
        Meniu meniu = new Meniu();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(new File("src\\proiect\\poo\\Meniu.xml"));
            
            NodeList list = doc.getDocumentElement().getElementsByTagName("produs");
            
            for (int i = 0; i < list.getLength(); i++){
                Element p = (Element) list.item(i);
                
                String nume = p.getElementsByTagName("nume").item(0).getTextContent();
                String tip = p.getElementsByTagName("tip").item(0).getTextContent();
                float pret = parseFloat(p.getElementsByTagName("pret").item(0).getTextContent());
                
                meniu.addProdus(new Produs(nume, tip, pret));
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
          e.printStackTrace();
        }
        return meniu;
    }
}    
