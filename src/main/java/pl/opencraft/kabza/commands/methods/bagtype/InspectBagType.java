package pl.opencraft.kabza.commands.methods.bagtype;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import pl.opencraft.kabza.commands.base.CmdDescription;
import pl.opencraft.kabza.commands.base.CmdMethod;
import pl.opencraft.kabza.commands.base.CmdMethodParams;

import static pl.opencraft.kabza.commands.base.should.CmdParamsValidator.*;
import static pl.opencraft.KabzaPlugin.plugin;
import static org.bukkit.ChatColor.*;

public class InspectBagType implements CmdMethod, CmdDescription {

    @Override
    public String description() {
        return "wyswietlenie informacji o typie kabzy";
    }

    @Override
    public void executeCommand(CmdMethodParams params) {
        should(playerHasAdminPermission, params);
        should(bagTypeExists, params);

        plugin.bagTypesService.findBagType(params.bagTypeId).ifPresent(bagType -> {
            StringBuilder sb = new StringBuilder();

            sb.append(BLUE).append(BOLD).append("BagType info:").append("\n")
                    .append(GRAY).append("bag_type_id: ").append(WHITE).append(bagType.getId()).append("\n")
                    .append(GRAY).append("bag_name: ").append(WHITE).append(ChatColor.translateAlternateColorCodes('&', bagType.getBagName())).append("\n")
                    .append(GRAY).append("bag_description:").append("\n");

            for(String s : bagType.getBagDescription()) {
                sb.append(ChatColor.translateAlternateColorCodes('&', s)).append("\n");
            }

            sb.append(GRAY).append("crafting_enabled: ").append(WHITE).append(bagType.isCraftingEnabled()).append("\n")
                    .append(GRAY).append("crafting_recipe:").append("\n");

            int i = 0;
            for(Material m : bagType.getCraftingRecipe()) {
                sb.append(WHITE).append("- ").append(i++).append(": ").append((m != null ? (m.name().substring(0, 1).toUpperCase() + m.name().substring(1).toLowerCase()) : "null")).append("\n");
            }

            sb.append(GRAY).append("allowed_item_types:").append("\n");
            /*for(Material type : bagType.getAllowedItems()) {
                sb.append(WHITE).append("- ").append(type.name().substring(0, 1).toUpperCase()).append(type.name().substring(1).toLowerCase()).append("\n");
            }*/

            params.player.sendMessage(sb.toString());
        });
    }
}
