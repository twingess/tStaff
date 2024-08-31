package net.twinges.staff.commands;

import net.frozenorb.qlib.command.Command;
import net.twinges.staff.jStaff;
import net.twinges.staff.util.CC;
import org.bukkit.entity.Player;


public class TestCommand {

    @Command(names = "test", permission = "op")
    public static void test(Player player) {
        if (jStaff.getInstance().getStaffModeHandler().isInStaffMode(player)) {
            player.sendMessage(CC.translate("&aYou are in staff mode!"));
        }
        if (!jStaff.getInstance().getStaffModeHandler().isInStaffMode(player)) {
            player.sendMessage(CC.translate("&cYou are not in staff mode!"));
        }

    }
    @Command(names = "test2", permission = "op")
    public static void test2(Player player) {
        jStaff.getInstance().getStaffModeHandler().openStaffGUI(player);
    }

    @Command(names = "randompvp", permission = "op")
    public static void randomPvP(Player player) {
        Player pvpers = jStaff.getInstance().getStaffModeHandler().getRandomPvP();
        if (pvpers != null) {
            player.sendMessage(CC.translate("&aYou have been teleported to " + pvpers.getName()));
            player.teleport(pvpers.getLocation());
        } else {
            player.sendMessage(CC.translate("&cThere is currently no one fighting."));
        }
    }

    @Command(names = "amivanish", permission = "op")
    public static void amIVanish(Player player) {
        if (jStaff.getInstance().getStaffModeHandler().isVanished(player)) {
            player.sendMessage(CC.translate("&aYou are vanished"));
        }
        if (!jStaff.getInstance().getStaffModeHandler().isVanished(player)) {
            player.sendMessage(CC.translate("&cYou are not vanished."));
        }
    }
}
