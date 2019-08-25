package pl.opencraft.kabza.gui.inventories.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;

/**
 * Created by Marcin Zielonka on 25/08/2019.
 */

public class MainGuiInventoryListener implements Listener {

    private GuiInventoryListener bagTypeEditorGuiInventoryListener = new BagTypeEditorGuiInventoryListener();

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        bagTypeEditorGuiInventoryListener.onInventoryClose(event);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        bagTypeEditorGuiInventoryListener.onInventoryClick(event);
    }

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event) {
        bagTypeEditorGuiInventoryListener.onInventoryDrag(event);
    }

}
