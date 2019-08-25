package pl.opencraft.kabza.gui.inventories.service;

import lombok.Data;
import pl.opencraft.kabza.bags.repository.dto.Identifiable;
import pl.opencraft.kabza.gui.inventories.domain.GuiInventory;
import pl.opencraft.kabza.gui.items.guiparams.GuiParams;

import java.util.UUID;

/**
 * Created by Marcin Zielonka on 25/08/2019.
 */

@Data
public class GuiInventoryActivityInfo<E extends Identifiable> {

    private UUID playerUuid;
    private E e;
    private GuiInventory guiInventory;

    public GuiInventoryActivityInfo(GuiParams<E> params, GuiInventory moduleGuiInventory) {
        this.playerUuid = params.getPlayer().getUniqueId();
        this.e = params.getE();
        this.guiInventory = moduleGuiInventory;
    }

}
