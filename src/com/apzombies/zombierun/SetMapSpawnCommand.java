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
						plugin.getConfig().set(worldName + ".mapSpawn", mapSpawn);
						p.sendMessage(ChatColor.RED + "[ZombieRun]" + ChatColor.GREEN + " Sucessfully set map spawn for map " + worldName);
					}else{
						p.sendMessage(ChatColor.GREEN + "[ZombieRun]" + ChatColor.RED + " This command is for zombieRun worlds only!");
					}
				}else if(args.length == 1){
					String worldNameArg = args[0];
					if(Bukkit.getWorld(worldNameArg) != null){
						if(worldNameArg.contains("zombieRun")||worldNameArg.equals("world")){
							Location mapSpawn = p.getLocation();
							plugin.getConfig().set(worldName + ".mapSpawn", mapSpawn);
							p.sendMessage(ChatColor.RED + "[ZombieRun]" + ChatColor.GREEN + " Successfully set map spawn for map " + worldNameArg);
						}else{
							p.sendMessage(ChatColor.GREEN + "[ZombieRun]" + ChatColor.RED + " This command is for zombieRun worlds only!");
						}
					}else{
						p.sendMessage(ChatColor.RED + "[ZombieRun]" + ChatColor.DARK_RED + " Could not find world " + worldNameArg);
					}
				}else{
					p.sendMessage(ChatColor.RED + "[ZombieRun]" + ChatColor.DARK_RED + "Too many arguments!");
				}
			}
		}else{
			log.warning("This command is in-game only!");
		}
		return false;
	}
}
