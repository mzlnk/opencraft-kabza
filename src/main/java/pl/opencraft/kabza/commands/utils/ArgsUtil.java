package pl.opencraft.kabza.commands.utils;

/**
 * Created by Marcin Zielonka on 14/08/2019.
 */

public class ArgsUtil {

    public static String[] restArgs(String[] args) {
        if (args.length < 2) {
            return new String[0];
        }
        String[] result = new String[args.length - 1];
        for (int i = 1; i < args.length; i++) {
            result[i - 1] = args[i];
        }
        return result;
    }

}
