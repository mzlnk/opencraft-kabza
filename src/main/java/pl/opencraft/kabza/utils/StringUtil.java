package pl.opencraft.kabza.utils;

/**
 * Created by Marcin Zielonka on 2019.09.05
 */

public class StringUtil {

    public static String firstLetterUpperCase(String s) {
        String[] words = s.split(" ");

        StringBuilder sb = new StringBuilder();
        for(String w : words) {
            if(w.length() <= 1) {
                sb.append(w.toUpperCase());
            }
            sb.append(w.substring(0, 1).toUpperCase()).append(w.substring(1).toLowerCase()).append(" ");
        }
        String result = sb.toString();
        return result.substring(0, result.length() - 1);
    }

}
