package pl.opencraft.kabza.gui.inventories.listeners;

import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import pl.opencraft.kabza.bags.repository.dto.BagType;
import pl.opencraft.kabza.gui.inventories.service.BagTypeEditorGuiInventoryService;
import pl.opencraft.kabza.gui.inventories.service.GuiInventoryActivityInfo;

import java.util.UUID;

import static pl.opencraft.KabzaPlugin.plugin;

/**
 * Created by Marcin Zielonka on 25/08/2019.
 */

public class BagTypeEditorGuiInventoryListener implements GuiInventoryListener {


    @Override
    public void onInventoryClose(InventoryCloseEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();
        BagTypeEditorGuiInventoryService service = plugin.bagTypeEditorGuiInventoryService;

        if(!service.isPlayerInGuiInventory(uuid)) {
            return;
        }

        GuiInventoryActivityInfo<BagType> activityInfo = service.getGuiInventoryActivityInfo(uuid);
        activityInfo.getGuiInventory().onInventoryClose(event);
    }

    @Override
    public void onInventoryDrag(InventoryDragEvent event) {
        UUID uuid = event.getWhoClicked().getUniqueId();
        BagTypeEditorGuiInventoryService service = plugin.bagTypeEditorGuiInventoryService;

        if(!service.isPlayerInGuiInventory(uuid)) {
            return;
        }

        GuiInventoryActivityInfo activityInfo = service.getGuiInventoryActivityInfo(uuid);
        activityInfo.getGuiInventory().onInventoryDrag(event);
    }

    @Override
    public void onInventoryClick(InventoryClickEvent event) {
        UUID uuid = event.getWhoClicked().getUniqueId();
        BagTypeEditorGuiInventoryService service = plugin.bagTypeEditorGuiInventoryService;

        if(!service.isPlayerInGuiInventory(uuid)) {
            return;
        }

        GuiInventoryActivityInfo<BagType> activityInfo = service.getGuiInventoryActivityInfo(uuid);
        activityInfo.getGuiInventory().onInventoryClick(event);
    }

}
