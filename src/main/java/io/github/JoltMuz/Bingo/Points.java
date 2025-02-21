package io.github.JoltMuz.Bingo;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;
import java.util.HashMap;

public class Points implements Listener
{
    static HashMap<String,Integer> points = new HashMap<>();

    @EventHandler
    public void PointsSystem(InventoryClickEvent e)
    {
    	Player p = (Player) e.getWhoClicked();
    	ItemStack clicked = e.getCurrentItem();

    	if (e.getClickedInventory() != null && e.getClickedInventory().getName().equals(ChatColor.RED + "B" + ChatColor.YELLOW + "i" + ChatColor.GREEN + "n" + ChatColor.AQUA + "g" + ChatColor.BLUE + "o") && points.containsKey(p.getName())) {
    	    e.setCancelled(true);

    	    if (p.getInventory().contains(clicked.getType())) 
    	    {
    	    	
    	        if (p.getItemOnCursor() != null && p.getItemOnCursor().getType() == clicked.getType()) {
    	            p.setItemOnCursor(new ItemStack(Material.AIR));
    	        }

    	        points.put(p.getName(), points.get(p.getName()) + 1);
    	        p.sendMessage(Bingo.signature + ChatColor.GREEN + "You claimed 1 point, Total: " + points.get(p.getName()));
    	        p.getInventory().remove( new ItemStack(clicked.getType(), 1));
    	        Bingo.inventories.get(p.getName()).setItem(e.getSlot(), new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.GREEN.getData()));

    	        if (clicked.getItemMeta().getLore().contains(ChatColor.BLUE + "Get this item to win extra 3 points!")) {
    	            points.put(p.getName(), points.get(p.getName()) + 3);
    	            p.sendMessage(Bingo.signature + ChatColor.BLUE + "You got 3 extra points for an enchanted item! Total: " + points.get(p.getName()));
    	        }

    	        // Check for full lines
    	        if ( e.getSlot() > 1 && e.getSlot() < 7 && Bingo.inventories.get(p.getName()).getItem(2).getType() == Material.STAINED_GLASS_PANE && Bingo.inventories.get(p.getName()).getItem(3).getType() == Material.STAINED_GLASS_PANE && Bingo.inventories.get(p.getName()).getItem(4).getType() == Material.STAINED_GLASS_PANE && Bingo.inventories.get(p.getName()).getItem(5).getType() == Material.STAINED_GLASS_PANE && Bingo.inventories.get(p.getName()).getItem(6).getType() == Material.STAINED_GLASS_PANE)
                {
                    points.put(p.getName(),points.get(p.getName()) + 3);
                    p.sendMessage(Bingo.signature + ChatColor.DARK_AQUA + "You got 3 extra points for a full line, Bingo! Total: " + points.get(p.getName()));
                }
                if ( e.getSlot() > 10 && e.getSlot() < 16 && Bingo.inventories.get(p.getName()).getItem(11).getType() == Material.STAINED_GLASS_PANE && Bingo.inventories.get(p.getName()).getItem(12).getType() == Material.STAINED_GLASS_PANE && Bingo.inventories.get(p.getName()).getItem(13).getType() == Material.STAINED_GLASS_PANE && Bingo.inventories.get(p.getName()).getItem(14).getType() == Material.STAINED_GLASS_PANE && Bingo.inventories.get(p.getName()).getItem(15).getType() == Material.STAINED_GLASS_PANE)
                {
                    points.put(p.getName(),points.get(p.getName()) + 3);
                    p.sendMessage(Bingo.signature + ChatColor.DARK_AQUA + "You got 3 extra points for a full line, Bingo! Total: " + points.get(p.getName()));
                }
                if ( e.getSlot() > 19 && e.getSlot() < 25 && Bingo.inventories.get(p.getName()).getItem(20).getType() == Material.STAINED_GLASS_PANE && Bingo.inventories.get(p.getName()).getItem(21).getType() == Material.STAINED_GLASS_PANE && Bingo.inventories.get(p.getName()).getItem(22).getType() == Material.STAINED_GLASS_PANE && Bingo.inventories.get(p.getName()).getItem(23).getType() == Material.STAINED_GLASS_PANE && Bingo.inventories.get(p.getName()).getItem(24).getType() == Material.STAINED_GLASS_PANE)
                {
                    points.put(p.getName(),points.get(p.getName()) + 3);
                    p.sendMessage(Bingo.signature + ChatColor.DARK_AQUA + "You got 3 extra points for a full line, Bingo! Total: " + points.get(p.getName()));
                }
                if ( e.getSlot() > 28 && e.getSlot() < 34 && Bingo.inventories.get(p.getName()).getItem(29).getType() == Material.STAINED_GLASS_PANE && Bingo.inventories.get(p.getName()).getItem(30).getType() == Material.STAINED_GLASS_PANE && Bingo.inventories.get(p.getName()).getItem(31).getType() == Material.STAINED_GLASS_PANE && Bingo.inventories.get(p.getName()).getItem(32).getType() == Material.STAINED_GLASS_PANE && Bingo.inventories.get(p.getName()).getItem(33).getType() == Material.STAINED_GLASS_PANE)
                {
                    points.put(p.getName(),points.get(p.getName()) + 3);
                    p.sendMessage(Bingo.signature + ChatColor.DARK_AQUA + "You got 3 extra points for a full line, Bingo! Total: " + points.get(p.getName()));
                }
                if ( e.getSlot() > 37 && e.getSlot() < 43 && Bingo.inventories.get(p.getName()).getItem(38).getType() == Material.STAINED_GLASS_PANE && Bingo.inventories.get(p.getName()).getItem(39).getType() == Material.STAINED_GLASS_PANE && Bingo.inventories.get(p.getName()).getItem(40).getType() == Material.STAINED_GLASS_PANE && Bingo.inventories.get(p.getName()).getItem(41).getType() == Material.STAINED_GLASS_PANE && Bingo.inventories.get(p.getName()).getItem(42).getType() == Material.STAINED_GLASS_PANE)
                {
                    points.put(p.getName(),points.get(p.getName()) + 3);
                    p.sendMessage(Bingo.signature + ChatColor.DARK_AQUA + "You got 3 extra points for a full line, Bingo! Total: " + points.get(p.getName()));
                }

                if ( (e.getSlot() == 2 || e.getSlot() == 11 || e.getSlot() == 20 || e.getSlot() == 29 || e.getSlot() == 38) && Bingo.inventories.get(p.getName()).getItem(2).getType() == Material.STAINED_GLASS_PANE && Bingo.inventories.get(p.getName()).getItem(11).getType() == Material.STAINED_GLASS_PANE && Bingo.inventories.get(p.getName()).getItem(20).getType() == Material.STAINED_GLASS_PANE && Bingo.inventories.get(p.getName()).getItem(29).getType() == Material.STAINED_GLASS_PANE && Bingo.inventories.get(p.getName()).getItem(38).getType() == Material.STAINED_GLASS_PANE)
                {
                    points.put(p.getName(),points.get(p.getName()) + 3);
                    p.sendMessage(Bingo.signature + ChatColor.DARK_AQUA + "You got 3 extra points for a full line, Bingo! Total: " + points.get(p.getName()));
                }
                if ( (e.getSlot() == 3 || e.getSlot() == 12 || e.getSlot() == 21 || e.getSlot() == 30 || e.getSlot() == 39) && Bingo.inventories.get(p.getName()).getItem(3).getType() == Material.STAINED_GLASS_PANE && Bingo.inventories.get(p.getName()).getItem(12).getType() == Material.STAINED_GLASS_PANE && Bingo.inventories.get(p.getName()).getItem(21).getType() == Material.STAINED_GLASS_PANE && Bingo.inventories.get(p.getName()).getItem(30).getType() == Material.STAINED_GLASS_PANE && Bingo.inventories.get(p.getName()).getItem(39).getType() == Material.STAINED_GLASS_PANE)
                {
                    points.put(p.getName(),points.get(p.getName()) + 3);
                    p.sendMessage(Bingo.signature + ChatColor.DARK_AQUA + "You got 3 extra points for a full line, Bingo! Total: " + points.get(p.getName()));
                }
                if ( (e.getSlot() == 4 || e.getSlot() == 13 || e.getSlot() == 22 || e.getSlot() == 31 || e.getSlot() == 40) && Bingo.inventories.get(p.getName()).getItem(4).getType() == Material.STAINED_GLASS_PANE && Bingo.inventories.get(p.getName()).getItem(13).getType() == Material.STAINED_GLASS_PANE && Bingo.inventories.get(p.getName()).getItem(22).getType() == Material.STAINED_GLASS_PANE && Bingo.inventories.get(p.getName()).getItem(31).getType() == Material.STAINED_GLASS_PANE && Bingo.inventories.get(p.getName()).getItem(40).getType() == Material.STAINED_GLASS_PANE)
                {
                    points.put(p.getName(),points.get(p.getName()) + 3);
                    p.sendMessage(Bingo.signature + ChatColor.DARK_AQUA + "You got 3 extra points for a full line, Bingo! Total: " + points.get(p.getName()));
                }
                if ( (e.getSlot() == 5 || e.getSlot() == 14 || e.getSlot() == 23 || e.getSlot() == 32 || e.getSlot() == 41) && Bingo.inventories.get(p.getName()).getItem(5).getType() == Material.STAINED_GLASS_PANE && Bingo.inventories.get(p.getName()).getItem(14).getType() == Material.STAINED_GLASS_PANE && Bingo.inventories.get(p.getName()).getItem(23).getType() == Material.STAINED_GLASS_PANE && Bingo.inventories.get(p.getName()).getItem(32).getType() == Material.STAINED_GLASS_PANE && Bingo.inventories.get(p.getName()).getItem(41).getType() == Material.STAINED_GLASS_PANE)
                {
                    points.put(p.getName(),points.get(p.getName()) + 3);
                    p.sendMessage(Bingo.signature + ChatColor.DARK_AQUA + "You got 3 extra points for a full line, Bingo! Total: " + points.get(p.getName()));
                }
                if ( (e.getSlot() == 6 || e.getSlot() == 15 || e.getSlot() == 24 || e.getSlot() == 33 || e.getSlot() == 42) && Bingo.inventories.get(p.getName()).getItem(6).getType() == Material.STAINED_GLASS_PANE && Bingo.inventories.get(p.getName()).getItem(15).getType() == Material.STAINED_GLASS_PANE && Bingo.inventories.get(p.getName()).getItem(24).getType() == Material.STAINED_GLASS_PANE && Bingo.inventories.get(p.getName()).getItem(33).getType() == Material.STAINED_GLASS_PANE && Bingo.inventories.get(p.getName()).getItem(42).getType() == Material.STAINED_GLASS_PANE)
                {
                    points.put(p.getName(),points.get(p.getName()) + 3);
                    p.sendMessage(Bingo.signature + ChatColor.DARK_AQUA + "You got 3 extra points for a full line, Bingo! Total: " + points.get(p.getName()));
                }
                if ( (e.getSlot() == 6 || e.getSlot() == 14 || e.getSlot() == 22 || e.getSlot() == 30 || e.getSlot() == 38) && Bingo.inventories.get(p.getName()).getItem(6).getType() == Material.STAINED_GLASS_PANE && Bingo.inventories.get(p.getName()).getItem(14).getType() == Material.STAINED_GLASS_PANE && Bingo.inventories.get(p.getName()).getItem(22).getType() == Material.STAINED_GLASS_PANE && Bingo.inventories.get(p.getName()).getItem(30).getType() == Material.STAINED_GLASS_PANE && Bingo.inventories.get(p.getName()).getItem(38).getType() == Material.STAINED_GLASS_PANE)
                {
                    points.put(p.getName(),points.get(p.getName()) + 3);
                    p.sendMessage(Bingo.signature + ChatColor.DARK_AQUA + "You got 3 extra points for a full line, Bingo! Total: " + points.get(p.getName()));
                }
                if ( (e.getSlot() == 2 || e.getSlot() == 12 || e.getSlot() == 22 || e.getSlot() == 32 || e.getSlot() == 42) && Bingo.inventories.get(p.getName()).getItem(2).getType() == Material.STAINED_GLASS_PANE && Bingo.inventories.get(p.getName()).getItem(12).getType() == Material.STAINED_GLASS_PANE && Bingo.inventories.get(p.getName()).getItem(22).getType() == Material.STAINED_GLASS_PANE && Bingo.inventories.get(p.getName()).getItem(32).getType() == Material.STAINED_GLASS_PANE && Bingo.inventories.get(p.getName()).getItem(42).getType() == Material.STAINED_GLASS_PANE)
                {
                    points.put(p.getName(),points.get(p.getName()) + 3);
                    p.sendMessage(Bingo.signature + ChatColor.DARK_AQUA + "You got 3 extra points for a full line, Bingo! Total: " + points.get(p.getName()));
                }
    	    }
    	}
    }
}
