package com.apzombies.zombierun;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class CreatureSpawnListener implements Listener{

	ZombieRunMain plugin;

	@EventHandler
	public void onCreatureSpawn(CreatureSpawnEvent event){
		final LivingEntity entity = event.getEntity();
		List<String> worldName = new ArrayList<String>();
		if(worldName.equals(plugin.getConfig().getStringList("zombieRunWorlds"))){
			PotionEffect superZombieEffect = new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1);
			PotionEffect superZombieEffect2 = new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 1);
			PotionEffect giantEffect = new PotionEffect(PotionEffectType.WEAKNESS, Integer.MAX_VALUE, 1);
			PotionEffect giantEffect2 = new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 1);
			if(entity.equals(EntityType.ZOMBIE)){
				entity.addPotionEffect(superZombieEffect);
				entity.addPotionEffect(superZombieEffect2);
			}else if(entity.equals(EntityType.GIANT)){
				entity.addPotionEffect(giantEffect);
				entity.addPotionEffect(giantEffect2);
			}
		}
	}
}

