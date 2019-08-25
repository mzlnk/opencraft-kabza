package pl.opencraft.kabza.gui.items.domain.bagtypeeditor;

import org.bukkit.inventory.ItemStack;
import pl.opencraft.kabza.bags.repository.dto.BagType;
import pl.opencraft.kabza.gui.items.domain.BaseFireworkChargeGuiItem;
import pl.opencraft.kabza.gui.items.domain.GuiItemType;
import pl.opencraft.kabza.gui.items.guiparams.GuiParams;
import pl.opencraft.kabza.nbtserializer.dto.NbtTagDto;
import pl.opencraft.kabza.nbtserializer.dto.NbtTagType;

import java.util.HashMap;
import java.util.Map;

import static pl.opencraft.KabzaPlugin.*;

/**
 * Created by Marcin Zielonka on 25/08/2019.
 */

public abstract class BaseBagTypeEditorFireworkChargeGuiItem extends BaseFireworkChargeGuiItem<BagType> {

    public BaseBagTypeEditorFireworkChargeGuiItem(GuiItemType type) {
        super(type);
    }

    public BaseBagTypeEditorFireworkChargeGuiItem(GuiItemType type, GuiParams<BagType> params) {
        super(type, params);
    }

    @Override
    protected ItemStack addNbtTags(ItemStack item) {
        Map<String, NbtTagDto> tags = new HashMap<>();
        tags.put("Identity", new NbtTagDto(NbtTagType.STRING).withTagStringValue(BAG_TYPE_EDITOR_GUI_ITEM_IDENTITY));
        tags.put("GuiItemType", new NbtTagDto(NbtTagType.STRING).withTagStringValue(guiItemType.name()));

        return plugin.nbtSerializer.createOrUpdateNbtTags(item, PLUGIN_NBT_KEY_ID, tags);
    }

}
