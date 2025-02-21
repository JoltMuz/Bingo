package io.github.JoltMuz.Bingo;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class Bingo extends JavaPlugin implements Listener, CommandExecutor
{
    public static Plugin plugin;
    
    @Override
    public void onEnable()
    {
        plugin = this;

        this.getCommand("bingo").setExecutor(this);
        this.getCommand("bround").setExecutor(new Round());
        this.getCommand("bend").setExecutor(new End());
        this.getCommand("lb").setExecutor(new LeaderboardCommand(plugin));
        getServer().getPluginManager().registerEvents(new LeaderboardCommand(plugin),this);
        getServer().getPluginManager().registerEvents(new Points(),this);
        getServer().getPluginManager().registerEvents(this,this);

    }

    @Override
    public void onDisable()
    {

    }
    public static HashMap<String,Inventory> inventories = new HashMap<>();
    public static String signature = ChatColor.BOLD.toString() + ChatColor.GOLD + "B" + ChatColor.YELLOW + "i" + ChatColor.GOLD + "n" + ChatColor.YELLOW + "g" + ChatColor.GOLD + "o" + ChatColor.DARK_GRAY + " 》 ";

    @EventHandler
    public void onJoin(PlayerJoinEvent e)
    {
    	inventories.put(e.getPlayer().getName(), Bukkit.createInventory(e.getPlayer(), 45, ChatColor.RED + "B" + ChatColor.YELLOW + "i" + ChatColor.GREEN + "n" + ChatColor.AQUA + "g" + ChatColor.BLUE + "o"));
        Points.points.put(e.getPlayer().getName(),0);
    }
    @Override
    public boolean onCommand(CommandSender Sender, Command command, String s, String[] strings)
    {
        if (Sender instanceof Player)
        {
            Player p = (Player) Sender;

            if (Round.RoundisRunning)
            {
                p.openInventory(inventories.get(p.getName()));
            }
            else
            {
                p.sendMessage(signature + ChatColor.RED + "No on-going round at the moment.");
            }
        }
        return true;
    }

}