package pl.opencraft.kabza.gui.items.domain;

import org.bukkit.inventory.ItemStack;
import pl.opencraft.kabza.bags.repository.dto.Identifiable;
import pl.opencraft.kabza.gui.items.guiparams.GuiParams;

/**
 * Created by Marcin Zielonka on 25/08/2019.
 */

public interface GuiItem<E extends Identifiable> {

    GuiItemType getGuiItemType();
    ItemStack toItemStack();
    void onRightClick();
    void onLeftClick();
    GuiItem<E> newInstance(GuiParams<E> params);
    void reload();

}
