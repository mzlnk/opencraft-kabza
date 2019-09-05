package pl.opencraft.kabza.messages;

import org.bukkit.ChatColor;

import java.util.Locale;
import java.util.ResourceBundle;

import static pl.opencraft.kabza.messages.MessageEnum.*;

/**
 * Created by Marcin Zielonka on 2019.09.05
 */

public class MessageBundle {

    private ResourceBundle messages;

    public MessageBundle(String locale) {
        messages = ResourceBundle.getBundle("messages", Locale.forLanguageTag(locale));
    }

    public String get(MessageEnum messageEnum, String... params) {
        return get(messageEnum.name(), params);
    }

    private String get(String key, String... params) {
        String message = messages.containsKey(key) ? messages.getString(key) : missingMessage(key);
        message = message.replace("{&}", "" + ChatColor.COLOR_CHAR);
        for (String param : params) {
            message = message.replaceFirst("\\{}", param);
        }
        return message;
    }

    private String missingMessage(String key) {
        return messages.getString(MISSING_MESSAGE.name()).replace("{message}", key);
    }


}
