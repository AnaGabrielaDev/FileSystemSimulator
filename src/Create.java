import java.io.IOException;

class Create implements Command {
    private Simulator fileSystem;

    public Create(Simulator fs) {
        this.fileSystem = fs;
    }

    @Override
    public void execute(String[] args) throws IOException {
        if (args.length != 2) {
            System.out.println("Usage: create <name>");
            return;
        }
        if (args[1].contains("/")) {
            if (args[1].matches(".*\\.[^/]+$")) {
                String directoryPath = args[1].substring(0, args[1].lastIndexOf("/"));
                createDirectoriesRecursively(directoryPath);
                fileSystem.createFile(args[1]);
            } else {
                createDirectoriesRecursively(args[1]);
            }
        } else {
            fileSystem.createFile(args[1]);
        }
    }

    private void createDirectoriesRecursively(String path) throws IOException {
        if (path == null || path.isEmpty())
            return;
        String[] directories = path.split("/");
        StringBuilder currentPath = new StringBuilder();
        for (String dir : directories) {
            currentPath.append(dir).append("/");
            fileSystem.createDirectory(currentPath.toString());
        }
    }
}
