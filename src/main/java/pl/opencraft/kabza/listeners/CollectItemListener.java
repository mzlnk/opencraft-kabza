package pl.opencraft.kabza.listeners;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import pl.opencraft.kabza.utils.InventoryUtil;

/**
 * Created by Marcin Zielonka on 15/08/2019.
 */

public class CollectItemListener implements Listener {

    @EventHandler
    public void onCollectItemToBag(EntityPickupItemEvent event) {
        if(!event.getEntityType().equals(EntityType.PLAYER)) {
            return;
        }

        Player player = (Player) event.getEntity();
        InventoryUtil inventoryUtil = new InventoryUtil(player);

        ItemStack newItem = inventoryUtil.addItemToApplicableBags(event.getItem().getItemStack());
        if(newItem != null) {
            event.getItem().setItemStack(newItem);
        } else {
            event.setCancelled(true);
            event.getItem().remove();
        }

    }

}
