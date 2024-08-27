package net.twinges.staff.listeners;

import net.twinges.staff.jStaff;
import net.twinges.staff.util.CC;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

public class ItemListeners implements Listener {

    @EventHandler
    public void onIE(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (jStaff.getInstance().getStaffModeHandler().isInStaffMode(player)) {
            if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {

                if (player.getItemInHand().isSimilar(jStaff.getInstance().getItemHandler().getCompass())) {
                    player.performCommand("thru");
                }

                if (player.getItemInHand().isSimilar(jStaff.getInstance().getItemHandler().getRandomTeleport())) {
                    Player pvpers = jStaff.getInstance().getStaffModeHandler().getRandomPvP();
                    if (pvpers != null) {
                        player.teleport(pvpers.getLocation());
                        player.sendMessage(CC.translate("&aYou have been teleported to " + pvpers.getName()));
                    } else {
                        player.sendMessage(CC.translate("&aThere is currently no players PvPing."));
                    }
                }
                if (player.getItemInHand().isSimilar(jStaff.getInstance().getItemHandler().getStaffGUI())) {
                    jStaff.getInstance().getStaffModeHandler().openStaffGUI(player);
                }
            }
        }
    }


    @EventHandler
    public void onIEE(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        Entity clickedTarget = event.getRightClicked();
        if (jStaff.getInstance().getStaffModeHandler().isInStaffMode(player)) {
            if (player.getItemInHand().isSimilar(jStaff.getInstance().getItemHandler().getInspectBook())) {

                if (clickedTarget instanceof Player) {
                    Player target = (Player) clickedTarget;
                    player.openInventory(target.getInventory());
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onICE(InventoryClickEvent event) {
        Inventory inventory = event.getInventory();
        String inventoryTitle = inventory.getTitle();


        if (inventoryTitle.equals("Online Staff GUI")) {
            event.setCancelled(true);
        }
    }
}
