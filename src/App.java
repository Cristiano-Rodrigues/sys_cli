import controllers.Invoice;

public class App {
    public static void main(String[] args) throws Exception {
        Invoice invoice = new Invoice(false, 370000);

        if (invoice.cadastrarFactura(1)) {
            System.out.println("Factura cadastrada com sucesso!");
        } else {
            System.out.println("Factura nao cadastrada!");
        }
    }
}
