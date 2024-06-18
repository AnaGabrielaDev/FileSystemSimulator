import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Journal {

  private List<String> operations;

  public Journal() {
    this.operations = new ArrayList<>();
  }

  public void logOperation(String operation, String source, String destination) {
    operations.add(operation + " " + source + (destination != null ? " " + destination : ""));
  }

  public void commit() {
    try (FileWriter writer = new FileWriter("journal.log", true)) {
      for (String op : operations) {
        writer.write(op + "\n");
      }
      operations.clear();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
