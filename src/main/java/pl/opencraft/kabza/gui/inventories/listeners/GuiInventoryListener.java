package pl.opencraft.kabza.gui.inventories.listeners;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;

/**
 * Created by Marcin Zielonka on 25/08/2019.
 */

public interface GuiInventoryListener {

    void onInventoryClose(InventoryCloseEvent event);
    void onInventoryDrag(InventoryDragEvent event);
    void onInventoryClick(InventoryClickEvent event);

}
