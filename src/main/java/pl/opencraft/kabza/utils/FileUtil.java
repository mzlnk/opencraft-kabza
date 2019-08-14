package pl.opencraft.kabza.utils;

import lombok.Getter;
import org.bukkit.plugin.Plugin;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Marcin Zielonka on 14/08/2019.
 */

public class FileUtil {

    private static File dataFolder;

    public FileUtil(Plugin plugin) {
        dataFolder = plugin.getDataFolder();
        prepareDirectories();
    }

    public File findFile(Directory directory, String filename) {
        return new File(dir(directory), filename);
    }

    public List<File> listFiles(Directory directory) {
        File[] files = dir(directory).listFiles();
        return files != null ? Arrays.asList(files) : new ArrayList<>();
    }

    public void createOrUpdateFile(Directory directory, String filename, String content) {
        File file = new File(dir(directory), filename);

        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeFile(Directory directory, String filename) {
        this.findFile(directory, filename).delete();
    }

    private File dir(Directory directory) {
        return new File(dataFolder, directory.getDirectory());
    }

    private void prepareDirectories() {
        Directory.listDirectories().stream().forEach(dir -> {
            File directory = dir(dir);
            if (!directory.exists()) {
                directory.mkdirs();
            }
        });
    }

    public static enum Directory {

        BAGS("bags"),
        BAG_TYPES("bagtypes");

        @Getter
        private String directory;

        private Directory(String directory) {
            this.directory = directory;
        }

        public static List<Directory> listDirectories() {
            return Arrays.asList(Directory.values());
        }


    }

}
