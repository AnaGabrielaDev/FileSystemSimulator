import java.io.IOException;
import java.util.Scanner;

public class CommandHandler {

  private Simulator fileSystem;

  public CommandHandler(Simulator fileSystem) {
    this.fileSystem = fileSystem;
  }

  public void startShell() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("File System Shell. Type 'exit' to quit.");
    while (true) {
      System.out.print("> ");
      String command = scanner.nextLine();
      if (command.equals("exit")) {
        break;
      }
      executeCommand(command);
    }
    scanner.close();
  }

  private void executeCommand(String command) {
    String[] parts = command.split(" ");
    try {
      switch (parts[0]) {
        case "copy":
          fileSystem.copyFile(parts[1], parts[2]);
          break;
        case "delete":
          fileSystem.deleteFile(parts[1]);
          break;
        case "rename":
          fileSystem.renameFile(parts[1], parts[2]);
          break;
        case "mkdir":
          fileSystem.createDirectory(parts[1]);
          break;
        case "rmdir":
          fileSystem.deleteDirectory(parts[1]);
          break;
        case "list":
          fileSystem.listDirectory(parts[1]);
          break;
        default:
          System.out.println("Unknown command");
      }
    } catch (IOException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }
}
