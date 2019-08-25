package pl.opencraft.kabza.gui.inventories.domain;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;

/**
 * Created by Marcin Zielonka on 25/08/2019.
 */

public interface GuiInventory {

    GuiInventoryType getGuiInventoryType();
    Inventory getInventory();
    void onInventoryClose(InventoryCloseEvent event);
    void onInventoryDrag(InventoryDragEvent event);
    void onInventoryClick(InventoryClickEvent event);
    void onTerminateTask();

}
