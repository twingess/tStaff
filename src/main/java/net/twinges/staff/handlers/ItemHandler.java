package net.twinges.staff.handlers;

import net.twinges.staff.util.CC;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemHandler {

    public ItemStack getCompass() {
        ItemStack itemStack = new ItemStack(Material.COMPASS);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(CC.translate("&6&lPhase Through"));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public ItemStack getBetterView() {
        ItemStack itemStack = new ItemStack(Material.CARPET);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(CC.translate("&6&lBetter View"));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public ItemStack getStaffGUI() {
        ItemStack itemStack = new ItemStack(Material.SKULL_ITEM);
        ItemMeta itemMeta=itemStack.getItemMeta();
        itemMeta.setDisplayName(CC.translate("&6&lStaff GUI"));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public ItemStack getWand() {
        ItemStack itemStack = new ItemStack(Material.WOOD_AXE);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(CC.translate("&6&lWorld Edit Wand"));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public ItemStack getRandomTeleport() {
        ItemStack itemStack = new ItemStack(Material.EMERALD);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(CC.translate("&6&lRandom Teleport"));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public ItemStack getInspectBook() {
        ItemStack itemStack = new ItemStack(Material.BOOK);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(CC.translate("&6&lInspect "));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
