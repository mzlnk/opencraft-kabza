package pl.opencraft.kabza.commands.base.should;

import pl.opencraft.kabza.bags.repository.dto.BagTypeItem;
import pl.opencraft.kabza.commands.base.CmdMethodParams;
import pl.opencraft.kabza.messages.MessageEnum;

/**
 * Created by Marcin Zielonka on 2019.09.05
 */

import java.util.Optional;

import static pl.opencraft.KabzaPlugin.PREFIX;
import static org.bukkit.ChatColor.RED;
import static pl.opencraft.KabzaPlugin.plugin;

public class AllowedItemNameNoIsValid extends CmdParamsValidator {

    @Override
    boolean areParamsValid(CmdMethodParams params) {
        return plugin.bagTypesService.findBagType(params.bagTypeId)
                .map(bagType -> bagType.findAllowedItemByType(params.itemType))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(BagTypeItem::getNames)
                .map(list -> params.nameNo >= 0 && params.nameNo <= list.size())
                .orElse(false);
    }

    @Override
    String failMessage() {
        return PREFIX + RED + plugin.messages.get(MessageEnum.CMD_ERROR_NAME_NO_NOT_VALID);
    }

}
