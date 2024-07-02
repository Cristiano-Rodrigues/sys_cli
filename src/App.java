import java.sql.Connection;
import controllers.Specialty;
import repositories.MySQLConnection;
import repositories.SpecialtyRepository;

public class App {
    public static void main(String[] args) throws Exception {
        Connection conn = MySQLConnection.getConnection();
        SpecialtyRepository specRepository = new SpecialtyRepository(conn);
        Specialty spec = specRepository.getById(4);

        if (spec != null) {
            System.out.println("Nome: " + spec.getName() + ", preco: " + spec.getPrice());
        } else {
            System.out.println("Algo correu mal");
        }
    }
}
