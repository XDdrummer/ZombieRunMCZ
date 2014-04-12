package com.apzombies.zombierun;

import java.util.HashMap;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlayerMoveListener implements Listener{

	ZombieRunMain plugin;

	HashMap<String, Long> cooldowns = new HashMap<String, Long>();
	
	public Location mapSpawn = (Location) plugin.getConfig().get("mapSpawns");

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event){
		Player p = event.getPlayer();
		String worldName = p.getWorld().getName();
		if(worldName.contains("zombieRun")||worldName.equals("world")){
			Material blockIsOn = p.getLocation().getBlock().getRelative(BlockFace.DOWN).getType();
			Material blockIsIn = p.getLocation().getBlock().getType();
			Location pLoc = p.getLocation();
			double chanceOfEntity = Math.random();

			if(blockIsOn == Material.OBSIDIAN){
				if(chanceOfEntity > 0.05){
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
				p.addPotionEffect(lapisBlock);
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
			if(blockIsIn == Material.WATER||blockIsIn == Material.STATIONARY_WATER){
				World world = p.getWorld();
				p.teleport((Location) plugin.getConfig().get(world.getName() + ".mapSpawn"));
			}else if(blockIsIn == Material.LAVA||blockIsIn == Material.STATIONARY_LAVA){
				World world = p.getWorld();
				p.teleport((Location) plugin.getConfig().get(world.getName() + ".mapSpawn"));
			}else{}
		}
	}

	@EventHandler
	public void onPlayerMoveZombieKill(PlayerMoveEvent event){
		Player p = event.getPlayer();
		Location pLoc = p.getLocation();
		String worldName = p.getWorld().getName();
		if(worldName.contains("zombieRun")||worldName.equals("world")){
			List<Entity> near = pLoc.getWorld().getEntities();
			for(Entity e : near) {
				List<Entity> nearP = e.getNearbyEntities(10, 10, 10);
				if(!(nearP.contains(EntityType.PLAYER))){
					LivingEntity le = (LivingEntity) e;
					le.setHealth(0);
				}else if(nearP.contains(EntityType.PLAYER)){}
			}
		}
	}
}

