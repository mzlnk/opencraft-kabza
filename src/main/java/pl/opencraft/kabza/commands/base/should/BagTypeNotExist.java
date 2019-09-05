package pl.opencraft.kabza.commands.base.should;

import pl.opencraft.kabza.commands.base.CmdMethodParams;

import static pl.opencraft.KabzaPlugin.plugin;
import static pl.opencraft.KabzaPlugin.PREFIX;
import static org.bukkit.ChatColor.RED;

/**
 * Created by Marcin Zielonka on 2019.09.05
 */

public class BagTypeNotExist extends CmdParamsValidator {


    @Override
    boolean areParamsValid(CmdMethodParams params) {
        return !plugin.bagTypesService.findBagType(params.bagTypeId).isPresent();
    }

    @Override
    String failMessage() {
        return PREFIX + RED + "Typ kabzy od danym ID juz istnieje!";
    }
}
