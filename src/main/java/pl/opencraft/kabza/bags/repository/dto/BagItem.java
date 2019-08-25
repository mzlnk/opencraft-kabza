package pl.opencraft.kabza.bags.repository.dto;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import pl.opencraft.kabza.nbtserializer.dto.ItemStackDto;

import java.util.Optional;

import static pl.opencraft.KabzaPlugin.plugin;

/**
 * Created by Marcin Zielonka on 14/08/2019.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BagItem {

    public static BagItem fromItemStack(ItemStack itemStack, int amount) {
        BagItem bagItem = new BagItem();
        bagItem.type = itemStack.getType();
        bagItem.amount = amount;

        ItemStackDto itemStackDto = plugin.nbtSerializer.serialize(itemStack).orElse(new ItemStackDto());
        bagItem.itemNbtRepresentation = new Gson().toJson(itemStackDto, ItemStackDto.class);

        return bagItem;
    }

    private Material type;
    private int amount;
    private String itemNbtRepresentation;

    public void add(int amount) {
        this.amount += amount;
    }

    public Optional<ItemStack> toItemStack() {
        ItemStackDto itemStackDto = new Gson().fromJson(itemNbtRepresentation, ItemStackDto.class);
        if(itemStackDto == null) {
            return Optional.empty();
        }

        return plugin.nbtSerializer.deserialize(itemStackDto);
    }

}
