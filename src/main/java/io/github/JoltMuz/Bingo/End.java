package io.github.JoltMuz.Bingo;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class End implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args)
    {
       if (commandSender.isOp())
    	{
    		Bukkit.broadcastMessage(Bingo.signature + ChatColor.YELLOW + ChatColor.BOLD.toString() + "The Bingo round has ended!");
            List<String> keys = Points.points.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).limit(3).map(Map.Entry::getKey).collect(Collectors.toList());
            if (keys.size() > 0)
            {
                Bukkit.broadcastMessage(Bingo.signature + ChatColor.YELLOW + "♕ #1 》" + keys.get(0) + " 》 " +  ChatColor.BLUE + Points.points.get(keys.get(0)));
            }
            if (keys.size() > 1)
            {
                Bukkit.broadcastMessage(Bingo.signature + ChatColor.WHITE + "♖ #2 》" + keys.get(1) + " 》 " + ChatColor.BLUE + Points.points.get(keys.get(1)));
            }
            if (keys.size() > 2)
            {
                Bukkit.broadcastMessage(Bingo.signature + ChatColor.GOLD + "♘ #3 》" + keys.get(2) + " 》 " + ChatColor.BLUE + Points.points.get(keys.get(2)));
            }
            if (Round.RoundisRunning)
            {
            	Round.round.cancel();
            }
    	}
    	else
    	{
    		commandSender.sendMessage(Bingo.signature + ChatColor.RED + "You must be operator to use this command!");
    	}

        return true;
    }
}