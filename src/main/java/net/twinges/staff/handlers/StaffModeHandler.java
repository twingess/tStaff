package net.twinges.staff.handlers;

import net.twinges.staff.jStaff;
import net.twinges.staff.util.CC;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.*;

public class StaffModeHandler {

    public HashMap<UUID, ItemStack[]> invArmor;
    public HashMap<UUID, ItemStack[]> inv;
    private final jStaff plugin;
    public List<Player> vanishList;
    public List<Player> buildModeList;


    public StaffModeHandler(jStaff plugin) {
        this.plugin = plugin;
        inv = new HashMap<>();
        invArmor = new HashMap<>();
        vanishList = new ArrayList<>();
        buildModeList = new ArrayList<>();
    }


    public boolean isInStaffMode(Player player) {
        return player.hasMetadata("mod");
    }

    public boolean isVanished(Player player) {
        return vanishList.contains(player);
    }

    public boolean hasBuildModeEnabled(Player player) {
        return buildModeList.contains(player);
    }

    public void putInStaffMode(Player player) {
        PlayerInventory playerInventory = player.getInventory();
        player.setMetadata("mod", new FixedMetadataValue(plugin, player));
        setVanished(player);

        invArmor.put(player.getUniqueId(), playerInventory.getArmorContents());
        inv.put(player.getUniqueId(), playerInventory.getContents());

        playerInventory.clear();
        playerInventory.setArmorContents(null);
        player.setGameMode(GameMode.CREATIVE);


        playerInventory.setItem(0, jStaff.getInstance().getItemHandler().getCompass());
        if (player.isOp()) {
            playerInventory.setItem(2, jStaff.getInstance().getItemHandler().getWand());
            playerInventory.setItem(3, jStaff.getInstance().getItemHandler().getBetterView());
        } else {
            playerInventory.setItem(2, jStaff.getInstance().getItemHandler().getBetterView());
        }
        playerInventory.setItem(1, jStaff.getInstance().getItemHandler().getInspectBook());
        playerInventory.setItem(7, jStaff.getInstance().getItemHandler().getStaffGUI());
        playerInventory.setItem(8, jStaff.getInstance().getItemHandler().getRandomTeleport());

    }

    public void removeStaffMode(Player player) {

        PlayerInventory playerInventory = player.getInventory();

        playerInventory.setContents(inv.get(player.getUniqueId()));
        playerInventory.setArmorContents(invArmor.get(player.getUniqueId()));
        player.setGameMode(GameMode.SURVIVAL);
        invArmor.remove(player.getUniqueId());
        inv.remove(player.getUniqueId());
        player.updateInventory();
        player.removeMetadata("mod", jStaff.getInstance());
        removeVanish(player);
    }

    public void setVanished(Player player) {
        for (Player players : Bukkit.getOnlinePlayers()) {
            if (!players.hasPermission("tstaff.staff")) {
                player.hidePlayer(players);
            }
        }
        vanishList.add(player);
    }

    public void removeVanish(Player player) {
        for (Player players : Bukkit.getOnlinePlayers()) {
            if (!players.hasPermission("tstaff.staff")) {
                player.showPlayer(players);
            }
        }
        vanishList.remove(player);
    }

    public void setBuildMode(Player player) {
        buildModeList.add(player);
    }

    public void removeBuildMode(Player player) {
        buildModeList.remove(player);
    }

    public Player getRandomPvP() {
        Player[] players = Bukkit.getOnlinePlayers().toArray(new Player[0]);
        Random random = new Random();
        Player pvpPlayer = null;

        for (Player player : players) {
            if (player.getWorld().getPVP() || player.getLastDamage() > 0) {
                if (random.nextInt(players.length) < 1) {
                    pvpPlayer = player;
                }
            }
        }
        return null;
    }

    public void openStaffGUI(Player player) {
        int staffmembers = 0;
        for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {
            if (onlinePlayers.hasPermission("tstaff.staff")) {
                staffmembers++;
            }
        }
        int rows = (staffmembers + 8) / 9;
        Inventory gui = Bukkit.createInventory(null, rows * 9, "Online Staff GUI");
        int slot = 0;
        for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {
            if (onlinePlayers.hasPermission("tstaff.staff")) {
                ItemStack playerHead = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
                SkullMeta skullMeta = (SkullMeta) playerHead.getItemMeta();
                if (skullMeta != null) {
                    skullMeta.setDisplayName(onlinePlayers.getDisplayName());
                    List<String> lore = new ArrayList<>();
                    lore.add(CC.translate("&fVanish: " + (jStaff.getInstance().getStaffModeHandler().isVanished(onlinePlayers) ? "&aYes" : "&cNo")));
                    lore.add(CC.translate("&fStaff Mode: " + (jStaff.getInstance().getStaffModeHandler().isInStaffMode(onlinePlayers) ? "&aYes" : "&cNo")));
                    skullMeta.setLore(lore);
                    skullMeta.setOwner(onlinePlayers.getDisplayName());
                    playerHead.setItemMeta(skullMeta);
                }
                gui.setItem(slot++, playerHead);
            }
        }
        player.openInventory(gui);
    }
}
