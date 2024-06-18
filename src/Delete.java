import java.io.IOException;

class Delete implements Command {
    private Simulator fileSystem;

    public Delete(Simulator fs) {
        this.fileSystem = fs;
    }

    @Override
    public void execute(String[] args) throws IOException {
        if (args.length != 2) {
            System.out.println("Usage: delete <directory>");
            return;
        }
        fileSystem.deleteDirectoryRecursively(args[1]);
    }
}