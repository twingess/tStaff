package net.twinges.staff.commands;

import net.frozenorb.qlib.command.Command;
import net.twinges.staff.jStaff;
import net.twinges.staff.util.CC;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.PrintStream;

public class TestCommand {

    @Command(names = "test", permission = "jstaff.test")
    public static void test(Player player) {
        if (jStaff.getInstance().getStaffModeHandler().isInStaffMode(player)) {
            player.sendMessage(CC.translate("&aYou are in staff mode!"));
        }
        if (!jStaff.getInstance().getStaffModeHandler().isInStaffMode(player)) {
            player.sendMessage(CC.translate("&cYou are not in staff mode!"));
        }

    }
    @Command(names = "test2", permission = "jstaff.test2")
    public static void test2(Player player) {
        if(jStaff.getInstance().getStaffModeHandler().isVanished(player)) {
            player.sendMessage(CC.translate("&aYou are vanished!"));
        }

        if (!jStaff.getInstance().getStaffModeHandler().isVanished(player)) {
            player.sendMessage(CC.translate("&cYou are not vanished!"));
        }
    }
}
