package pl.opencraft.kabza.bags.repository.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pl.opencraft.kabza.bags.exception.BagTypeNotFoundException;
import pl.opencraft.kabza.nbtserializer.dto.NbtTagDto;
import pl.opencraft.kabza.nbtserializer.dto.NbtTagType;

import java.util.*;

import static org.bukkit.ChatColor.*;
import static pl.opencraft.KabzaPlugin.BAG_NBT_ID;
import static pl.opencraft.KabzaPlugin.plugin;

/**
 * Created by Marcin Zielonka on 14/08/2019.
 */

@Data
@AllArgsConstructor
public class Bag {

    private UUID uuid;
    private boolean opened;
    private String bagTypeId;
    private List<BagItem> content;

    public ItemStack toItemStack() {
        BagType bagType = plugin.bagTypesService.findBagType(bagTypeId)
                .orElseThrow(BagTypeNotFoundException::new);

        ItemStack item = new ItemStack(bagType.getBagItemType(), 1);
        ItemMeta itemMeta = item.getItemMeta();

        itemMeta.setDisplayName(translateAlternateColorCodes('&', bagType.getBagName()));

        List<String> lore = new ArrayList<>();
        for (String s : bagType.getBagDescription()) {
            lore.add(translateAlternateColorCodes('&', s));
        }
        lore.add("");
        lore.add(GRAY + "Zajete sloty: " + WHITE + ""); // todo: code here
        lore.add(GRAY + "Zapelnienie: " + WHITE + ""); // todo: code here

        itemMeta.setLore(lore);

        item.setItemMeta(itemMeta);

        Map<String, NbtTagDto> tags = new HashMap<>();
        tags.put("uuid", new NbtTagDto(NbtTagType.STRING).withTagStringValue(uuid.toString()));
        item = plugin.nbtSerializer.createOrUpdateNbtTags(item, BAG_NBT_ID, tags);

        return item;
    }

}
