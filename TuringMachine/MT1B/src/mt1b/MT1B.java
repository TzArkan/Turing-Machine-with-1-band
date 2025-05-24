package mt1b; 

import java.io.*;
import java.util.*;

public class MT1B {
    public static void main(String[] args) throws IOException {                 
        BufferedReader br = new BufferedReader(new FileReader("test.txt"));             // Deschide fișierul "test.txt" pentru citirea datelor
        
        String stareInitiala = br.readLine().trim();                                    // Citește prima linie: starea inițială
        String stareFinala = br.readLine().trim();                                      // Citește a doua linie: starea finală (care accepta cuvantul)
        
        Set<String> stariFinale = new HashSet<>();                                      // Creează o mulțime ce conține stările finale (poate fi extinsă la mai multe)
        stariFinale.add(stareFinala);                           

        br.readLine();                                                                   // Sare peste o linie goală sau comentariu

        
        Set<Tranzitie> tranzitii = new HashSet<>();                                      // Inițializează o mulțime pentru tranziții
        String linie;
        
        
        while ((linie = br.readLine()) != null) {                                       // Citește fiecare linie cu definiții de tranziții
            if (linie.trim().isEmpty()) continue;                                       // Ignoră liniile goale
            String[] p = linie.trim().split("\\s+");                                    // Împarte linia în componente, separate de spatii albe (spatiu, tab), "+" -> cel putin un spatiu de acest tip
            tranzitii.add(new Tranzitie(p[0], p[1].charAt(0), p[2], p[3].charAt(0), p[4].charAt(0)));
        }                                                                               // Creează și adaugă o tranziție în set, cu p[0] stare curenta, p[1] element citit
                                                                                        // p[2] stare urmatoare, p[3] element scris in locul celui citit, p[4] directia stanga/dreapta

        
        MasinaTuring mt = new MasinaTuring(tranzitii, stareInitiala, stariFinale);      // Creează obiectul Mașina Turing

        
        System.out.println("Masina este " + (mt.eDeterminista() ? "determinista." : "nedeterminista."));
                                                                                        // Afișează dacă mașina este deterministă sau nu - daca la un anumit moment dat
                                                                                        // exista o stare, care prin acelasi simbol citit, poate duce in 2 stari diferite
        
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduceti un cuvant: ");
        String input = sc.nextLine();                                                   // Citește cuvântul introdus de utilizator

        
        boolean rezultat = mt.accepta(input);                                           
        System.out.println("Rezultat pentru \"" + input + "\": " + (rezultat ? "ACCEPTAT" : "RESPINS"));
                                                                                        // Verifică dacă mașina acceptă cuvântul
    }
}
