import java.io.File;

public class CustomFile extends File {
    public CustomFile(String pathname) {
        super("folders/" + pathname);
    }

    @Override
    public boolean exists() {
        return super.exists();
    }

    public boolean mkdirs() {
        return super.mkdirs();
    }

    @Override
    public boolean isDirectory() {
        return super.isDirectory();
    }

    @Override
    public CustomFile[] listFiles() {
        File[] files = super.listFiles();
        if (files != null) {
            CustomFile[] customFiles = new CustomFile[files.length];
            for (int i = 0; i < files.length; i++) {
                customFiles[i] = new CustomFile(files[i].getPath());
            }
            return customFiles;
        }
        return null;
    }

    @Override
    public boolean delete() {
        return super.delete();
    }

    public boolean renameTo(CustomFile destination) {
        return super.renameTo(destination);
    }

}