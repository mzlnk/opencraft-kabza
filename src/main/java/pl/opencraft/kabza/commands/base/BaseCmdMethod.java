package pl.opencraft.kabza.commands.base;

import static org.bukkit.ChatColor.GREEN;
import static pl.opencraft.KabzaPlugin.PREFIX;

/**
 * Created by Marcin Zielonka on 2019.09.05
 */

public abstract class BaseCmdMethod implements CmdMethod, CmdDescription {

    protected void sendSuccessMessage(CmdMethodParams params, String message) {
        params.player.sendMessage(PREFIX + GREEN + message);
    }

}
