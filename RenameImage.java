import java.io.File;

public class RenameImage {
    public static void main(String[] args) {

        String originPath = "E:\\bad apple";
        String newPath = "E:\\bad apple renamed";
        String imageFormat = ".png";
        String fileSeparator = File.separator;


        File file = new File(originPath);
        File[] imageFiles = file.listFiles();
        int length = imageFiles.length;
        System.out.println("There are " + length + " files");
        System.out.println("-----------------------------");

        for (int i = 0; i < length; i++) {
            boolean isRenamed = imageFiles[i].renameTo(new File(newPath + fileSeparator + (i + 1) + imageFormat));
            System.out.println((i + 1) + "  " + isRenamed);
        }

        System.out.println("All process are done");

    }
}
