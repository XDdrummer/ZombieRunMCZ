package com.apzombies.zombierun;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

public class ZombieRunKitCommand implements CommandExecutor{

	Logger log = Bukkit.getLogger();

	HashMap<String, Long> cooldowns = new HashMap<String, Long>();

	ZombieRunMain plugin;

	public ZombieRunKitCommand(ZombieRunMain pPlugin)
	{
		this.plugin = pPlugin;
	}

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args){
		if(s instanceof Player){
			Player p = (Player) s;
			String redName = ChatColor.RED + "[ZR]" + p.getName();
			String blueName = ChatColor.BLUE + "[ZR]" + p.getName();
			String greenName = ChatColor.GREEN + "[ZR]" + p.getName();
			String yellowName = ChatColor.YELLOW + "[ZR]" + p.getName();
			String goldName = ChatColor.GOLD + "[ZR]" + p.getName();

			List<String> lore = new ArrayList<String>();

			if(p.getWorld().getName().contains("zombieRun")||p.getWorld().getName().contains("zRunDonor")){
				if(args.length == 0){
					p.sendMessage(ChatColor.DARK_RED + "[ZombieRun]"
							+ ChatColor.GREEN + "The current free zombie run kits include:"
							+ "\n" + ChatColor.RED + "Escapist (Do /zrkit escapist for more information)"
							+ "\n" + ChatColor.BLUE + "Hunter (Do /zrkit hunter for more information)"
							+ "\n" + ChatColor.GREEN + "Jumper (Do /zrkit jumper for more information)"
							+ "\n" + ChatColor.YELLOW + "Survivor (Do /zrkit survivor for more information)"
							+ "\n" + ChatColor.GOLD + "Get more kits by donating at mc-z.net! Do /zrkit donor for more info"
							+ "\n" + ChatColor.ITALIC + "Note- weapons have their own special effects (varies by weapon)!");
				}else if(args.length == 1){
					if(args[0].equalsIgnoreCase("escapist")){
						p.sendMessage(ChatColor.GOLD + "[ZombieRun]"
								+ ChatColor.RED + " Escapist kit includes:"
								+ "\n" + "An infinite speed 2 effect"
								+ "\n" + "A stone sword"
								+ "\n" + "A diamond chestplate"
								+ "\n" + "A red list name");

						p.setPlayerListName(redName);
						p.getInventory().clear();
					    p.getInventory().setHelmet(new ItemStack(Material.REDSTONE_BLOCK, 1));
					    p.getInventory().setChestplate(null);
					    p.getInventory().setLeggings(null);
					    p.getInventory().setBoots(null);
						for(PotionEffect effect : p.getActivePotionEffects()){
							p.removePotionEffect(effect.getType());
						}

						PotionEffect escapist = new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 2);
						p.addPotionEffect(escapist);

						ItemStack escapistWeapon = new ItemStack(Material.STONE_SWORD);
						escapistWeapon.addUnsafeEnchantment(Enchantment.DURABILITY, 127);
						p.getInventory().addItem(escapistWeapon);

						ItemStack escapistArmor = new ItemStack(Material.DIAMOND_CHESTPLATE);
						escapistArmor.addUnsafeEnchantment(Enchantment.DURABILITY, 127);
						p.getInventory().addItem(escapistArmor);

					}else if(args[0].equalsIgnoreCase("hunter")){
						p.sendMessage(ChatColor.GOLD + "[ZombieRun]"
								+ ChatColor.BLUE + " Hunter kit includes:"
								+ "\n" + "An infinite slowness + strength effect (level 1)"
								+ "\n" + "A diamond sword"
								+ "\n" + "Diamond leggings & boots"
								+ "\n" + "A blue list name");

						p.setPlayerListName(blueName);
						p.getInventory().clear();
					    p.getInventory().setHelmet(new ItemStack(Material.LAPIS_BLOCK, 1));
					    p.getInventory().setChestplate(null);
					    p.getInventory().setLeggings(null);
					    p.getInventory().setBoots(null);
						for(PotionEffect effect : p.getActivePotionEffects()){
							p.removePotionEffect(effect.getType());
						}

						PotionEffect hunter = new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 1);
						PotionEffect hunter1 = new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 1);
						p.addPotionEffect(hunter);
						p.addPotionEffect(hunter1);

						ItemStack hunterWeapon = new ItemStack(Material.IRON_AXE);
						hunterWeapon.addUnsafeEnchantment(Enchantment.DURABILITY, 127);
						p.getInventory().addItem(hunterWeapon);

						ItemStack hunterArmor = new ItemStack(Material.DIAMOND_LEGGINGS);
						hunterArmor.addUnsafeEnchantment(Enchantment.DURABILITY, 127);
						p.getInventory().addItem(hunterArmor);

						ItemStack hunterArmor1 = new ItemStack(Material.DIAMOND_BOOTS);
						hunterArmor1.addUnsafeEnchantment(Enchantment.DURABILITY, 127);
						p.getInventory().addItem(hunterArmor1);

					}else if(args[0].equalsIgnoreCase("jumper")){
						p.sendMessage(ChatColor.GOLD + "[ZombieRun]"
								+ ChatColor.GREEN + " Jumper kit includes:"
								+ "\n" + "An infinite Jump + Damage Resistance effect (level 1)"
								+ "\n" + "A stone axe"
								+ "\n" + "A full set of chainmail armor (minus the helmet)"
								+ "\n" + "A green list name");

						p.setPlayerListName(greenName);
						p.getInventory().clear();
					    p.getInventory().setHelmet(new ItemStack(Material.EMERALD_BLOCK, 1));
					    p.getInventory().setChestplate(null);
					    p.getInventory().setLeggings(null);
					    p.getInventory().setBoots(null);
						for(PotionEffect effect : p.getActivePotionEffects()){
							p.removePotionEffect(effect.getType());
						}

						PotionEffect jumper = new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 2);
						PotionEffect jumper1 = new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 1);
						p.addPotionEffect(jumper);
						p.addPotionEffect(jumper1);

						ItemStack jumperWeapon = new ItemStack(Material.STONE_AXE);
						jumperWeapon.addUnsafeEnchantment(Enchantment.DURABILITY, 127);
						p.getInventory().addItem(jumperWeapon);

						ItemStack jumperArmor = new ItemStack(Material.CHAINMAIL_BOOTS);
						jumperArmor.addUnsafeEnchantment(Enchantment.DURABILITY, 127);
						p.getInventory().addItem(jumperArmor);

						ItemStack jumperArmor2 = new ItemStack(Material.CHAINMAIL_LEGGINGS);
						jumperArmor2.addUnsafeEnchantment(Enchantment.DURABILITY, 127);
						p.getInventory().addItem(jumperArmor2);

					}else if(args[0].equalsIgnoreCase("survivor")){
						p.sendMessage(ChatColor.GOLD + "[ZombieRun]"
								+ ChatColor.YELLOW + " Survivor kit includes:"
								+ "\n" + "An infinite Regeneration + Speed effect (level 1)"
								+ "\n" + "A gold sword"
								+ "\n" + "A full set of leather armor (minus the helmet)"
								+ "\n" + "A yellow list name");

						p.setPlayerListName(yellowName);
						p.getInventory().clear();
					    p.getInventory().setHelmet(new ItemStack(Material.GOLD_BLOCK, 1));
					    p.getInventory().setChestplate(null);
					    p.getInventory().setLeggings(null);
					    p.getInventory().setBoots(null);
						for(PotionEffect effect : p.getActivePotionEffects()){
							p.removePotionEffect(effect.getType());
						}

						PotionEffect survivor = new PotionEffect(PotionEffectType.REGENERATION, Integer.MAX_VALUE, 1);
						PotionEffect survivor1 = new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1);
						p.addPotionEffect(survivor);
						p.addPotionEffect(survivor1);

						ItemStack survivorWeapon = new ItemStack(Material.GOLD_SWORD);
						survivorWeapon.addUnsafeEnchantment(Enchantment.DURABILITY, 127);
						ItemMeta meta = survivorWeapon.getItemMeta();
						meta.setDisplayName(ChatColor.RED + "Survivor's Protection");
						lore.add(ChatColor.GOLD + "Even the ones with the strongest will to survive need a protector..");
						meta.setLore(lore);
						lore.removeAll(lore);
						p.getInventory().addItem(survivorWeapon);

						ItemStack survivorArmor = new ItemStack(Material.LEATHER_BOOTS);
						survivorArmor.addUnsafeEnchantment(Enchantment.DURABILITY, 127);
						p.getInventory().addItem(survivorArmor);

						ItemStack survivorArmor1 = new ItemStack(Material.LEATHER_CHESTPLATE);
						survivorArmor1.addUnsafeEnchantment(Enchantment.DURABILITY, 127);
						p.getInventory().addItem(survivorArmor1);

						ItemStack survivorArmor3 = new ItemStack(Material.LEATHER_LEGGINGS);
						survivorArmor3.addUnsafeEnchantment(Enchantment.DURABILITY, 127);
						p.getInventory().addItem(survivorArmor3);

					}else if(args[0].equalsIgnoreCase("donor")){
						p.sendMessage(ChatColor.GOLD + "[ZombieRun]"
								+ ChatColor.AQUA + "Donate for more MC-Z Zombie Run kits at donate.mc-z.net!"
								+ "\n" + "Kits include:"
								+ "\n" + "Subscriber, Slayer, Fusionist, Paradox, Tallahassee, Specialist, Guru"
								+ "\n" + "Enjoy our free kits, too! More coming soon!");
					}else if(args[0].equalsIgnoreCase("subscriber")){
						if(p.hasPermission("zombierun.kit.subscriber")||p.hasPermission("zombierun.*")){
							p.sendMessage(ChatColor.GOLD + "[ZombieRun]"
									+ ChatColor.GREEN + "Set your kit to " + ChatColor.BLUE + "subscriber");

							p.setPlayerListName(goldName);
							p.getInventory().clear();
						    p.getInventory().setHelmet(new ItemStack(Material.DIAMOND_BLOCK, 1));
						    p.getInventory().setChestplate(null);
						    p.getInventory().setLeggings(null);
						    p.getInventory().setBoots(null);
							for(PotionEffect effect : p.getActivePotionEffects()){
								p.removePotionEffect(effect.getType());
							}

							PotionEffect subscriber = new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 2);
							PotionEffect subscriber1 = new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 1);
							p.addPotionEffect(subscriber);
							p.addPotionEffect(subscriber1);

							ItemStack subscriberWeapon = new ItemStack(Material.STONE_SWORD);
							subscriberWeapon.addUnsafeEnchantment(Enchantment.DURABILITY, 127);
							ItemMeta meta = subscriberWeapon.getItemMeta();
							meta.setDisplayName(ChatColor.BLUE + "Subscriber's Fury");
							lore.add(ChatColor.GOLD + "Fear the wrath of the subscriber..");
							meta.setLore(lore);
							lore.removeAll(lore);
							p.getInventory().addItem(subscriberWeapon);

							ItemStack subscriberArmor = new ItemStack(Material.IRON_CHESTPLATE);
							subscriberArmor.addUnsafeEnchantment(Enchantment.DURABILITY, 127);
							p.getInventory().addItem(subscriberArmor);


							ItemStack subscriberArmor1 = new ItemStack(Material.CHAINMAIL_BOOTS);
							subscriberArmor1.addUnsafeEnchantment(Enchantment.DURABILITY, 127);
							p.getInventory().addItem(subscriberArmor1);

						}else{
							p.sendMessage(ChatColor.GOLD + "[ZombieRun]"
									+ ChatColor.RED + "You don't have access to that kit! Donate at mc-z.net");
						}
					}else if(args[0].equalsIgnoreCase("slayer")){
						if(p.hasPermission("zombierun.kit.slayer")||p.hasPermission("zombierun.*")){
							p.sendMessage(ChatColor.GOLD + "[ZombieRun]"
									+ ChatColor.GREEN + "Set your kit to " + ChatColor.BLUE + "slayer");

							p.setPlayerListName(goldName);
							p.getInventory().clear();
						    p.getInventory().setHelmet(new ItemStack(Material.DIAMOND_BLOCK, 1));
						    p.getInventory().setChestplate(null);
						    p.getInventory().setLeggings(null);
						    p.getInventory().setBoots(null);
							for(PotionEffect effect : p.getActivePotionEffects()){
								p.removePotionEffect(effect.getType());
							}

							PotionEffect slayer = new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 2);
							PotionEffect slayer1 = new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 1);
							p.addPotionEffect(slayer);
							p.addPotionEffect(slayer1);

							ItemStack slayerWeapon = new ItemStack(Material.IRON_SWORD);
							slayerWeapon.addUnsafeEnchantment(Enchantment.DURABILITY, 127);
							ItemMeta meta = slayerWeapon.getItemMeta();
							meta.setDisplayName(ChatColor.BLUE + "Iron Destructor");
							lore.add(ChatColor.GOLD + "Gotta slay 'em all!");
							meta.setLore(lore);
							lore.removeAll(lore);
							p.getInventory().addItem(slayerWeapon);

							ItemStack slayerArmor = new ItemStack(Material.IRON_CHESTPLATE);
							slayerArmor.addUnsafeEnchantment(Enchantment.DURABILITY, 127);
							slayerArmor.addEnchantment(Enchantment.THORNS, 1);
							p.getInventory().addItem(slayerArmor);

							ItemStack slayerArmor1 = new ItemStack(Material.IRON_LEGGINGS);
							slayerArmor1.addUnsafeEnchantment(Enchantment.DURABILITY, 127);
							p.getInventory().addItem(slayerArmor1);

						}else{
							p.sendMessage(ChatColor.GOLD + "[ZombieRun]"
									+ ChatColor.RED + "You don't have access to that kit! Donate at mc-z.net");
						}
					}else if(args[0].equalsIgnoreCase("fusionist")){
						if(p.hasPermission("slayer.kit.fusionist")||p.hasPermission("zombierun.*")){
							p.sendMessage(ChatColor.GOLD + "[ZombieRun]"
									+ ChatColor.GREEN + "Set your kit to " + ChatColor.BLUE + "fusionist");

							p.setPlayerListName(goldName);
							p.getInventory().clear();
						    p.getInventory().setHelmet(new ItemStack(Material.DIAMOND_BLOCK, 1));
						    p.getInventory().setChestplate(null);
						    p.getInventory().setLeggings(null);
						    p.getInventory().setBoots(null);
							for(PotionEffect effect : p.getActivePotionEffects()){
								p.removePotionEffect(effect.getType());
							}

							PotionEffect fusionist = new PotionEffect(PotionEffectType.ABSORPTION, Integer.MAX_VALUE, 2);
							PotionEffect fusionist1 = new PotionEffect(PotionEffectType.HEALTH_BOOST, Integer.MAX_VALUE, 2);
							p.addPotionEffect(fusionist);
							p.addPotionEffect(fusionist1);

							Potion speedPotion = new Potion(PotionType.SPEED, 1);
							ItemStack stackOfSpeed = speedPotion.toItemStack(2);
							ItemMeta meta = stackOfSpeed.getItemMeta();
							meta.setDisplayName(ChatColor.BLUE + "Fusionist Speed!");
							lore.add(ChatColor.GOLD + "Ain't nobody got time fo zambies!");
							meta.setLore(lore);
							lore.removeAll(lore);
							p.getInventory().addItem(stackOfSpeed);

							Potion strengthPotion = new Potion(PotionType.STRENGTH, 1);
							ItemStack stackOfStrength = strengthPotion.toItemStack(2);
							ItemMeta meta3 = stackOfStrength.getItemMeta();
							meta3.setDisplayName(ChatColor.BLUE + "Fusionist Strength!");
							lore.add(ChatColor.GOLD + "Kill da zambies with da strengths");
							meta3.setLore(lore);
							lore.removeAll(lore);
							p.getInventory().addItem(stackOfStrength);

							Potion healPotion = new Potion(PotionType.INSTANT_HEAL, 3).splash();
							healPotion.setSplash(true);
							ItemStack stackOfHeal = healPotion.toItemStack(2);
							ItemMeta meta4 = stackOfHeal.getItemMeta();
							meta4.setDisplayName(ChatColor.BLUE + "Fusionist Healing!");
							lore.add(ChatColor.GOLD + "Remember, healing hurts da zambies!");
							meta4.setLore(lore);
							lore.removeAll(lore);
							p.getInventory().addItem(stackOfHeal);

							ItemStack fusionistWeapon = new ItemStack(Material.STONE_SWORD);
							fusionistWeapon.addUnsafeEnchantment(Enchantment.DURABILITY, 127);
							fusionistWeapon.addEnchantment(Enchantment.DAMAGE_UNDEAD, 1);
							ItemMeta meta5 = fusionistWeapon.getItemMeta();
							meta5.setDisplayName(ChatColor.BLUE + "Fusionist Stick");
							lore.add(ChatColor.GOLD + "One of da most OP sticks you'll ever see");
							meta5.setLore(lore);
							lore.removeAll(lore);
							p.getInventory().addItem(fusionistWeapon);

							ItemStack fusionistArmor = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
							fusionistArmor.addUnsafeEnchantment(Enchantment.DURABILITY, 127);
							fusionistArmor.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
							p.getInventory().addItem(fusionistArmor);

							ItemStack fusionistArmor1 = new ItemStack(Material.LEATHER_BOOTS);
							fusionistArmor1.addUnsafeEnchantment(Enchantment.DURABILITY, 127);
							fusionistArmor1.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 1);
							p.getInventory().addItem(fusionistArmor1);

						}else{
							p.sendMessage(ChatColor.GOLD + "[ZombieRun]"
									+ ChatColor.RED + "You don't have access to that kit! Donate at mc-z.net");
						}
					}else if(args[0].equalsIgnoreCase("paradox")){
						if(p.hasPermission("zombierun.kit.paradox")||p.hasPermission("zombierun.*")){
							p.sendMessage(ChatColor.GOLD + "[ZombieRun]"
									+ ChatColor.GREEN + "Set your kit to " + ChatColor.BLUE + "paradox");

							p.setPlayerListName(goldName);
							p.getInventory().clear();
						    p.getInventory().setHelmet(new ItemStack(Material.DIAMOND_BLOCK, 1));
						    p.getInventory().setChestplate(null);
						    p.getInventory().setLeggings(null);
						    p.getInventory().setBoots(null);
							for(PotionEffect effect : p.getActivePotionEffects()){
								p.removePotionEffect(effect.getType());
							}

							PotionEffect paradox = new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 1);
							PotionEffect paradox1 = new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1);
							PotionEffect paradox2 = new PotionEffect(PotionEffectType.WEAKNESS, Integer.MAX_VALUE, 1);
							PotionEffect paradox3 = new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 1);
							PotionEffect paradox4 = new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 1);
							p.addPotionEffect(paradox);
							p.addPotionEffect(paradox1);
							p.addPotionEffect(paradox2);
							p.addPotionEffect(paradox3);
							p.addPotionEffect(paradox4);

							ItemStack paradoxGrenades = new ItemStack(Material.ENDER_PEARL, 16);
							ItemMeta paradoxGrenadeMeta = paradoxGrenades.getItemMeta();
							paradoxGrenadeMeta.setDisplayName(ChatColor.GREEN + "Paradox Grenades");
							lore.add(ChatColor.AQUA + "Watch out, they can blow you off the path!");
							paradoxGrenadeMeta.setLore(lore);
							lore.removeAll(lore);
							p.getInventory().addItem(paradoxGrenades);

							ItemStack paradoxTwinkie = new ItemStack(Material.GOLD_INGOT, 1);
							ItemMeta paradoxTwinkieMeta = paradoxTwinkie.getItemMeta();
							paradoxTwinkieMeta.setDisplayName(ChatColor.GREEN + "Paradox Twinkie");
							lore.add(ChatColor.AQUA + "Tallahassee approves.");
							paradoxTwinkieMeta.setLore(lore);
							lore.removeAll(lore);
							p.getInventory().addItem(paradoxTwinkie);
							
							ItemStack paradoxWeapon = new ItemStack(Material.DIAMOND_HOE, 1);
							paradoxWeapon.addUnsafeEnchantment(Enchantment.DURABILITY, 127);
							ItemMeta paradoxWeaponMeta = paradoxWeapon.getItemMeta();
							paradoxWeaponMeta.setDisplayName(ChatColor.GREEN + "Paradox's Hoe");
							lore.add(ChatColor.AQUA + "Cuz Paradox is too gangster for a sword.");
							paradoxWeaponMeta.setLore(lore);
							lore.removeAll(lore);
							p.getInventory().addItem(paradoxWeapon);
							
							ItemStack paradoxArmor = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
							paradoxArmor.addUnsafeEnchantment(Enchantment.DURABILITY, 127);
							paradoxArmor.addEnchantment(Enchantment.THORNS, 2);
							paradoxArmor.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 1);
							p.getInventory().addItem(paradoxArmor);
							
							ItemStack paradoxArmor1 = new ItemStack(Material.IRON_BOOTS, 1);
							paradoxArmor1.addUnsafeEnchantment(Enchantment.THORNS, 1);
							p.getInventory().addItem(paradoxArmor1);
						}else{
							p.sendMessage(ChatColor.GOLD + "[ZombieRun]"
									+ ChatColor.RED + "You don't have access to that kit! Donate at mc-z.net");
						}
						
					}else if(args[0].equalsIgnoreCase("tallahassee")||p.hasPermission("zombierun.*")){
						if(p.hasPermission("zombierun.kit.tallahassee")){
							p.sendMessage(ChatColor.GOLD + "[ZombieRun]"
									+ ChatColor.GREEN + "Set your kit to " + ChatColor.BLUE + "tallahassee");

							p.setPlayerListName(goldName);
							p.getInventory().clear();
						    p.getInventory().setHelmet(new ItemStack(Material.DIAMOND_BLOCK, 1));
						    p.getInventory().setChestplate(null);
						    p.getInventory().setLeggings(null);
						    p.getInventory().setBoots(null);
							for(PotionEffect effect : p.getActivePotionEffects()){
								p.removePotionEffect(effect.getType());
							}
							
							ItemStack tallahasseeTwinkie = new ItemStack(Material.GOLD_INGOT, 32);
							tallahasseeTwinkie.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 7);
							tallahasseeTwinkie.addUnsafeEnchantment(Enchantment.KNOCKBACK, 1);
							ItemMeta tallahasseeTwinkieMeta = tallahasseeTwinkie.getItemMeta();
							tallahasseeTwinkieMeta.setDisplayName(ChatColor.GREEN + "Tallahassee's Twinkie");
							lore.add(ChatColor.AQUA + "Gotta eat 'em all!");
							tallahasseeTwinkieMeta.setLore(lore);
							lore.removeAll(lore);
							p.getInventory().addItem(tallahasseeTwinkie);
							
							
							
						}else{
							p.sendMessage(ChatColor.GOLD + "[ZombieRun]"
									+ ChatColor.RED + "You don't have access to that kit! Donate at mc-z.net");
						}
							
					}else{
						String invalidKit = args[0];
						p.sendMessage(ChatColor.GOLD + "[ZombieRun]"
								+ ChatColor.RED + "Invalid kit '"
								+ ChatColor.GREEN + invalidKit
								+ ChatColor.RED + "'!");
					}
				}else{
					p.sendMessage(ChatColor.GOLD + "[ZombieRun]"
							+ ChatColor.RED + "Too many arguments! /zrkit <KITNAME>");
				}
			}else{
				p.sendMessage(ChatColor.GOLD + "[ZombieRun]"
						+ ChatColor.RED + "This command is for ZombieRun worlds only!");
			}
		}else{
			log.warning("[ZombieRun] This command is in-game only!");
		}
		return false;
	}
}