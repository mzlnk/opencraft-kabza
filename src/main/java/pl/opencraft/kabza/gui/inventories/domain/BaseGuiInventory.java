package pl.opencraft.kabza.gui.inventories.domain;

import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import pl.opencraft.kabza.bags.repository.dto.BagType;
import pl.opencraft.kabza.bags.repository.dto.Identifiable;
import pl.opencraft.kabza.gui.inventories.service.BagTypeEditorGuiInventoryService;
import pl.opencraft.kabza.gui.inventories.service.GuiInventoryActivityInfo;
import pl.opencraft.kabza.gui.items.domain.GuiItem;
import pl.opencraft.kabza.gui.items.guiparams.GuiParams;

import java.util.UUID;

import static pl.opencraft.KabzaPlugin.*;

/**
 * Created by Marcin Zielonka on 25/08/2019.
 */

public abstract class BaseGuiInventory<E extends Identifiable> implements GuiInventory {

    protected static final int DOUBLE_CHEST_INVENTORY_SIZE = 54;

    protected GuiInventoryType moduleGuiInventoryType;
    protected GuiParams<E> params;

    protected String inventoryTitle = "";
    protected InventoryType inventoryType;
    protected int inventorySize = 54;
    protected Inventory inventory;

    public BaseGuiInventory(GuiInventoryType guiInventoryType, GuiParams<E> params) {
        this.moduleGuiInventoryType = guiInventoryType;
        this.params = params;
    }

    protected abstract void prepareInventory();

    @Override
    public Inventory getInventory() {
        return inventory;
    }

    @Override
    public void onInventoryClose(InventoryCloseEvent event) {
        //plugin.debugLogger.info("BaseModuleGuiInventory - InventoryCloseEvent thrown!");
        UUID uuid = event.getPlayer().getUniqueId();
        BagTypeEditorGuiInventoryService service = plugin.bagTypeEditorGuiInventoryService;

        service.removePlayerFromGuiInventory(uuid);
    }

    @Override
    public void onInventoryDrag(InventoryDragEvent event) {
        //plugin.debugLogger.info("BaseModuleGuiInventory - InventoryDragEvent thrown!");
        event.setCancelled(true);
    }

    @Override
    public void onInventoryClick(InventoryClickEvent event) {
        //plugin.debugLogger.info("BaseModuleGuiInventory - InventoryClickEvent thrown!");
        try {
            GuiItem item = plugin.guiItemService.getGuiItem(event.getCurrentItem(), params);

            if(event.isLeftClick()) {
                item.onLeftClick();
            }

            if(event.isRightClick()) {
                item.onRightClick();
            }

            event.setCancelled(true);
        } catch (NotGuiItemException e) {
        }
    }

    @Override
    public void onTerminateTask() {

    }

    protected void withInventoryType(InventoryType inventoryType) {
        this.inventoryType = inventoryType;
    }

    protected void withInventorySize(int inventorySize) {
        this.inventorySize = inventorySize;
    }

    protected void withInventoryTitle(String inventoryTitle) {
        this.inventoryTitle = inventoryTitle;
    }

    protected void addItemToInventory(int slot, GuiItem item) {
        this.inventory.setItem(slot, item.toItemStack());
    }

    protected void addItemToInventory(int slot, ItemStack item) {
        this.inventory.setItem(slot, item);
    }

    protected void openInventory(GuiInventory inventoryGui) {
        GuiInventoryActivityInfo activityInfo = new GuiInventoryActivityInfo<BagType>(params, inventoryGui);
        Bukkit.getScheduler().runTask(plugin, () -> {
            params.getPlayer().closeInventory();
            params.getPlayer().openInventory(inventoryGui.getInventory());
            plugin.bagTypesService.putPlayerInGuiInventory(activityInfo); //todo: change bagtypeeditorguiservice to overall inv gui service
        });
    }

}
