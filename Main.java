import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan nama file : ");
        String fileName = scanner.nextLine();
        System.out.print("Masukkan jumlah baris untuk setiap bagian: ");
        int linesPerFile = scanner.nextInt();

        Queue<String> queue = new LinkedList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            int fileCount = 1;
            int lineCount = 0;


            while ((line = br.readLine()) != null) {
                queue.add(line);
                lineCount++;


                if (lineCount == linesPerFile) {
                    writeToFile(queue, fileCount);
                    fileCount++;
                    lineCount = 0;
                }
            }


            if (!queue.isEmpty()) {
                writeToFile(queue, fileCount);
            }

            System.out.println("Pemotongan file selesai!");
        } catch (IOException e) {
            System.err.println("Terjadi kesalahan saat membaca file: " + e.getMessage());
        }
    }


    private static void writeToFile(Queue<String> queue, int fileCount) {
        String outputFileName = "output_part" + fileCount + ".txt";
        try (FileWriter writer = new FileWriter(outputFileName)) {
            while (!queue.isEmpty()) {
                writer.write(queue.poll() + System.lineSeparator());
            }
            System.out.println("Bagian " + fileCount + " disimpan di " + outputFileName);
        } catch (IOException e) {
            System.err.println("Terjadi kesalahan saat menulis ke file: " + e.getMessage());
        }
    }
}
