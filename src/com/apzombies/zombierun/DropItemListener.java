package com.apzombies.zombierun;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class DropItemListener implements Listener{

	ZombieRunMain plugin;

	@EventHandler
	public void onItemDrop(PlayerDropItemEvent event){
		Player p = event.getPlayer();
		String worldName = p.getWorld().getName();
		if(worldName.contains("zombieRun")||worldName.equals("world")){
			if(!p.isOp()){
				p.sendMessage(ChatColor.RED + "Sorry, you can't drop items in Zombie Run!");
				event.setCancelled(true);
			}else{
				p.sendMessage(ChatColor.RED + "Warning! Items should not be dropped in Zombie Run.");
			}
		}
	}


}
