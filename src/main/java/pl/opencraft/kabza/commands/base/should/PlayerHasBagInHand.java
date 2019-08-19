package pl.opencraft.kabza.commands.base.should;

import pl.opencraft.kabza.commands.base.CmdMethodParams;

import static pl.opencraft.KabzaPlugin.plugin;
import static pl.opencraft.KabzaPlugin.PREFIX;
import static org.bukkit.ChatColor.RED;

public class PlayerHasBagInHand extends CmdParamsValidator {

    @Override
    boolean areParamsValid(CmdMethodParams params) {
        return plugin.bagsService.isBag(params.item);
    }

    @Override
    String failMessage() {
        return PREFIX + RED + "Musisz trzymac Kabze w reku!";
    }
}
