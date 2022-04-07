package io.github.joltmuz.bingo;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.Random;

public class round implements CommandExecutor
{
    String usage = ChatColor.RED + "/bround start [Duration] [Delay]";
    public static BukkitTask round;
    public static boolean RoundisRunning = false;

    static ArrayList<ItemStack> AllItems = new ArrayList<>();

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
                        Sender.sendMessage(bingo.signature + ChatColor.RED + "There is an active round currently.");
                    }
                    else
                    {

                        try
                        {
                            int delay = Integer.parseInt(args[2]);
                            final int duration = Integer.parseInt(args[1]);
                            RoundisRunning = true;
                            RandomizeBingo();

                            Bukkit.broadcastMessage(bingo.signature + ChatColor.YELLOW + "Round will start after " + ChatColor.GOLD + delay + ChatColor.YELLOW + " seconds.");

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
                                        Bukkit.broadcastMessage(bingo.signature + ChatColor.YELLOW + "Round has started!");
                                    }
                                    new ActionBar(ChatColor.YELLOW + "Round Ends: " + minutes + " : " + seconds).sendToAll();
                                    if (countdown == 0)
                                    {
                                        RoundisRunning = false;
                                        Bukkit.broadcastMessage(bingo.signature + ChatColor.YELLOW + "Round has ended!");
                                        new ActionBar(ChatColor.YELLOW + ChatColor.BOLD.toString() + "Round Ended!").sendToAll();
                                        this.cancel();
                                    }
                                    countdown -= 1;
                                }
                            }.runTaskTimer(bingo.plugin, 20L * delay, 20L);

                        } catch (NumberFormatException error)
                        {
                            Sender.sendMessage(bingo.signature + ChatColor.RED + "Duration and Delay must be numbers.");
                        }
                    }
                }
                else
                {
                    Sender.sendMessage(bingo.signature + usage);
                }
            }
            else if (args.length == 1 && args[0].equalsIgnoreCase("end"))
            {
                if (RoundisRunning)
                {
                    RoundisRunning = false;
                    round.cancel();
                    Bukkit.broadcastMessage(bingo.signature + ChatColor.YELLOW + "Round force-ended.");
                    new ActionBar(ChatColor.YELLOW + ChatColor.BOLD.toString() + "Round Ended!").sendToAll();
                }
                else
                {
                    Sender.sendMessage(bingo.signature + ChatColor.RED + "There is no active round.");
                }
            }
            else
            {
                Sender.sendMessage(bingo.signature + usage);
            }
        }
        else
        {
            Sender.sendMessage(bingo.signature + ChatColor.RED + "This command requires OP.");
        }
        return true;
    }
    public static void RandomizeBingo()
    {
        bingo.inv.clear();
        bingo.inv.setItem(0, new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.GRAY.getData()));
        bingo.inv.setItem(1, new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.GRAY.getData()));
        bingo.inv.setItem(7, new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.GRAY.getData()));
        bingo.inv.setItem(8, new ItemStack(Material.STAINED_GLASS_PANE, 1,DyeColor.GRAY.getData()));
        bingo.inv.setItem(9, new ItemStack(Material.STAINED_GLASS_PANE, 1,DyeColor.GRAY.getData()));
        bingo.inv.setItem(10, new ItemStack(Material.STAINED_GLASS_PANE,1,DyeColor.GRAY.getData()));
        bingo.inv.setItem(16, new ItemStack(Material.STAINED_GLASS_PANE,1,DyeColor.GRAY.getData()));
        bingo.inv.setItem(17, new ItemStack(Material.STAINED_GLASS_PANE,1,DyeColor.GRAY.getData()));
        bingo.inv.setItem(18, new ItemStack(Material.STAINED_GLASS_PANE,1,DyeColor.GRAY.getData()));
        bingo.inv.setItem(19, new ItemStack(Material.STAINED_GLASS_PANE,1,DyeColor.GRAY.getData()));
        bingo.inv.setItem(25, new ItemStack(Material.STAINED_GLASS_PANE,1,DyeColor.GRAY.getData()));
        bingo.inv.setItem(26, new ItemStack(Material.STAINED_GLASS_PANE,1,DyeColor.GRAY.getData()));
        bingo.inv.setItem(27, new ItemStack(Material.STAINED_GLASS_PANE,1,DyeColor.GRAY.getData()));
        bingo.inv.setItem(28, new ItemStack(Material.STAINED_GLASS_PANE,1,DyeColor.GRAY.getData()));
        bingo.inv.setItem(34, new ItemStack(Material.STAINED_GLASS_PANE,1,DyeColor.GRAY.getData()));
        bingo.inv.setItem(35, new ItemStack(Material.STAINED_GLASS_PANE,1,DyeColor.GRAY.getData()));
        bingo.inv.setItem(36, new ItemStack(Material.STAINED_GLASS_PANE,1,DyeColor.GRAY.getData()));
        bingo.inv.setItem(37, new ItemStack(Material.STAINED_GLASS_PANE,1,DyeColor.GRAY.getData()));
        bingo.inv.setItem(43, new ItemStack(Material.STAINED_GLASS_PANE,1,DyeColor.GRAY.getData()));
        bingo.inv.setItem(44, new ItemStack(Material.STAINED_GLASS_PANE,1,DyeColor.GRAY.getData()));

        while(bingo.inv.firstEmpty() > 0)
        {
            for ( int i = 2 ; i < 43; i += 1)
            {
                int rand = new Random().nextInt(AllItems.size());
                if (!bingo.inv.contains(AllItems.get(rand)) && bingo.inv.getItem(i) == null)
                {
                    bingo.inv.setItem(i,AllItems.get(rand));
                }
            }
        }

        int rand = new Random().nextInt(bingo.inv.getSize());
        if (bingo.inv.getItem(rand).getType() !=null && bingo.inv.getItem(rand).getType() != Material.STAINED_GLASS_PANE)
        {
            bingo.inv.getItem(rand).addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS,1);
        }

        for (Player p : Bukkit.getServer().getOnlinePlayers())
        {
            bingo.Inventories.get(p.getName()).setContents(bingo.inv.getContents());
        }
    }
}
