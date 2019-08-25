package pl.opencraft.kabza.gui.inventories.domain;

import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import pl.opencraft.kabza.bags.repository.dto.Identifiable;
import pl.opencraft.kabza.gui.items.guiparams.GuiParams;

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

}
