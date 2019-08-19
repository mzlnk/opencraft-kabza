package pl.opencraft.kabza.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;
import pl.opencraft.kabza.bags.repository.dto.Bag;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static pl.opencraft.KabzaPlugin.plugin;

/**
 * Created by Marcin Zielonka on 15/08/2019.
 */

public class CraftBagListener implements Listener {

    private Map<UUID, UUID> preCraftedBags = new HashMap<>();

    @EventHandler(priority = EventPriority.HIGH)
    public void onCraftBag(PrepareItemCraftEvent event) {
        UUID uuid = event.getViewers().get(0).getUniqueId();
        if (preCraftedBags.containsKey(uuid)) {
            ItemStack item = event.getInventory().getResult();
            if (item == null || plugin.bagsService.isBag(item)) {
                plugin.bagsService.removeBag(preCraftedBags.get(uuid));
                preCraftedBags.remove(uuid);
            }
            return;
        }

        plugin.bagTypesService.fromRecipe(event.getInventory().getMatrix()).ifPresent(bagType -> {
            Bag bag = plugin.bagsService.createNewBag(bagType.getBagTypeId());
            event.getInventory().setResult(bag.toItemStack());
            preCraftedBags.put(event.getViewers().get(0).getUniqueId(), bag.getUuid());
        });
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();
        if (!preCraftedBags.containsKey(uuid)) {
            return;
        }

        plugin.bagsService.removeBag(preCraftedBags.get(uuid));
        preCraftedBags.remove(event.getPlayer().getUniqueId());
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onInventoryClick(InventoryClickEvent event) {
        UUID uuid = event.getWhoClicked().getUniqueId();

        if (!preCraftedBags.containsKey(uuid)) {
            return;
        }

        if (!event.getSlotType().equals(InventoryType.SlotType.RESULT)) {
            return;
        }

        ItemStack result = event.getCurrentItem();
        if (plugin.bagsService.isBag(result)) {
            preCraftedBags.remove(uuid);
        }
    }

}
