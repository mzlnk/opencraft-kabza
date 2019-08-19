package pl.opencraft.kabza.commands.base.should;

import pl.opencraft.kabza.commands.base.CmdMethodParams;

import static pl.opencraft.KabzaPlugin.PREFIX;
import static org.bukkit.ChatColor.RED;

public class PlayerHasAdminPermission extends CmdParamsValidator {

    @Override
    boolean areParamsValid(CmdMethodParams params) {
        return params.player.hasPermission("kabza.admin");
    }

    @Override
    String failMessage() {
        return PREFIX + RED + "Nie masz odpowiednich uprawnien!";
    }
}
