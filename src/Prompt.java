import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

interface Command {
  void execute(String[] args) throws IOException;
}

class Prompt {
  private Map<String, Command> commandMap = new HashMap<>();
  private Simulator fileSystem;

  public Prompt(Simulator fs) {
    this.fileSystem = fs;
    commandMap.put("copy", new Copy(fs));
    commandMap.put("rename", new Rename(fs));
    commandMap.put("mkdir", new Create(fs));
    commandMap.put("rmdir", new Delete(fs));
    commandMap.put("ls", new List(fs));
  }

  public void startShell() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Bem vindo(a) ao simulador de sistema de arquivos!");
    while (true) {
      System.out.println("Digite 'copy', 'delete', 'rename', 'mkdir', 'rmdir', or 'ls' para começar.");
      String command = scanner.nextLine();
      executeCommand(command);
    }
  }

  public void executeCommand(String prompt) {
    String[] parts = prompt.split(" ");
    String commandKey = parts[0];
    Command command = commandMap.get(commandKey);
    if (command == null) {
      System.out.println("Error! Unknown command.");
      return;
    }
    try {
      command.execute(parts);
    } catch (IOException e) {
      System.out.println("Error executing command: " + e.getMessage());
    }
  }
}
