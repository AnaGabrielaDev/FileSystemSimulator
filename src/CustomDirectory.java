import java.io.File;

public class CustomDirectory extends File {
    public CustomDirectory(String pathname) {
        super("folders/" + pathname);
    }

    @Override
    public boolean exists() {
        return super.exists() && super.isDirectory();
    }

    @Override
    public boolean mkdirs() {
        return super.mkdirs();
    }

    public File[] listFiles() {
        return super.listFiles();
    }

    public void deleteRecursively() {
        deleteDirRecursively(this);
    }

    private void deleteDirRecursively(File dir) {
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteDirRecursively(file);
                } else {
                    file.delete();
                }
            }
        }
        dir.delete();
    }

    @Override
    public boolean delete() {
        return super.delete();
    }
}