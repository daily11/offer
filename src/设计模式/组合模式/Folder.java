package 设计模式.组合模式;

import java.util.ArrayList;
import java.util.List;

public class Folder extends File {

    private String name;
    private List<File> fileList = new ArrayList<>();

    public Folder(String name) {
        this.name = name;
    }

    /**
     * @param file
     */
    public void add(File file) {
        fileList.add(file);
    }

    /**
     * @param file
     */
    public void delete(File file) {
        fileList.remove(file);
    }

    /**
     * @param i
     */
    public File getChild(int i) {
        return fileList.get(i);
    }

    public void killVirus() {
        if (fileList != null) {
            for (File file : fileList) {
                file.killVirus();
            }
        }
    }

}