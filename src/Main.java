import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CurrencyConverter converter = new CurrencyConverter();
        ConversionHistory history = new ConversionHistory();

        System.out.println("=== Conversor de Moedas ===");
        System.out.println("===========================\n" +
                "Moedas disponíveis:\n" +
                "ARS (Peso argentino)\n" +
                "BOB (Boliviano)\n" +
                "BRL (Real)\n" +
                "CLP (Peso chileno)\n" +
                "COP (Peso colombiano)\n" +
                "USD (Dólar americano)\n" +
                "===========================");

        boolean continuar = true;

        while (continuar) {
            System.out.println("\nMenu:");
            System.out.println("1 - Converter moeda");
            System.out.println("2 - Ver histórico de conversões");
            System.out.println("3 - Sair");
            System.out.print("Escolha uma opção: ");
            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    System.out.print("Moeda de origem (ARS, BOB, BRL, CLP, COP, USD): ");
                    String fromCurrency = scanner.nextLine().toUpperCase();

                    System.out.print("Moeda de destino (ARS, BOB, BRL, CLP, COP, USD): ");
                    String toCurrency = scanner.nextLine().toUpperCase();

                    System.out.print("Valor a converter: ");
                    double amount;
                    try {
                        amount = Double.parseDouble(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Valor inválido. Tente novamente.");
                        break;
                    }

                    double result = converter.convert(fromCurrency, toCurrency, amount);

                    if (result >= 0) {
                        System.out.printf("Resultado: %.2f %s = %.2f %s\n",
                                amount, fromCurrency, result, toCurrency);
                        history.add(fromCurrency, toCurrency, amount, result);
                    } else {
                        System.out.println("Conversão falhou. Verifique as moedas e tente novamente.");
                    }
                    break;

                case "2":
                    history.show();
                    break;

                case "3":
                    continuar = false;
                    System.out.println("Encerrando o conversor.");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }

        scanner.close();
    }
}