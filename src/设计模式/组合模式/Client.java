package 设计模式.组合模式;

public class Client {
    public static void main(String[] args) {
        File imageFile = new ImageFile("文本");
        File videoFile = new VideoFile("视频");

        File folder = new Folder("文件夹");
        folder.add(imageFile);
        folder.add(videoFile);

        folder.killVirus();
    }
}
