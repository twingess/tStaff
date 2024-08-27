package net.twinges.staff.commands;

import net.frozenorb.qlib.command.Command;
import net.twinges.staff.jStaff;
import net.twinges.staff.util.CC;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class BuildModeCommand {


    @Command(names = "buildmode", permission = "tstaff.buildmode")
    public static void buildModeCommand(Player player) {
        if (!jStaff.getInstance().getStaffModeHandler().hasBuildModeEnabled(player)) {
            jStaff.getInstance().getStaffModeHandler().setBuildMode(player);
            player.sendMessage(CC.translate("&aYou have enabled build mode."));
        } else {
            jStaff.getInstance().getStaffModeHandler().removeBuildMode(player);
            player.sendMessage(CC.translate("&cYou have disabled build mode."));
        }
    }
}
