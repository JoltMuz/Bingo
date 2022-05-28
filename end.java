package io.github.joltmuz.bingo;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class end implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args)
    {
       if (commandSender.isOp())
    	{
    		Bukkit.broadcastMessage(bingo.signature + ChatColor.YELLOW + ChatColor.BOLD.toString() + "The Bingo Event has ended!");
            List<String> keys = points.points.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).limit(3).map(Map.Entry::getKey).collect(Collectors.toList());
            if (keys.size() > 0)
            {
                Bukkit.broadcastMessage(bingo.signature + ChatColor.AQUA + "♕ #1 》" + keys.get(0));
            }
            if (keys.size() > 1)
            {
                Bukkit.broadcastMessage(bingo.signature + ChatColor.YELLOW + "♖ #2 》" + keys.get(1));
            }
            if (keys.size() > 2)
            {
                Bukkit.broadcastMessage(bingo.signature + ChatColor.WHITE + "♘ #3 》" + keys.get(2));
            }
            if (round.RoundisRunning)
            {
                round.round.cancel();
            }
    	}
    	else
    	{
    		commandSender.sendMessage(bingo.signature + ChatColor.RED + "You must be operator to use this command!");
    	}

        return true;
    }
}
