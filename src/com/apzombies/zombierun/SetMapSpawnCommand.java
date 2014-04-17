package com.apzombies.zombierun;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetMapSpawnCommand implements CommandExecutor{
	Logger log = Bukkit.getLogger();

	ZombieRunMain plugin;

	public SetMapSpawnCommand(ZombieRunMain pPlugin)
	{
		this.plugin = pPlugin;
	}

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args){
		if(s instanceof Player){
			Player p = (Player) s;
			if(p.hasPermission("zombierun.*")){
				World world = p.getWorld();
				String worldName = world.getName();
				if(args.length == 0){
					if(worldName.contains("zombieRun")||worldName.equals("world")){
						Location mapSpawn = p.getLocation();
						int spawnX = (int) mapSpawn.getX();
						int spawnY = (int) mapSpawn.getY();
						int spawnZ = (int) mapSpawn.getZ();
						world.setSpawnLocation(spawnX, spawnY, spawnZ);
						p.sendMessage(ChatColor.RED + "[ZombieRun]" + ChatColor.GREEN + " Sucessfully set map spawn for map " + worldName);
					}else{
						p.sendMessage(ChatColor.GREEN + "[ZombieRun]" + ChatColor.RED + " This command is for zombieRun worlds only!");
					}
				}else{
					p.sendMessage(ChatColor.RED + "[ZombieRun]" + ChatColor.DARK_RED + "Too many arguments!");
				}
			}else{
				p.sendMessage(ChatColor.GOLD + "[ZombieRun]"
						+ ChatColor.RED + "Only admins can use this command!");
			}
		}else{
			log.warning("This command is in-game only!");
		}
		return false;
	}
}
