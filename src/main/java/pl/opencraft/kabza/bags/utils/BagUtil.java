package pl.opencraft.kabza.bags.utils;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Material;
import pl.opencraft.kabza.bags.repository.dto.Bag;
import pl.opencraft.kabza.bags.repository.dto.BagItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.bukkit.Material.*;

/**
 * Created by Marcin Zielonka on 15/08/2019.
 */

@AllArgsConstructor
public class BagUtil {

    private static final double MAX_BAG_SLOTS = 27.0D;
    private static final double ONE_SLOT_OCCUPY_PERCENTAGE = 1 / MAX_BAG_SLOTS;

    private Bag bag;

    public boolean canAddItem(Material type) {
        if(this.getOccupiedSlots() < MAX_BAG_SLOTS) {
            return true;
        }

        if(!this.containsItem(type)) {
            return false;
        }

        return false;
    }

    public int getOccupiedSlots() {
        return bag.getContent().stream()
                .mapToInt(item -> (int) Math.ceil((double) item.getAmount() / (double) BagItemSlotData.fromMaterial(item.getType()).getMaxItemsPerSlot()))
                .sum();
    }

    public double getPercentageCapacity() {
        double availableSlotsCapacity = (MAX_BAG_SLOTS - this.getOccupiedSlots()) / MAX_BAG_SLOTS;

        double restCapacity = bag.getContent().stream()
                .mapToDouble(item -> {
                    int maxItemsPerSlot = BagItemSlotData.fromMaterial(item.getType()).getMaxItemsPerSlot();
                    double occupiedSlots = (double) item.getAmount() / (double) maxItemsPerSlot;
                    double restSlot = occupiedSlots - Math.floor(occupiedSlots);

                    return (1.0D - restSlot) * ONE_SLOT_OCCUPY_PERCENTAGE;
                })
                .sum();

        return Math.round((1.0D - availableSlotsCapacity - restCapacity) * 10000.0D) / 100.0D;
    }

    private boolean containsItem(Material type) {
        return bag.getContent().stream()
                .map(BagItem::getType)
                .anyMatch(t -> t.equals(type));
    }

    private enum BagItemSlotData {

        SIGN(16, ACACIA_SIGN, BIRCH_SIGN, SPRUCE_SIGN, OAK_SIGN, JUNGLE_SIGN, DARK_OAK_SIGN),
        SNOWBALL(16, Material.SNOWBALL),
        BUCKET(16, Material.BUCKET, LAVA_BUCKET, WATER_BUCKET, MILK_BUCKET),
        ENDER_PEARL(16, Material.ENDER_PEARL),
        OTHER(64);

        @Getter(AccessLevel.PRIVATE)
        private int maxItemsPerSlot;

        @Getter(AccessLevel.PRIVATE)
        private List<Material> types;

        private BagItemSlotData(int maxItemsPerSlot, Material... types) {
            this.maxItemsPerSlot = maxItemsPerSlot;
            this.types = (types != null ? Arrays.asList(types) : new ArrayList<>());
        }

        private static BagItemSlotData fromMaterial(Material type) {
            return Arrays.stream(BagItemSlotData.values())
                    .filter(d -> d.types.contains(type))
                    .findFirst()
                    .orElse(OTHER);
        }

    }

}
