package net.twinges.staff.listeners;

import net.twinges.staff.handlers.StaffModeHandler;
import net.twinges.staff.jStaff;
import net.twinges.staff.util.CC;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.Inventory;

public class StaffListeners implements Listener {


    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (jStaff.getInstance().getStaffModeHandler().isFrozen(player)) return;
        if (jStaff.getInstance().getStaffModeHandler().isInStaffMode(player)) {
            if (jStaff.getInstance().getStaffModeHandler().hasBuildModeEnabled(player)) return;
            event.setCancelled(true);
            player.sendMessage(CC.translate("&cYou cannot break blocks while in staff mode."));
        }

    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if (jStaff.getInstance().getStaffModeHandler().isFrozen(player)) return;
        if (jStaff.getInstance().getStaffModeHandler().isInStaffMode(player)) {
            if (jStaff.getInstance().getStaffModeHandler().hasBuildModeEnabled(player)) return;
            event.setCancelled(true);
            player.sendMessage(CC.translate("&cYou cannot place blocks while in staff mode."));
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (jStaff.getInstance().getStaffModeHandler().isFrozen(player)) return;
        if (player.hasPermission("tstaff.admin")) return;
        if (event.getWhoClicked() instanceof Player) {
            if (jStaff.getInstance().getStaffModeHandler().isInStaffMode(player)) {
                if (jStaff.getInstance().getStaffModeHandler().hasBuildModeEnabled(player)) return;
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onGameModeChange(PlayerGameModeChangeEvent event) {
        Player player = event.getPlayer();
        if (jStaff.getInstance().getStaffModeHandler().isInStaffMode(player)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission("tstaff.staff")) {
            if (jStaff.getInstance().getStaffModeHandler().isInStaffMode(player)) {
                jStaff.getInstance().getStaffModeHandler().removeStaffMode(player);
            }
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission("tstaff.staff")) {
            if (!jStaff.getInstance().getStaffModeHandler().isInStaffMode(player)) {
                jStaff.getInstance().getStaffModeHandler().putInStaffMode(player);
            }
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Block block = event.getClickedBlock();

        if (!jStaff.getInstance().getStaffModeHandler().isVanished(player)) return;
        if (block == null) return;
        if (block.getType() != Material.CHEST && block.getType() != Material.TRAPPED_CHEST) return;
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if (jStaff.getInstance().getStaffModeHandler().isFrozen(player)) return;


        event.setCancelled(true);
        Chest chest = (Chest) block.getState();
        Inventory inventory = Bukkit.createInventory(null, chest.getInventory().getSize(), "Silent Open");
        inventory.setContents(chest.getInventory().getContents());
        player.openInventory(inventory);

        player.sendMessage(CC.translate("&cOpening chest silently..."));
    }

    @EventHandler
    public void onDropItem(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        if (jStaff.getInstance().getStaffModeHandler().isInStaffMode(player)) {
            event.setCancelled(true);
            player.updateInventory();
        }
    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        Player damager = (Player) event.getDamager();
        Player entity = (Player) event.getEntity();
        if (jStaff.getInstance().getStaffModeHandler().isInStaffMode(damager) || jStaff.getInstance().getStaffModeHandler().isVanished(damager)) {
            event.setCancelled(true);
            damager.sendMessage(CC.translate("&cYou cannot hit entities while in staff mode!"));
        }
        if (jStaff.getInstance().getStaffModeHandler().isFrozen(damager)) {
            event.setCancelled(true);
        }
        if (jStaff.getInstance().getStaffModeHandler().isFrozen(entity)) {
            event.setCancelled(true);
            damager.sendMessage(CC.translate("&c" + entity.getName() + " is currently frozen!"));
        }
    }


    @EventHandler
    public void onPickup(PlayerPickupItemEvent event) {
        Player player = event.getPlayer();
        if (jStaff.getInstance().getStaffModeHandler().isInStaffMode(player) || jStaff.getInstance().getStaffModeHandler().isVanished(player)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if (jStaff.getInstance().getStaffModeHandler().isInStaffChat(player)) {
            event.setCancelled(true);
            for (Player players : Bukkit.getOnlinePlayers()) {
                if (players.hasPermission("tstaff.staff")) {
                    players.sendMessage(CC.translate("&9[SC] &a" + event.getPlayer().getName() + "&f: " + event.getMessage()));
                }
            }
        }
        if (jStaff.getInstance().getStaffModeHandler().isFrozen(player)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (jStaff.getInstance().getStaffModeHandler().isFrozen(player)) {
            event.setCancelled(true);
        }
    }


}

