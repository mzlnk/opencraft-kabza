package pl.opencraft.kabza.commands.base.should;

import pl.opencraft.kabza.commands.base.CmdMethodParams;

import static org.bukkit.ChatColor.RED;
import static pl.opencraft.KabzaPlugin.PREFIX;

/**
 * Created by Marcin Zielonka on 30/08/2019.
 */

public class ItemTypeExists extends CmdParamsValidator {

    @Override
    boolean areParamsValid(CmdMethodParams params) {
        return params.itemType != null;
    }

    @Override
    String failMessage() {
        return PREFIX + RED + "Przedmiot od danym typie nie istnieje!";
    }
}
