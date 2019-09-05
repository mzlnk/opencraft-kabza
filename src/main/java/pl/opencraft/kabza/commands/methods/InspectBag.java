package pl.opencraft.kabza.commands.methods;

import pl.opencraft.kabza.bags.repository.dto.BagItem;
import pl.opencraft.kabza.commands.base.CmdDescription;
import pl.opencraft.kabza.commands.base.CmdMethod;
import pl.opencraft.kabza.commands.base.CmdMethodParams;
import pl.opencraft.kabza.messages.MessageEnum;

import static org.bukkit.ChatColor.*;
import static pl.opencraft.KabzaPlugin.plugin;
import static pl.opencraft.kabza.commands.base.should.CmdParamsValidator.*;

public class InspectBag implements CmdMethod, CmdDescription {

    @Override
    public String description() {
        return plugin.messages.get(MessageEnum.CMD_INFO_INSPECT_BAG);
    }

    @Override
    public void executeCommand(CmdMethodParams params) {
        should(playerHasAdminPermission, params);
        should(playerHasBagInHand, params);

        plugin.bagsService.fromItemStack(params.item).ifPresent(bag -> {
            StringBuilder sb = new StringBuilder();

            sb.append(BLUE).append(BOLD).append("Bag info:").append("\n")
              .append(GRAY).append("uuid: ").append(WHITE).append(bag.getUuid()).append("\n")
              .append(GRAY).append("bag_type_id: ").append(WHITE).append(bag.getBagTypeId()).append("\n")
              .append(GRAY).append("content:").append("\n");

            for (BagItem bagItem : bag.getContent()) {
                String type = bagItem.getType().name();

                int maxStackSize = bagItem.getType().getMaxStackSize();
                String amount = (bagItem.getAmount() / maxStackSize) + " x " + maxStackSize + " + " + (bagItem.getAmount() % maxStackSize);

                sb.append(WHITE).append("- ").append(type.substring(0, 1).toUpperCase()).append(type.substring(1).toLowerCase()).append(": ").append(amount).append("\n");
            }

            params.player.sendMessage(sb.toString());
        });
    }
}
