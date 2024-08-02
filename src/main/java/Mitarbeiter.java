import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Mitarbeiter {
    private String id;
    private String vorname;
    private String nachname;
    private String geburtsdatum;
    private boolean verheiratet;
    private String email;
    private String telefonnummer;
    private String position;
    private final List<String> mitarbeiterIdList = new ArrayList<>();
    private int mitarbeiterAnzahl = -1;
    public Mitarbeiter(){};
    public Mitarbeiter(String vorname, String nachname, String geburtsdatum, boolean verheiratet,
                       String email, String telefonnummer, String position){
        generateUniqueId();
        this.vorname = vorname;
        this.nachname = nachname;
        this.geburtsdatum = geburtsdatum;
        this.verheiratet = verheiratet;
        this.email = email;
        this.telefonnummer = telefonnummer;
        this.position = position;
    }

    private void generateUniqueId(){
        this.id = String.format("%010d", new Random().nextInt(1000000000));
        this.mitarbeiterAnzahl++;
        checkGeneratedUniqueId();
    }
    private void checkGeneratedUniqueId() {
        for (Mitarbeiter mitarbeiter : EmployeeForm.mitarbeiterList) {
            mitarbeiterIdList.add(mitarbeiter.getId());
        }
        boolean idExist = mitarbeiterIdList.stream()
                .anyMatch(s -> s.equals(this.id));
        if (idExist){
            if (mitarbeiterAnzahl >= EmployeeForm.mitarbeiterList.size()){
                throw new RuntimeException("JSON-Datei ist voll mit Mitarbeiter");
            };
            generateUniqueId();
        }
    }

    public String getId(){
        return id;
    }
    public String getVorname() {
        return vorname;
    }
    public String getNachname() {
        return nachname;
    }
    public String getGeburtsdatum() {
        return geburtsdatum;
    }
    public String getEmail() {
        return email;
    }
    public String getTelefonnummer() {
        return telefonnummer;
    }
    public boolean getVerheiratet() {
        return verheiratet;
    }
    public String getPosition(){
        return position;
    }

    public void setId(String id) {
        this.id = id;
    }
    public void setVorname(String vorname) {
        this.vorname = vorname;
    }
    public void setNachname(String nachname) {
        this.nachname = nachname;
    }
    public void setGeburtsdatum(String geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setTelefonnummer(String telefonnummer) {
        this.telefonnummer = telefonnummer;
    }
    public void setVerheiratet(boolean verheiratet) {
        this.verheiratet = verheiratet;
    }
    public void setPosition(String position) {
        this.position = position;
    }
}
