import controllers.Specialty;

public class App {
    public static void main(String[] args) throws Exception {
        Specialty spec = new Specialty("Teste", "Teste", 320000.0);

        if (spec.cadastrarEspecialidade()) {
            System.out.println("Especialidade cadastrada");
        } else {
            System.out.println("Algo correu mal");
        }
    }
}
