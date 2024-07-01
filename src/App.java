import java.time.LocalDate;

import controllers.Employee;

public class App {
    public static void main(String[] args) throws Exception {
        Employee emp = new Employee("Abigail", "Ginecologista", 3000000.0, LocalDate.of(2022, 05, 12));

        if (emp.cadastrarFuncionario()) {
            System.out.println("funcionario cadastrado");
        } else {
            System.out.println("Algo correu mal");
        }
    }
}
