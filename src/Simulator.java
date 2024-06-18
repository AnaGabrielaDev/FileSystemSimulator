import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class Simulator {

  private Journal journal;

  public Simulator(Journal journal) {
    this.journal = journal;
  }

  public void copyFile(String source, String destination) throws IOException {
    CustomFile sourceFile = new CustomFile(source);
    CustomFile destinationFile = new CustomFile(destination);

    Files.copy(sourceFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
    journal.addJournal("COPY", source, destination);
  }

  public void renameFile(String source, String destination) {
    CustomFile sourceFile = new CustomFile(source);
    CustomFile destinationFile = new CustomFile(destination);
    if (sourceFile.exists()) {
      sourceFile.renameTo(destinationFile);
      journal.add();
    }
    journal.addJournal("RENAME", source, destination);
  }

  public void createDirectory(String path) {
    CustomDirectory dir = new CustomDirectory(path);
    if (!dir.exists()) {
      dir.mkdirs();
      journal.add();
    }
    journal.addJournal("MKDIR", path, null);
  }

  public void createFile(String path) throws IOException {
    CustomFile file = new CustomFile(path);
    if (!file.exists()) {
      file.createNewFile();
      journal.add();
    }
    journal.addJournal("CREATE", path, null);
  }

  public void deleteDirectoryRecursively(String path) {
    CustomDirectory dir = new CustomDirectory(path);
    if (!dir.exists() || !dir.isDirectory()) {
      System.out.println("Diretório não existe ou não é um diretório: " + path);
      return;
    }
    dir.delete();

    deleteDirectoryContents(dir);
    if (dir.delete()) {
      journal.addJournal("RMDIR", dir.getPath(), null);
      System.out.println("Diretório deletado: " + dir.getPath());
    } else {
      System.err.println("Falha ao deletar diretório: " + dir.getPath());
    }
  }

  private void deleteDirectoryContents(CustomDirectory dir) {
    File[] allContents = dir.listFiles();
    if (allContents != null) {
      for (File file : allContents) {
        if (file.isDirectory()) {
          CustomDirectory customDir = new CustomDirectory(file.getPath().replaceFirst("^folders", ""));
          deleteDirectoryContents(customDir);
          if (customDir.delete()) {
            journal.addJournal("RMDIR", customDir.getPath(), null);
            System.out.println("Diretório deletado: " + customDir.getPath());
          } else {
            System.err.println("Falha ao deletar diretório: " + customDir.getPath());
          }
        } else {
          if (file.delete()) {
            journal.addJournal("DELETE", file.getPath(), null);
            System.out.println("Arquivo deletado: " + file.getPath());
          } else {
            System.err.println("Falha ao deletar arquivo: " + file.getPath());
          }
        }
      }
    }
  }

  public void renameDirectory(String source, String destination) {
    CustomDirectory sourceDir = new CustomDirectory(source);
    CustomDirectory destinationDir = new CustomDirectory(destination);
    if (sourceDir.exists()) {
      sourceDir.renameTo(destinationDir);
      journal.add();
    }
    journal.addJournal("RENAME", source, destination);
  }

  public void listDirectory(String path) {
    System.out.println("Listing directory: " + path);
    CustomFile dir = new CustomFile(path);
    if (dir.exists() && dir.isDirectory()) {
      CustomFile[] files = dir.listFiles();
      if (files != null) {
        for (CustomFile file : files) {
          System.out.println(file.getName());
        }
      }
    }
    journal.addJournal("LS", path, null);
  }
}
