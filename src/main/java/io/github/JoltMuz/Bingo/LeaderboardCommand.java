package io.github.JoltMuz.Bingo;

import java.util.*;
import java.util.stream.Collectors;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.Plugin;

import net.md_5.bungee.api.ChatColor;

public class LeaderboardCommand implements CommandExecutor, Listener {

	private final Plugin plugin;

    public LeaderboardCommand(Plugin plugin) {
        this.plugin = plugin;
    }


	@Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            List<String> topPlayers = getTopPlayers(54);
            Inventory inventory = plugin.getServer().createInventory(player, 54, "Top Players");

            for (String playerName : topPlayers) {
                ItemStack playerItem = createPlayerItem(playerName);
                inventory.addItem(playerItem);
            }

            player.openInventory(inventory);
        }
        return true;
    }

    private List<String> getTopPlayers(int count) 
    {
        return Points.points.entrySet()
                .stream()
                .sorted(Comparator.comparingInt(entry -> -entry.getValue())) // Sort in descending order
                .limit(count)
                .map(HashMap.Entry::getKey)
                .collect(Collectors.toList());
    }

    private ItemStack createPlayerItem(String playerName) {
        // Customize the ItemStack creation as per your plugin's needs
    	ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
	    SkullMeta meta = (SkullMeta) head.getItemMeta();
	    if (meta != null) {
	        meta.setOwner(playerName);
	        meta.setDisplayName(ChatColor.GOLD + playerName + ChatColor.DARK_GRAY + " [" + ChatColor.YELLOW + Points.points.getOrDefault(playerName,0) + ChatColor.DARK_GRAY + "]");	
	        head.setItemMeta(meta);
	    }
        return head;
    }
    
    @EventHandler
    public void inventoryClick(InventoryClickEvent e)
    {
    	if (e.getInventory().getName().equalsIgnoreCase("Top Players"))
    	{
    		e.setCancelled(true);
    	}
    }

}
