import java.time.LocalDate;

import controllers.Doctor;

public class App {
    public static void main(String[] args) throws Exception {
        Doctor doc = new Doctor("Abigail", "Ginecologista", 3000000.0, LocalDate.of(2022, 05, 12), "1001");

        if (doc.cadastrarMedico()) {
            System.out.println("Medico cadastrado");
        } else {
            System.out.println("Algo correu mal");
        }
    }
}
