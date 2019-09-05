package pl.opencraft.kabza.commands.base.should;

import pl.opencraft.kabza.bags.repository.dto.BagTypeItem;
import pl.opencraft.kabza.bags.repository.dto.BagTypeItemLore;
import pl.opencraft.kabza.commands.base.CmdMethodParams;

import java.util.Optional;

import static org.bukkit.ChatColor.RED;
import static pl.opencraft.KabzaPlugin.PREFIX;
import static pl.opencraft.KabzaPlugin.plugin;

/**
 * Created by Marcin Zielonka on 2019.09.05
 */

public class AllowedItemLoreLineIsValid extends CmdParamsValidator {

    @Override
    boolean areParamsValid(CmdMethodParams params) {
        return plugin.bagTypesService.findBagType(params.bagTypeId)
                .map(bagType -> bagType.findAllowedItemByType(params.itemType))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(BagTypeItem::getLores)
                .map(list -> list.get(params.loreNo))
                .map(BagTypeItemLore::getLore)
                .map(list -> params.lineNo >= 0 && params.lineNo <= list.size())
                .orElse(false);
    }

    @Override
    String failMessage() {
        return PREFIX + RED + "Niewlasciwy numer linii!";
    }

}
