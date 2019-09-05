package pl.opencraft.kabza.commands.base.should;

import pl.opencraft.kabza.bags.repository.dto.BagTypeItem;
import pl.opencraft.kabza.commands.base.BaseCmdMethod;
import pl.opencraft.kabza.commands.base.CmdMethodParams;
import pl.opencraft.kabza.messages.MessageEnum;

import java.util.Optional;

import static org.bukkit.ChatColor.RED;
import static pl.opencraft.KabzaPlugin.PREFIX;
import static pl.opencraft.KabzaPlugin.plugin;

/**
 * Created by Marcin Zielonka on 2019.09.05
 */

public class AllowedItemLoreNoIsValid extends CmdParamsValidator {

    @Override
    boolean areParamsValid(CmdMethodParams params) {
        return plugin.bagTypesService.findBagType(params.bagTypeId)
                .map(bagType -> bagType.findAllowedItemByType(params.itemType))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(BagTypeItem::getLores)
                .map(list -> params.loreNo >= 0 && params.loreNo <= list.size())
                .orElse(false);
    }

    @Override
    String failMessage() {
        return PREFIX + RED + plugin.messages.get(MessageEnum.CMD_ERROR_LORE_NO_NOT_VALID);
    }

}
