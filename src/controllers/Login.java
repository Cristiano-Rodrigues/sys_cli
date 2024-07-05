package controllers;

import java.sql.Connection;
import org.jasypt.util.text.BasicTextEncryptor;
import io.github.cdimascio.dotenv.Dotenv;
import repositories.EmployeeRepository;
import repositories.MySQLConnection;

public class Login {
    public static Employee logarFuncionario (String nomeUsuario, String senha) {
        try {
            Connection conn = MySQLConnection.getConnection();
            EmployeeRepository empRepository = new EmployeeRepository(conn);
            Employee employee = empRepository.getByUsername(nomeUsuario);

            if (employee == null) {
                return null;
            }

            Dotenv dotenv = Dotenv.load();
            String criptoKey = dotenv.get("KEY");
            BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
            textEncryptor.setPassword(criptoKey);
            String decryptedPass = textEncryptor.decrypt(employee.getPassword());
            
            if (!decryptedPass.equals(senha)) {
              return null;
            }
            
            return employee;
        } catch (Exception e) {
            return null;
        } finally {
          MySQLConnection.closeConnection();
        }
    }
}
