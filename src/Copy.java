import java.io.IOException;

class Copy implements Command {
    private Simulator fileSystem;

    public Copy(Simulator fs) {
        this.fileSystem = fs;
    }

    @Override
    public void execute(String[] args) throws IOException {
        if (args.length != 3) {
            System.out.println("Usage: copy <source> <destination>");
            return;
        }
        fileSystem.copyFile(args[1], args[2]);
    }
}
