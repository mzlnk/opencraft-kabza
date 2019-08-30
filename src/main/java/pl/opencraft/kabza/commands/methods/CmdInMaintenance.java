package pl.opencraft.kabza.commands.methods;

import pl.opencraft.kabza.commands.base.CmdDescription;
import pl.opencraft.kabza.commands.base.CmdMethod;
import pl.opencraft.kabza.commands.base.CmdMethodParams;

import static pl.opencraft.KabzaPlugin.PREFIX;
import static org.bukkit.ChatColor.BLUE;

/**
 * Created by Marcin Zielonka on 30/08/2019.
 */

public class CmdInMaintenance implements CmdMethod, CmdDescription {

    @Override
    public String description() {
        return "w budowie";
    }

    @Override
    public void executeCommand(CmdMethodParams params) {
        params.player.sendMessage(PREFIX + BLUE + "Komenda w budowie");
    }
}
