package pl.opencraft.kabza.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.craftbukkit.libs.it.unimi.dsi.fastutil.ints.IntArrayList;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import pl.opencraft.kabza.bags.repository.dto.Bag;
import sun.jvm.hotspot.utilities.IntArray;

import java.util.Optional;

import static pl.opencraft.KabzaPlugin.plugin;

/**
 * Created by Marcin Zielonka on 15/08/2019.
 */

public class InventoryUtil {

    private PlayerInventory inventory;

    public InventoryUtil(Player player) {
        this.inventory = player.getInventory();
    }

    public Optional<InventoryEntity<Bag>> findApplicableBagByAllowedItem(ItemStack item) {
        for(int slotIdx = 0; slotIdx < 36; slotIdx++) {
            ItemStack itemStack = inventory.getItem(slotIdx);
            if(!plugin.bagsService.isBag(itemStack)) {
                continue;
            }

            Bag bag = plugin.bagsService.fromItemStack(itemStack).get();
            if(bag.)
        }
    }

    @Getter
    @AllArgsConstructor
    public static class InventoryEntity<T> {

        private int slot;
        private T entity;

    }

}
