package net.twinges.staff.handlers;

import com.avaje.ebean.validation.NotNull;
import net.md_5.bungee.api.ChatMessageType;
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
import org.bukkit.plugin.Plugin;

import java.awt.*;
import java.util.*;
import java.util.List;

public class StaffModeHandler {

    public HashMap<UUID, ItemStack[]> invArmor;
    public HashMap<UUID, ItemStack[]> inv;
    private final jStaff plugin;


    public StaffModeHandler(jStaff plugin) {
        this.plugin = plugin;
        inv = new HashMap<>();
        invArmor = new HashMap<>();
    }



    public boolean isInStaffMode(Player player) {
        return player.hasMetadata("mod");
    }

    public boolean isVanished(Player player) {
        return player.hasMetadata("vanish");
    }

    public boolean hasBuildModeEnabled(Player player) {
        return player.hasMetadata("buildmode");
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
        player.setMetadata("vanish", new FixedMetadataValue(plugin, player));
    }

    public void removeVanish(Player player) {
        for (Player players : Bukkit.getOnlinePlayers()) {
            if (!players.hasPermission("tstaff.staff")) {
                player.showPlayer(players);
            }
        }
        player.removeMetadata("vanish", plugin);
    }

    public void setBuildMode(Player player) {
        player.setMetadata("buildmode", new FixedMetadataValue(plugin, player));
    }

    public void removeBuildMode(Player player) {
        player.removeMetadata("buildmode", plugin);
    }

    public Player getRandomPvP() {
        //Creating an array players;
        Player[] players = Bukkit.getOnlinePlayers().toArray(new Player[0]);
        //Doing the random class aka you.
        Random random = new Random();
        //Initializing the pvpPlayer, we're going to return it!
        Player pvpPlayer = null;
        //Looping through the players in the array.
        for (Player player : players) {
            //Checking if the players last damage is greater than 0 aka he took damage recently
            if (player.getWorld().getPVP() || player.getLastDamage() > 0) {
                // Running a random
                if (random.nextInt(players.length) > 1) {
                    //Setting pvpPlayer to player;
                    pvpPlayer = player;
                }
            }
        }
        return pvpPlayer;
    }

    public void openStaffGUI(Player player) {
        //Initialize Staff Members
        int staffmembers = 0;
        //Loop through the online players.
        for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {
            //Check if the online players have the permission.
            if (onlinePlayers.hasPermission("tstaff.staff")) {
                //Do an incrememnt depending on the players that have the permission, basically add a one to the "int staffmembers = 0" for each player that has that permission.
                staffmembers++;
            }
        }
        //Initialize a row, we are adding 8 because there is already a slot taken up by a member. And then dividing by 9 so we can count them in rows, not the amount of space we have in a row.
        int rows = (staffmembers + 8) / 9;
        // Do the GUI, in here, we do the "rows * 9" for the amount of rows we have. Basically doing the size.
        Inventory gui = Bukkit.createInventory(null, rows * 9, "Online Staff GUI");
        int slot = 0;
        for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {
            if (onlinePlayers.hasPermission("tstaff.staff")) {
                ItemStack playerHead = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
                SkullMeta skullMeta = (SkullMeta) playerHead.getItemMeta();
                if (skullMeta != null) {
                    skullMeta.setDisplayName(onlinePlayers.getDisplayName());
                    List<String> lore = new ArrayList<>();
                    lore.add(CC.translate("&7&m------------------"));
                    lore.add(CC.translate("&6Vanish: " + (jStaff.getInstance().getStaffModeHandler().isVanished(onlinePlayers) ? "&aYes" : "&cNo")));
                    lore.add(CC.translate("&6Staff Mode: " + (jStaff.getInstance().getStaffModeHandler().isInStaffMode(onlinePlayers) ? "&aYes" : "&cNo")));
                    lore.add(CC.translate("&7&m------------------"));
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
