package pl.opencraft.kabza.utils;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import pl.opencraft.kabza.bags.repository.dto.Bag;
import pl.opencraft.kabza.bags.service.BagsService;

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

    public boolean hasBagInInventory() {
        for(int slotIdx = 0; slotIdx < 36; slotIdx++) {
            if(plugin.bagsService.isBag(inventory.getItem(slotIdx))) {
                return true;
            }
        }
        return false;
    }

    public ItemStack addItemToApplicableBags(ItemStack item) {
        while (item.getAmount() > 0) {
            Optional<InventoryEntity<Bag>> bagOptional = this.findApplicableBagByAllowedItem(item.getType());
            if (!bagOptional.isPresent()) {
                return item;
            }
            bagOptional.ifPresent(bagEntity -> {
                Bag bag = bagEntity.getEntity();
                int amountToRemove = Math.min(bagEntity.getMaxAmount(), item.getAmount());
                bag.addItem(item.getType(), amountToRemove);
                item.setAmount(item.getAmount() - amountToRemove);
                inventory.setItem(bagEntity.getSlot(), bag.toItemStack());
            });
        }
        return (item.getAmount() > 0 ? item : null);
    }

    private Optional<InventoryEntity<Bag>> findApplicableBagByAllowedItem(Material type) {
        for (int slotIdx = 0; slotIdx < 36; slotIdx++) {
            ItemStack itemStack = inventory.getItem(slotIdx);
            if (!plugin.bagsService.isBag(itemStack)) {
                continue;
            }

            Bag bag = plugin.bagsService.fromItemStack(itemStack).get();
            if(!bag.isApplicableItem(type)) {
                continue;
            }
            int maxItemAmountToAdd = bag.getMaxItemAmountToAdd(type);
            if (maxItemAmountToAdd > 0) {
                return Optional.of(new InventoryEntity<>(slotIdx, maxItemAmountToAdd, bag));
            }
        }
        return Optional.empty();
    }

    @Getter(AccessLevel.PRIVATE)
    @AllArgsConstructor
    private static class InventoryEntity<T> {

        private int slot;
        private int maxAmount;
        private T entity;

    }

}
