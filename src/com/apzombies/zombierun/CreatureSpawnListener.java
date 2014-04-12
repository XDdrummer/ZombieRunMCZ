package com.apzombies.zombierun;

import java.util.List;

import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class CreatureSpawnListener implements Listener{

	ZombieRunMain plugin; 

	@EventHandler
	public void onCreatureSpawn(CreatureSpawnEvent event){
		final LivingEntity entity = event.getEntity();
		String worldName = entity.getWorld().getName();
		if(worldName.contains("zombieRun")||worldName.equals("world")){
			PotionEffect superZombieEffect = new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1);
			PotionEffect superZombieEffect2 = new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 1);
			PotionEffect giantEffect = new PotionEffect(PotionEffectType.HUNGER, Integer.MAX_VALUE, 1);
			PotionEffect giantEffect2 = new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 1);
			if(entity.equals(EntityType.ZOMBIE)){
				entity.addPotionEffect(superZombieEffect);
				entity.addPotionEffect(superZombieEffect2);
				entity.setMaxHealth(10);
				entity.setHealth(5);
			}else if(entity.equals(EntityType.GIANT)){
				entity.addPotionEffect(giantEffect);
				entity.addPotionEffect(giantEffect2);
				entity.setMaxHealth(5);
				entity.setHealth(3);
			}else if(entity.equals(EntityType.SKELETON)){
				entity.teleport(entity);
				entity.setMaxHealth(7);
				entity.setHealth(4);
			}
		}
	}

	@EventHandler
	public void onCreatureSpawnFor(CreatureSpawnEvent event){
		Entity entity = event.getEntity();
		World world = entity.getWorld();
		List<Entity> entities = world.getEntities();
		if(!(entities.contains(EntityType.ZOMBIE))){

		}

	}


	@EventHandler
	public void onEntity(EntityEvent event){
		final Entity entity = event.getEntity();
		String worldName = entity.getWorld().getName();
		if(worldName.contains("zombieRun")||worldName.equals("world")){
			if(entity instanceof LivingEntity){
				if(entity.getType().equals(EntityType.ZOMBIE)){
					List<Entity> entityNearby = entity.getNearbyEntities(10, 10, 10);
					if(!(entityNearby instanceof Player)){
						((LivingEntity) entity).setHealth(0);
					}else{
						((LivingEntity) entity).setHealth(10);
					}
				}
			}
		}
	}
}

