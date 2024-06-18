import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Journal {

  private List<String> operations;

  public Journal() {
    this.operations = new ArrayList<>();
  }

  public void addJournal(String operation, String source, String destination) {
    operation = (operation + " " + source + (destination != null ? " " + destination : ""));
    operations.add(operation);
    System.out.println("operation added in journal: " + operation);
  }

  public void add() {
    try (FileWriter writer = new FileWriter("journal.log", true)) {
      for (String op : operations) {
        System.out.println("add op " + op);
        writer.write(op + "\n");
      }
      operations.clear();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void read() {
    try {
      List<String> lines = Files.readAllLines(Paths.get("journal.log"));
      for (String line : lines) {
        System.out.println(line);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
