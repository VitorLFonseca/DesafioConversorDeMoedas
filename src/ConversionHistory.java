import java.util.ArrayList;
import java.util.List;

public class ConversionHistory {
    private final List<String> history = new ArrayList<>();

    public void add(String from, String to, double amount, double result) {
        String entry = String.format("%.2f %s → %.2f %s", amount, from, result, to);
        history.add(entry);
    }

    public void show() {
        if (history.isEmpty()) {
            System.out.println("Nenhuma conversão registrada ainda.");
        } else {
            System.out.println("\n=== Histórico de Conversões ===");
            for (int i = 0; i < history.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, history.get(i));
            }
        }
    }
}