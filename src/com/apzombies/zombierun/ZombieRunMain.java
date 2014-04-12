package com.apzombies.zombierun;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class ZombieRunMain extends JavaPlugin{

	ZombieRunMain plugin;

	Logger log = Bukkit.getLogger();

	@Override
	public void onEnable(){
		log.info("=======---=======");
		log.info("[ZombieRun] Enabled");
		log.info("=======---=======");

		this.getServer().getPluginManager().registerEvents(new DropItemListener(), this);
		this.getServer().getPluginManager().registerEvents(new PlayerMoveListener(), this);
		this.getServer().getPluginManager().registerEvents(new CreatureSpawnListener(), this);

		this.saveDefaultConfig();
	}

	@Override
	public void onDisable(){
		log.info("=======---=======");
		log.info("[ZombieRun] Disabled");
		log.info("=======---=======");
	}
}