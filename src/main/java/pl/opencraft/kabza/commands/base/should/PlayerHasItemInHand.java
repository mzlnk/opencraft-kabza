package pl.opencraft.kabza.commands.base.should;

import org.bukkit.Material;
import pl.opencraft.kabza.commands.base.CmdMethodParams;

import static org.bukkit.ChatColor.RED;
import static pl.opencraft.KabzaPlugin.PREFIX;

/**
 * Created by Marcin Zielonka on 30/08/2019.
 */

public class PlayerHasItemInHand extends CmdParamsValidator {

    @Override
    boolean areParamsValid(CmdMethodParams params) {
        return params.item == null || params.item.getType().equals(Material.AIR);
    }

    @Override
    String failMessage() {
        return PREFIX + RED + "Musisz trzymac przedmiot w rece!";
    }

}
