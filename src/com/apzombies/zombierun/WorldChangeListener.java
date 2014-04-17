package com.apzombies.zombierun;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

public class WorldChangeListener implements Listener{

	ZombieRunMain plugin;
	
	@EventHandler
	public void onWorldChange(PlayerChangedWorldEvent event){
		World formerWorld = event.getFrom();
		Player p = event.getPlayer();
		Location newLoc = p.getLocation();
		World newWorld = newLoc.getWorld();
		String newWorldName = newWorld.getName();
		if(newWorldName.contains("zRunDonor")){
			if(!p.hasPermission("zombierun.map.donor")&&!p.hasPermission("zombierun.map.*")&&!p.hasPermission("zombierun.*")){
				p.teleport((Location) formerWorld);
				p.sendMessage(ChatColor.GREEN + "[ZombieRun]" + ChatColor.RED + "You don't have permission to enter this world!");
			}else{
				p.sendMessage(ChatColor.YELLOW + "[ZombieRun]" + ChatColor.GREEN + " Choose a ZombieRun kit using /zrkit <kitname>!");
			}
		}else if(newWorldName.contains("zombieRun")){
			if(!p.hasPermission("zombierun.map.default")&&!p.hasPermission("zombierun.map.*")&&!p.hasPermission("zombierun.*")){
				p.teleport((Location) formerWorld);
				p.sendMessage(ChatColor.GREEN + "[ZombieRun]" + ChatColor.RED + "You don't have permission to enter this world!");
			}else{
				p.sendMessage(ChatColor.YELLOW + "[ZombieRun]" + ChatColor.GREEN + " Choose a ZombieRun kit using /zrkit <kitname>!");
			}
		}else{}
		
		if(formerWorld.getName().contains("zombieRun")||formerWorld.getName().contains("zRunDonor")){
			p.setPlayerListName(ChatColor.WHITE + p.getName() + ChatColor.WHITE);
		}
	
	}
}
