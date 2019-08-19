package pl.opencraft.kabza.bags.repository.dto;

import lombok.Data;
import org.bukkit.Material;
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
public class Bag {

    private static final double MAX_BAG_SLOTS = 27.0D;
    private static final double ONE_SLOT_OCCUPY_PERCENTAGE = 1 / MAX_BAG_SLOTS;

    public static Bag newInstance(String bagTypeId) {
        Bag bag = new Bag();

        bag.uuid = UUID.randomUUID();
        bag.opened = false;
        bag.bagTypeId = bagTypeId;
        bag.content = new ArrayList<>();

        return bag;
    }

    private Bag() {
    }

    private UUID uuid;
    private boolean opened;
    private String bagTypeId;
    private List<BagItem> content;

    private boolean containsItem(Material type) {
        return content.stream()
                .map(BagItem::getType)
                .anyMatch(t -> t.equals(type));
    }

    private int getItemAmount(Material type) {
        if (!this.containsItem(type)) {
            return 0;
        }
        for (BagItem item : content) {
            if (item.getType().equals(type)) {
                return item.getAmount();
            }
        }
        return 0;
    }

    public int getOccupiedSlots() {
        return content.stream()
                .mapToInt(item -> (int) Math.ceil((double) item.getAmount() / (double) item.getType().getMaxStackSize()))
                .sum();
    }

    public double getPercentageCapacity() {
        double availableSlotsCapacity = (MAX_BAG_SLOTS - this.getOccupiedSlots()) / MAX_BAG_SLOTS;

        double restCapacity = content.stream()
                .mapToDouble(item -> {
                    int maxStackSize = item.getType().getMaxStackSize();
                    double occupiedSlots = (double) item.getAmount() / (double) maxStackSize;
                    double restSlot = occupiedSlots - Math.floor(occupiedSlots);

                    return (1.0D - restSlot) * ONE_SLOT_OCCUPY_PERCENTAGE;
                })
                .sum();

        return Math.round((1.0D - availableSlotsCapacity - restCapacity) * 10000.0D) / 100.0D;
    }

    public int getMaxItemAmountToAdd(Material type) {
        int amount = 0;

        amount += (Math.max(MAX_BAG_SLOTS - this.getOccupiedSlots(), 0) * type.getMaxStackSize());
        amount += type.getMaxStackSize() - (this.getItemAmount(type) % type.getMaxStackSize());

        return amount;
    }

    public boolean isApplicableItem(Material type) {
        List<Material> applicableItems = plugin.bagTypesService.findBagType(bagTypeId)
                .map(BagType::getAllowedItems).orElse(new ArrayList<>());

        return applicableItems.contains(type);
    }

    public void addItem(Material type, int amount) {
        BagItem bagItem = null;
        for (BagItem i : content) {
            if (i.getType().equals(type)) {
                bagItem = i;
                break;
            }
        }
        if (bagItem == null) {
            content.add(new BagItem(type, amount));
        } else {
            bagItem.add(amount);
        }
        plugin.bagsService.updateBag(this);
    }

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

        lore.add(GRAY + "Zajete sloty: " + WHITE + this.getOccupiedSlots() + "/27");
        lore.add(GRAY + "Zapelnienie: " + WHITE + this.getPercentageCapacity() + "%");
        lore.add("");

        itemMeta.setLore(lore);

        item.setItemMeta(itemMeta);

        Map<String, NbtTagDto> tags = new HashMap<>();
        tags.put("uuid", new NbtTagDto(NbtTagType.STRING).withTagStringValue(uuid.toString()));
        item = plugin.nbtSerializer.createOrUpdateNbtTags(item, BAG_NBT_ID, tags);

        return item;
    }

}
