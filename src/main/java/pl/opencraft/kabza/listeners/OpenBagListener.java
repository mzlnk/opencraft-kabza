package pl.opencraft.kabza.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import pl.opencraft.kabza.bags.repository.dto.BagItem;

import static pl.opencraft.KabzaPlugin.plugin;

public class OpenBagListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onOpenBag(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();

        if (!plugin.bagsService.isBag(item)) {
            return;
        }

        if (!event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            event.setCancelled(true);
            return;
        }
        if (!event.getBlockFace().equals(BlockFace.UP)) {
            event.setCancelled(true);
            return;
        }

        if (event.getClickedBlock() == null) {
            event.setCancelled(true);
            return;
        }

        Block b = event.getClickedBlock().getRelative(BlockFace.UP);
        if (!b.isEmpty()) {
            event.setCancelled(true);
            return;
        }

        plugin.bagsService.fromItemStack(item).ifPresent(bag -> {
            if(bag.isOpened()) {
                player.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
                return;
            }

            b.setType(Material.CHEST);
            Chest chest = (Chest) b.getState();

            int slotIdx = 0;
            for(BagItem bagItem : bag.getContent()) {
                int itemMaxStackSize = bagItem.getType().getMaxStackSize();

                int fullItemSlots = (int)((double) bagItem.getAmount() / (double) itemMaxStackSize);
                int restAmount = bagItem.getAmount() % itemMaxStackSize;

                for(int i = 0; i < fullItemSlots; i++) {
                    ItemStack itemStack = bagItem.toItemStack().orElse(new ItemStack(Material.AIR));
                    itemStack.setAmount(itemMaxStackSize);
                    chest.getBlockInventory().setItem(slotIdx++, itemStack);
                }
                if(restAmount > 0) {
                    ItemStack itemStack = bagItem.toItemStack().orElse(new ItemStack(Material.AIR));
                    itemStack.setAmount(restAmount);
                    chest.getBlockInventory().setItem(slotIdx++, itemStack);
                }
            }

            bag.setOpened(true);
            plugin.bagsService.updateBag(bag);
            player.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
        });
    }

}
