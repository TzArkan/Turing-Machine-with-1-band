package mt1b;

import java.util.*;

public class MasinaTuring {
    // Membrii clasei:   
    Set<Tranzitie> tranzitii;                                                                       // setul de tranziții,
    String stareInitiala;                                                                           // starea inițială,
    Set<String> stariFinale;                                                                        // stările finale

    // Constructor MT
    public MasinaTuring(Set<Tranzitie> tranzitii, String stareInitiala, Set<String> stariFinale) {
        this.tranzitii = tranzitii;
        this.stareInitiala = stareInitiala;
        this.stariFinale = stariFinale;
    }

    // Verifică dacă mașina este deterministă
    public boolean eDeterminista() {
        Map<String, Set<Character>> verificare = new HashMap<>();                                   // map stare curenta | caracter citit
        for (Tranzitie t : tranzitii) {                                                             // pentru fiecare tranzitie din setul de tranzitii               
            verificare.putIfAbsent(t.stareCurenta, new HashSet<>());                                // daca lipseste starea curent citita din map, se introduce in map 
            Set<Character> simboluri = verificare.get(t.stareCurenta);                              // set de simboluri (unice)
            if (simboluri.contains(t.simbolCitit)) {                                                // daca avem deja un caracter citit mapat la starea curenta
                return false;                                                                       // Două tranziții cu același (stare, simbol) => nedeterminist
            }
            simboluri.add(t.simbolCitit);                                                           // daca nu avem deja caracterul citit mapat la starea curenta, il mapam
        }
        return true;
    }

    // Verifică dacă mașina acceptă un cuvânt
    public boolean accepta(String cuvant) {
        StringBuilder banda = new StringBuilder();
        banda.append('B');                                                                          // Marker stânga bandă
        banda.append(cuvant);                                                                       // Cuvântul introdus
        banda.append('B');                                                                          // Marker dreapta bandă

        int pozitie = 0;                                                                            // Poziția capului de citire
        String stareCurenta = stareInitiala;

        while (true) {
            // Afișează starea curentă și banda
            System.out.print("Stare: " + stareCurenta + " | Banda: ");
            for (int i = 0; i < banda.length(); i++) {
                System.out.print(i == pozitie ? "[" + banda.charAt(i) + "]" : " " + banda.charAt(i) + " ");
            }
            System.out.println();

            char simbolCurent = banda.charAt(pozitie); // Simbolul de sub cap

            Tranzitie tranzitieAplicata = null;
            for (Tranzitie t : tranzitii) {                                                         // verificam pentru fiecare tranzitie din set,
                if (t.sePotriveste(stareCurenta, simbolCurent)) {                                   // daca avem o tranzitie care sa contina starea curenta si simbolul citit
                    tranzitieAplicata = t;                                                          // daca da, atunci tranzitia e valida
                    break;
                }
            }

            if (tranzitieAplicata == null) {                                                        // daca nu avem o tranzitie valida care sa continue
                
                if (stariFinale.contains(stareCurenta)) {                                           // daca suntem intr-o stare finala, atunci cuvantul e acceptat
                    System.out.println("Nu mai avem tranzitii, dar suntem in stare finala: " + stareCurenta);
                    return true;
                } else {
                    System.out.println("Nu suntem intr-o stare finala. Automat oprit.");            // altfel, suntem intr-o stare care nu e finala, cuvantul e respins
                    return false;
                }
            }

            // Aplică tranziția: scrie simbol, schimbă stare, mută capul
            banda.setCharAt(pozitie, tranzitieAplicata.simbolScris);
            stareCurenta = tranzitieAplicata.stareUrmatoare;

            switch (tranzitieAplicata.directie) {
                case 'R':
                    pozitie++;
                    if (pozitie >= banda.length()) banda.append('B');                               // daca avem capul de citire sarit de pe banda la dreapta, marim banda
                    break;
                case 'L':
                    pozitie--;
                    if (pozitie < 0) {
                        System.out.println("Automatul a sarit de pe banda la stanga.");             // daca avem capul de citire sarit de pe banda la stanga, cuvantul e respins si iesim
                        return false;
                    }
                    break;
            }
        }
    }
}
