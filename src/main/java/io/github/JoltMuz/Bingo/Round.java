package io.github.JoltMuz.Bingo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class Round implements CommandExecutor
{
    String usage = ChatColor.RED + "/bround start [Duration] [Delay]";
    public static BukkitTask round;
    public static boolean RoundisRunning = false;

    static ArrayList<ItemStack> AllItems = new ArrayList<>();
    
    private static int[] unUsedSlots = {0,1,7,8,9,10,16,17,18,19,25,26,27,28,34,35,36,37,43,44};

    static {

        AllItems.add(new ItemStack(Material.COCOA));
        AllItems.add(new ItemStack(Material.WATER_LILY));
        AllItems.add(new ItemStack(Material.NOTE_BLOCK));
        AllItems.add(new ItemStack(Material.RED_MUSHROOM));
        AllItems.add(new ItemStack(Material.GOLD_NUGGET));
        AllItems.add(new ItemStack(Material.IRON_DOOR));
        AllItems.add(new ItemStack(Material.MINECART));
        AllItems.add(new ItemStack(Material.INK_SACK));
        AllItems.add(new ItemStack(Material.PAPER));
        AllItems.add(new ItemStack(Material.SAPLING));
        AllItems.add(new ItemStack(Material.WHEAT));
        AllItems.add(new ItemStack(Material.CAULDRON));
        AllItems.add(new ItemStack(Material.ENCHANTMENT_TABLE));
        AllItems.add(new ItemStack(Material.REDSTONE_COMPARATOR));
        AllItems.add(new ItemStack(Material.BRICK_STAIRS));
        AllItems.add(new ItemStack(Material.PISTON_BASE));
        AllItems.add(new ItemStack(Material.ACTIVATOR_RAIL));
        AllItems.add(new ItemStack(Material.ARMOR_STAND));
        AllItems.add(new ItemStack(Material.BANNER));
        AllItems.add(new ItemStack(Material.BOOKSHELF));
        AllItems.add(new ItemStack(Material.LEAVES));
        AllItems.add(new ItemStack(Material.CACTUS));
        AllItems.add(new ItemStack(Material.DROPPER));
        AllItems.add(new ItemStack(Material.GOLDEN_CARROT));
        AllItems.add(new ItemStack(Material.WATER_BUCKET));
        AllItems.add(new ItemStack(Material.WATCH));
        AllItems.add(new ItemStack(Material.POTION));
    }

    @Override
    public boolean onCommand(CommandSender Sender, Command command, String s, String[] args)
    {
        if (Sender.isOp())
        {
            if (args.length == 3)
            {
                if (args[0].equalsIgnoreCase("start"))
                {
                    if (RoundisRunning)
                    {
                        Sender.sendMessage(Bingo.signature + ChatColor.RED + "There is an active round currently.");
                    }
                    else
                    {

                        try
                        {
                            int delay = Integer.parseInt(args[2]);
                            final int duration = Integer.parseInt(args[1]);
                            RoundisRunning = true;
                            for (Player p: Bukkit.getOnlinePlayers())
                            {
                            	if (!Points.points.containsKey(p.getName()))
                            	{
                            		Points.points.put(p.getName(),0);
                            	}
                            	RandomizeBingo(p);
                            }

                            Bukkit.broadcastMessage(Bingo.signature + ChatColor.YELLOW + "Round will start after " + ChatColor.GOLD + delay + ChatColor.YELLOW + " seconds.");

                            round = new BukkitRunnable()
                            {
                                int countdown = duration;

                                @Override
                                public void run()
                                {

                                    int seconds = countdown % 60;
                                    int minutes = countdown / 60;
                                    if (duration == countdown)
                                    {
                                        Bukkit.broadcastMessage(Bingo.signature + ChatColor.YELLOW + "Round has started!");
                                    }
                                    ActionBar.sendToAll(ChatColor.YELLOW + "Round Ends: " + minutes + ":" + seconds);
                                    if (countdown == 0)
                                    {
                                        RoundisRunning = false;
                                        Bukkit.broadcastMessage(Bingo.signature + ChatColor.YELLOW + "Round has ended!");
                                        ActionBar.sendToAll(ChatColor.YELLOW + ChatColor.BOLD.toString() + "Round Ended!");
                                        this.cancel();
                                    }
                                    countdown -= 1;
                                }
                            }.runTaskTimer(Bingo.plugin, 20L * delay, 20L);

                        } catch (NumberFormatException error)
                        {
                            Sender.sendMessage(Bingo.signature + ChatColor.RED + "Duration and Delay must be numbers.");
                        }
                    }
                }
                else
                {
                    Sender.sendMessage(Bingo.signature + usage);
                }
            }
            else if (args.length == 1 && args[0].equalsIgnoreCase("end"))
            {
                if (RoundisRunning)
                {
                    RoundisRunning = false;
                    round.cancel();
                    Bukkit.broadcastMessage(Bingo.signature + ChatColor.YELLOW + "Round force-ended.");
                    ActionBar.sendToAll(ChatColor.YELLOW + ChatColor.BOLD.toString() + "Round Ended!");
                }
                else
                {
                    Sender.sendMessage(Bingo.signature + ChatColor.RED + "There is no active round.");
                }
            }
            else
            {
                Sender.sendMessage(Bingo.signature + usage);
            }
        }
        else
        {
            Sender.sendMessage(Bingo.signature + ChatColor.RED + "This command requires OP.");
        }
        return true;
    }
    public static void RandomizeBingo(Player player) {
    	String playerName = player.getName();
    	if (!Bingo.inventories.containsKey(playerName))
		{
    		Bingo.inventories.put(playerName, Bukkit.createInventory(player, 45, ChatColor.RED + "B" + ChatColor.YELLOW + "i" + ChatColor.GREEN + "n" + ChatColor.AQUA + "g" + ChatColor.BLUE + "o"));

		}
        Inventory playerInventory = Bingo.inventories.get(player.getName());
        playerInventory.clear();
        
        for (int i : unUsedSlots)
        {
        	playerInventory.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.GRAY.getData()));
        }

        while (playerInventory.firstEmpty() > 0) {
            int rand = new Random().nextInt(AllItems.size());
            ItemStack randomItem = AllItems.get(rand);

            if (!playerInventory.contains(randomItem) && playerInventory.getItem(playerInventory.firstEmpty()) == null) {
                playerInventory.addItem(randomItem);
            }
        }

        Random random = new Random();
        int rand1, rand2;
        ItemStack selectedItem1, selectedItem2;

        // Select the first item
        do {
            rand1 = random.nextInt(playerInventory.getSize());
            selectedItem1 = playerInventory.getItem(rand1);
        } while (selectedItem1 == null || selectedItem1.getType() == Material.STAINED_GLASS_PANE);

        // Modify the first item
        ItemStack modifiedItem1 = selectedItem1.clone();
        ItemMeta meta1 = modifiedItem1.getItemMeta();
        meta1.setLore(Collections.singletonList(ChatColor.BLUE + "Get this item to win extra 3 points!"));
        modifiedItem1.setItemMeta(meta1);
        modifiedItem1.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 1);
        playerInventory.setItem(rand1, modifiedItem1);

        // Select the second item
        do {
            rand2 = random.nextInt(playerInventory.getSize());
            selectedItem2 = playerInventory.getItem(rand2);
        } while (selectedItem2 == null || selectedItem2.getType() == Material.STAINED_GLASS_PANE);

        // Modify the second item
        ItemStack modifiedItem2 = selectedItem2.clone();
        ItemMeta meta2 = modifiedItem2.getItemMeta();
        meta2.setLore(Collections.singletonList(ChatColor.BLUE + "Get this item to win extra 3 points!"));
        modifiedItem2.setItemMeta(meta2);
        modifiedItem2.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 1);
        playerInventory.setItem(rand2, modifiedItem2);

    }

}
