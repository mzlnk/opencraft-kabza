package pl.opencraft.kabza.gui.items.domain;

import org.bukkit.Color;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import pl.opencraft.kabza.bags.repository.dto.Identifiable;
import pl.opencraft.kabza.gui.items.guiparams.GuiParams;

/**
 * Created by Marcin Zielonka on 25/08/2019.
 */

public abstract class BasePotionGuiItem<E extends Identifiable> extends BaseGuiItem<E> {

    protected Color potionColor = Color.BLUE;

    public BasePotionGuiItem(GuiItemType type) {
        super(type);
    }

    public BasePotionGuiItem(GuiItemType type, GuiParams<E> params) {
        super(type, params);
    }

    protected void withPotionColor(Color potionColor) {
        this.potionColor = potionColor;
    }

    @Override
    public ItemStack toItemStack() {
        ItemStack item = super.toItemStack();

        PotionMeta pm = (PotionMeta) item.getItemMeta();
        pm.setColor(potionColor);
        item.setItemMeta(pm);

        return item;
    }

}
