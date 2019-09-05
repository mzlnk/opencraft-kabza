package pl.opencraft.kabza.commands.base.should;

import pl.opencraft.kabza.bags.repository.dto.BagType;
import pl.opencraft.kabza.commands.base.CmdMethodParams;

import static org.bukkit.ChatColor.RED;
import static pl.opencraft.KabzaPlugin.PREFIX;
import static pl.opencraft.KabzaPlugin.plugin;

/**
 * Created by Marcin Zielonka on 2019.09.05
 */

public class BagDescriptionLineIsValid extends CmdParamsValidator {

    @Override
    boolean areParamsValid(CmdMethodParams params) {
        return plugin.bagTypesService.findBagType(params.bagTypeId)
                .map(BagType::getBagDescription)
                .map(list -> params.lineNo >= 0 && params.lineNo <= list.size())
                .orElse(false);
    }

    @Override
    String failMessage() {
        return PREFIX + RED + "Niewlasciwy numer linii!";
    }

}
