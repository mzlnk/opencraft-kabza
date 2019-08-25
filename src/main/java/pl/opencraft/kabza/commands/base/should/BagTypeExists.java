package pl.opencraft.kabza.commands.base.should;

import pl.opencraft.kabza.commands.base.CmdMethodParams;

import static pl.opencraft.KabzaPlugin.plugin;
import static pl.opencraft.KabzaPlugin.PREFIX;
import static org.bukkit.ChatColor.RED;

public class BagTypeExists extends CmdParamsValidator {

    @Override
    boolean areParamsValid(CmdMethodParams params) {
        return plugin.bagTypesService.findBagType(params.bagTypeId).isPresent();
    }

    @Override
    String failMessage() {
        return PREFIX + RED + "Typ kabzy o danym id nie istnieje";
    }
}