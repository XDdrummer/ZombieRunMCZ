package com.apzombies.zombierun;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlayerMoveListener implements Listener{

	ZombieRunMain plugin;

	HashMap<String, Long> cooldowns = new HashMap<String, Long>();

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event){
		Player p = event.getPlayer();
		List<String> worldName = new ArrayList<String>();
		if(worldName.equals(plugin.getConfig().getStringList("zombieRunWorlds")){
			Material blockIsOn = p.getLocation().getBlock().getRelative(BlockFace.DOWN).getType();
			Material blockIsIn = p.getLocation().getBlock().getType();
			Location pLoc = p.getLocation();
			double chanceOfEntity = Math.random();

			if(blockIsOn == Material.OBSIDIAN){
				if(chanceOfEntity > 0.1){
					EntityType entity =  EntityType.ZOMBIE;
					p.getWorld().spawnCreature(pLoc, entity);
				}else{
					EntityType entity = EntityType.SKELETON;
					p.getWorld().spawnCreature(pLoc, entity);
				}
			}else if(blockIsOn == Material.EMERALD_BLOCK){
				EntityType entity = EntityType.GIANT;
				p.getWorld().spawnCreature(pLoc, entity);
			}else if(blockIsOn == Material.DIAMOND_BLOCK){
				PotionEffect diamondBlock = new PotionEffect(PotionEffectType.SPEED, 200, 1);
				p.addPotionEffect(diamondBlock);
			}else if(blockIsOn == Material.IRON_BLOCK){
				PotionEffect ironBlock = new PotionEffect(PotionEffectType.JUMP, 200, 1);
				p.addPotionEffect(ironBlock);
			}else if(blockIsOn == Material.LAPIS_BLOCK){
				PotionEffect lapisBlock = new PotionEffect(PotionEffectType.SLOW, 200, 1);

			}else if(blockIsOn == Material.GOLD_BLOCK){
				int cooldownTime = 5;
				if(cooldowns.containsKey(p.getName())) {
					long secondsLeft = ((cooldowns.get(p.getName())/1000)+cooldownTime) - (System.currentTimeMillis()/1000);
					if(secondsLeft>0) {
						p.sendMessage(ChatColor.YELLOW + "[ZombieRunTokens]" + ChatColor.RED + "You cannot collect more tokens from this block yet!");
					}
				}else{
					cooldowns.put(p.getName(), System.currentTimeMillis());
					if(plugin.getConfig().contains("tokens." + p.getUniqueId())){
						plugin.getConfig().set("tokens." + p.getUniqueId() + ".tokenNumber", (plugin.getConfig().getInt("tokens." + p.getUniqueId() + ".tokenNumber")) + 1);
						p.sendMessage(ChatColor.YELLOW + "[ZombieRunTokens]" + ChatColor.GREEN + "You gained 1 token!");
					}else{
						plugin.getConfig().createSection("tokens." + p.getUniqueId());
						plugin.getConfig().set("tokens." + p.getUniqueId() + ".tokenNumber", (plugin.getConfig().getInt("tokens." + p.getUniqueId() + ".tokenNumber")) + 1);
						p.sendMessage(ChatColor.YELLOW + "[ZombieRunTokens]" + ChatColor.GREEN + "You gained 1 token!");
					}
				}
			}
		}
	}
}

