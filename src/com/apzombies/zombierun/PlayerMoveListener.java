package com.apzombies.zombierun;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlayerMoveListener implements Listener{

	ZombieRunMain plug;
	
	Plugin plugin = plug;

	ArrayList<Player> cooldown = new ArrayList<Player>();
	ArrayList<Player> obbycooldown = new ArrayList<Player>();
	ArrayList<Player> emeraldcooldown = new ArrayList<Player>();
 	ArrayList<Player> diamondcooldown = new ArrayList<Player>();
 	ArrayList<Player> ironcooldown = new ArrayList<Player>();
 	ArrayList<Player> lapiscooldown = new ArrayList<Player>();

	Map<UUID, int[]> tokens = new HashMap<UUID, int[]>();

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event){
		final Player p = event.getPlayer();
		String worldName = p.getWorld().getName();
		if(worldName.contains("zombieRun")||worldName.equals("world")){
			Material blockIsOn = p.getLocation().getBlock().getRelative(BlockFace.DOWN).getType();
			Location pLoc = p.getLocation();
			double chanceOfEntity = Math.random();

			if(blockIsOn == Material.OBSIDIAN){
				if(obbycooldown.contains(p)){
				}else{
					if(chanceOfEntity > 0.05){
						EntityType entity =  EntityType.ZOMBIE;
						p.getWorld().spawnCreature(pLoc, entity);
						obbycooldown.add(p);
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable(){
							@Override
							public void run() {
								obbycooldown.remove(p);
							}
						}, 20);
					}else{
						EntityType entity = EntityType.SKELETON;
						p.getWorld().spawnCreature(pLoc, entity);
						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable(){
							@Override
							public void run() {
								obbycooldown.remove(p);
							}
						}, 20);
					}
				}

			}else if(blockIsOn == Material.EMERALD_BLOCK){
				EntityType entity = EntityType.GIANT;
				if(emeraldcooldown.contains(p)){
				}else{
					p.getWorld().spawnCreature(pLoc, entity);
					emeraldcooldown.add(p);
				}
				
			}else if(blockIsOn == Material.DIAMOND_BLOCK){
				PotionEffect diamondBlock = new PotionEffect(PotionEffectType.SPEED, 200, 1);
				
				if(diamondcooldown.contains(p)){
				}else{
					p.addPotionEffect(diamondBlock);
					diamondcooldown.add(p);
				}
				
			}else if(blockIsOn == Material.IRON_BLOCK){
				PotionEffect ironBlock = new PotionEffect(PotionEffectType.JUMP, 200, 1);
				if(ironcooldown.contains(p)){
				}else{
					p.addPotionEffect(ironBlock);
					ironcooldown.add(p);
				}

			}else if(blockIsOn == Material.LAPIS_BLOCK){
				PotionEffect lapisBlock = new PotionEffect(PotionEffectType.SLOW, 200, 1);
				if(lapiscooldown.contains(p)){
				}else{
					p.addPotionEffect(lapisBlock);
					lapiscooldown.add(p);
				}
				
			}else if(blockIsOn == Material.PUMPKIN){

			}
		}
	}

	@EventHandler
	public void onPlayerMoveIn(PlayerMoveEvent event){
		final Player p = event.getPlayer();
		String worldName = p.getWorld().getName();
		if(worldName.contains("zombieRun")||worldName.equals("world")){
			Material blockIsIn = p.getLocation().getBlock().getType();
			World world = p.getWorld();
			Location mapSpawn = world.getSpawnLocation();
			if(blockIsIn == Material.WATER||blockIsIn == Material.STATIONARY_WATER){
				p.teleport(mapSpawn);
				p.sendMessage(ChatColor.RED + "[ZombieRun]" + ChatColor.AQUA + " Good luck next time!");
			}else if(blockIsIn == Material.LAVA||blockIsIn == Material.STATIONARY_LAVA){
				p.teleport(mapSpawn);
				p.sendMessage(ChatColor.RED + "[ZombieRun]" + ChatColor.AQUA + " Good luck next time!");
			}else{}
		}
	}
}

