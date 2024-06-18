import java.io.IOException;

class Rename implements Command {
    private Simulator fileSystem;

    public Rename(Simulator fs) {
        this.fileSystem = fs;
    }

    @Override
    public void execute(String[] args) throws IOException {
        if (args.length != 3) {
            System.out.println("Usage: rename <source> <destination>");
            return;
        }
        fileSystem.renameFile(args[1], args[2]);
    }
}