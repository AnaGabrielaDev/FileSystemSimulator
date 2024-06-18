public class Main {
  public static void main(String[] args) {
    Journal journal = new Journal();
    Simulator fileSystem = new Simulator(journal);
    Prompt handler = new Prompt(fileSystem);
    handler.startShell();
  }
}