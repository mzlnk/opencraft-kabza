package pl.opencraft.kabza.utils;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Marcin Zielonka on 14/08/2019.
 */

public class FileUtil {

    public static enum Directory {

        BAGS("bags");

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
