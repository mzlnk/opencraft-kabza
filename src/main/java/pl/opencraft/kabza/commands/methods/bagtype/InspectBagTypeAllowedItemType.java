package pl.opencraft.kabza.commands.methods.bagtype;

import pl.opencraft.kabza.bags.repository.dto.BagTypeItem;
import pl.opencraft.kabza.commands.base.CmdDescription;
import pl.opencraft.kabza.commands.base.CmdMethod;
import pl.opencraft.kabza.commands.base.CmdMethodParams;

import static pl.opencraft.kabza.commands.base.should.CmdParamsValidator.*;
import static pl.opencraft.KabzaPlugin.plugin;
import static org.bukkit.ChatColor.*;
import static pl.opencraft.kabza.utils.StringUtil.firstLetterUpperCase;

/**
 * Created by Marcin Zielonka on 2019.09.05
 */

public class InspectBagTypeAllowedItemType implements CmdMethod, CmdDescription {

    @Override
    public String description() {
        return "wyswietlenie informacji o dozwolonym przedmiocie w danym worku";
    }

    @Override
    public void executeCommand(CmdMethodParams params) {
        should(playerHasAdminPermission, params);
        should(itemTypeExists, params);
        should(bagTypeExists, params);
        should(allowedItemTypeExists, params);

        plugin.bagTypesService.findBagType(params.bagTypeId).ifPresent(bagType -> {
            StringBuilder sb = new StringBuilder();

            BagTypeItem bagTypeItem = null;
            for(BagTypeItem item : bagType.getAllowedItems()) {
                if(item.getType().equals(params.itemType)) {
                    bagTypeItem = item;
                }
            }

            if(bagTypeItem == null) {
                return;
            }

            sb.append(BLUE).append(BOLD).append("Allowed item in bag - ").append(firstLetterUpperCase(bagTypeItem.getType().name())).append(":").append(RESET).append("\n");

            sb.append(GRAY).append("all_names_allowed: ").append(WHITE).append(bagTypeItem.isAllNamesAllowed()).append("\n");
            sb.append(GRAY).append("all_lores_allowed: ").append(WHITE).append(bagTypeItem.isAllLoresAllowed()).append("\n");
            sb.append(GRAY).append("no_name_allowed: ").append(WHITE).append(bagTypeItem.isNoNameAllowed()).append("\n");
            sb.append(GRAY).append("no_lore_allowed: ").append(WHITE).append(bagTypeItem.isNoLoreAllowed()).append("\n");

            sb.append(GRAY).append("allowed_names:").append("\n");
            for(String name : bagTypeItem.getNames()) {
                sb.append(WHITE).append("- ").append(name).append("\n");
            }

            sb.append(GRAY).append("allowed_lores:").append("\n");
            for(int i = 0, j = 1; i < bagTypeItem.getLores().size(); i++, j++) {
                sb.append(WHITE).append("Lore #").append(j).append(":").append("\n");
                for(String s : bagTypeItem.getLores().get(i).getLore()) {
                    sb.append(WHITE).append("- ").append(s).append("\n");
                }
            }

            params.player.sendMessage(sb.toString());
        });
    }
}
