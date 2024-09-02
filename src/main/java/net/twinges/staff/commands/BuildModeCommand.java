package net.twinges.staff.commands;

import net.frozenorb.qlib.command.Command;
import net.twinges.staff.jStaff;
import net.twinges.staff.util.CC;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BuildModeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            if (!jStaff.getInstance().getStaffModeHandler().hasBuildModeEnabled(player)) {
                jStaff.getInstance().getStaffModeHandler().setBuildMode(player);
                player.sendMessage(CC.translate("&aYou have enabled build mode"));
            } else {
                jStaff.getInstance().getStaffModeHandler().removeBuildMode(player);
                player.sendMessage(CC.translate("&cYou have disabled build mode."));
            }
        } else {
            sender.sendMessage(CC.translate("&cYou must be a player to execute this command."));
        }



        return false;
    }
}
