package pl.opencraft.kabza.commands.base.should;

import pl.opencraft.kabza.bags.repository.dto.BagType;
import pl.opencraft.kabza.commands.base.CmdMethodParams;
import pl.opencraft.kabza.messages.MessageEnum;

import static pl.opencraft.KabzaPlugin.plugin;
import static pl.opencraft.KabzaPlugin.PREFIX;
import static org.bukkit.ChatColor.RED;

/**
 * Created by Marcin Zielonka on 30/08/2019.
 */

public class AllowedItemTypeExists extends CmdParamsValidator {

    @Override
    boolean areParamsValid(CmdMethodParams params) {
        return plugin.bagTypesService.findBagType(params.bagTypeId)
                .map(BagType::getAllowedItems)
                .map(list -> list.stream().anyMatch(bagTypeItem -> bagTypeItem.getType().equals(params.itemType)))
                .orElse(false);
    }

    @Override
    String failMessage() {
        return PREFIX + RED + plugin.messages.get(MessageEnum.CMD_ERROR_ITEM_TYPE_NOT_FOUND);
    }

}
