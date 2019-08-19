package pl.opencraft.kabza.commands.methods;

import org.bukkit.ChatColor;
import pl.opencraft.kabza.commands.base.CmdDescription;
import pl.opencraft.kabza.commands.base.CmdMethod;
import pl.opencraft.kabza.commands.base.CmdMethodParams;

import static pl.opencraft.KabzaPlugin.PREFIX;
import static pl.opencraft.KabzaPlugin.plugin;

/**
 * Created by Marcin Zielonka on 15/08/2019.
 */

public class Reload implements CmdMethod, CmdDescription {
    @Override
    public String description() {
        return "przeladowanie pluginu";
    }

    @Override
    public void executeCommand(CmdMethodParams params) {
        plugin.bagsService.reload();
        plugin.bagTypesService.reload();
        params.player.sendMessage(PREFIX + ChatColor.GREEN + "Plugin zostal zaladowany ponownie");
    }
}
