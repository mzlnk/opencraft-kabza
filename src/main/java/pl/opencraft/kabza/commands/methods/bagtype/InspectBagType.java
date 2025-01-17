package pl.opencraft.kabza.commands.methods.bagtype;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import pl.opencraft.kabza.bags.repository.dto.BagTypeItem;
import pl.opencraft.kabza.commands.base.BaseCmdMethod;
import pl.opencraft.kabza.commands.base.CmdMethodParams;
import pl.opencraft.kabza.messages.MessageEnum;

import static org.bukkit.ChatColor.*;
import static pl.opencraft.KabzaPlugin.plugin;
import static pl.opencraft.kabza.commands.base.should.CmdParamsValidator.*;
import static pl.opencraft.kabza.utils.StringUtil.firstLetterUpperCase;

public class InspectBagType extends BaseCmdMethod {

    @Override
    public String description() {
        return plugin.messages.get(MessageEnum.CMD_INFO_INSPECT_BAG_TYPE);
    }

    @Override
    public void executeCommand(CmdMethodParams params) {
        should(playerHasAdminPermission, params);
        should(bagTypeExists, params);

        plugin.bagTypesService.findBagType(params.bagTypeId).ifPresent(bagType -> {
            StringBuilder sb = new StringBuilder();

            sb.append(BLUE).append(BOLD).append("BagType info:").append("\n")
                    .append(GRAY).append("id: ").append(WHITE).append(bagType.getId()).append("\n")
                    .append(GRAY).append("bag name: ").append(WHITE).append(ChatColor.translateAlternateColorCodes('&', bagType.getBagName())).append("\n")
                    .append(GRAY).append("bag_description:").append("\n");

            for (String s : bagType.getBagDescription()) {
                sb.append(ChatColor.translateAlternateColorCodes('&', s)).append("\n");
            }

            sb.append(GRAY).append("bag_item_type: ").append(WHITE).append(firstLetterUpperCase(bagType.getBagItemType().name())).append("\n");

            sb.append(GRAY).append("crafting_enabled: ").append(WHITE).append(bagType.isCraftingEnabled()).append("\n").append(GRAY).append("\n");
            sb.append(GRAY).append("shapeless_crafting: ").append(WHITE).append(bagType.isShapelessCrafting()).append("\n");


            sb.append("crafting_recipe:").append("\n");
            int i = 0;
            for (Material m : bagType.getCraftingRecipe()) {
                sb.append(WHITE).append("- ").append(i++).append(": ").append((m != null ? (firstLetterUpperCase(m.name())) : "null")).append("\n");
            }

            sb.append(GRAY).append("all_items_allowed: ").append(WHITE).append(bagType.isAllItemsAllowed()).append("\n");

            sb.append(GRAY).append("allowed_item_types:").append("\n");
            for (BagTypeItem item : bagType.getAllowedItems()) {
                sb.append(WHITE).append("- ").append(firstLetterUpperCase(item.getType().name())).append("\n");
            }

            params.player.sendMessage(sb.toString());
        });
    }
}
