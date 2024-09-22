package net.twinges.staff.commands;

import net.twinges.staff.jStaff;
import net.twinges.staff.util.CC;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FreezeCommand implements CommandExecutor {


    //              /freeze <player>

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(CC.translate("&cYou must be a player to execute this command!"));
            return false;
        }
        Player player = (Player) sender;
        Player target = Bukkit.getPlayer(args[0]);
        if (!player.hasPermission("tstaff.freeze")) {
            player.sendMessage(CC.translate("&cNo permission."));
            return false;
        }
        if (args.length != 1) {
            player.sendMessage(CC.translate("&cUsage: /freeze <player>"));
            return false;
        }

        if (!jStaff.getInstance().getStaffModeHandler().isFrozen(target)) {
            jStaff.getInstance().getStaffModeHandler().freezePlayer(target);
            player.sendMessage(CC.translate("&aYou have successfully frozen " + target.getName()));
        } else {
            jStaff.getInstance().getStaffModeHandler().unFreezePlayer(target);
            player.sendMessage(CC.translate("&aYou have successfully unfrozen " + target.getName()));
        }







        return false;
    }
}
