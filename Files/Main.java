import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        StringBuilder log = new StringBuilder();
        List<String> subDir1 = Arrays.asList("src", "res", "savegames", "temp");
        List<String> subDir2 = Arrays.asList("main", "test");
        List<String> subDir3 = Arrays.asList("drawables", "vectors", "icons");
        List<String> files1 = Arrays.asList("Main.java", "Utils.java");

        for (String subDir : subDir1) {
            File dir = new File("D://Games", subDir);
            if (dir.mkdir()) {
                log.append("Директория " + dir.getName() + " создана");
                log.append("\n");
            }
        }

        for (String subDir : subDir2) {
            File dir = new File("D://Games/src", subDir);
            if (dir.mkdir()) {
                log.append("Директория " + dir.getName() + " создана");
                log.append("\n");
            }
        }

        for (String subDir : subDir3) {
            File dir = new File("D://Games/res", subDir);
            if (dir.mkdir()) {
                log.append("Директория " + dir.getName() + " создана");
                log.append("\n");
            }
        }

        for (String files : files1) {
            File file = new File("D://Games/src/main", files);
            try {
                if (file.createNewFile()) {
                    log.append("Файл " + file.getName() + " создан");
                    log.append("\n");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        File tempFile = new File("D://Games/temp/temp.txt");
        try {
            tempFile.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (FileWriter writer = new FileWriter("D://Games/temp/temp.txt", true)) {
            writer.append(log);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
