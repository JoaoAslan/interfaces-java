package aula1.application;

import aula1.entities.CarRental;
import aula1.entities.Vehicle;
import aula1.services.BrazilTaxService;
import aula1.services.RentalService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        System.out.println("Entre com os dados do aluguel");
        System.out.print("Modelo do carro: ");
        String carModel = sc.nextLine();
        System.out.print("Retirada (dd/MM/yyyy hh:mm): ");
        LocalDateTime start = LocalDateTime.parse(sc.nextLine(), DTF);
        System.out.print("Retorno (dd/MM/yyyy hh:mm): ");
        LocalDateTime finish = LocalDateTime.parse(sc.nextLine(), DTF);

        CarRental cr = new CarRental(start, finish, new Vehicle(carModel));

        System.out.print("Entre com o preço por hora: R$");
        double pricePerHour  = sc.nextDouble();
        System.out.print("Entre com o preço por dia: R$");
        double pricePerDay = sc.nextDouble();
        //                                                                        \/      UPCASTING    \/
        RentalService rentalService = new RentalService(pricePerDay, pricePerHour, new BrazilTaxService());

        rentalService.processInvoice(cr);

        System.out.println("\nFATURA:");
        System.out.println("Pagamento basico: R$" + String.format("%.2f", cr.getInvoice().getBasicPayment()));
        System.out.println("Imposto: R$" + String.format("%.2f", cr.getInvoice().getTax()));
        System.out.println("Pagamento total: R$" + String.format("%.2f", cr.getInvoice().getTotalPayment()));

        sc.close();
    }
}
