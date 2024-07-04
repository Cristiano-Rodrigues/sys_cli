import java.time.LocalDateTime;
import controllers.Appointment;

public class App {
    public static void main(String[] args) throws Exception {
        Appointment app = new Appointment(
            "em espera",
            LocalDateTime.of(2024, 10, 10, 12, 0)
        );

        boolean scheduled = app.agendarConsulta(1, 4, 1);

        if (scheduled) {
            System.out.println("Consulta marcada");
        } else {
            System.out.println("Consulta nao marcada");
        }
    }
}
