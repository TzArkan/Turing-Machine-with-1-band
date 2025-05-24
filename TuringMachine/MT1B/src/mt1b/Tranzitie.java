package mt1b;

import java.util.Objects;

public class Tranzitie {
    // Membrii clasei - componentele unei tranziții
    String stareCurenta;
    char simbolCitit;
    String stareUrmatoare;
    char simbolScris;
    char directie; // 'L' = stânga, 'R' = dreapta

    // Constructor pentru inițializarea unei tranziții
    public Tranzitie(String stareCurenta, char simbolCitit, String stareUrmatoare, char simbolScris, char directie) {
        this.stareCurenta = stareCurenta;
        this.simbolCitit = simbolCitit;
        this.stareUrmatoare = stareUrmatoare;
        this.simbolScris = simbolScris;
        this.directie = directie;
    }

    // Verifică dacă tranziția se potrivește cu starea și simbolul de pe bandă
    public boolean sePotriveste(String stare, char simbol) {
        return stareCurenta.equals(stare) && simbolCitit == simbol;
    }

    // Redefinirea metodei equals pentru compararea obiectelor de tip Tranzitie
    // Vrem sa verificam 
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tranzitie)) return false;
        Tranzitie that = (Tranzitie) o;
        return simbolCitit == that.simbolCitit &&
               simbolScris == that.simbolScris &&
               directie == that.directie &&
               Objects.equals(stareCurenta, that.stareCurenta) &&
               Objects.equals(stareUrmatoare, that.stareUrmatoare);
    }

    // Redefinirea metodei hashCode pentru a funcționa corect în HashSet
    @Override
    public int hashCode() {
        return Objects.hash(stareCurenta, simbolCitit, stareUrmatoare, simbolScris, directie);
    }
}
