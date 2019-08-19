package pl.opencraft.kabza.commands.base.should;

import org.bukkit.Material;
import pl.opencraft.kabza.commands.base.CmdMethodParams;

import static pl.opencraft.KabzaPlugin.PREFIX;
import static org.bukkit.ChatColor.RED;

public class PlayerHasNoItemInHand extends CmdParamsValidator {

    @Override
    boolean areParamsValid(CmdMethodParams params) {
        return params.item != null && params.item.getType().equals(Material.AIR);
    }

    @Override
    String failMessage() {
        return PREFIX + RED + "Nie mozesz trzymac przedmioty w rece!";
    }
}
