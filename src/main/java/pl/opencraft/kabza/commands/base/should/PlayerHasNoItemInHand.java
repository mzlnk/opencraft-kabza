package pl.opencraft.kabza.commands.base.should;

import org.bukkit.Material;
import pl.opencraft.kabza.commands.base.CmdMethodParams;
import pl.opencraft.kabza.messages.MessageEnum;

import static pl.opencraft.KabzaPlugin.PREFIX;
import static org.bukkit.ChatColor.RED;
import static pl.opencraft.KabzaPlugin.plugin;

public class PlayerHasNoItemInHand extends CmdParamsValidator {

    @Override
    boolean areParamsValid(CmdMethodParams params) {
        return params.item == null || params.item.getType().equals(Material.AIR);
    }

    @Override
    String failMessage() {
        return PREFIX + RED + plugin.messages.get(MessageEnum.CMD_ERROR_PLAYER_HAS_ITEM_IN_HAND);
    }

}
