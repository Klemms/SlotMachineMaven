package fr.klemms.slotmachine.utils.sounds;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.Sound;

import fr.klemms.slotmachine.SlotPlugin;

public class SoundToMaterialList_113 {

	public static void initList() {
		SlotPlugin.soundMaterialMap = new HashMap<Sound, Material>();
		
		for (Sound sound : Sound.values()) {
			switch(sound) {
				case AMBIENT_CAVE:
					SlotPlugin.soundMaterialMap.put(sound, Material.STONE);
					break;
				case AMBIENT_UNDERWATER_ENTER:
					SlotPlugin.soundMaterialMap.put(sound, Material.WATER_BUCKET);
					break;
				case AMBIENT_UNDERWATER_EXIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.WATER_BUCKET);
					break;
				case AMBIENT_UNDERWATER_LOOP:
					SlotPlugin.soundMaterialMap.put(sound, Material.WATER_BUCKET);
					break;
				case AMBIENT_UNDERWATER_LOOP_ADDITIONS:
					SlotPlugin.soundMaterialMap.put(sound, Material.WATER_BUCKET);
					break;
				case AMBIENT_UNDERWATER_LOOP_ADDITIONS_RARE:
					SlotPlugin.soundMaterialMap.put(sound, Material.WATER_BUCKET);
					break;
				case AMBIENT_UNDERWATER_LOOP_ADDITIONS_ULTRA_RARE:
					SlotPlugin.soundMaterialMap.put(sound, Material.WATER_BUCKET);
					break;
				case BLOCK_ANVIL_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.ANVIL);
					break;
				case BLOCK_ANVIL_DESTROY:
					SlotPlugin.soundMaterialMap.put(sound, Material.ANVIL);
					break;
				case BLOCK_ANVIL_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.ANVIL);
					break;
				case BLOCK_ANVIL_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.ANVIL);
					break;
				case BLOCK_ANVIL_LAND:
					SlotPlugin.soundMaterialMap.put(sound, Material.ANVIL);
					break;
				case BLOCK_ANVIL_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.ANVIL);
					break;
				case BLOCK_ANVIL_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.ANVIL);
					break;
				case BLOCK_ANVIL_USE:
					SlotPlugin.soundMaterialMap.put(sound, Material.ANVIL);
					break;
				case BLOCK_BEACON_ACTIVATE:
					SlotPlugin.soundMaterialMap.put(sound, Material.BEACON);
					break;
				case BLOCK_BEACON_AMBIENT:
					SlotPlugin.soundMaterialMap.put(sound, Material.BEACON);
					break;
				case BLOCK_BEACON_DEACTIVATE:
					SlotPlugin.soundMaterialMap.put(sound, Material.BEACON);
					break;
				case BLOCK_BEACON_POWER_SELECT:
					SlotPlugin.soundMaterialMap.put(sound, Material.BEACON);
					break;
				case BLOCK_BREWING_STAND_BREW:
					SlotPlugin.soundMaterialMap.put(sound, Material.BREWING_STAND);
					break;
				case BLOCK_BUBBLE_COLUMN_BUBBLE_POP:
					SlotPlugin.soundMaterialMap.put(sound, Material.WATER_BUCKET);
					break;
				case BLOCK_BUBBLE_COLUMN_UPWARDS_AMBIENT:
					SlotPlugin.soundMaterialMap.put(sound, Material.WATER_BUCKET);
					break;
				case BLOCK_BUBBLE_COLUMN_UPWARDS_INSIDE:
					SlotPlugin.soundMaterialMap.put(sound, Material.WATER_BUCKET);
					break;
				case BLOCK_BUBBLE_COLUMN_WHIRLPOOL_AMBIENT:
					SlotPlugin.soundMaterialMap.put(sound, Material.WATER_BUCKET);
					break;
				case BLOCK_BUBBLE_COLUMN_WHIRLPOOL_INSIDE:
					SlotPlugin.soundMaterialMap.put(sound, Material.WATER_BUCKET);
					break;
				case BLOCK_CHEST_CLOSE:
					SlotPlugin.soundMaterialMap.put(sound, Material.CHEST);
					break;
				case BLOCK_CHEST_LOCKED:
					SlotPlugin.soundMaterialMap.put(sound, Material.CHEST);
					break;
				case BLOCK_CHEST_OPEN:
					SlotPlugin.soundMaterialMap.put(sound, Material.CHEST);
					break;
				case BLOCK_CHORUS_FLOWER_DEATH:
					SlotPlugin.soundMaterialMap.put(sound, Material.CHORUS_FRUIT);
					break;
				case BLOCK_CHORUS_FLOWER_GROW:
					SlotPlugin.soundMaterialMap.put(sound, Material.CHORUS_FRUIT);
					break;
				case BLOCK_COMPARATOR_CLICK:
					SlotPlugin.soundMaterialMap.put(sound, Material.COMPARATOR);
					break;
				case BLOCK_CONDUIT_ACTIVATE:
					SlotPlugin.soundMaterialMap.put(sound, Material.CONDUIT);
					break;
				case BLOCK_CONDUIT_AMBIENT:
					SlotPlugin.soundMaterialMap.put(sound, Material.CONDUIT);
					break;
				case BLOCK_CONDUIT_AMBIENT_SHORT:
					SlotPlugin.soundMaterialMap.put(sound, Material.CONDUIT);
					break;
				case BLOCK_CONDUIT_ATTACK_TARGET:
					SlotPlugin.soundMaterialMap.put(sound, Material.CONDUIT);
					break;
				case BLOCK_CONDUIT_DEACTIVATE:
					SlotPlugin.soundMaterialMap.put(sound, Material.CONDUIT);
					break;
				case BLOCK_CORAL_BLOCK_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.TUBE_CORAL);
					break;
				case BLOCK_CORAL_BLOCK_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.TUBE_CORAL);
					break;
				case BLOCK_CORAL_BLOCK_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.TUBE_CORAL);
					break;
				case BLOCK_CORAL_BLOCK_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.TUBE_CORAL);
					break;
				case BLOCK_CORAL_BLOCK_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.TUBE_CORAL);
					break;
				case BLOCK_DISPENSER_DISPENSE:
					SlotPlugin.soundMaterialMap.put(sound, Material.DISPENSER);
					break;
				case BLOCK_DISPENSER_FAIL:
					SlotPlugin.soundMaterialMap.put(sound, Material.DISPENSER);
					break;
				case BLOCK_DISPENSER_LAUNCH:
					SlotPlugin.soundMaterialMap.put(sound, Material.DISPENSER);
					break;
				case BLOCK_ENCHANTMENT_TABLE_USE:
					SlotPlugin.soundMaterialMap.put(sound, Material.ENCHANTING_TABLE);
					break;
				case BLOCK_ENDER_CHEST_CLOSE:
					SlotPlugin.soundMaterialMap.put(sound, Material.ENDER_CHEST);
					break;
				case BLOCK_ENDER_CHEST_OPEN:
					SlotPlugin.soundMaterialMap.put(sound, Material.ENDER_CHEST);
					break;
				case BLOCK_END_GATEWAY_SPAWN:
					SlotPlugin.soundMaterialMap.put(sound, Material.ENDER_PEARL);
					break;
				case BLOCK_END_PORTAL_FRAME_FILL:
					SlotPlugin.soundMaterialMap.put(sound, Material.END_PORTAL_FRAME);
					break;
				case BLOCK_END_PORTAL_SPAWN:
					SlotPlugin.soundMaterialMap.put(sound, Material.END_PORTAL_FRAME);
					break;
				case BLOCK_FENCE_GATE_CLOSE:
					SlotPlugin.soundMaterialMap.put(sound, Material.OAK_FENCE_GATE);
					break;
				case BLOCK_FENCE_GATE_OPEN:
					SlotPlugin.soundMaterialMap.put(sound, Material.OAK_FENCE_GATE);
					break;
				case BLOCK_FIRE_AMBIENT:
					SlotPlugin.soundMaterialMap.put(sound, Material.FLINT_AND_STEEL);
					break;
				case BLOCK_FIRE_EXTINGUISH:
					SlotPlugin.soundMaterialMap.put(sound, Material.FLINT_AND_STEEL);
					break;
				case BLOCK_FURNACE_FIRE_CRACKLE:
					SlotPlugin.soundMaterialMap.put(sound, Material.FURNACE);
					break;
				case BLOCK_GLASS_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.GLASS);
					break;
				case BLOCK_GLASS_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.GLASS);
					break;
				case BLOCK_GLASS_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.GLASS);
					break;
				case BLOCK_GLASS_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.GLASS);
					break;
				case BLOCK_GLASS_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.GLASS);
					break;
				case BLOCK_GRASS_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.GRASS_BLOCK);
					break;
				case BLOCK_GRASS_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.GRASS_BLOCK);
					break;
				case BLOCK_GRASS_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.GRASS_BLOCK);
					break;
				case BLOCK_GRASS_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.GRASS_BLOCK);
					break;
				case BLOCK_GRASS_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.GRASS_BLOCK);
					break;
				case BLOCK_GRAVEL_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.GRAVEL);
					break;
				case BLOCK_GRAVEL_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.GRAVEL);
					break;
				case BLOCK_GRAVEL_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.GRAVEL);
					break;
				case BLOCK_GRAVEL_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.GRAVEL);
					break;
				case BLOCK_GRAVEL_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.GRAVEL);
					break;
				case BLOCK_IRON_DOOR_CLOSE:
					SlotPlugin.soundMaterialMap.put(sound, Material.IRON_DOOR);
					break;
				case BLOCK_IRON_DOOR_OPEN:
					SlotPlugin.soundMaterialMap.put(sound, Material.IRON_DOOR);
					break;
				case BLOCK_IRON_TRAPDOOR_CLOSE:
					SlotPlugin.soundMaterialMap.put(sound, Material.IRON_TRAPDOOR);
					break;
				case BLOCK_IRON_TRAPDOOR_OPEN:
					SlotPlugin.soundMaterialMap.put(sound, Material.IRON_TRAPDOOR);
					break;
				case BLOCK_LADDER_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.LADDER);
					break;
				case BLOCK_LADDER_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.LADDER);
					break;
				case BLOCK_LADDER_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.LADDER);
					break;
				case BLOCK_LADDER_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.LADDER);
					break;
				case BLOCK_LADDER_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.LADDER);
					break;
				case BLOCK_LAVA_AMBIENT:
					SlotPlugin.soundMaterialMap.put(sound, Material.LAVA_BUCKET);
					break;
				case BLOCK_LAVA_EXTINGUISH:
					SlotPlugin.soundMaterialMap.put(sound, Material.LAVA_BUCKET);
					break;
				case BLOCK_LAVA_POP:
					SlotPlugin.soundMaterialMap.put(sound, Material.LAVA_BUCKET);
					break;
				case BLOCK_LEVER_CLICK:
					SlotPlugin.soundMaterialMap.put(sound, Material.LEVER);
					break;
				case BLOCK_LILY_PAD_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.LILY_PAD);
					break;
				case BLOCK_METAL_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.IRON_BLOCK);
					break;
				case BLOCK_METAL_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.IRON_BLOCK);
					break;
				case BLOCK_METAL_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.IRON_BLOCK);
					break;
				case BLOCK_METAL_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.IRON_BLOCK);
					break;
				case BLOCK_METAL_PRESSURE_PLATE_CLICK_OFF:
					SlotPlugin.soundMaterialMap.put(sound, Material.HEAVY_WEIGHTED_PRESSURE_PLATE);
					break;
				case BLOCK_METAL_PRESSURE_PLATE_CLICK_ON:
					SlotPlugin.soundMaterialMap.put(sound, Material.HEAVY_WEIGHTED_PRESSURE_PLATE);
					break;
				case BLOCK_METAL_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.IRON_BLOCK);
					break;
				case BLOCK_NOTE_BLOCK_BASEDRUM:
					SlotPlugin.soundMaterialMap.put(sound, Material.NOTE_BLOCK);
					break;
				case BLOCK_NOTE_BLOCK_BASS:
					SlotPlugin.soundMaterialMap.put(sound, Material.NOTE_BLOCK);
					break;
				case BLOCK_NOTE_BLOCK_BELL:
					SlotPlugin.soundMaterialMap.put(sound, Material.NOTE_BLOCK);
					break;
				case BLOCK_NOTE_BLOCK_CHIME:
					SlotPlugin.soundMaterialMap.put(sound, Material.NOTE_BLOCK);
					break;
				case BLOCK_NOTE_BLOCK_FLUTE:
					SlotPlugin.soundMaterialMap.put(sound, Material.NOTE_BLOCK);
					break;
				case BLOCK_NOTE_BLOCK_GUITAR:
					SlotPlugin.soundMaterialMap.put(sound, Material.NOTE_BLOCK);
					break;
				case BLOCK_NOTE_BLOCK_HARP:
					SlotPlugin.soundMaterialMap.put(sound, Material.NOTE_BLOCK);
					break;
				case BLOCK_NOTE_BLOCK_HAT:
					SlotPlugin.soundMaterialMap.put(sound, Material.NOTE_BLOCK);
					break;
				case BLOCK_NOTE_BLOCK_PLING:
					SlotPlugin.soundMaterialMap.put(sound, Material.NOTE_BLOCK);
					break;
				case BLOCK_NOTE_BLOCK_SNARE:
					SlotPlugin.soundMaterialMap.put(sound, Material.NOTE_BLOCK);
					break;
				case BLOCK_NOTE_BLOCK_XYLOPHONE:
					SlotPlugin.soundMaterialMap.put(sound, Material.NOTE_BLOCK);
					break;
				case BLOCK_PISTON_CONTRACT:
					SlotPlugin.soundMaterialMap.put(sound, Material.PISTON);
					break;
				case BLOCK_PISTON_EXTEND:
					SlotPlugin.soundMaterialMap.put(sound, Material.PISTON);
					break;
				case BLOCK_PORTAL_AMBIENT:
					SlotPlugin.soundMaterialMap.put(sound, Material.OBSIDIAN);
					break;
				case BLOCK_PORTAL_TRAVEL:
					SlotPlugin.soundMaterialMap.put(sound, Material.OBSIDIAN);
					break;
				case BLOCK_PORTAL_TRIGGER:
					SlotPlugin.soundMaterialMap.put(sound, Material.OBSIDIAN);
					break;
				case BLOCK_PUMPKIN_CARVE:
					SlotPlugin.soundMaterialMap.put(sound, Material.PUMPKIN);
					break;
				case BLOCK_REDSTONE_TORCH_BURNOUT:
					SlotPlugin.soundMaterialMap.put(sound, Material.REDSTONE_TORCH);
					break;
				case BLOCK_SAND_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.SAND);
					break;
				case BLOCK_SAND_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.SAND);
					break;
				case BLOCK_SAND_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.SAND);
					break;
				case BLOCK_SAND_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.SAND);
					break;
				case BLOCK_SAND_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.SAND);
					break;
				case BLOCK_SHULKER_BOX_CLOSE:
					SlotPlugin.soundMaterialMap.put(sound, Material.SHULKER_BOX);
					break;
				case BLOCK_SHULKER_BOX_OPEN:
					SlotPlugin.soundMaterialMap.put(sound, Material.SHULKER_BOX);
					break;
				case BLOCK_SLIME_BLOCK_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.SLIME_BLOCK);
					break;
				case BLOCK_SLIME_BLOCK_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.SLIME_BLOCK);
					break;
				case BLOCK_SLIME_BLOCK_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.SLIME_BLOCK);
					break;
				case BLOCK_SLIME_BLOCK_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.SLIME_BLOCK);
					break;
				case BLOCK_SLIME_BLOCK_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.SLIME_BLOCK);
					break;
				case BLOCK_SNOW_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.SNOW_BLOCK);
					break;
				case BLOCK_SNOW_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.SNOW_BLOCK);
					break;
				case BLOCK_SNOW_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.SNOW_BLOCK);
					break;
				case BLOCK_SNOW_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.SNOW_BLOCK);
					break;
				case BLOCK_SNOW_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.SNOW_BLOCK);
					break;
				case BLOCK_STONE_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.STONE);
					break;
				case BLOCK_STONE_BUTTON_CLICK_OFF:
					SlotPlugin.soundMaterialMap.put(sound, Material.STONE_BUTTON);
					break;
				case BLOCK_STONE_BUTTON_CLICK_ON:
					SlotPlugin.soundMaterialMap.put(sound, Material.STONE_BUTTON);
					break;
				case BLOCK_STONE_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.STONE);
					break;
				case BLOCK_STONE_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.STONE);
					break;
				case BLOCK_STONE_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.STONE);
					break;
				case BLOCK_STONE_PRESSURE_PLATE_CLICK_OFF:
					SlotPlugin.soundMaterialMap.put(sound, Material.STONE_PRESSURE_PLATE);
					break;
				case BLOCK_STONE_PRESSURE_PLATE_CLICK_ON:
					SlotPlugin.soundMaterialMap.put(sound, Material.STONE_PRESSURE_PLATE);
					break;
				case BLOCK_STONE_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.STONE);
					break;
				case BLOCK_TRIPWIRE_ATTACH:
					SlotPlugin.soundMaterialMap.put(sound, Material.TRIPWIRE_HOOK);
					break;
				case BLOCK_TRIPWIRE_CLICK_OFF:
					SlotPlugin.soundMaterialMap.put(sound, Material.TRIPWIRE_HOOK);
					break;
				case BLOCK_TRIPWIRE_CLICK_ON:
					SlotPlugin.soundMaterialMap.put(sound, Material.TRIPWIRE_HOOK);
					break;
				case BLOCK_TRIPWIRE_DETACH:
					SlotPlugin.soundMaterialMap.put(sound, Material.TRIPWIRE_HOOK);
					break;
				case BLOCK_WATER_AMBIENT:
					SlotPlugin.soundMaterialMap.put(sound, Material.WATER_BUCKET);
					break;
				case BLOCK_WET_GRASS_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.PODZOL);
					break;
				case BLOCK_WET_GRASS_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.PODZOL);
					break;
				case BLOCK_WET_GRASS_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.PODZOL);
					break;
				case BLOCK_WET_GRASS_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.PODZOL);
					break;
				case BLOCK_WET_GRASS_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.PODZOL);
					break;
				case BLOCK_WOODEN_BUTTON_CLICK_OFF:
					SlotPlugin.soundMaterialMap.put(sound, Material.OAK_BUTTON);
					break;
				case BLOCK_WOODEN_BUTTON_CLICK_ON:
					SlotPlugin.soundMaterialMap.put(sound, Material.OAK_BUTTON);
					break;
				case BLOCK_WOODEN_DOOR_CLOSE:
					SlotPlugin.soundMaterialMap.put(sound, Material.OAK_DOOR);
					break;
				case BLOCK_WOODEN_DOOR_OPEN:
					SlotPlugin.soundMaterialMap.put(sound, Material.OAK_DOOR);
					break;
				case BLOCK_WOODEN_PRESSURE_PLATE_CLICK_OFF:
					SlotPlugin.soundMaterialMap.put(sound, Material.OAK_PRESSURE_PLATE);
					break;
				case BLOCK_WOODEN_PRESSURE_PLATE_CLICK_ON:
					SlotPlugin.soundMaterialMap.put(sound, Material.OAK_PRESSURE_PLATE);
					break;
				case BLOCK_WOODEN_TRAPDOOR_CLOSE:
					SlotPlugin.soundMaterialMap.put(sound, Material.OAK_TRAPDOOR);
					break;
				case BLOCK_WOODEN_TRAPDOOR_OPEN:
					SlotPlugin.soundMaterialMap.put(sound, Material.OAK_TRAPDOOR);
					break;
				case BLOCK_WOOD_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.OAK_LOG);
					break;
				case BLOCK_WOOD_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.OAK_LOG);
					break;
				case BLOCK_WOOD_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.OAK_LOG);
					break;
				case BLOCK_WOOD_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.OAK_LOG);
					break;
				case BLOCK_WOOD_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.OAK_LOG);
					break;
				case BLOCK_WOOL_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.WHITE_WOOL);
					break;
				case BLOCK_WOOL_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.WHITE_WOOL);
					break;
				case BLOCK_WOOL_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.WHITE_WOOL);
					break;
				case BLOCK_WOOL_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.WHITE_WOOL);
					break;
				case BLOCK_WOOL_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.WHITE_WOOL);
					break;
				case ENCHANT_THORNS_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.ENCHANTED_BOOK);
					break;
				case ENTITY_ARMOR_STAND_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.ARMOR_STAND);
					break;
				case ENTITY_ARMOR_STAND_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.ARMOR_STAND);
					break;
				case ENTITY_ARMOR_STAND_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.ARMOR_STAND);
					break;
				case ENTITY_ARMOR_STAND_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.ARMOR_STAND);
					break;
				case ENTITY_ARROW_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.ARROW);
					break;
				case ENTITY_ARROW_HIT_PLAYER:
					SlotPlugin.soundMaterialMap.put(sound, Material.ARROW);
					break;
				case ENTITY_ARROW_SHOOT:
					SlotPlugin.soundMaterialMap.put(sound, Material.ARROW);
					break;
				case ENTITY_BAT_AMBIENT:
					SlotPlugin.soundMaterialMap.put(sound, Material.BAT_SPAWN_EGG);
					break;
				case ENTITY_BAT_DEATH:
					SlotPlugin.soundMaterialMap.put(sound, Material.BAT_SPAWN_EGG);
					break;
				case ENTITY_BAT_HURT:
					SlotPlugin.soundMaterialMap.put(sound, Material.BAT_SPAWN_EGG);
					break;
				case ENTITY_BAT_LOOP:
					SlotPlugin.soundMaterialMap.put(sound, Material.BAT_SPAWN_EGG);
					break;
				case ENTITY_BAT_TAKEOFF:
					SlotPlugin.soundMaterialMap.put(sound, Material.BAT_SPAWN_EGG);
					break;
				case ENTITY_BLAZE_AMBIENT:
					SlotPlugin.soundMaterialMap.put(sound, Material.BLAZE_SPAWN_EGG);
					break;
				case ENTITY_BLAZE_BURN:
					SlotPlugin.soundMaterialMap.put(sound, Material.BLAZE_SPAWN_EGG);
					break;
				case ENTITY_BLAZE_DEATH:
					SlotPlugin.soundMaterialMap.put(sound, Material.BLAZE_SPAWN_EGG);
					break;
				case ENTITY_BLAZE_HURT:
					SlotPlugin.soundMaterialMap.put(sound, Material.BLAZE_SPAWN_EGG);
					break;
				case ENTITY_BLAZE_SHOOT:
					SlotPlugin.soundMaterialMap.put(sound, Material.BLAZE_SPAWN_EGG);
					break;
				case ENTITY_BOAT_PADDLE_LAND:
					SlotPlugin.soundMaterialMap.put(sound, Material.OAK_BOAT);
					break;
				case ENTITY_BOAT_PADDLE_WATER:
					SlotPlugin.soundMaterialMap.put(sound, Material.OAK_BOAT);
					break;
				case ENTITY_CAT_AMBIENT:
					SlotPlugin.soundMaterialMap.put(sound, Material.OCELOT_SPAWN_EGG);
					break;
				case ENTITY_CAT_DEATH:
					SlotPlugin.soundMaterialMap.put(sound, Material.OCELOT_SPAWN_EGG);
					break;
				case ENTITY_CAT_HISS:
					SlotPlugin.soundMaterialMap.put(sound, Material.OCELOT_SPAWN_EGG);
					break;
				case ENTITY_CAT_HURT:
					SlotPlugin.soundMaterialMap.put(sound, Material.OCELOT_SPAWN_EGG);
					break;
				case ENTITY_CAT_PURR:
					SlotPlugin.soundMaterialMap.put(sound, Material.OCELOT_SPAWN_EGG);
					break;
				case ENTITY_CAT_PURREOW:
					SlotPlugin.soundMaterialMap.put(sound, Material.OCELOT_SPAWN_EGG);
					break;
				case ENTITY_CHICKEN_AMBIENT:
					SlotPlugin.soundMaterialMap.put(sound, Material.CHICKEN_SPAWN_EGG);
					break;
				case ENTITY_CHICKEN_DEATH:
					SlotPlugin.soundMaterialMap.put(sound, Material.CHICKEN_SPAWN_EGG);
					break;
				case ENTITY_CHICKEN_EGG:
					SlotPlugin.soundMaterialMap.put(sound, Material.CHICKEN_SPAWN_EGG);
					break;
				case ENTITY_CHICKEN_HURT:
					SlotPlugin.soundMaterialMap.put(sound, Material.CHICKEN_SPAWN_EGG);
					break;
				case ENTITY_CHICKEN_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.CHICKEN_SPAWN_EGG);
					break;
				case ENTITY_COD_AMBIENT:
					SlotPlugin.soundMaterialMap.put(sound, Material.COD_SPAWN_EGG);
					break;
				case ENTITY_COD_DEATH:
					SlotPlugin.soundMaterialMap.put(sound, Material.COD_SPAWN_EGG);
					break;
				case ENTITY_COD_FLOP:
					SlotPlugin.soundMaterialMap.put(sound, Material.COD_SPAWN_EGG);
					break;
				case ENTITY_COD_HURT:
					SlotPlugin.soundMaterialMap.put(sound, Material.COD_SPAWN_EGG);
					break;
				case ENTITY_COW_AMBIENT:
					SlotPlugin.soundMaterialMap.put(sound, Material.COW_SPAWN_EGG);
					break;
				case ENTITY_COW_DEATH:
					SlotPlugin.soundMaterialMap.put(sound, Material.COW_SPAWN_EGG);
					break;
				case ENTITY_COW_HURT:
					SlotPlugin.soundMaterialMap.put(sound, Material.COW_SPAWN_EGG);
					break;
				case ENTITY_COW_MILK:
					SlotPlugin.soundMaterialMap.put(sound, Material.COW_SPAWN_EGG);
					break;
				case ENTITY_COW_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.COW_SPAWN_EGG);
					break;
				case ENTITY_CREEPER_DEATH:
					SlotPlugin.soundMaterialMap.put(sound, Material.CREEPER_SPAWN_EGG);
					break;
				case ENTITY_CREEPER_HURT:
					SlotPlugin.soundMaterialMap.put(sound, Material.CREEPER_SPAWN_EGG);
					break;
				case ENTITY_CREEPER_PRIMED:
					SlotPlugin.soundMaterialMap.put(sound, Material.CREEPER_SPAWN_EGG);
					break;
				case ENTITY_DOLPHIN_AMBIENT:
					SlotPlugin.soundMaterialMap.put(sound, Material.DOLPHIN_SPAWN_EGG);
					break;
				case ENTITY_DOLPHIN_AMBIENT_WATER:
					SlotPlugin.soundMaterialMap.put(sound, Material.DOLPHIN_SPAWN_EGG);
					break;
				case ENTITY_DOLPHIN_ATTACK:
					SlotPlugin.soundMaterialMap.put(sound, Material.DOLPHIN_SPAWN_EGG);
					break;
				case ENTITY_DOLPHIN_DEATH:
					SlotPlugin.soundMaterialMap.put(sound, Material.DOLPHIN_SPAWN_EGG);
					break;
				case ENTITY_DOLPHIN_EAT:
					SlotPlugin.soundMaterialMap.put(sound, Material.DOLPHIN_SPAWN_EGG);
					break;
				case ENTITY_DOLPHIN_HURT:
					SlotPlugin.soundMaterialMap.put(sound, Material.DOLPHIN_SPAWN_EGG);
					break;
				case ENTITY_DOLPHIN_JUMP:
					SlotPlugin.soundMaterialMap.put(sound, Material.DOLPHIN_SPAWN_EGG);
					break;
				case ENTITY_DOLPHIN_PLAY:
					SlotPlugin.soundMaterialMap.put(sound, Material.DOLPHIN_SPAWN_EGG);
					break;
				case ENTITY_DOLPHIN_SPLASH:
					SlotPlugin.soundMaterialMap.put(sound, Material.DOLPHIN_SPAWN_EGG);
					break;
				case ENTITY_DOLPHIN_SWIM:
					SlotPlugin.soundMaterialMap.put(sound, Material.DOLPHIN_SPAWN_EGG);
					break;
				case ENTITY_DONKEY_AMBIENT:
					SlotPlugin.soundMaterialMap.put(sound, Material.DONKEY_SPAWN_EGG);
					break;
				case ENTITY_DONKEY_ANGRY:
					SlotPlugin.soundMaterialMap.put(sound, Material.DONKEY_SPAWN_EGG);
					break;
				case ENTITY_DONKEY_CHEST:
					SlotPlugin.soundMaterialMap.put(sound, Material.DONKEY_SPAWN_EGG);
					break;
				case ENTITY_DONKEY_DEATH:
					SlotPlugin.soundMaterialMap.put(sound, Material.DONKEY_SPAWN_EGG);
					break;
				case ENTITY_DONKEY_HURT:
					SlotPlugin.soundMaterialMap.put(sound, Material.DONKEY_SPAWN_EGG);
					break;
				case ENTITY_DRAGON_FIREBALL_EXPLODE:
					SlotPlugin.soundMaterialMap.put(sound, Material.FIRE_CHARGE);
					break;
				case ENTITY_DROWNED_AMBIENT:
					SlotPlugin.soundMaterialMap.put(sound, Material.DROWNED_SPAWN_EGG);
					break;
				case ENTITY_DROWNED_AMBIENT_WATER:
					SlotPlugin.soundMaterialMap.put(sound, Material.DROWNED_SPAWN_EGG);
					break;
				case ENTITY_DROWNED_DEATH:
					SlotPlugin.soundMaterialMap.put(sound, Material.DROWNED_SPAWN_EGG);
					break;
				case ENTITY_DROWNED_DEATH_WATER:
					SlotPlugin.soundMaterialMap.put(sound, Material.DROWNED_SPAWN_EGG);
					break;
				case ENTITY_DROWNED_HURT:
					SlotPlugin.soundMaterialMap.put(sound, Material.DROWNED_SPAWN_EGG);
					break;
				case ENTITY_DROWNED_HURT_WATER:
					SlotPlugin.soundMaterialMap.put(sound, Material.DROWNED_SPAWN_EGG);
					break;
				case ENTITY_DROWNED_SHOOT:
					SlotPlugin.soundMaterialMap.put(sound, Material.DROWNED_SPAWN_EGG);
					break;
				case ENTITY_DROWNED_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.DROWNED_SPAWN_EGG);
					break;
				case ENTITY_DROWNED_SWIM:
					SlotPlugin.soundMaterialMap.put(sound, Material.DROWNED_SPAWN_EGG);
					break;
				case ENTITY_EGG_THROW:
					SlotPlugin.soundMaterialMap.put(sound, Material.EGG);
					break;
				case ENTITY_ELDER_GUARDIAN_AMBIENT:
					SlotPlugin.soundMaterialMap.put(sound, Material.ELDER_GUARDIAN_SPAWN_EGG);
					break;
				case ENTITY_ELDER_GUARDIAN_AMBIENT_LAND:
					SlotPlugin.soundMaterialMap.put(sound, Material.ELDER_GUARDIAN_SPAWN_EGG);
					break;
				case ENTITY_ELDER_GUARDIAN_CURSE:
					SlotPlugin.soundMaterialMap.put(sound, Material.ELDER_GUARDIAN_SPAWN_EGG);
					break;
				case ENTITY_ELDER_GUARDIAN_DEATH:
					SlotPlugin.soundMaterialMap.put(sound, Material.ELDER_GUARDIAN_SPAWN_EGG);
					break;
				case ENTITY_ELDER_GUARDIAN_DEATH_LAND:
					SlotPlugin.soundMaterialMap.put(sound, Material.ELDER_GUARDIAN_SPAWN_EGG);
					break;
				case ENTITY_ELDER_GUARDIAN_FLOP:
					SlotPlugin.soundMaterialMap.put(sound, Material.ELDER_GUARDIAN_SPAWN_EGG);
					break;
				case ENTITY_ELDER_GUARDIAN_HURT:
					SlotPlugin.soundMaterialMap.put(sound, Material.ELDER_GUARDIAN_SPAWN_EGG);
					break;
				case ENTITY_ELDER_GUARDIAN_HURT_LAND:
					SlotPlugin.soundMaterialMap.put(sound, Material.ELDER_GUARDIAN_SPAWN_EGG);
					break;
				case ENTITY_ENDERMAN_AMBIENT:
					SlotPlugin.soundMaterialMap.put(sound, Material.ENDERMAN_SPAWN_EGG);
					break;
				case ENTITY_ENDERMAN_DEATH:
					SlotPlugin.soundMaterialMap.put(sound, Material.ENDERMAN_SPAWN_EGG);
					break;
				case ENTITY_ENDERMAN_HURT:
					SlotPlugin.soundMaterialMap.put(sound, Material.ENDERMAN_SPAWN_EGG);
					break;
				case ENTITY_ENDERMAN_SCREAM:
					SlotPlugin.soundMaterialMap.put(sound, Material.ENDERMAN_SPAWN_EGG);
					break;
				case ENTITY_ENDERMAN_STARE:
					SlotPlugin.soundMaterialMap.put(sound, Material.ENDERMAN_SPAWN_EGG);
					break;
				case ENTITY_ENDERMAN_TELEPORT:
					SlotPlugin.soundMaterialMap.put(sound, Material.ENDERMAN_SPAWN_EGG);
					break;
				case ENTITY_ENDERMITE_AMBIENT:
					SlotPlugin.soundMaterialMap.put(sound, Material.ENDERMITE_SPAWN_EGG);
					break;
				case ENTITY_ENDERMITE_DEATH:
					SlotPlugin.soundMaterialMap.put(sound, Material.ENDERMITE_SPAWN_EGG);
					break;
				case ENTITY_ENDERMITE_HURT:
					SlotPlugin.soundMaterialMap.put(sound, Material.ENDERMITE_SPAWN_EGG);
					break;
				case ENTITY_ENDERMITE_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.ENDERMITE_SPAWN_EGG);
					break;
				case ENTITY_ENDER_DRAGON_AMBIENT:
					SlotPlugin.soundMaterialMap.put(sound, Material.DRAGON_EGG);
					break;
				case ENTITY_ENDER_DRAGON_DEATH:
					SlotPlugin.soundMaterialMap.put(sound, Material.DRAGON_EGG);
					break;
				case ENTITY_ENDER_DRAGON_FLAP:
					SlotPlugin.soundMaterialMap.put(sound, Material.DRAGON_EGG);
					break;
				case ENTITY_ENDER_DRAGON_GROWL:
					SlotPlugin.soundMaterialMap.put(sound, Material.DRAGON_EGG);
					break;
				case ENTITY_ENDER_DRAGON_HURT:
					SlotPlugin.soundMaterialMap.put(sound, Material.DRAGON_EGG);
					break;
				case ENTITY_ENDER_DRAGON_SHOOT:
					SlotPlugin.soundMaterialMap.put(sound, Material.DRAGON_EGG);
					break;
				case ENTITY_ENDER_EYE_DEATH:
					SlotPlugin.soundMaterialMap.put(sound, Material.ENDER_EYE);
					break;
				case ENTITY_ENDER_EYE_LAUNCH:
					SlotPlugin.soundMaterialMap.put(sound, Material.ENDER_EYE);
					break;
				case ENTITY_ENDER_PEARL_THROW:
					SlotPlugin.soundMaterialMap.put(sound, Material.ENDER_PEARL);
					break;
				case ENTITY_EVOKER_AMBIENT:
					SlotPlugin.soundMaterialMap.put(sound, Material.EVOKER_SPAWN_EGG);
					break;
				case ENTITY_EVOKER_CAST_SPELL:
					SlotPlugin.soundMaterialMap.put(sound, Material.EVOKER_SPAWN_EGG);
					break;
				case ENTITY_EVOKER_DEATH:
					SlotPlugin.soundMaterialMap.put(sound, Material.EVOKER_SPAWN_EGG);
					break;
				case ENTITY_EVOKER_FANGS_ATTACK:
					SlotPlugin.soundMaterialMap.put(sound, Material.EVOKER_SPAWN_EGG);
					break;
				case ENTITY_EVOKER_HURT:
					SlotPlugin.soundMaterialMap.put(sound, Material.EVOKER_SPAWN_EGG);
					break;
				case ENTITY_EVOKER_PREPARE_ATTACK:
					SlotPlugin.soundMaterialMap.put(sound, Material.EVOKER_SPAWN_EGG);
					break;
				case ENTITY_EVOKER_PREPARE_SUMMON:
					SlotPlugin.soundMaterialMap.put(sound, Material.EVOKER_SPAWN_EGG);
					break;
				case ENTITY_EVOKER_PREPARE_WOLOLO:
					SlotPlugin.soundMaterialMap.put(sound, Material.EVOKER_SPAWN_EGG);
					break;
				case ENTITY_EXPERIENCE_BOTTLE_THROW:
					SlotPlugin.soundMaterialMap.put(sound, Material.EXPERIENCE_BOTTLE);
					break;
				case ENTITY_EXPERIENCE_ORB_PICKUP:
					SlotPlugin.soundMaterialMap.put(sound, Material.EXPERIENCE_BOTTLE);
					break;
				case ENTITY_FIREWORK_ROCKET_BLAST:
					SlotPlugin.soundMaterialMap.put(sound, Material.FIREWORK_ROCKET);
					break;
				case ENTITY_FIREWORK_ROCKET_BLAST_FAR:
					SlotPlugin.soundMaterialMap.put(sound, Material.FIREWORK_ROCKET);
					break;
				case ENTITY_FIREWORK_ROCKET_LARGE_BLAST:
					SlotPlugin.soundMaterialMap.put(sound, Material.FIREWORK_ROCKET);
					break;
				case ENTITY_FIREWORK_ROCKET_LARGE_BLAST_FAR:
					SlotPlugin.soundMaterialMap.put(sound, Material.FIREWORK_ROCKET);
					break;
				case ENTITY_FIREWORK_ROCKET_LAUNCH:
					SlotPlugin.soundMaterialMap.put(sound, Material.FIREWORK_ROCKET);
					break;
				case ENTITY_FIREWORK_ROCKET_SHOOT:
					SlotPlugin.soundMaterialMap.put(sound, Material.FIREWORK_ROCKET);
					break;
				case ENTITY_FIREWORK_ROCKET_TWINKLE:
					SlotPlugin.soundMaterialMap.put(sound, Material.FIREWORK_ROCKET);
					break;
				case ENTITY_FIREWORK_ROCKET_TWINKLE_FAR:
					SlotPlugin.soundMaterialMap.put(sound, Material.FIREWORK_ROCKET);
					break;
				case ENTITY_FISHING_BOBBER_RETRIEVE:
					SlotPlugin.soundMaterialMap.put(sound, Material.FISHING_ROD);
					break;
				case ENTITY_FISHING_BOBBER_SPLASH:
					SlotPlugin.soundMaterialMap.put(sound, Material.FISHING_ROD);
					break;
				case ENTITY_FISHING_BOBBER_THROW:
					SlotPlugin.soundMaterialMap.put(sound, Material.FISHING_ROD);
					break;
				case ENTITY_FISH_SWIM:
					SlotPlugin.soundMaterialMap.put(sound, Material.SALMON);
					break;
				case ENTITY_GENERIC_BIG_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.PLAYER_HEAD);
					break;
				case ENTITY_GENERIC_BURN:
					SlotPlugin.soundMaterialMap.put(sound, Material.FLINT_AND_STEEL);
					break;
				case ENTITY_GENERIC_DEATH:
					SlotPlugin.soundMaterialMap.put(sound, Material.PLAYER_HEAD);
					break;
				case ENTITY_GENERIC_DRINK:
					SlotPlugin.soundMaterialMap.put(sound, Material.GLASS_BOTTLE);
					break;
				case ENTITY_GENERIC_EAT:
					SlotPlugin.soundMaterialMap.put(sound, Material.COOKED_BEEF);
					break;
				case ENTITY_GENERIC_EXPLODE:
					SlotPlugin.soundMaterialMap.put(sound, Material.TNT);
					break;
				case ENTITY_GENERIC_EXTINGUISH_FIRE:
					SlotPlugin.soundMaterialMap.put(sound, Material.FLINT_AND_STEEL);
					break;
				case ENTITY_GENERIC_HURT:
					SlotPlugin.soundMaterialMap.put(sound, Material.PLAYER_HEAD);
					break;
				case ENTITY_GENERIC_SMALL_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.PLAYER_HEAD);
					break;
				case ENTITY_GENERIC_SPLASH:
					SlotPlugin.soundMaterialMap.put(sound, Material.PLAYER_HEAD);
					break;
				case ENTITY_GENERIC_SWIM:
					SlotPlugin.soundMaterialMap.put(sound, Material.PLAYER_HEAD);
					break;
				case ENTITY_GHAST_AMBIENT:
					SlotPlugin.soundMaterialMap.put(sound, Material.GHAST_SPAWN_EGG);
					break;
				case ENTITY_GHAST_DEATH:
					SlotPlugin.soundMaterialMap.put(sound, Material.GHAST_SPAWN_EGG);
					break;
				case ENTITY_GHAST_HURT:
					SlotPlugin.soundMaterialMap.put(sound, Material.GHAST_SPAWN_EGG);
					break;
				case ENTITY_GHAST_SCREAM:
					SlotPlugin.soundMaterialMap.put(sound, Material.GHAST_SPAWN_EGG);
					break;
				case ENTITY_GHAST_SHOOT:
					SlotPlugin.soundMaterialMap.put(sound, Material.GHAST_SPAWN_EGG);
					break;
				case ENTITY_GHAST_WARN:
					SlotPlugin.soundMaterialMap.put(sound, Material.GHAST_SPAWN_EGG);
					break;
				case ENTITY_GUARDIAN_AMBIENT:
					SlotPlugin.soundMaterialMap.put(sound, Material.GUARDIAN_SPAWN_EGG);
					break;
				case ENTITY_GUARDIAN_AMBIENT_LAND:
					SlotPlugin.soundMaterialMap.put(sound, Material.GUARDIAN_SPAWN_EGG);
					break;
				case ENTITY_GUARDIAN_ATTACK:
					SlotPlugin.soundMaterialMap.put(sound, Material.GUARDIAN_SPAWN_EGG);
					break;
				case ENTITY_GUARDIAN_DEATH:
					SlotPlugin.soundMaterialMap.put(sound, Material.GUARDIAN_SPAWN_EGG);
					break;
				case ENTITY_GUARDIAN_DEATH_LAND:
					SlotPlugin.soundMaterialMap.put(sound, Material.GUARDIAN_SPAWN_EGG);
					break;
				case ENTITY_GUARDIAN_FLOP:
					SlotPlugin.soundMaterialMap.put(sound, Material.GUARDIAN_SPAWN_EGG);
					break;
				case ENTITY_GUARDIAN_HURT:
					SlotPlugin.soundMaterialMap.put(sound, Material.GUARDIAN_SPAWN_EGG);
					break;
				case ENTITY_GUARDIAN_HURT_LAND:
					SlotPlugin.soundMaterialMap.put(sound, Material.GUARDIAN_SPAWN_EGG);
					break;
				case ENTITY_HORSE_AMBIENT:
					SlotPlugin.soundMaterialMap.put(sound, Material.HORSE_SPAWN_EGG);
					break;
				case ENTITY_HORSE_ANGRY:
					SlotPlugin.soundMaterialMap.put(sound, Material.HORSE_SPAWN_EGG);
					break;
				case ENTITY_HORSE_ARMOR:
					SlotPlugin.soundMaterialMap.put(sound, Material.HORSE_SPAWN_EGG);
					break;
				case ENTITY_HORSE_BREATHE:
					SlotPlugin.soundMaterialMap.put(sound, Material.HORSE_SPAWN_EGG);
					break;
				case ENTITY_HORSE_DEATH:
					SlotPlugin.soundMaterialMap.put(sound, Material.HORSE_SPAWN_EGG);
					break;
				case ENTITY_HORSE_EAT:
					SlotPlugin.soundMaterialMap.put(sound, Material.HORSE_SPAWN_EGG);
					break;
				case ENTITY_HORSE_GALLOP:
					SlotPlugin.soundMaterialMap.put(sound, Material.HORSE_SPAWN_EGG);
					break;
				case ENTITY_HORSE_HURT:
					SlotPlugin.soundMaterialMap.put(sound, Material.HORSE_SPAWN_EGG);
					break;
				case ENTITY_HORSE_JUMP:
					SlotPlugin.soundMaterialMap.put(sound, Material.HORSE_SPAWN_EGG);
					break;
				case ENTITY_HORSE_LAND:
					SlotPlugin.soundMaterialMap.put(sound, Material.HORSE_SPAWN_EGG);
					break;
				case ENTITY_HORSE_SADDLE:
					SlotPlugin.soundMaterialMap.put(sound, Material.HORSE_SPAWN_EGG);
					break;
				case ENTITY_HORSE_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.HORSE_SPAWN_EGG);
					break;
				case ENTITY_HORSE_STEP_WOOD:
					SlotPlugin.soundMaterialMap.put(sound, Material.HORSE_SPAWN_EGG);
					break;
				case ENTITY_HOSTILE_BIG_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.PLAYER_HEAD);
					break;
				case ENTITY_HOSTILE_DEATH:
					SlotPlugin.soundMaterialMap.put(sound, Material.PLAYER_HEAD);
					break;
				case ENTITY_HOSTILE_HURT:
					SlotPlugin.soundMaterialMap.put(sound, Material.PLAYER_HEAD);
					break;
				case ENTITY_HOSTILE_SMALL_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.PLAYER_HEAD);
					break;
				case ENTITY_HOSTILE_SPLASH:
					SlotPlugin.soundMaterialMap.put(sound, Material.PLAYER_HEAD);
					break;
				case ENTITY_HOSTILE_SWIM:
					SlotPlugin.soundMaterialMap.put(sound, Material.PLAYER_HEAD);
					break;
				case ENTITY_HUSK_AMBIENT:
					SlotPlugin.soundMaterialMap.put(sound, Material.HUSK_SPAWN_EGG);
					break;
				case ENTITY_HUSK_CONVERTED_TO_ZOMBIE:
					SlotPlugin.soundMaterialMap.put(sound, Material.HUSK_SPAWN_EGG);
					break;
				case ENTITY_HUSK_DEATH:
					SlotPlugin.soundMaterialMap.put(sound, Material.HUSK_SPAWN_EGG);
					break;
				case ENTITY_HUSK_HURT:
					SlotPlugin.soundMaterialMap.put(sound, Material.HUSK_SPAWN_EGG);
					break;
				case ENTITY_HUSK_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.HUSK_SPAWN_EGG);
					break;
				case ENTITY_ILLUSIONER_AMBIENT:
					SlotPlugin.soundMaterialMap.put(sound, Material.EVOKER_SPAWN_EGG);
					break;
				case ENTITY_ILLUSIONER_CAST_SPELL:
					SlotPlugin.soundMaterialMap.put(sound, Material.EVOKER_SPAWN_EGG);
					break;
				case ENTITY_ILLUSIONER_DEATH:
					SlotPlugin.soundMaterialMap.put(sound, Material.EVOKER_SPAWN_EGG);
					break;
				case ENTITY_ILLUSIONER_HURT:
					SlotPlugin.soundMaterialMap.put(sound, Material.EVOKER_SPAWN_EGG);
					break;
				case ENTITY_ILLUSIONER_MIRROR_MOVE:
					SlotPlugin.soundMaterialMap.put(sound, Material.EVOKER_SPAWN_EGG);
					break;
				case ENTITY_ILLUSIONER_PREPARE_BLINDNESS:
					SlotPlugin.soundMaterialMap.put(sound, Material.EVOKER_SPAWN_EGG);
					break;
				case ENTITY_ILLUSIONER_PREPARE_MIRROR:
					SlotPlugin.soundMaterialMap.put(sound, Material.EVOKER_SPAWN_EGG);
					break;
				case ENTITY_IRON_GOLEM_ATTACK:
					SlotPlugin.soundMaterialMap.put(sound, Material.CARVED_PUMPKIN);
					break;
				case ENTITY_IRON_GOLEM_DEATH:
					SlotPlugin.soundMaterialMap.put(sound, Material.CARVED_PUMPKIN);
					break;
				case ENTITY_IRON_GOLEM_HURT:
					SlotPlugin.soundMaterialMap.put(sound, Material.CARVED_PUMPKIN);
					break;
				case ENTITY_IRON_GOLEM_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.CARVED_PUMPKIN);
					break;
				case ENTITY_ITEM_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.PLAYER_HEAD);
					break;
				case ENTITY_ITEM_FRAME_ADD_ITEM:
					SlotPlugin.soundMaterialMap.put(sound, Material.ITEM_FRAME);
					break;
				case ENTITY_ITEM_FRAME_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.ITEM_FRAME);
					break;
				case ENTITY_ITEM_FRAME_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.ITEM_FRAME);
					break;
				case ENTITY_ITEM_FRAME_REMOVE_ITEM:
					SlotPlugin.soundMaterialMap.put(sound, Material.ITEM_FRAME);
					break;
				case ENTITY_ITEM_FRAME_ROTATE_ITEM:
					SlotPlugin.soundMaterialMap.put(sound, Material.ITEM_FRAME);
					break;
				case ENTITY_ITEM_PICKUP:
					SlotPlugin.soundMaterialMap.put(sound, Material.PLAYER_HEAD);
					break;
				case ENTITY_LEASH_KNOT_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.LEAD);
					break;
				case ENTITY_LEASH_KNOT_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.LEAD);
					break;
				case ENTITY_LIGHTNING_BOLT_IMPACT:
					SlotPlugin.soundMaterialMap.put(sound, Material.TRIDENT);
					break;
				case ENTITY_LIGHTNING_BOLT_THUNDER:
					SlotPlugin.soundMaterialMap.put(sound, Material.TRIDENT);
					break;
				case ENTITY_LINGERING_POTION_THROW:
					SlotPlugin.soundMaterialMap.put(sound, Material.LINGERING_POTION);
					break;
				case ENTITY_LLAMA_AMBIENT:
					SlotPlugin.soundMaterialMap.put(sound, Material.LLAMA_SPAWN_EGG);
					break;
				case ENTITY_LLAMA_ANGRY:
					SlotPlugin.soundMaterialMap.put(sound, Material.LLAMA_SPAWN_EGG);
					break;
				case ENTITY_LLAMA_CHEST:
					SlotPlugin.soundMaterialMap.put(sound, Material.LLAMA_SPAWN_EGG);
					break;
				case ENTITY_LLAMA_DEATH:
					SlotPlugin.soundMaterialMap.put(sound, Material.LLAMA_SPAWN_EGG);
					break;
				case ENTITY_LLAMA_EAT:
					SlotPlugin.soundMaterialMap.put(sound, Material.LLAMA_SPAWN_EGG);
					break;
				case ENTITY_LLAMA_HURT:
					SlotPlugin.soundMaterialMap.put(sound, Material.LLAMA_SPAWN_EGG);
					break;
				case ENTITY_LLAMA_SPIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.LLAMA_SPAWN_EGG);
					break;
				case ENTITY_LLAMA_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.LLAMA_SPAWN_EGG);
					break;
				case ENTITY_LLAMA_SWAG:
					SlotPlugin.soundMaterialMap.put(sound, Material.LLAMA_SPAWN_EGG);
					break;
				case ENTITY_MAGMA_CUBE_DEATH:
					SlotPlugin.soundMaterialMap.put(sound, Material.MAGMA_CUBE_SPAWN_EGG);
					break;
				case ENTITY_MAGMA_CUBE_DEATH_SMALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.MAGMA_CUBE_SPAWN_EGG);
					break;
				case ENTITY_MAGMA_CUBE_HURT:
					SlotPlugin.soundMaterialMap.put(sound, Material.MAGMA_CUBE_SPAWN_EGG);
					break;
				case ENTITY_MAGMA_CUBE_HURT_SMALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.MAGMA_CUBE_SPAWN_EGG);
					break;
				case ENTITY_MAGMA_CUBE_JUMP:
					SlotPlugin.soundMaterialMap.put(sound, Material.MAGMA_CUBE_SPAWN_EGG);
					break;
				case ENTITY_MAGMA_CUBE_SQUISH:
					SlotPlugin.soundMaterialMap.put(sound, Material.MAGMA_CUBE_SPAWN_EGG);
					break;
				case ENTITY_MAGMA_CUBE_SQUISH_SMALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.MAGMA_CUBE_SPAWN_EGG);
					break;
				case ENTITY_MINECART_INSIDE:
					SlotPlugin.soundMaterialMap.put(sound, Material.MINECART);
					break;
				case ENTITY_MINECART_RIDING:
					SlotPlugin.soundMaterialMap.put(sound, Material.MINECART);
					break;
				case ENTITY_MOOSHROOM_SHEAR:
					SlotPlugin.soundMaterialMap.put(sound, Material.MOOSHROOM_SPAWN_EGG);
					break;
				case ENTITY_MULE_AMBIENT:
					SlotPlugin.soundMaterialMap.put(sound, Material.MULE_SPAWN_EGG);
					break;
				case ENTITY_MULE_CHEST:
					SlotPlugin.soundMaterialMap.put(sound, Material.MULE_SPAWN_EGG);
					break;
				case ENTITY_MULE_DEATH:
					SlotPlugin.soundMaterialMap.put(sound, Material.MULE_SPAWN_EGG);
					break;
				case ENTITY_MULE_HURT:
					SlotPlugin.soundMaterialMap.put(sound, Material.MULE_SPAWN_EGG);
					break;
				case ENTITY_PAINTING_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.PAINTING);
					break;
				case ENTITY_PAINTING_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.PAINTING);
					break;
				case ENTITY_PARROT_AMBIENT:
					SlotPlugin.soundMaterialMap.put(sound, Material.PARROT_SPAWN_EGG);
					break;
				case ENTITY_PARROT_DEATH:
					SlotPlugin.soundMaterialMap.put(sound, Material.PARROT_SPAWN_EGG);
					break;
				case ENTITY_PARROT_EAT:
					SlotPlugin.soundMaterialMap.put(sound, Material.PARROT_SPAWN_EGG);
					break;
				case ENTITY_PARROT_FLY:
					SlotPlugin.soundMaterialMap.put(sound, Material.PARROT_SPAWN_EGG);
					break;
				case ENTITY_PARROT_HURT:
					SlotPlugin.soundMaterialMap.put(sound, Material.PARROT_SPAWN_EGG);
					break;
				case ENTITY_PARROT_IMITATE_BLAZE:
					SlotPlugin.soundMaterialMap.put(sound, Material.PARROT_SPAWN_EGG);
					break;
				case ENTITY_PARROT_IMITATE_CREEPER:
					SlotPlugin.soundMaterialMap.put(sound, Material.PARROT_SPAWN_EGG);
					break;
				case ENTITY_PARROT_IMITATE_DROWNED:
					SlotPlugin.soundMaterialMap.put(sound, Material.PARROT_SPAWN_EGG);
					break;
				case ENTITY_PARROT_IMITATE_ELDER_GUARDIAN:
					SlotPlugin.soundMaterialMap.put(sound, Material.PARROT_SPAWN_EGG);
					break;
				case ENTITY_PARROT_IMITATE_ENDERMITE:
					SlotPlugin.soundMaterialMap.put(sound, Material.PARROT_SPAWN_EGG);
					break;
				case ENTITY_PARROT_IMITATE_ENDER_DRAGON:
					SlotPlugin.soundMaterialMap.put(sound, Material.PARROT_SPAWN_EGG);
					break;
				case ENTITY_PARROT_IMITATE_EVOKER:
					SlotPlugin.soundMaterialMap.put(sound, Material.PARROT_SPAWN_EGG);
					break;
				case ENTITY_PARROT_IMITATE_GHAST:
					SlotPlugin.soundMaterialMap.put(sound, Material.PARROT_SPAWN_EGG);
					break;
				case ENTITY_PARROT_IMITATE_HUSK:
					SlotPlugin.soundMaterialMap.put(sound, Material.PARROT_SPAWN_EGG);
					break;
				case ENTITY_PARROT_IMITATE_ILLUSIONER:
					SlotPlugin.soundMaterialMap.put(sound, Material.PARROT_SPAWN_EGG);
					break;
				case ENTITY_PARROT_IMITATE_MAGMA_CUBE:
					SlotPlugin.soundMaterialMap.put(sound, Material.PARROT_SPAWN_EGG);
					break;
				case ENTITY_PARROT_IMITATE_PHANTOM:
					SlotPlugin.soundMaterialMap.put(sound, Material.PARROT_SPAWN_EGG);
					break;
				case ENTITY_PARROT_IMITATE_SHULKER:
					SlotPlugin.soundMaterialMap.put(sound, Material.PARROT_SPAWN_EGG);
					break;
				case ENTITY_PARROT_IMITATE_SILVERFISH:
					SlotPlugin.soundMaterialMap.put(sound, Material.PARROT_SPAWN_EGG);
					break;
				case ENTITY_PARROT_IMITATE_SKELETON:
					SlotPlugin.soundMaterialMap.put(sound, Material.PARROT_SPAWN_EGG);
					break;
				case ENTITY_PARROT_IMITATE_SLIME:
					SlotPlugin.soundMaterialMap.put(sound, Material.PARROT_SPAWN_EGG);
					break;
				case ENTITY_PARROT_IMITATE_SPIDER:
					SlotPlugin.soundMaterialMap.put(sound, Material.PARROT_SPAWN_EGG);
					break;
				case ENTITY_PARROT_IMITATE_STRAY:
					SlotPlugin.soundMaterialMap.put(sound, Material.PARROT_SPAWN_EGG);
					break;
				case ENTITY_PARROT_IMITATE_VEX:
					SlotPlugin.soundMaterialMap.put(sound, Material.PARROT_SPAWN_EGG);
					break;
				case ENTITY_PARROT_IMITATE_VINDICATOR:
					SlotPlugin.soundMaterialMap.put(sound, Material.PARROT_SPAWN_EGG);
					break;
				case ENTITY_PARROT_IMITATE_WITCH:
					SlotPlugin.soundMaterialMap.put(sound, Material.PARROT_SPAWN_EGG);
					break;
				case ENTITY_PARROT_IMITATE_WITHER:
					SlotPlugin.soundMaterialMap.put(sound, Material.PARROT_SPAWN_EGG);
					break;
				case ENTITY_PARROT_IMITATE_WITHER_SKELETON:
					SlotPlugin.soundMaterialMap.put(sound, Material.PARROT_SPAWN_EGG);
					break;
				case ENTITY_PARROT_IMITATE_ZOMBIE:
					SlotPlugin.soundMaterialMap.put(sound, Material.PARROT_SPAWN_EGG);
					break;
				case ENTITY_PARROT_IMITATE_ZOMBIE_VILLAGER:
					SlotPlugin.soundMaterialMap.put(sound, Material.PARROT_SPAWN_EGG);
					break;
				case ENTITY_PARROT_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.PARROT_SPAWN_EGG);
					break;
				case ENTITY_PHANTOM_AMBIENT:
					SlotPlugin.soundMaterialMap.put(sound, Material.PHANTOM_SPAWN_EGG);
					break;
				case ENTITY_PHANTOM_BITE:
					SlotPlugin.soundMaterialMap.put(sound, Material.PHANTOM_SPAWN_EGG);
					break;
				case ENTITY_PHANTOM_DEATH:
					SlotPlugin.soundMaterialMap.put(sound, Material.PHANTOM_SPAWN_EGG);
					break;
				case ENTITY_PHANTOM_FLAP:
					SlotPlugin.soundMaterialMap.put(sound, Material.PHANTOM_SPAWN_EGG);
					break;
				case ENTITY_PHANTOM_HURT:
					SlotPlugin.soundMaterialMap.put(sound, Material.PHANTOM_SPAWN_EGG);
					break;
				case ENTITY_PHANTOM_SWOOP:
					SlotPlugin.soundMaterialMap.put(sound, Material.PHANTOM_SPAWN_EGG);
					break;
				case ENTITY_PIG_AMBIENT:
					SlotPlugin.soundMaterialMap.put(sound, Material.PIG_SPAWN_EGG);
					break;
				case ENTITY_PIG_DEATH:
					SlotPlugin.soundMaterialMap.put(sound, Material.PIG_SPAWN_EGG);
					break;
				case ENTITY_PIG_HURT:
					SlotPlugin.soundMaterialMap.put(sound, Material.PIG_SPAWN_EGG);
					break;
				case ENTITY_PIG_SADDLE:
					SlotPlugin.soundMaterialMap.put(sound, Material.PIG_SPAWN_EGG);
					break;
				case ENTITY_PIG_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.PIG_SPAWN_EGG);
					break;
				case ENTITY_PLAYER_ATTACK_CRIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.PLAYER_HEAD);
					break;
				case ENTITY_PLAYER_ATTACK_KNOCKBACK:
					SlotPlugin.soundMaterialMap.put(sound, Material.PLAYER_HEAD);
					break;
				case ENTITY_PLAYER_ATTACK_NODAMAGE:
					SlotPlugin.soundMaterialMap.put(sound, Material.PLAYER_HEAD);
					break;
				case ENTITY_PLAYER_ATTACK_STRONG:
					SlotPlugin.soundMaterialMap.put(sound, Material.PLAYER_HEAD);
					break;
				case ENTITY_PLAYER_ATTACK_SWEEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.PLAYER_HEAD);
					break;
				case ENTITY_PLAYER_ATTACK_WEAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.PLAYER_HEAD);
					break;
				case ENTITY_PLAYER_BIG_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.PLAYER_HEAD);
					break;
				case ENTITY_PLAYER_BREATH:
					SlotPlugin.soundMaterialMap.put(sound, Material.PLAYER_HEAD);
					break;
				case ENTITY_PLAYER_BURP:
					SlotPlugin.soundMaterialMap.put(sound, Material.PLAYER_HEAD);
					break;
				case ENTITY_PLAYER_DEATH:
					SlotPlugin.soundMaterialMap.put(sound, Material.PLAYER_HEAD);
					break;
				case ENTITY_PLAYER_HURT:
					SlotPlugin.soundMaterialMap.put(sound, Material.PLAYER_HEAD);
					break;
				case ENTITY_PLAYER_HURT_DROWN:
					SlotPlugin.soundMaterialMap.put(sound, Material.PLAYER_HEAD);
					break;
				case ENTITY_PLAYER_HURT_ON_FIRE:
					SlotPlugin.soundMaterialMap.put(sound, Material.PLAYER_HEAD);
					break;
				case ENTITY_PLAYER_LEVELUP:
					SlotPlugin.soundMaterialMap.put(sound, Material.PLAYER_HEAD);
					break;
				case ENTITY_PLAYER_SMALL_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.PLAYER_HEAD);
					break;
				case ENTITY_PLAYER_SPLASH:
					SlotPlugin.soundMaterialMap.put(sound, Material.PLAYER_HEAD);
					break;
				case ENTITY_PLAYER_SPLASH_HIGH_SPEED:
					SlotPlugin.soundMaterialMap.put(sound, Material.PLAYER_HEAD);
					break;
				case ENTITY_PLAYER_SWIM:
					SlotPlugin.soundMaterialMap.put(sound, Material.PLAYER_HEAD);
					break;
				case ENTITY_POLAR_BEAR_AMBIENT:
					SlotPlugin.soundMaterialMap.put(sound, Material.POLAR_BEAR_SPAWN_EGG);
					break;
				case ENTITY_POLAR_BEAR_AMBIENT_BABY:
					SlotPlugin.soundMaterialMap.put(sound, Material.POLAR_BEAR_SPAWN_EGG);
					break;
				case ENTITY_POLAR_BEAR_DEATH:
					SlotPlugin.soundMaterialMap.put(sound, Material.POLAR_BEAR_SPAWN_EGG);
					break;
				case ENTITY_POLAR_BEAR_HURT:
					SlotPlugin.soundMaterialMap.put(sound, Material.POLAR_BEAR_SPAWN_EGG);
					break;
				case ENTITY_POLAR_BEAR_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.POLAR_BEAR_SPAWN_EGG);
					break;
				case ENTITY_POLAR_BEAR_WARNING:
					SlotPlugin.soundMaterialMap.put(sound, Material.POLAR_BEAR_SPAWN_EGG);
					break;
				case ENTITY_PUFFER_FISH_AMBIENT:
					SlotPlugin.soundMaterialMap.put(sound, Material.PUFFERFISH_BUCKET);
					break;
				case ENTITY_PUFFER_FISH_BLOW_OUT:
					SlotPlugin.soundMaterialMap.put(sound, Material.PUFFERFISH_BUCKET);
					break;
				case ENTITY_PUFFER_FISH_BLOW_UP:
					SlotPlugin.soundMaterialMap.put(sound, Material.PUFFERFISH_BUCKET);
					break;
				case ENTITY_PUFFER_FISH_DEATH:
					SlotPlugin.soundMaterialMap.put(sound, Material.PUFFERFISH_BUCKET);
					break;
				case ENTITY_PUFFER_FISH_FLOP:
					SlotPlugin.soundMaterialMap.put(sound, Material.PUFFERFISH_BUCKET);
					break;
				case ENTITY_PUFFER_FISH_HURT:
					SlotPlugin.soundMaterialMap.put(sound, Material.PUFFERFISH_BUCKET);
					break;
				case ENTITY_PUFFER_FISH_STING:
					SlotPlugin.soundMaterialMap.put(sound, Material.PUFFERFISH_BUCKET);
					break;
				case ENTITY_RABBIT_AMBIENT:
					SlotPlugin.soundMaterialMap.put(sound, Material.RABBIT_SPAWN_EGG);
					break;
				case ENTITY_RABBIT_ATTACK:
					SlotPlugin.soundMaterialMap.put(sound, Material.RABBIT_SPAWN_EGG);
					break;
				case ENTITY_RABBIT_DEATH:
					SlotPlugin.soundMaterialMap.put(sound, Material.RABBIT_SPAWN_EGG);
					break;
				case ENTITY_RABBIT_HURT:
					SlotPlugin.soundMaterialMap.put(sound, Material.RABBIT_SPAWN_EGG);
					break;
				case ENTITY_RABBIT_JUMP:
					SlotPlugin.soundMaterialMap.put(sound, Material.RABBIT_SPAWN_EGG);
					break;
				case ENTITY_SALMON_AMBIENT:
					SlotPlugin.soundMaterialMap.put(sound, Material.SALMON_BUCKET);
					break;
				case ENTITY_SALMON_DEATH:
					SlotPlugin.soundMaterialMap.put(sound, Material.SALMON_BUCKET);
					break;
				case ENTITY_SALMON_FLOP:
					SlotPlugin.soundMaterialMap.put(sound, Material.SALMON_BUCKET);
					break;
				case ENTITY_SALMON_HURT:
					SlotPlugin.soundMaterialMap.put(sound, Material.SALMON_BUCKET);
					break;
				case ENTITY_SHEEP_AMBIENT:
					SlotPlugin.soundMaterialMap.put(sound, Material.SHEEP_SPAWN_EGG);
					break;
				case ENTITY_SHEEP_DEATH:
					SlotPlugin.soundMaterialMap.put(sound, Material.SHEEP_SPAWN_EGG);
					break;
				case ENTITY_SHEEP_HURT:
					SlotPlugin.soundMaterialMap.put(sound, Material.SHEEP_SPAWN_EGG);
					break;
				case ENTITY_SHEEP_SHEAR:
					SlotPlugin.soundMaterialMap.put(sound, Material.SHEEP_SPAWN_EGG);
					break;
				case ENTITY_SHEEP_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.SHEEP_SPAWN_EGG);
					break;
				case ENTITY_SHULKER_AMBIENT:
					SlotPlugin.soundMaterialMap.put(sound, Material.SHULKER_BOX);
					break;
				case ENTITY_SHULKER_BULLET_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.SHULKER_BOX);
					break;
				case ENTITY_SHULKER_BULLET_HURT:
					SlotPlugin.soundMaterialMap.put(sound, Material.SHULKER_BOX);
					break;
				case ENTITY_SHULKER_CLOSE:
					SlotPlugin.soundMaterialMap.put(sound, Material.SHULKER_BOX);
					break;
				case ENTITY_SHULKER_DEATH:
					SlotPlugin.soundMaterialMap.put(sound, Material.SHULKER_BOX);
					break;
				case ENTITY_SHULKER_HURT:
					SlotPlugin.soundMaterialMap.put(sound, Material.SHULKER_BOX);
					break;
				case ENTITY_SHULKER_HURT_CLOSED:
					SlotPlugin.soundMaterialMap.put(sound, Material.SHULKER_BOX);
					break;
				case ENTITY_SHULKER_OPEN:
					SlotPlugin.soundMaterialMap.put(sound, Material.SHULKER_BOX);
					break;
				case ENTITY_SHULKER_SHOOT:
					SlotPlugin.soundMaterialMap.put(sound, Material.SHULKER_BOX);
					break;
				case ENTITY_SHULKER_TELEPORT:
					SlotPlugin.soundMaterialMap.put(sound, Material.SHULKER_BOX);
					break;
				case ENTITY_SILVERFISH_AMBIENT:
					SlotPlugin.soundMaterialMap.put(sound, Material.SILVERFISH_SPAWN_EGG);
					break;
				case ENTITY_SILVERFISH_DEATH:
					SlotPlugin.soundMaterialMap.put(sound, Material.SILVERFISH_SPAWN_EGG);
					break;
				case ENTITY_SILVERFISH_HURT:
					SlotPlugin.soundMaterialMap.put(sound, Material.SILVERFISH_SPAWN_EGG);
					break;
				case ENTITY_SILVERFISH_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.SILVERFISH_SPAWN_EGG);
					break;
				case ENTITY_SKELETON_AMBIENT:
					SlotPlugin.soundMaterialMap.put(sound, Material.SKELETON_SKULL);
					break;
				case ENTITY_SKELETON_DEATH:
					SlotPlugin.soundMaterialMap.put(sound, Material.SKELETON_SKULL);
					break;
				case ENTITY_SKELETON_HORSE_AMBIENT:
					SlotPlugin.soundMaterialMap.put(sound, Material.SKELETON_HORSE_SPAWN_EGG);
					break;
				case ENTITY_SKELETON_HORSE_AMBIENT_WATER:
					SlotPlugin.soundMaterialMap.put(sound, Material.SKELETON_HORSE_SPAWN_EGG);
					break;
				case ENTITY_SKELETON_HORSE_DEATH:
					SlotPlugin.soundMaterialMap.put(sound, Material.SKELETON_HORSE_SPAWN_EGG);
					break;
				case ENTITY_SKELETON_HORSE_GALLOP_WATER:
					SlotPlugin.soundMaterialMap.put(sound, Material.SKELETON_HORSE_SPAWN_EGG);
					break;
				case ENTITY_SKELETON_HORSE_HURT:
					SlotPlugin.soundMaterialMap.put(sound, Material.SKELETON_HORSE_SPAWN_EGG);
					break;
				case ENTITY_SKELETON_HORSE_JUMP_WATER:
					SlotPlugin.soundMaterialMap.put(sound, Material.SKELETON_HORSE_SPAWN_EGG);
					break;
				case ENTITY_SKELETON_HORSE_STEP_WATER:
					SlotPlugin.soundMaterialMap.put(sound, Material.SKELETON_HORSE_SPAWN_EGG);
					break;
				case ENTITY_SKELETON_HORSE_SWIM:
					SlotPlugin.soundMaterialMap.put(sound, Material.SKELETON_HORSE_SPAWN_EGG);
					break;
				case ENTITY_SKELETON_HURT:
					SlotPlugin.soundMaterialMap.put(sound, Material.SKELETON_SKULL);
					break;
				case ENTITY_SKELETON_SHOOT:
					SlotPlugin.soundMaterialMap.put(sound, Material.SKELETON_SKULL);
					break;
				case ENTITY_SKELETON_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.SKELETON_SKULL);
					break;
				case ENTITY_SLIME_ATTACK:
					SlotPlugin.soundMaterialMap.put(sound, Material.SLIME_SPAWN_EGG);
					break;
				case ENTITY_SLIME_DEATH:
					SlotPlugin.soundMaterialMap.put(sound, Material.SLIME_SPAWN_EGG);
					break;
				case ENTITY_SLIME_DEATH_SMALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.SLIME_SPAWN_EGG);
					break;
				case ENTITY_SLIME_HURT:
					SlotPlugin.soundMaterialMap.put(sound, Material.SLIME_SPAWN_EGG);
					break;
				case ENTITY_SLIME_HURT_SMALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.SLIME_SPAWN_EGG);
					break;
				case ENTITY_SLIME_JUMP:
					SlotPlugin.soundMaterialMap.put(sound, Material.SLIME_SPAWN_EGG);
					break;
				case ENTITY_SLIME_JUMP_SMALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.SLIME_SPAWN_EGG);
					break;
				case ENTITY_SLIME_SQUISH:
					SlotPlugin.soundMaterialMap.put(sound, Material.SLIME_SPAWN_EGG);
					break;
				case ENTITY_SLIME_SQUISH_SMALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.SLIME_SPAWN_EGG);
					break;
				case ENTITY_SNOWBALL_THROW:
					SlotPlugin.soundMaterialMap.put(sound, Material.SNOWBALL);
					break;
				case ENTITY_SNOW_GOLEM_AMBIENT:
					SlotPlugin.soundMaterialMap.put(sound, Material.JACK_O_LANTERN);
					break;
				case ENTITY_SNOW_GOLEM_DEATH:
					SlotPlugin.soundMaterialMap.put(sound, Material.JACK_O_LANTERN);
					break;
				case ENTITY_SNOW_GOLEM_HURT:
					SlotPlugin.soundMaterialMap.put(sound, Material.JACK_O_LANTERN);
					break;
				case ENTITY_SNOW_GOLEM_SHOOT:
					SlotPlugin.soundMaterialMap.put(sound, Material.JACK_O_LANTERN);
					break;
				case ENTITY_SPIDER_AMBIENT:
					SlotPlugin.soundMaterialMap.put(sound, Material.SPIDER_SPAWN_EGG);
					break;
				case ENTITY_SPIDER_DEATH:
					SlotPlugin.soundMaterialMap.put(sound, Material.SPIDER_SPAWN_EGG);
					break;
				case ENTITY_SPIDER_HURT:
					SlotPlugin.soundMaterialMap.put(sound, Material.SPIDER_SPAWN_EGG);
					break;
				case ENTITY_SPIDER_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.SPIDER_SPAWN_EGG);
					break;
				case ENTITY_SPLASH_POTION_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.SPLASH_POTION);
					break;
				case ENTITY_SPLASH_POTION_THROW:
					SlotPlugin.soundMaterialMap.put(sound, Material.SPLASH_POTION);
					break;
				case ENTITY_SQUID_AMBIENT:
					SlotPlugin.soundMaterialMap.put(sound, Material.SQUID_SPAWN_EGG);
					break;
				case ENTITY_SQUID_DEATH:
					SlotPlugin.soundMaterialMap.put(sound, Material.SQUID_SPAWN_EGG);
					break;
				case ENTITY_SQUID_HURT:
					SlotPlugin.soundMaterialMap.put(sound, Material.SQUID_SPAWN_EGG);
					break;
				case ENTITY_SQUID_SQUIRT:
					SlotPlugin.soundMaterialMap.put(sound, Material.SQUID_SPAWN_EGG);
					break;
				case ENTITY_STRAY_AMBIENT:
					SlotPlugin.soundMaterialMap.put(sound, Material.STRAY_SPAWN_EGG);
					break;
				case ENTITY_STRAY_DEATH:
					SlotPlugin.soundMaterialMap.put(sound, Material.STRAY_SPAWN_EGG);
					break;
				case ENTITY_STRAY_HURT:
					SlotPlugin.soundMaterialMap.put(sound, Material.STRAY_SPAWN_EGG);
					break;
				case ENTITY_STRAY_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.STRAY_SPAWN_EGG);
					break;
				case ENTITY_TNT_PRIMED:
					SlotPlugin.soundMaterialMap.put(sound, Material.TNT);
					break;
				case ENTITY_TROPICAL_FISH_AMBIENT:
					SlotPlugin.soundMaterialMap.put(sound, Material.TROPICAL_FISH_BUCKET);
					break;
				case ENTITY_TROPICAL_FISH_DEATH:
					SlotPlugin.soundMaterialMap.put(sound, Material.TROPICAL_FISH_BUCKET);
					break;
				case ENTITY_TROPICAL_FISH_FLOP:
					SlotPlugin.soundMaterialMap.put(sound, Material.TROPICAL_FISH_BUCKET);
					break;
				case ENTITY_TROPICAL_FISH_HURT:
					SlotPlugin.soundMaterialMap.put(sound, Material.TROPICAL_FISH_BUCKET);
					break;
				case ENTITY_TURTLE_AMBIENT_LAND:
					SlotPlugin.soundMaterialMap.put(sound, Material.TURTLE_SPAWN_EGG);
					break;
				case ENTITY_TURTLE_DEATH:
					SlotPlugin.soundMaterialMap.put(sound, Material.TURTLE_SPAWN_EGG);
					break;
				case ENTITY_TURTLE_DEATH_BABY:
					SlotPlugin.soundMaterialMap.put(sound, Material.TURTLE_SPAWN_EGG);
					break;
				case ENTITY_TURTLE_EGG_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.TURTLE_EGG);
					break;
				case ENTITY_TURTLE_EGG_CRACK:
					SlotPlugin.soundMaterialMap.put(sound, Material.TURTLE_EGG);
					break;
				case ENTITY_TURTLE_EGG_HATCH:
					SlotPlugin.soundMaterialMap.put(sound, Material.TURTLE_EGG);
					break;
				case ENTITY_TURTLE_HURT:
					SlotPlugin.soundMaterialMap.put(sound, Material.TURTLE_SPAWN_EGG);
					break;
				case ENTITY_TURTLE_HURT_BABY:
					SlotPlugin.soundMaterialMap.put(sound, Material.TURTLE_SPAWN_EGG);
					break;
				case ENTITY_TURTLE_LAY_EGG:
					SlotPlugin.soundMaterialMap.put(sound, Material.TURTLE_SPAWN_EGG);
					break;
				case ENTITY_TURTLE_SHAMBLE:
					SlotPlugin.soundMaterialMap.put(sound, Material.TURTLE_SPAWN_EGG);
					break;
				case ENTITY_TURTLE_SHAMBLE_BABY:
					SlotPlugin.soundMaterialMap.put(sound, Material.TURTLE_SPAWN_EGG);
					break;
				case ENTITY_TURTLE_SWIM:
					SlotPlugin.soundMaterialMap.put(sound, Material.TURTLE_SPAWN_EGG);
					break;
				case ENTITY_VEX_AMBIENT:
					SlotPlugin.soundMaterialMap.put(sound, Material.VEX_SPAWN_EGG);
					break;
				case ENTITY_VEX_CHARGE:
					SlotPlugin.soundMaterialMap.put(sound, Material.VEX_SPAWN_EGG);
					break;
				case ENTITY_VEX_DEATH:
					SlotPlugin.soundMaterialMap.put(sound, Material.VEX_SPAWN_EGG);
					break;
				case ENTITY_VEX_HURT:
					SlotPlugin.soundMaterialMap.put(sound, Material.VEX_SPAWN_EGG);
					break;
				case ENTITY_VILLAGER_AMBIENT:
					SlotPlugin.soundMaterialMap.put(sound, Material.VILLAGER_SPAWN_EGG);
					break;
				case ENTITY_VILLAGER_DEATH:
					SlotPlugin.soundMaterialMap.put(sound, Material.VILLAGER_SPAWN_EGG);
					break;
				case ENTITY_VILLAGER_HURT:
					SlotPlugin.soundMaterialMap.put(sound, Material.VILLAGER_SPAWN_EGG);
					break;
				case ENTITY_VILLAGER_NO:
					SlotPlugin.soundMaterialMap.put(sound, Material.VILLAGER_SPAWN_EGG);
					break;
				case ENTITY_VILLAGER_TRADE:
					SlotPlugin.soundMaterialMap.put(sound, Material.VILLAGER_SPAWN_EGG);
					break;
				case ENTITY_VILLAGER_YES:
					SlotPlugin.soundMaterialMap.put(sound, Material.VILLAGER_SPAWN_EGG);
					break;
				case ENTITY_VINDICATOR_AMBIENT:
					SlotPlugin.soundMaterialMap.put(sound, Material.VINDICATOR_SPAWN_EGG);
					break;
				case ENTITY_VINDICATOR_DEATH:
					SlotPlugin.soundMaterialMap.put(sound, Material.VINDICATOR_SPAWN_EGG);
					break;
				case ENTITY_VINDICATOR_HURT:
					SlotPlugin.soundMaterialMap.put(sound, Material.VINDICATOR_SPAWN_EGG);
					break;
				case ENTITY_WITCH_AMBIENT:
					SlotPlugin.soundMaterialMap.put(sound, Material.WITCH_SPAWN_EGG);
					break;
				case ENTITY_WITCH_DEATH:
					SlotPlugin.soundMaterialMap.put(sound, Material.WITCH_SPAWN_EGG);
					break;
				case ENTITY_WITCH_DRINK:
					SlotPlugin.soundMaterialMap.put(sound, Material.WITCH_SPAWN_EGG);
					break;
				case ENTITY_WITCH_HURT:
					SlotPlugin.soundMaterialMap.put(sound, Material.WITCH_SPAWN_EGG);
					break;
				case ENTITY_WITCH_THROW:
					SlotPlugin.soundMaterialMap.put(sound, Material.WITCH_SPAWN_EGG);
					break;
				case ENTITY_WITHER_AMBIENT:
					SlotPlugin.soundMaterialMap.put(sound, Material.WITHER_SKELETON_SKULL);
					break;
				case ENTITY_WITHER_BREAK_BLOCK:
					SlotPlugin.soundMaterialMap.put(sound, Material.WITHER_SKELETON_SKULL);
					break;
				case ENTITY_WITHER_DEATH:
					SlotPlugin.soundMaterialMap.put(sound, Material.WITHER_SKELETON_SKULL);
					break;
				case ENTITY_WITHER_HURT:
					SlotPlugin.soundMaterialMap.put(sound, Material.WITHER_SKELETON_SKULL);
					break;
				case ENTITY_WITHER_SHOOT:
					SlotPlugin.soundMaterialMap.put(sound, Material.WITHER_SKELETON_SKULL);
					break;
				case ENTITY_WITHER_SKELETON_AMBIENT:
					SlotPlugin.soundMaterialMap.put(sound, Material.WITHER_SKELETON_SPAWN_EGG);
					break;
				case ENTITY_WITHER_SKELETON_DEATH:
					SlotPlugin.soundMaterialMap.put(sound, Material.WITHER_SKELETON_SPAWN_EGG);
					break;
				case ENTITY_WITHER_SKELETON_HURT:
					SlotPlugin.soundMaterialMap.put(sound, Material.WITHER_SKELETON_SPAWN_EGG);
					break;
				case ENTITY_WITHER_SKELETON_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.WITHER_SKELETON_SPAWN_EGG);
					break;
				case ENTITY_WITHER_SPAWN:
					SlotPlugin.soundMaterialMap.put(sound, Material.WITHER_SKELETON_SKULL);
					break;
				case ENTITY_WOLF_AMBIENT:
					SlotPlugin.soundMaterialMap.put(sound, Material.WOLF_SPAWN_EGG);
					break;
				case ENTITY_WOLF_DEATH:
					SlotPlugin.soundMaterialMap.put(sound, Material.WOLF_SPAWN_EGG);
					break;
				case ENTITY_WOLF_GROWL:
					SlotPlugin.soundMaterialMap.put(sound, Material.WOLF_SPAWN_EGG);
					break;
				case ENTITY_WOLF_HOWL:
					SlotPlugin.soundMaterialMap.put(sound, Material.WOLF_SPAWN_EGG);
					break;
				case ENTITY_WOLF_HURT:
					SlotPlugin.soundMaterialMap.put(sound, Material.WOLF_SPAWN_EGG);
					break;
				case ENTITY_WOLF_PANT:
					SlotPlugin.soundMaterialMap.put(sound, Material.WOLF_SPAWN_EGG);
					break;
				case ENTITY_WOLF_SHAKE:
					SlotPlugin.soundMaterialMap.put(sound, Material.WOLF_SPAWN_EGG);
					break;
				case ENTITY_WOLF_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.WOLF_SPAWN_EGG);
					break;
				case ENTITY_WOLF_WHINE:
					SlotPlugin.soundMaterialMap.put(sound, Material.WOLF_SPAWN_EGG);
					break;
				case ENTITY_ZOMBIE_AMBIENT:
					SlotPlugin.soundMaterialMap.put(sound, Material.ZOMBIE_HEAD);
					break;
				case ENTITY_ZOMBIE_ATTACK_IRON_DOOR:
					SlotPlugin.soundMaterialMap.put(sound, Material.ZOMBIE_HEAD);
					break;
				case ENTITY_ZOMBIE_ATTACK_WOODEN_DOOR:
					SlotPlugin.soundMaterialMap.put(sound, Material.ZOMBIE_HEAD);
					break;
				case ENTITY_ZOMBIE_BREAK_WOODEN_DOOR:
					SlotPlugin.soundMaterialMap.put(sound, Material.ZOMBIE_HEAD);
					break;
				case ENTITY_ZOMBIE_CONVERTED_TO_DROWNED:
					SlotPlugin.soundMaterialMap.put(sound, Material.ZOMBIE_HEAD);
					break;
				case ENTITY_ZOMBIE_DEATH:
					SlotPlugin.soundMaterialMap.put(sound, Material.ZOMBIE_HEAD);
					break;
				case ENTITY_ZOMBIE_DESTROY_EGG:
					SlotPlugin.soundMaterialMap.put(sound, Material.ZOMBIE_HEAD);
					break;
				case ENTITY_ZOMBIE_HORSE_AMBIENT:
					SlotPlugin.soundMaterialMap.put(sound, Material.ZOMBIE_HEAD);
					break;
				case ENTITY_ZOMBIE_HORSE_DEATH:
					SlotPlugin.soundMaterialMap.put(sound, Material.ZOMBIE_HEAD);
					break;
				case ENTITY_ZOMBIE_HORSE_HURT:
					SlotPlugin.soundMaterialMap.put(sound, Material.ZOMBIE_HEAD);
					break;
				case ENTITY_ZOMBIE_HURT:
					SlotPlugin.soundMaterialMap.put(sound, Material.ZOMBIE_HEAD);
					break;
				case ENTITY_ZOMBIE_INFECT:
					SlotPlugin.soundMaterialMap.put(sound, Material.ZOMBIE_HEAD);
					break;
				case ENTITY_ZOMBIE_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.ZOMBIE_HEAD);
					break;
				case ENTITY_ZOMBIE_VILLAGER_AMBIENT:
					SlotPlugin.soundMaterialMap.put(sound, Material.ZOMBIE_VILLAGER_SPAWN_EGG);
					break;
				case ENTITY_ZOMBIE_VILLAGER_CONVERTED:
					SlotPlugin.soundMaterialMap.put(sound, Material.ZOMBIE_VILLAGER_SPAWN_EGG);
					break;
				case ENTITY_ZOMBIE_VILLAGER_CURE:
					SlotPlugin.soundMaterialMap.put(sound, Material.ZOMBIE_VILLAGER_SPAWN_EGG);
					break;
				case ENTITY_ZOMBIE_VILLAGER_DEATH:
					SlotPlugin.soundMaterialMap.put(sound, Material.ZOMBIE_VILLAGER_SPAWN_EGG);
					break;
				case ENTITY_ZOMBIE_VILLAGER_HURT:
					SlotPlugin.soundMaterialMap.put(sound, Material.ZOMBIE_VILLAGER_SPAWN_EGG);
					break;
				case ENTITY_ZOMBIE_VILLAGER_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.ZOMBIE_VILLAGER_SPAWN_EGG);
					break;
				case ITEM_ARMOR_EQUIP_CHAIN:
					SlotPlugin.soundMaterialMap.put(sound, Material.CHAINMAIL_CHESTPLATE);
					break;
				case ITEM_ARMOR_EQUIP_DIAMOND:
					SlotPlugin.soundMaterialMap.put(sound, Material.DIAMOND_CHESTPLATE);
					break;
				case ITEM_ARMOR_EQUIP_ELYTRA:
					SlotPlugin.soundMaterialMap.put(sound, Material.ELYTRA);
					break;
				case ITEM_ARMOR_EQUIP_GENERIC:
					SlotPlugin.soundMaterialMap.put(sound, Material.IRON_LEGGINGS);
					break;
				case ITEM_ARMOR_EQUIP_GOLD:
					SlotPlugin.soundMaterialMap.put(sound, Material.GOLDEN_CHESTPLATE);
					break;
				case ITEM_ARMOR_EQUIP_IRON:
					SlotPlugin.soundMaterialMap.put(sound, Material.IRON_CHESTPLATE);
					break;
				case ITEM_ARMOR_EQUIP_LEATHER:
					SlotPlugin.soundMaterialMap.put(sound, Material.LEATHER_CHESTPLATE);
					break;
				case ITEM_ARMOR_EQUIP_TURTLE:
					SlotPlugin.soundMaterialMap.put(sound, Material.TURTLE_HELMET);
					break;
				case ITEM_AXE_STRIP:
					SlotPlugin.soundMaterialMap.put(sound, Material.DIAMOND_AXE);
					break;
				case ITEM_BOTTLE_EMPTY:
					SlotPlugin.soundMaterialMap.put(sound, Material.GLASS_BOTTLE);
					break;
				case ITEM_BOTTLE_FILL:
					SlotPlugin.soundMaterialMap.put(sound, Material.GLASS_BOTTLE);
					break;
				case ITEM_BOTTLE_FILL_DRAGONBREATH:
					SlotPlugin.soundMaterialMap.put(sound, Material.DRAGON_BREATH);
					break;
				case ITEM_BUCKET_EMPTY:
					SlotPlugin.soundMaterialMap.put(sound, Material.BUCKET);
					break;
				case ITEM_BUCKET_EMPTY_FISH:
					SlotPlugin.soundMaterialMap.put(sound, Material.COD_BUCKET);
					break;
				case ITEM_BUCKET_EMPTY_LAVA:
					SlotPlugin.soundMaterialMap.put(sound, Material.LAVA_BUCKET);
					break;
				case ITEM_BUCKET_FILL:
					SlotPlugin.soundMaterialMap.put(sound, Material.WATER_BUCKET);
					break;
				case ITEM_BUCKET_FILL_FISH:
					SlotPlugin.soundMaterialMap.put(sound, Material.COD_BUCKET);
					break;
				case ITEM_BUCKET_FILL_LAVA:
					SlotPlugin.soundMaterialMap.put(sound, Material.LAVA_BUCKET);
					break;
				case ITEM_CHORUS_FRUIT_TELEPORT:
					SlotPlugin.soundMaterialMap.put(sound, Material.CHORUS_FRUIT);
					break;
				case ITEM_ELYTRA_FLYING:
					SlotPlugin.soundMaterialMap.put(sound, Material.ELYTRA);
					break;
				case ITEM_FIRECHARGE_USE:
					SlotPlugin.soundMaterialMap.put(sound, Material.FIRE_CHARGE);
					break;
				case ITEM_FLINTANDSTEEL_USE:
					SlotPlugin.soundMaterialMap.put(sound, Material.FLINT_AND_STEEL);
					break;
				case ITEM_HOE_TILL:
					SlotPlugin.soundMaterialMap.put(sound, Material.DIAMOND_HOE);
					break;
				case ITEM_SHIELD_BLOCK:
					SlotPlugin.soundMaterialMap.put(sound, Material.SHIELD);
					break;
				case ITEM_SHIELD_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.SHIELD);
					break;
				case ITEM_SHOVEL_FLATTEN:
					SlotPlugin.soundMaterialMap.put(sound, Material.DIAMOND_SHOVEL);
					break;
				case ITEM_TOTEM_USE:
					SlotPlugin.soundMaterialMap.put(sound, Material.TOTEM_OF_UNDYING);
					break;
				case ITEM_TRIDENT_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.TRIDENT);
					break;
				case ITEM_TRIDENT_HIT_GROUND:
					SlotPlugin.soundMaterialMap.put(sound, Material.TRIDENT);
					break;
				case ITEM_TRIDENT_RETURN:
					SlotPlugin.soundMaterialMap.put(sound, Material.TRIDENT);
					break;
				case ITEM_TRIDENT_RIPTIDE_1:
					SlotPlugin.soundMaterialMap.put(sound, Material.TRIDENT);
					break;
				case ITEM_TRIDENT_RIPTIDE_2:
					SlotPlugin.soundMaterialMap.put(sound, Material.TRIDENT);
					break;
				case ITEM_TRIDENT_RIPTIDE_3:
					SlotPlugin.soundMaterialMap.put(sound, Material.TRIDENT);
					break;
				case ITEM_TRIDENT_THROW:
					SlotPlugin.soundMaterialMap.put(sound, Material.TRIDENT);
					break;
				case ITEM_TRIDENT_THUNDER:
					SlotPlugin.soundMaterialMap.put(sound, Material.TRIDENT);
					break;
				case MUSIC_CREATIVE:
					SlotPlugin.soundMaterialMap.put(sound, Material.JUKEBOX);
					break;
				case MUSIC_CREDITS:
					SlotPlugin.soundMaterialMap.put(sound, Material.JUKEBOX);
					break;
				case MUSIC_DISC_11:
					SlotPlugin.soundMaterialMap.put(sound, Material.MUSIC_DISC_11);
					break;
				case MUSIC_DISC_13:
					SlotPlugin.soundMaterialMap.put(sound, Material.MUSIC_DISC_13);
					break;
				case MUSIC_DISC_BLOCKS:
					SlotPlugin.soundMaterialMap.put(sound, Material.MUSIC_DISC_BLOCKS);
					break;
				case MUSIC_DISC_CAT:
					SlotPlugin.soundMaterialMap.put(sound, Material.MUSIC_DISC_CAT);
					break;
				case MUSIC_DISC_CHIRP:
					SlotPlugin.soundMaterialMap.put(sound, Material.MUSIC_DISC_CHIRP);
					break;
				case MUSIC_DISC_FAR:
					SlotPlugin.soundMaterialMap.put(sound, Material.MUSIC_DISC_FAR);
					break;
				case MUSIC_DISC_MALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.MUSIC_DISC_MALL);
					break;
				case MUSIC_DISC_MELLOHI:
					SlotPlugin.soundMaterialMap.put(sound, Material.MUSIC_DISC_MELLOHI);
					break;
				case MUSIC_DISC_STAL:
					SlotPlugin.soundMaterialMap.put(sound, Material.MUSIC_DISC_STAL);
					break;
				case MUSIC_DISC_STRAD:
					SlotPlugin.soundMaterialMap.put(sound, Material.MUSIC_DISC_STRAD);
					break;
				case MUSIC_DISC_WAIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.MUSIC_DISC_WAIT);
					break;
				case MUSIC_DISC_WARD:
					SlotPlugin.soundMaterialMap.put(sound, Material.MUSIC_DISC_WARD);
					break;
				case MUSIC_DRAGON:
					SlotPlugin.soundMaterialMap.put(sound, Material.JUKEBOX);
					break;
				case MUSIC_END:
					SlotPlugin.soundMaterialMap.put(sound, Material.JUKEBOX);
					break;
				case MUSIC_GAME:
					SlotPlugin.soundMaterialMap.put(sound, Material.JUKEBOX);
					break;
				case MUSIC_MENU:
					SlotPlugin.soundMaterialMap.put(sound, Material.JUKEBOX);
					break;
				case MUSIC_UNDER_WATER:
					SlotPlugin.soundMaterialMap.put(sound, Material.JUKEBOX);
					break;
				case UI_BUTTON_CLICK:
					break;
				case UI_TOAST_CHALLENGE_COMPLETE:
					break;
				case UI_TOAST_IN:
					break;
				case UI_TOAST_OUT:
					break;
				case WEATHER_RAIN:
					break;
				case WEATHER_RAIN_ABOVE:
					break;
			default:
				break;
			}
		}
	}
}
