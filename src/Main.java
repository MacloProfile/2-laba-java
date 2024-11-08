import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите имя входного файла: ");
        String inputFileName = scanner.nextLine();

        File inputFile = new File(inputFileName);
        if (!inputFile.exists()) {
            System.out.println("Файл не существует.");
            return;
        }

        System.out.print("Введите имя выходного файла: ");
        String outputFileName = scanner.nextLine();

        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char c = 'A'; c <= 'Z'; c++) {
            frequencyMap.put(c, 0);
        }
        for (char c = 'a'; c <= 'z'; c++) {
            frequencyMap.put(c, 0);
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            int ch;
            while ((ch = reader.read()) != -1) {
                char character = (char) ch;
                if (frequencyMap.containsKey(character)) {
                    frequencyMap.put(character, frequencyMap.get(character) + 1);
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
            for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
                if (entry.getValue() > 0) {
                    writer.write(entry.getKey() + ": " + entry.getValue());
                    writer.newLine();
                }
            }
            System.out.println("Результаты успешно записаны в " + outputFileName);
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }
}
