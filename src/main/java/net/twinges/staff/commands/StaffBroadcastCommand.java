package net.twinges.staff.commands;

import net.twinges.staff.util.CC;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StaffBroadcastCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(CC.translate("&cYou must be a player to execute this command!"));
            return false;
        }

        Player player = (Player) sender;
        if (!player.hasPermission("tstaff.staff")) {
            player.sendMessage(CC.translate("&cNo permission."));
            return false;
        }
        if (!(args.length >= 1)) {
            player.sendMessage(CC.translate("&cUsage: /staffbroadcast <message>"));
            return false;
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < args.length; i++) {
            builder.append(args[i]).append(" ");
        }
        for (Player players : Bukkit.getOnlinePlayers()) {
            if (players.hasPermission("tstaff.staff")) {
                players.sendMessage(CC.translate("&4[Staff Announcement] &f" + builder.toString()));
            }
        }




        return false;
    }
}
