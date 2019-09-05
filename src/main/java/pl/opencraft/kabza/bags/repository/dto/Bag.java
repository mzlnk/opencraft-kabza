package pl.opencraft.kabza.bags.repository.dto;

import lombok.Data;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pl.opencraft.kabza.bags.exception.BagTypeNotFoundException;
import pl.opencraft.kabza.messages.MessageEnum;
import pl.opencraft.kabza.nbtserializer.dto.NbtTagDto;
import pl.opencraft.kabza.nbtserializer.dto.NbtTagType;

import java.util.*;

import static org.bukkit.ChatColor.*;
import static pl.opencraft.KabzaPlugin.*;

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

    private boolean containsItem(ItemStack itemStack) {
        return content.stream()
                .map(BagItem::toItemStack)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .anyMatch(is -> is.isSimilar(itemStack));
    }

    private int getItemAmount(ItemStack itemStack) {
        if (!this.containsItem(itemStack)) {
            return 0;
        }
        return content.stream()
                .map(BagItem::toItemStack)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .filter(is -> is.isSimilar(itemStack))
                .findFirst()
                .map(ItemStack::getAmount)
                .orElse(0);
    }

    public int getOccupiedSlots() {
        return content.stream()
                .mapToInt(item -> (int) Math.ceil((double) item.getAmount() / (double) item.getType().getMaxStackSize()))
                .sum();
    }

    public double getPercentageCapacity() {
        double occupiedSlots = this.getOccupiedSlots();
        double slotFactor = occupiedSlots / MAX_BAG_SLOTS;

        int itemsInOccupiedSlots = content.stream()
                .mapToInt(BagItem::getAmount)
                .sum();

        int maxItemsInOccupiedSlots = content.stream()
                .mapToInt(item -> {
                    int maxStackSize = item.getType().getMaxStackSize();
                    int slots = (int) Math.ceil((double) item.getAmount() / (double) maxStackSize);

                    return slots * maxStackSize;
                })
                .sum();

        return Math.round(((double) itemsInOccupiedSlots / (double) maxItemsInOccupiedSlots * slotFactor) * 10000.0D) / 100.0D;
    }

    public int getMaxItemAmountToAdd(ItemStack itemStack) {
        Material type = itemStack.getType();
        int availableSlots = ((int) MAX_BAG_SLOTS - this.getOccupiedSlots());

        int maxItemsInAvailableSlots = (Math.max(availableSlots, 0) * type.getMaxStackSize());
        int maxItemsInLastSlot = (type.getMaxStackSize() - (this.getItemAmount(itemStack) % type.getMaxStackSize()));

        if (maxItemsInLastSlot == type.getMaxStackSize()) {
            maxItemsInLastSlot = 0;
        }

        return (maxItemsInAvailableSlots + maxItemsInLastSlot);
    }

    public boolean isItemApplicable(ItemStack itemStack) {
        BagType bagType = plugin.bagTypesService.findBagType(bagTypeId).orElse(null);
        if (bagType == null) {
            return false;
        }

        if(bagType.isAllItemsAllowed()) {
            return true;
        }

        for(BagTypeItem bagTypeItem : bagType.getAllowedItems()) {
            if(bagTypeItem.isItemApplicable(itemStack)) {
                return true;
            }
        }

        return false;
    }

    public void addItem(ItemStack itemStack, int amount) {
        BagItem bagItem = null;
        for (BagItem i : content) {
            boolean result = i.toItemStack()
                    .map(is -> is.isSimilar(itemStack))
                    .orElse(false);

            if (result) {
                bagItem = i;
                break;
            }
        }
        if (bagItem == null) {
            content.add(BagItem.fromItemStack(itemStack, amount));
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

        lore.add(GRAY + plugin.messages.get(MessageEnum.BAG_INFO_OCCUPIED_SLOTS) + ": " + WHITE + this.getOccupiedSlots() + "/27");
        lore.add(GRAY + plugin.messages.get(MessageEnum.BAG_INFO_CAPACITY) + ": " + WHITE + this.getPercentageCapacity() + "%");
        lore.add("");

        itemMeta.setLore(lore);

        item.setItemMeta(itemMeta);

        Map<String, NbtTagDto> tags = new HashMap<>();
        tags.put("Identity", new NbtTagDto(NbtTagType.STRING).withTagStringValue(BAG_NBT_IDENTITY));
        tags.put("Uuid", new NbtTagDto(NbtTagType.STRING).withTagStringValue(uuid.toString()));
        item = plugin.nbtSerializer.createOrUpdateNbtTags(item, PLUGIN_NBT_KEY_ID, tags);

        return item;
    }

}
