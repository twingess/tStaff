package net.twinges.staff.commands;

import net.twinges.staff.jStaff;
import net.twinges.staff.util.CC;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UnFreezeCommand implements CommandExecutor {

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
            player.sendMessage(CC.translate("&cUsage: /unfreeze <player>"));
            return false;
        }
        if (!jStaff.getInstance().getStaffModeHandler().isFrozen(target)) {
            player.sendMessage(CC.translate("&c" + target.getName() + " is not frozen!"));
            return false;
        }
        jStaff.getInstance().getStaffModeHandler().unFreezePlayer(target);




        return false;
    }
}
