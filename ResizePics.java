import java.io.File;

public class ResizePics {
    public static void main(String[] args) throws Exception {

        String filePath = "E:\\bad apple renamed";
        String fileSeparator = File.separator;
        String fileResizePath = "E:\\bad apple resized";

        File imageFiles_Directory = new File(filePath);
        File[] imageFiles = imageFiles_Directory.listFiles();

        int fileNumbers = imageFiles.length;

        for (int i = 0; i < fileNumbers; i++) {
            ResizePicsUtil.zoomBySize(imageFiles[i].getPath(), fileResizePath + fileSeparator + imageFiles[i].getName());
        }

    }
}
