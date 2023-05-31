import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {
        GameProgress progress1 = new GameProgress(30, 10,20,4);
        GameProgress progress2 = new GameProgress(45, 20,50,6);
        GameProgress progress3 = new GameProgress(50, 25,30,4);
        List<String> archive = Arrays.asList("D://Games/savegames/save1.dat",
                "D://Games/savegames/save2.dat",
                "D://Games/savegames/save3.dat");


        saveGame("D://Games/savegames/save1.dat", progress1);
        saveGame("D://Games/savegames/save2.dat", progress2);
        saveGame("D://Games/savegames/save3.dat", progress3);

        zipFiles("D://Games/saves.zip", archive);
    }

    public static void saveGame(String dirFile, GameProgress progress) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dirFile))) {
            oos.writeObject(progress);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void zipFiles(String dirZip, List<String> paths) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(dirZip))) {
            for (String path : paths) {
                File file = new File(path);
                FileInputStream fis = new FileInputStream(file);
                ZipEntry entry = new ZipEntry(file.getName());
                zout.putNextEntry(entry);
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                fis.close();
                zout.write(buffer);
                zout.closeEntry();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}