package pl.opencraft.kabza.gui.items.domain;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkEffectMeta;
import pl.opencraft.kabza.bags.repository.dto.Identifiable;
import pl.opencraft.kabza.gui.items.guiparams.GuiParams;

/**
 * Created by Marcin Zielonka on 25/08/2019.
 */

public abstract class BaseFireworkChargeGuiItem<E extends Identifiable> extends BaseGuiItem<E> {

    protected Color fireworkChargeColor = Color.BLACK;

    public BaseFireworkChargeGuiItem(GuiItemType type) {
        super(type);
    }

    public BaseFireworkChargeGuiItem(GuiItemType type, GuiParams<E> params) {
        super(type, params);
    }

    protected void withFireworkChargeColor(Color fireworkChargeColor) {
        this.fireworkChargeColor = fireworkChargeColor;
    }

    @Override
    public ItemStack toItemStack() {
        ItemStack item = super.toItemStack();

        FireworkEffectMeta meta = (FireworkEffectMeta) item.getItemMeta();
        FireworkEffect fireworkEffect = FireworkEffect.builder()
                .with(FireworkEffect.Type.BALL)
                .withColor(fireworkChargeColor)
                .withFade(fireworkChargeColor)
                .build();
        meta.setEffect(fireworkEffect);
        item.setItemMeta(meta);
        return item;
    }

}
