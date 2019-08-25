package pl.opencraft.kabza.gui.items.domain;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pl.opencraft.kabza.bags.repository.dto.Identifiable;
import pl.opencraft.kabza.gui.items.guiparams.GuiParams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Marcin Zielonka on 25/08/2019.
 */

public abstract class BaseGuiItem<E extends Identifiable> implements GuiItem<E> {

    protected GuiItemType guiItemType;
    protected GuiParams<E> params;

    protected Material type = Material.APPLE;
    protected String displayName;
    protected List<String> lore = new ArrayList<>();

    protected boolean enchantmentShadow;

    protected abstract void applyParams();
    protected abstract ItemStack addNbtTags(ItemStack itemStack);

    public BaseGuiItem(GuiItemType guiItemType) {
        this.guiItemType = guiItemType;
    }

    public BaseGuiItem(GuiItemType guiItemType, GuiParams<E> params) {
        this(guiItemType);
        this.params = params;
    }

    @Override
    public GuiItemType getGuiItemType() {
        return guiItemType;
    }

    @Override
    public ItemStack toItemStack() {
        ItemStack item = new ItemStack(type, 1);
        item = this.addItemDescription(item);
        item = this.addNbtTags(item);

        return item;
    }

    @Override
    public void reload() {

    }

    protected void withType(Material type) {
        this.type = type;
    }

    protected void withDisplayName(String displayName) {
        this.displayName = displayName;
    }

    protected void withLore(String... lore) {
        this.lore = Arrays.asList(lore);
    }

    protected void withEnchantmentShadow() {
        this.enchantmentShadow = true;
    }

    protected void openInventory() {

    }

    protected ItemStack addItemDescription(ItemStack itemStack) {
        if (enchantmentShadow) {
            itemStack.addUnsafeEnchantment(Enchantment.LUCK, 1);
        }
        ItemMeta meta = itemStack.getItemMeta();
        if (displayName != null) {
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayName));
        }
        List<String> lore = new ArrayList<>();
        for (String s : this.lore) {
            lore.add(ChatColor.translateAlternateColorCodes('&', s));
        }
        meta.setLore(lore);

        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_DESTROYS);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_PLACED_ON);
        meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);

        itemStack.setItemMeta(meta);
        return itemStack;
    }
}
