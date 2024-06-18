import java.io.IOException;

class List implements Command {
    private Simulator fileSystem;

    public List(Simulator fs) {
        this.fileSystem = fs;
    }

    @Override
    public void execute(String[] args) throws IOException {
        if (args.length == 1) {
            System.out.println("Usage: list <directory>");
            return;
        } else {
            fileSystem.listDirectory(args[1]);
        }
    }
}