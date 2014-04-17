package com.apzombies.zombierun;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


public class ZombieRunMain extends JavaPlugin implements Listener{

	ZombieRunMain plugin;
	
	Plugin plugin1 = plugin;

	Logger log = Bukkit.getLogger();

	Map<UUID, int[]> tokens = new HashMap<UUID, int[]>();

	@Override
	public void onEnable(){
		log.info("=======---=======");
		log.info("[ZombieRun] Enabled");
		log.info("=======---=======");
		
		plugin = this;

		this.getServer().getPluginManager().addPermission(new Permission("zombierun.*"));
		this.getServer().getPluginManager().addPermission(new Permission("zombierun.map.default"));
		this.getServer().getPluginManager().addPermission(new Permission("zombierun.map.donor"));
		this.getServer().getPluginManager().addPermission(new Permission("zombierun.map.*"));
		this.getServer().getPluginManager().addPermission(new Permission("zombierun.kit.subscriber"));
		this.getServer().getPluginManager().addPermission(new Permission("zombierun.kit.slayer"));
		this.getServer().getPluginManager().addPermission(new Permission("zombierun.kit.fusionist"));
		this.getServer().getPluginManager().addPermission(new Permission("zombierun.kit.paradox"));

		this.getServer().getPluginManager().registerEvents(new DropItemListener(), this);
		this.getServer().getPluginManager().registerEvents(new PlayerMoveListener(), this);
		this.getServer().getPluginManager().registerEvents(new CreatureSpawnListener(), this);

		this.getCommand("setmapspawn").setExecutor(new SetMapSpawnCommand(this));
		this.getCommand("zrkit").setExecutor(new ZombieRunKitCommand(this));

		this.saveDefaultConfig();

		for(Entry<UUID, int[]> entry : tokens.entrySet()) {
			getConfig().set("tokens." + entry.getKey(), entry.getValue());
		}
	}

	@Override
	public void onDisable(){
		log.info("=======---=======");
		log.info("[ZombieRun] Disabled");
		log.info("=======---=======");

		for(Entry<UUID, int[]> entry : tokens.entrySet()) {
			getConfig().set("tokens." + entry.getKey(), entry.getValue());
		}
	}

	ArrayList<Player> cooldown = new ArrayList<Player>();

	@SuppressWarnings("unchecked")
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerMove(PlayerMoveEvent event){
		final Player p = event.getPlayer();
		String worldName = p.getWorld().getName();
		Material blockIsOn = p.getLocation().getBlock().getRelative(BlockFace.DOWN).getType();
		if(worldName.contains("zombieRun")||worldName.equals("world")){

			if(blockIsOn == Material.GOLD_BLOCK){
				if(cooldown.contains(p)){
					p.sendMessage(ChatColor.RED + "[ZombieRun]"
							+ ChatColor.YELLOW + "Cannot collect more tokens yet!");
				}else{
					UUID playerUUID = p.getUniqueId();
					if(tokens.containsKey(playerUUID)){
						int[] currentTokens = ((Entry<UUID, int[]>) tokens).getValue();
						int[] addToken = new int[1];

					}else{
						int[] tokenReset = new int[0];
						tokens.put(playerUUID, tokenReset);
						int[] tokenOne = new int[1];
						tokens.put(playerUUID, tokenOne);
					}
					
					cooldown.add(p);
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable(){
						@Override
						public void run() {
							cooldown.remove(p);
						}
					}, 75);
				}
			}
		}
	}
	
	ArrayList<Player> obbycooldown = new ArrayList<Player>();
	ArrayList<Player> emeraldcooldown = new ArrayList<Player>();
 	ArrayList<Player> diamondcooldown = new ArrayList<Player>();
 	ArrayList<Player> ironcooldown = new ArrayList<Player>();
 	ArrayList<Player> lapiscooldown = new ArrayList<Player>();

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerMoveBlocks(PlayerMoveEvent event){
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
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable(){
						@Override
						public void run() {
							emeraldcooldown.remove(p);
						}
					}, 40);
				}
				
			}else if(blockIsOn == Material.DIAMOND_BLOCK){
				PotionEffect diamondBlock = new PotionEffect(PotionEffectType.SPEED, 200, 1);
				
				if(diamondcooldown.contains(p)){
				}else{
					p.addPotionEffect(diamondBlock);
					diamondcooldown.add(p);
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable(){
						@Override
						public void run() {
							diamondcooldown.remove(p);
						}
					}, 40);
				}
				
			}else if(blockIsOn == Material.IRON_BLOCK){
				PotionEffect ironBlock = new PotionEffect(PotionEffectType.JUMP, 200, 1);
				if(ironcooldown.contains(p)){
				}else{
					p.addPotionEffect(ironBlock);
					ironcooldown.add(p);
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable(){
						@Override
						public void run() {
							ironcooldown.remove(p);
						}
					}, 40);
				}

			}else if(blockIsOn == Material.LAPIS_BLOCK){
				PotionEffect lapisBlock = new PotionEffect(PotionEffectType.SLOW, 200, 1);
				if(lapiscooldown.contains(p)){
				}else{
					p.addPotionEffect(lapisBlock);
					lapiscooldown.add(p);
					Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable(){
						@Override
						public void run() {
							lapiscooldown.remove(p);
						}
					}, 40);
				}
				
			}else if(blockIsOn == Material.PUMPKIN){

			}
		}
	}
}

