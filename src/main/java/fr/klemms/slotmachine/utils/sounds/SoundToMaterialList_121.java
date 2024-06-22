package fr.klemms.slotmachine.utils.sounds;

import fr.klemms.slotmachine.SlotPlugin;
import org.bukkit.Material;
import org.bukkit.Sound;

import java.util.HashMap;

public class SoundToMaterialList_121 {

	public static void initList() {
		SlotPlugin.soundMaterialMap = new HashMap<Sound, Material>();

		for (Sound sound : Sound.values()) {
			switch(sound) {
				case AMBIENT_BASALT_DELTAS_ADDITIONS:
				case AMBIENT_BASALT_DELTAS_LOOP:
				case AMBIENT_BASALT_DELTAS_MOOD:
					SlotPlugin.soundMaterialMap.put(sound, Material.BASALT);
					break;
				case AMBIENT_CAVE:
					SlotPlugin.soundMaterialMap.put(sound, Material.STONE);
					break;
				case AMBIENT_CRIMSON_FOREST_ADDITIONS:
					SlotPlugin.soundMaterialMap.put(sound, Material.CRIMSON_NYLIUM);
					break;
				case AMBIENT_CRIMSON_FOREST_LOOP:
					SlotPlugin.soundMaterialMap.put(sound, Material.CRIMSON_NYLIUM);
					break;
				case AMBIENT_CRIMSON_FOREST_MOOD:
					SlotPlugin.soundMaterialMap.put(sound, Material.CRIMSON_NYLIUM);
					break;
				case AMBIENT_NETHER_WASTES_ADDITIONS:
					SlotPlugin.soundMaterialMap.put(sound, Material.NETHERRACK);
					break;
				case AMBIENT_NETHER_WASTES_LOOP:
					SlotPlugin.soundMaterialMap.put(sound, Material.NETHERRACK);
					break;
				case AMBIENT_NETHER_WASTES_MOOD:
					SlotPlugin.soundMaterialMap.put(sound, Material.NETHERRACK);
					break;
				case AMBIENT_SOUL_SAND_VALLEY_ADDITIONS:
					SlotPlugin.soundMaterialMap.put(sound, Material.SOUL_SAND);
					break;
				case AMBIENT_SOUL_SAND_VALLEY_LOOP:
					SlotPlugin.soundMaterialMap.put(sound, Material.SOUL_SAND);
					break;
				case AMBIENT_SOUL_SAND_VALLEY_MOOD:
					SlotPlugin.soundMaterialMap.put(sound, Material.SOUL_SAND);
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
				case AMBIENT_WARPED_FOREST_ADDITIONS:
					SlotPlugin.soundMaterialMap.put(sound, Material.WARPED_NYLIUM);
					break;
				case AMBIENT_WARPED_FOREST_LOOP:
					SlotPlugin.soundMaterialMap.put(sound, Material.WARPED_NYLIUM);
					break;
				case AMBIENT_WARPED_FOREST_MOOD:
					SlotPlugin.soundMaterialMap.put(sound, Material.WARPED_NYLIUM);
					break;
				case BLOCK_ANCIENT_DEBRIS_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.ANCIENT_DEBRIS);
					break;
				case BLOCK_ANCIENT_DEBRIS_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.ANCIENT_DEBRIS);
					break;
				case BLOCK_ANCIENT_DEBRIS_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.ANCIENT_DEBRIS);
					break;
				case BLOCK_ANCIENT_DEBRIS_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.ANCIENT_DEBRIS);
					break;
				case BLOCK_ANCIENT_DEBRIS_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.ANCIENT_DEBRIS);
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
				case BLOCK_BAMBOO_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.BAMBOO);
					break;
				case BLOCK_BAMBOO_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.BAMBOO);
					break;
				case BLOCK_BAMBOO_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.BAMBOO);
					break;
				case BLOCK_BAMBOO_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.BAMBOO);
					break;
				case BLOCK_BAMBOO_SAPLING_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.BAMBOO);
					break;
				case BLOCK_BAMBOO_SAPLING_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.BAMBOO);
					break;
				case BLOCK_BAMBOO_SAPLING_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.BAMBOO);
					break;
				case BLOCK_BAMBOO_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.BAMBOO);
					break;
				case BLOCK_BARREL_CLOSE:
					SlotPlugin.soundMaterialMap.put(sound, Material.BARREL);
					break;
				case BLOCK_BARREL_OPEN:
					SlotPlugin.soundMaterialMap.put(sound, Material.BARREL);
					break;
				case BLOCK_BASALT_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.BASALT);
					break;
				case BLOCK_BASALT_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.BASALT);
					break;
				case BLOCK_BASALT_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.BASALT);
					break;
				case BLOCK_BASALT_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.BASALT);
					break;
				case BLOCK_BASALT_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.BASALT);
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
				case BLOCK_BEEHIVE_DRIP:
					SlotPlugin.soundMaterialMap.put(sound, Material.BEEHIVE);
					break;
				case BLOCK_BEEHIVE_ENTER:
					SlotPlugin.soundMaterialMap.put(sound, Material.BEEHIVE);
					break;
				case BLOCK_BEEHIVE_EXIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.BEEHIVE);
					break;
				case BLOCK_BEEHIVE_SHEAR:
					SlotPlugin.soundMaterialMap.put(sound, Material.BEEHIVE);
					break;
				case BLOCK_BEEHIVE_WORK:
					SlotPlugin.soundMaterialMap.put(sound, Material.BEEHIVE);
					break;
				case BLOCK_BELL_RESONATE:
					SlotPlugin.soundMaterialMap.put(sound, Material.BELL);
					break;
				case BLOCK_BELL_USE:
					SlotPlugin.soundMaterialMap.put(sound, Material.BELL);
					break;
				case BLOCK_BLASTFURNACE_FIRE_CRACKLE:
					SlotPlugin.soundMaterialMap.put(sound, Material.BLAST_FURNACE);
					break;
				case BLOCK_BONE_BLOCK_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.BONE_BLOCK);
					break;
				case BLOCK_BONE_BLOCK_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.BONE_BLOCK);
					break;
				case BLOCK_BONE_BLOCK_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.BONE_BLOCK);
					break;
				case BLOCK_BONE_BLOCK_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.BONE_BLOCK);
					break;
				case BLOCK_BONE_BLOCK_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.BONE_BLOCK);
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
				case BLOCK_CAMPFIRE_CRACKLE:
					SlotPlugin.soundMaterialMap.put(sound, Material.CAMPFIRE);
					break;
				case BLOCK_CHAIN_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.CHAIN);
					break;
				case BLOCK_CHAIN_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.CHAIN);
					break;
				case BLOCK_CHAIN_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.CHAIN);
					break;
				case BLOCK_CHAIN_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.CHAIN);
					break;
				case BLOCK_CHAIN_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.CHAIN);
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
                /*case BLOCK_COBWEB_BREAK:
                case BLOCK_COBWEB_FALL:
                case BLOCK_COBWEB_HIT:
                case BLOCK_COBWEB_PLACE:
                case BLOCK_COBWEB_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.COBWEB);
                    break;*/
                case BLOCK_COMPARATOR_CLICK:
					SlotPlugin.soundMaterialMap.put(sound, Material.COMPARATOR);
					break;
				case BLOCK_COMPOSTER_EMPTY:
					SlotPlugin.soundMaterialMap.put(sound, Material.COMPOSTER);
					break;
				case BLOCK_COMPOSTER_FILL:
					SlotPlugin.soundMaterialMap.put(sound, Material.COMPOSTER);
					break;
				case BLOCK_COMPOSTER_FILL_SUCCESS:
					SlotPlugin.soundMaterialMap.put(sound, Material.COMPOSTER);
					break;
				case BLOCK_COMPOSTER_READY:
					SlotPlugin.soundMaterialMap.put(sound, Material.COMPOSTER);
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
				case BLOCK_CROP_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.WHEAT);
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
				case BLOCK_FROGLIGHT_BREAK:
				case BLOCK_FROGLIGHT_FALL:
				case BLOCK_FROGLIGHT_HIT:
				case BLOCK_FROGLIGHT_PLACE:
				case BLOCK_FROGLIGHT_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.OCHRE_FROGLIGHT);
					break;
				case BLOCK_FROGSPAWN_BREAK:
				case BLOCK_FROGSPAWN_FALL:
				case BLOCK_FROGSPAWN_HATCH:
				case BLOCK_FROGSPAWN_HIT:
				case BLOCK_FROGSPAWN_PLACE:
				case BLOCK_FROGSPAWN_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.FROGSPAWN);
					break;
				case BLOCK_FUNGUS_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.CRIMSON_FUNGUS);
					break;
				case BLOCK_FUNGUS_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.CRIMSON_FUNGUS);
					break;
				case BLOCK_FUNGUS_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.CRIMSON_FUNGUS);
					break;
				case BLOCK_FUNGUS_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.CRIMSON_FUNGUS);
					break;
				case BLOCK_FUNGUS_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.CRIMSON_FUNGUS);
					break;
				case BLOCK_FURNACE_FIRE_CRACKLE:
					SlotPlugin.soundMaterialMap.put(sound, Material.FURNACE);
					break;
				case BLOCK_GILDED_BLACKSTONE_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.GILDED_BLACKSTONE);
					break;
				case BLOCK_GILDED_BLACKSTONE_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.GILDED_BLACKSTONE);
					break;
				case BLOCK_GILDED_BLACKSTONE_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.GILDED_BLACKSTONE);
					break;
				case BLOCK_GILDED_BLACKSTONE_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.GILDED_BLACKSTONE);
					break;
				case BLOCK_GILDED_BLACKSTONE_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.GILDED_BLACKSTONE);
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
				case BLOCK_GRINDSTONE_USE:
					SlotPlugin.soundMaterialMap.put(sound, Material.GRINDSTONE);
					break;
                /*case BLOCK_HEAVY_CORE_BREAK:
                case BLOCK_HEAVY_CORE_FALL:
                case BLOCK_HEAVY_CORE_HIT:
                case BLOCK_HEAVY_CORE_PLACE:
                case BLOCK_HEAVY_CORE_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.HEAVY_CORE);
                    break;*/
                case BLOCK_HONEY_BLOCK_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.HONEY_BLOCK);
					break;
				case BLOCK_HONEY_BLOCK_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.HONEY_BLOCK);
					break;
				case BLOCK_HONEY_BLOCK_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.HONEY_BLOCK);
					break;
				case BLOCK_HONEY_BLOCK_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.HONEY_BLOCK);
					break;
				case BLOCK_HONEY_BLOCK_SLIDE:
					SlotPlugin.soundMaterialMap.put(sound, Material.HONEY_BLOCK);
					break;
				case BLOCK_HONEY_BLOCK_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.HONEY_BLOCK);
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
				case BLOCK_LANTERN_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.LANTERN);
					break;
				case BLOCK_LANTERN_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.LANTERN);
					break;
				case BLOCK_LANTERN_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.LANTERN);
					break;
				case BLOCK_LANTERN_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.LANTERN);
					break;
				case BLOCK_LANTERN_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.LANTERN);
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
				case BLOCK_LODESTONE_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.LODESTONE);
					break;
				case BLOCK_LODESTONE_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.LODESTONE);
					break;
				case BLOCK_LODESTONE_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.LODESTONE);
					break;
				case BLOCK_LODESTONE_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.LODESTONE);
					break;
				case BLOCK_LODESTONE_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.LODESTONE);
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
				case BLOCK_NETHERITE_BLOCK_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.NETHERITE_BLOCK);
					break;
				case BLOCK_NETHERITE_BLOCK_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.NETHERITE_BLOCK);
					break;
				case BLOCK_NETHERITE_BLOCK_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.NETHERITE_BLOCK);
					break;
				case BLOCK_NETHERITE_BLOCK_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.NETHERITE_BLOCK);
					break;
				case BLOCK_NETHERITE_BLOCK_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.NETHERITE_BLOCK);
					break;
				case BLOCK_NETHERRACK_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.NETHERRACK);
					break;
				case BLOCK_NETHERRACK_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.NETHERRACK);
					break;
				case BLOCK_NETHERRACK_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.NETHERRACK);
					break;
				case BLOCK_NETHERRACK_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.NETHERRACK);
					break;
				case BLOCK_NETHERRACK_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.NETHERRACK);
					break;
				case BLOCK_NETHER_BRICKS_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.NETHER_BRICK);
					break;
				case BLOCK_NETHER_BRICKS_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.NETHER_BRICK);
					break;
				case BLOCK_NETHER_BRICKS_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.NETHER_BRICK);
					break;
				case BLOCK_NETHER_BRICKS_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.NETHER_BRICK);
					break;
				case BLOCK_NETHER_BRICKS_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.NETHER_BRICK);
					break;
				case BLOCK_NETHER_GOLD_ORE_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.NETHER_GOLD_ORE);
					break;
				case BLOCK_NETHER_GOLD_ORE_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.NETHER_GOLD_ORE);
					break;
				case BLOCK_NETHER_GOLD_ORE_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.NETHER_GOLD_ORE);
					break;
				case BLOCK_NETHER_GOLD_ORE_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.NETHER_GOLD_ORE);
					break;
				case BLOCK_NETHER_GOLD_ORE_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.NETHER_GOLD_ORE);
					break;
				case BLOCK_NETHER_ORE_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.NETHER_QUARTZ_ORE);
					break;
				case BLOCK_NETHER_ORE_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.NETHER_QUARTZ_ORE);
					break;
				case BLOCK_NETHER_ORE_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.NETHER_QUARTZ_ORE);
					break;
				case BLOCK_NETHER_ORE_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.NETHER_QUARTZ_ORE);
					break;
				case BLOCK_NETHER_ORE_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.NETHER_QUARTZ_ORE);
					break;
				case BLOCK_NETHER_SPROUTS_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.NETHER_SPROUTS);
					break;
				case BLOCK_NETHER_SPROUTS_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.NETHER_SPROUTS);
					break;
				case BLOCK_NETHER_SPROUTS_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.NETHER_SPROUTS);
					break;
				case BLOCK_NETHER_SPROUTS_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.NETHER_SPROUTS);
					break;
				case BLOCK_NETHER_SPROUTS_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.NETHER_SPROUTS);
					break;
				case BLOCK_NETHER_WART_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.NETHER_WART);
					break;
				case BLOCK_NOTE_BLOCK_BANJO:
					SlotPlugin.soundMaterialMap.put(sound, Material.NOTE_BLOCK);
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
				case BLOCK_NOTE_BLOCK_BIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.NOTE_BLOCK);
					break;
				case BLOCK_NOTE_BLOCK_CHIME:
					SlotPlugin.soundMaterialMap.put(sound, Material.NOTE_BLOCK);
					break;
				case BLOCK_NOTE_BLOCK_COW_BELL:
					SlotPlugin.soundMaterialMap.put(sound, Material.NOTE_BLOCK);
					break;
				case BLOCK_NOTE_BLOCK_DIDGERIDOO:
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
				case BLOCK_NOTE_BLOCK_IRON_XYLOPHONE:
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
				case BLOCK_NYLIUM_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.CRIMSON_NYLIUM);
					break;
				case BLOCK_NYLIUM_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.CRIMSON_NYLIUM);
					break;
				case BLOCK_NYLIUM_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.CRIMSON_NYLIUM);
					break;
				case BLOCK_NYLIUM_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.CRIMSON_NYLIUM);
					break;
				case BLOCK_NYLIUM_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.CRIMSON_NYLIUM);
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
				case BLOCK_RESPAWN_ANCHOR_AMBIENT:
					SlotPlugin.soundMaterialMap.put(sound, Material.RESPAWN_ANCHOR);
					break;
				case BLOCK_RESPAWN_ANCHOR_CHARGE:
					SlotPlugin.soundMaterialMap.put(sound, Material.RESPAWN_ANCHOR);
					break;
				case BLOCK_RESPAWN_ANCHOR_DEPLETE:
					SlotPlugin.soundMaterialMap.put(sound, Material.RESPAWN_ANCHOR);
					break;
				case BLOCK_RESPAWN_ANCHOR_SET_SPAWN:
					SlotPlugin.soundMaterialMap.put(sound, Material.RESPAWN_ANCHOR);
					break;
				case BLOCK_ROOTS_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.WEEPING_VINES);
					break;
				case BLOCK_ROOTS_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.WEEPING_VINES);
					break;
				case BLOCK_ROOTS_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.WEEPING_VINES);
					break;
				case BLOCK_ROOTS_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.WEEPING_VINES);
					break;
				case BLOCK_ROOTS_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.WEEPING_VINES);
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
				case BLOCK_SCAFFOLDING_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.SCAFFOLDING);
					break;
				case BLOCK_SCAFFOLDING_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.SCAFFOLDING);
					break;
				case BLOCK_SCAFFOLDING_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.SCAFFOLDING);
					break;
				case BLOCK_SCAFFOLDING_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.SCAFFOLDING);
					break;
				case BLOCK_SCAFFOLDING_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.SCAFFOLDING);
					break;
				case BLOCK_SHROOMLIGHT_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.SHROOMLIGHT);
					break;
				case BLOCK_SHROOMLIGHT_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.SHROOMLIGHT);
					break;
				case BLOCK_SHROOMLIGHT_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.SHROOMLIGHT);
					break;
				case BLOCK_SHROOMLIGHT_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.SHROOMLIGHT);
					break;
				case BLOCK_SHROOMLIGHT_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.SHROOMLIGHT);
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
				case BLOCK_SMITHING_TABLE_USE:
					SlotPlugin.soundMaterialMap.put(sound, Material.SMITHING_TABLE);
					break;
				case BLOCK_SMOKER_SMOKE:
					SlotPlugin.soundMaterialMap.put(sound, Material.SMOKER);
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
				case BLOCK_SOUL_SAND_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.SOUL_SAND);
					break;
				case BLOCK_SOUL_SAND_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.SOUL_SAND);
					break;
				case BLOCK_SOUL_SAND_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.SOUL_SAND);
					break;
				case BLOCK_SOUL_SAND_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.SOUL_SAND);
					break;
				case BLOCK_SOUL_SAND_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.SOUL_SAND);
					break;
				case BLOCK_SOUL_SOIL_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.SOUL_SOIL);
					break;
				case BLOCK_SOUL_SOIL_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.SOUL_SOIL);
					break;
				case BLOCK_SOUL_SOIL_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.SOUL_SOIL);
					break;
				case BLOCK_SOUL_SOIL_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.SOUL_SOIL);
					break;
				case BLOCK_SOUL_SOIL_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.SOUL_SOIL);
					break;
				case BLOCK_STEM_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.TWISTING_VINES);
					break;
				case BLOCK_STEM_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.TWISTING_VINES);
					break;
				case BLOCK_STEM_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.TWISTING_VINES);
					break;
				case BLOCK_STEM_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.TWISTING_VINES);
					break;
				case BLOCK_STEM_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.TWISTING_VINES);
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
				case BLOCK_SWEET_BERRY_BUSH_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.SWEET_BERRIES);
					break;
				case BLOCK_SWEET_BERRY_BUSH_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.SWEET_BERRIES);
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
				case BLOCK_VINE_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.TWISTING_VINES);
					break;
				case BLOCK_WART_BLOCK_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.NETHER_WART_BLOCK);
					break;
				case BLOCK_WART_BLOCK_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.NETHER_WART_BLOCK);
					break;
				case BLOCK_WART_BLOCK_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.NETHER_WART_BLOCK);
					break;
				case BLOCK_WART_BLOCK_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.NETHER_WART_BLOCK);
					break;
				case BLOCK_WART_BLOCK_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.NETHER_WART_BLOCK);
					break;
				case BLOCK_WATER_AMBIENT:
					SlotPlugin.soundMaterialMap.put(sound, Material.WATER_BUCKET);
					break;
				case BLOCK_WEEPING_VINES_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.WEEPING_VINES);
					break;
				case BLOCK_WEEPING_VINES_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.WEEPING_VINES);
					break;
				case BLOCK_WEEPING_VINES_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.WEEPING_VINES);
					break;
				case BLOCK_WEEPING_VINES_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.WEEPING_VINES);
					break;
				case BLOCK_WEEPING_VINES_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.WEEPING_VINES);
					break;
				case BLOCK_WET_GRASS_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.WARPED_NYLIUM);
					break;
				case BLOCK_WET_GRASS_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.WARPED_NYLIUM);
					break;
				case BLOCK_WET_GRASS_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.WARPED_NYLIUM);
					break;
				case BLOCK_WET_GRASS_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.WARPED_NYLIUM);
					break;
				case BLOCK_WET_GRASS_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.WARPED_NYLIUM);
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
				case ENTITY_BEE_DEATH:
					SlotPlugin.soundMaterialMap.put(sound, Material.BEE_SPAWN_EGG);
					break;
				case ENTITY_BEE_HURT:
					SlotPlugin.soundMaterialMap.put(sound, Material.BEE_SPAWN_EGG);
					break;
				case ENTITY_BEE_LOOP:
					SlotPlugin.soundMaterialMap.put(sound, Material.BEE_SPAWN_EGG);
					break;
				case ENTITY_BEE_LOOP_AGGRESSIVE:
					SlotPlugin.soundMaterialMap.put(sound, Material.BEE_SPAWN_EGG);
					break;
				case ENTITY_BEE_POLLINATE:
					SlotPlugin.soundMaterialMap.put(sound, Material.BEE_SPAWN_EGG);
					break;
				case ENTITY_BEE_STING:
					SlotPlugin.soundMaterialMap.put(sound, Material.BEE_SPAWN_EGG);
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
					SlotPlugin.soundMaterialMap.put(sound, Material.CAT_SPAWN_EGG);
					break;
				case ENTITY_CAT_BEG_FOR_FOOD:
					SlotPlugin.soundMaterialMap.put(sound, Material.CAT_SPAWN_EGG);
					break;
				case ENTITY_CAT_DEATH:
					SlotPlugin.soundMaterialMap.put(sound, Material.CAT_SPAWN_EGG);
					break;
				case ENTITY_CAT_EAT:
					SlotPlugin.soundMaterialMap.put(sound, Material.CAT_SPAWN_EGG);
					break;
				case ENTITY_CAT_HISS:
					SlotPlugin.soundMaterialMap.put(sound, Material.CAT_SPAWN_EGG);
					break;
				case ENTITY_CAT_HURT:
					SlotPlugin.soundMaterialMap.put(sound, Material.CAT_SPAWN_EGG);
					break;
				case ENTITY_CAT_PURR:
					SlotPlugin.soundMaterialMap.put(sound, Material.CAT_SPAWN_EGG);
					break;
				case ENTITY_CAT_PURREOW:
					SlotPlugin.soundMaterialMap.put(sound, Material.CAT_SPAWN_EGG);
					break;
				case ENTITY_CAT_STRAY_AMBIENT:
					SlotPlugin.soundMaterialMap.put(sound, Material.CAT_SPAWN_EGG);
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
				case ENTITY_DONKEY_EAT:
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
				case ENTITY_EVOKER_CELEBRATE:
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
				case ENTITY_FOX_AGGRO:
					SlotPlugin.soundMaterialMap.put(sound, Material.FOX_SPAWN_EGG);
					break;
				case ENTITY_FOX_AMBIENT:
					SlotPlugin.soundMaterialMap.put(sound, Material.FOX_SPAWN_EGG);
					break;
				case ENTITY_FOX_BITE:
					SlotPlugin.soundMaterialMap.put(sound, Material.FOX_SPAWN_EGG);
					break;
				case ENTITY_FOX_DEATH:
					SlotPlugin.soundMaterialMap.put(sound, Material.FOX_SPAWN_EGG);
					break;
				case ENTITY_FOX_EAT:
					SlotPlugin.soundMaterialMap.put(sound, Material.FOX_SPAWN_EGG);
					break;
				case ENTITY_FOX_HURT:
					SlotPlugin.soundMaterialMap.put(sound, Material.FOX_SPAWN_EGG);
					break;
				case ENTITY_FOX_SCREECH:
					SlotPlugin.soundMaterialMap.put(sound, Material.FOX_SPAWN_EGG);
					break;
				case ENTITY_FOX_SLEEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.FOX_SPAWN_EGG);
					break;
				case ENTITY_FOX_SNIFF:
					SlotPlugin.soundMaterialMap.put(sound, Material.FOX_SPAWN_EGG);
					break;
				case ENTITY_FOX_SPIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.FOX_SPAWN_EGG);
					break;
				case ENTITY_FOX_TELEPORT:
					SlotPlugin.soundMaterialMap.put(sound, Material.FOX_SPAWN_EGG);
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
				case ENTITY_HOGLIN_AMBIENT:
					SlotPlugin.soundMaterialMap.put(sound, Material.HOGLIN_SPAWN_EGG);
					break;
				case ENTITY_HOGLIN_ANGRY:
					SlotPlugin.soundMaterialMap.put(sound, Material.HOGLIN_SPAWN_EGG);
					break;
				case ENTITY_HOGLIN_ATTACK:
					SlotPlugin.soundMaterialMap.put(sound, Material.HOGLIN_SPAWN_EGG);
					break;
				case ENTITY_HOGLIN_CONVERTED_TO_ZOMBIFIED:
					SlotPlugin.soundMaterialMap.put(sound, Material.HOGLIN_SPAWN_EGG);
					break;
				case ENTITY_HOGLIN_DEATH:
					SlotPlugin.soundMaterialMap.put(sound, Material.HOGLIN_SPAWN_EGG);
					break;
				case ENTITY_HOGLIN_HURT:
					SlotPlugin.soundMaterialMap.put(sound, Material.HOGLIN_SPAWN_EGG);
					break;
				case ENTITY_HOGLIN_RETREAT:
					SlotPlugin.soundMaterialMap.put(sound, Material.HOGLIN_SPAWN_EGG);
					break;
				case ENTITY_HOGLIN_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.HOGLIN_SPAWN_EGG);
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
				case ENTITY_IRON_GOLEM_DAMAGE:
					SlotPlugin.soundMaterialMap.put(sound, Material.CARVED_PUMPKIN);
					break;
				case ENTITY_IRON_GOLEM_DEATH:
					SlotPlugin.soundMaterialMap.put(sound, Material.CARVED_PUMPKIN);
					break;
				case ENTITY_IRON_GOLEM_HURT:
					SlotPlugin.soundMaterialMap.put(sound, Material.CARVED_PUMPKIN);
					break;
				case ENTITY_IRON_GOLEM_REPAIR:
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
				case ENTITY_MOOSHROOM_CONVERT:
					SlotPlugin.soundMaterialMap.put(sound, Material.MOOSHROOM_SPAWN_EGG);
					break;
				case ENTITY_MOOSHROOM_EAT:
					SlotPlugin.soundMaterialMap.put(sound, Material.MOOSHROOM_SPAWN_EGG);
					break;
				case ENTITY_MOOSHROOM_MILK:
					SlotPlugin.soundMaterialMap.put(sound, Material.MOOSHROOM_SPAWN_EGG);
					break;
				case ENTITY_MOOSHROOM_SHEAR:
					SlotPlugin.soundMaterialMap.put(sound, Material.MOOSHROOM_SPAWN_EGG);
					break;
				case ENTITY_MOOSHROOM_SUSPICIOUS_MILK:
					SlotPlugin.soundMaterialMap.put(sound, Material.MOOSHROOM_SPAWN_EGG);
					break;
				case ENTITY_MULE_AMBIENT:
					SlotPlugin.soundMaterialMap.put(sound, Material.MULE_SPAWN_EGG);
					break;
				case ENTITY_MULE_ANGRY:
					SlotPlugin.soundMaterialMap.put(sound, Material.MULE_SPAWN_EGG);
					break;
				case ENTITY_MULE_CHEST:
					SlotPlugin.soundMaterialMap.put(sound, Material.MULE_SPAWN_EGG);
					break;
				case ENTITY_MULE_DEATH:
					SlotPlugin.soundMaterialMap.put(sound, Material.MULE_SPAWN_EGG);
					break;
				case ENTITY_MULE_EAT:
					SlotPlugin.soundMaterialMap.put(sound, Material.MULE_SPAWN_EGG);
					break;
				case ENTITY_MULE_HURT:
					SlotPlugin.soundMaterialMap.put(sound, Material.MULE_SPAWN_EGG);
					break;
				case ENTITY_OCELOT_AMBIENT:
					SlotPlugin.soundMaterialMap.put(sound, Material.OCELOT_SPAWN_EGG);
					break;
				case ENTITY_OCELOT_DEATH:
					SlotPlugin.soundMaterialMap.put(sound, Material.OCELOT_SPAWN_EGG);
					break;
				case ENTITY_OCELOT_HURT:
					SlotPlugin.soundMaterialMap.put(sound, Material.OCELOT_SPAWN_EGG);
					break;
				case ENTITY_PAINTING_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.PAINTING);
					break;
				case ENTITY_PAINTING_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.PAINTING);
					break;
				case ENTITY_PANDA_AGGRESSIVE_AMBIENT:
					SlotPlugin.soundMaterialMap.put(sound, Material.PANDA_SPAWN_EGG);
					break;
				case ENTITY_PANDA_AMBIENT:
					SlotPlugin.soundMaterialMap.put(sound, Material.PANDA_SPAWN_EGG);
					break;
				case ENTITY_PANDA_BITE:
					SlotPlugin.soundMaterialMap.put(sound, Material.PANDA_SPAWN_EGG);
					break;
				case ENTITY_PANDA_CANT_BREED:
					SlotPlugin.soundMaterialMap.put(sound, Material.PANDA_SPAWN_EGG);
					break;
				case ENTITY_PANDA_DEATH:
					SlotPlugin.soundMaterialMap.put(sound, Material.PANDA_SPAWN_EGG);
					break;
				case ENTITY_PANDA_EAT:
					SlotPlugin.soundMaterialMap.put(sound, Material.PANDA_SPAWN_EGG);
					break;
				case ENTITY_PANDA_HURT:
					SlotPlugin.soundMaterialMap.put(sound, Material.PANDA_SPAWN_EGG);
					break;
				case ENTITY_PANDA_PRE_SNEEZE:
					SlotPlugin.soundMaterialMap.put(sound, Material.PANDA_SPAWN_EGG);
					break;
				case ENTITY_PANDA_SNEEZE:
					SlotPlugin.soundMaterialMap.put(sound, Material.PANDA_SPAWN_EGG);
					break;
				case ENTITY_PANDA_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.PANDA_SPAWN_EGG);
					break;
				case ENTITY_PANDA_WORRIED_AMBIENT:
					SlotPlugin.soundMaterialMap.put(sound, Material.PANDA_SPAWN_EGG);
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
				case ENTITY_PARROT_IMITATE_GUARDIAN:
					SlotPlugin.soundMaterialMap.put(sound, Material.PARROT_SPAWN_EGG);
					break;
				case ENTITY_PARROT_IMITATE_HOGLIN:
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
				case ENTITY_PARROT_IMITATE_PIGLIN:
					SlotPlugin.soundMaterialMap.put(sound, Material.PARROT_SPAWN_EGG);
					break;
				case ENTITY_PARROT_IMITATE_PILLAGER:
					SlotPlugin.soundMaterialMap.put(sound, Material.PARROT_SPAWN_EGG);
					break;
				case ENTITY_PARROT_IMITATE_RAVAGER:
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
				case ENTITY_PARROT_IMITATE_ZOGLIN:
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
				case ENTITY_PIGLIN_ADMIRING_ITEM:
					SlotPlugin.soundMaterialMap.put(sound, Material.PIGLIN_SPAWN_EGG);
					break;
				case ENTITY_PIGLIN_AMBIENT:
					SlotPlugin.soundMaterialMap.put(sound, Material.PIGLIN_SPAWN_EGG);
					break;
				case ENTITY_PIGLIN_ANGRY:
					SlotPlugin.soundMaterialMap.put(sound, Material.PIGLIN_SPAWN_EGG);
					break;
				case ENTITY_PIGLIN_CELEBRATE:
					SlotPlugin.soundMaterialMap.put(sound, Material.PIGLIN_SPAWN_EGG);
					break;
				case ENTITY_PIGLIN_CONVERTED_TO_ZOMBIFIED:
					SlotPlugin.soundMaterialMap.put(sound, Material.PIGLIN_SPAWN_EGG);
					break;
				case ENTITY_PIGLIN_DEATH:
					SlotPlugin.soundMaterialMap.put(sound, Material.PIGLIN_SPAWN_EGG);
					break;
				case ENTITY_PIGLIN_HURT:
					SlotPlugin.soundMaterialMap.put(sound, Material.PIGLIN_SPAWN_EGG);
					break;
				case ENTITY_PIGLIN_JEALOUS:
					SlotPlugin.soundMaterialMap.put(sound, Material.PIGLIN_SPAWN_EGG);
					break;
				case ENTITY_PIGLIN_RETREAT:
					SlotPlugin.soundMaterialMap.put(sound, Material.PIGLIN_SPAWN_EGG);
					break;
				case ENTITY_PIGLIN_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.PIGLIN_SPAWN_EGG);
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
				case ENTITY_PILLAGER_AMBIENT:
					SlotPlugin.soundMaterialMap.put(sound, Material.PILLAGER_SPAWN_EGG);
					break;
				case ENTITY_PILLAGER_CELEBRATE:
					SlotPlugin.soundMaterialMap.put(sound, Material.PILLAGER_SPAWN_EGG);
					break;
				case ENTITY_PILLAGER_DEATH:
					SlotPlugin.soundMaterialMap.put(sound, Material.PILLAGER_SPAWN_EGG);
					break;
				case ENTITY_PILLAGER_HURT:
					SlotPlugin.soundMaterialMap.put(sound, Material.PILLAGER_SPAWN_EGG);
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
				case ENTITY_PLAYER_HURT_SWEET_BERRY_BUSH:
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
				case ENTITY_RAVAGER_AMBIENT:
					SlotPlugin.soundMaterialMap.put(sound, Material.RAVAGER_SPAWN_EGG);
					break;
				case ENTITY_RAVAGER_ATTACK:
					SlotPlugin.soundMaterialMap.put(sound, Material.RAVAGER_SPAWN_EGG);
					break;
				case ENTITY_RAVAGER_CELEBRATE:
					SlotPlugin.soundMaterialMap.put(sound, Material.RAVAGER_SPAWN_EGG);
					break;
				case ENTITY_RAVAGER_DEATH:
					SlotPlugin.soundMaterialMap.put(sound, Material.RAVAGER_SPAWN_EGG);
					break;
				case ENTITY_RAVAGER_HURT:
					SlotPlugin.soundMaterialMap.put(sound, Material.RAVAGER_SPAWN_EGG);
					break;
				case ENTITY_RAVAGER_ROAR:
					SlotPlugin.soundMaterialMap.put(sound, Material.RAVAGER_SPAWN_EGG);
					break;
				case ENTITY_RAVAGER_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.RAVAGER_SPAWN_EGG);
					break;
				case ENTITY_RAVAGER_STUNNED:
					SlotPlugin.soundMaterialMap.put(sound, Material.RAVAGER_SPAWN_EGG);
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
				case ENTITY_SNOW_GOLEM_SHEAR:
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
				case ENTITY_STRIDER_AMBIENT:
					SlotPlugin.soundMaterialMap.put(sound, Material.STRIDER_SPAWN_EGG);
					break;
				case ENTITY_STRIDER_DEATH:
					SlotPlugin.soundMaterialMap.put(sound, Material.STRIDER_SPAWN_EGG);
					break;
				case ENTITY_STRIDER_EAT:
					SlotPlugin.soundMaterialMap.put(sound, Material.STRIDER_SPAWN_EGG);
					break;
				case ENTITY_STRIDER_HAPPY:
					SlotPlugin.soundMaterialMap.put(sound, Material.STRIDER_SPAWN_EGG);
					break;
				case ENTITY_STRIDER_HURT:
					SlotPlugin.soundMaterialMap.put(sound, Material.STRIDER_SPAWN_EGG);
					break;
				case ENTITY_STRIDER_RETREAT:
					SlotPlugin.soundMaterialMap.put(sound, Material.STRIDER_SPAWN_EGG);
					break;
				case ENTITY_STRIDER_SADDLE:
					SlotPlugin.soundMaterialMap.put(sound, Material.STRIDER_SPAWN_EGG);
					break;
				case ENTITY_STRIDER_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.STRIDER_SPAWN_EGG);
					break;
				case ENTITY_STRIDER_STEP_LAVA:
					SlotPlugin.soundMaterialMap.put(sound, Material.STRIDER_SPAWN_EGG);
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
				case ENTITY_VILLAGER_CELEBRATE:
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
				case ENTITY_VILLAGER_WORK_ARMORER:
					SlotPlugin.soundMaterialMap.put(sound, Material.VILLAGER_SPAWN_EGG);
					break;
				case ENTITY_VILLAGER_WORK_BUTCHER:
					SlotPlugin.soundMaterialMap.put(sound, Material.VILLAGER_SPAWN_EGG);
					break;
				case ENTITY_VILLAGER_WORK_CARTOGRAPHER:
					SlotPlugin.soundMaterialMap.put(sound, Material.VILLAGER_SPAWN_EGG);
					break;
				case ENTITY_VILLAGER_WORK_CLERIC:
					SlotPlugin.soundMaterialMap.put(sound, Material.VILLAGER_SPAWN_EGG);
					break;
				case ENTITY_VILLAGER_WORK_FARMER:
					SlotPlugin.soundMaterialMap.put(sound, Material.VILLAGER_SPAWN_EGG);
					break;
				case ENTITY_VILLAGER_WORK_FISHERMAN:
					SlotPlugin.soundMaterialMap.put(sound, Material.VILLAGER_SPAWN_EGG);
					break;
				case ENTITY_VILLAGER_WORK_FLETCHER:
					SlotPlugin.soundMaterialMap.put(sound, Material.VILLAGER_SPAWN_EGG);
					break;
				case ENTITY_VILLAGER_WORK_LEATHERWORKER:
					SlotPlugin.soundMaterialMap.put(sound, Material.VILLAGER_SPAWN_EGG);
					break;
				case ENTITY_VILLAGER_WORK_LIBRARIAN:
					SlotPlugin.soundMaterialMap.put(sound, Material.VILLAGER_SPAWN_EGG);
					break;
				case ENTITY_VILLAGER_WORK_MASON:
					SlotPlugin.soundMaterialMap.put(sound, Material.VILLAGER_SPAWN_EGG);
					break;
				case ENTITY_VILLAGER_WORK_SHEPHERD:
					SlotPlugin.soundMaterialMap.put(sound, Material.VILLAGER_SPAWN_EGG);
					break;
				case ENTITY_VILLAGER_WORK_TOOLSMITH:
					SlotPlugin.soundMaterialMap.put(sound, Material.VILLAGER_SPAWN_EGG);
					break;
				case ENTITY_VILLAGER_WORK_WEAPONSMITH:
					SlotPlugin.soundMaterialMap.put(sound, Material.VILLAGER_SPAWN_EGG);
					break;
				case ENTITY_VILLAGER_YES:
					SlotPlugin.soundMaterialMap.put(sound, Material.VILLAGER_SPAWN_EGG);
					break;
				case ENTITY_VINDICATOR_AMBIENT:
					SlotPlugin.soundMaterialMap.put(sound, Material.VINDICATOR_SPAWN_EGG);
					break;
				case ENTITY_VINDICATOR_CELEBRATE:
					SlotPlugin.soundMaterialMap.put(sound, Material.VINDICATOR_SPAWN_EGG);
					break;
				case ENTITY_VINDICATOR_DEATH:
					SlotPlugin.soundMaterialMap.put(sound, Material.VINDICATOR_SPAWN_EGG);
					break;
				case ENTITY_VINDICATOR_HURT:
					SlotPlugin.soundMaterialMap.put(sound, Material.VINDICATOR_SPAWN_EGG);
					break;
				case ENTITY_WANDERING_TRADER_AMBIENT:
					SlotPlugin.soundMaterialMap.put(sound, Material.WANDERING_TRADER_SPAWN_EGG);
					break;
				case ENTITY_WANDERING_TRADER_DEATH:
					SlotPlugin.soundMaterialMap.put(sound, Material.WANDERING_TRADER_SPAWN_EGG);
					break;
				case ENTITY_WANDERING_TRADER_DISAPPEARED:
					SlotPlugin.soundMaterialMap.put(sound, Material.WANDERING_TRADER_SPAWN_EGG);
					break;
				case ENTITY_WANDERING_TRADER_DRINK_MILK:
					SlotPlugin.soundMaterialMap.put(sound, Material.WANDERING_TRADER_SPAWN_EGG);
					break;
				case ENTITY_WANDERING_TRADER_DRINK_POTION:
					SlotPlugin.soundMaterialMap.put(sound, Material.WANDERING_TRADER_SPAWN_EGG);
					break;
				case ENTITY_WANDERING_TRADER_HURT:
					SlotPlugin.soundMaterialMap.put(sound, Material.WANDERING_TRADER_SPAWN_EGG);
					break;
				case ENTITY_WANDERING_TRADER_NO:
					SlotPlugin.soundMaterialMap.put(sound, Material.WANDERING_TRADER_SPAWN_EGG);
					break;
				case ENTITY_WANDERING_TRADER_REAPPEARED:
					SlotPlugin.soundMaterialMap.put(sound, Material.WANDERING_TRADER_SPAWN_EGG);
					break;
				case ENTITY_WANDERING_TRADER_TRADE:
					SlotPlugin.soundMaterialMap.put(sound, Material.WANDERING_TRADER_SPAWN_EGG);
					break;
				case ENTITY_WANDERING_TRADER_YES:
					SlotPlugin.soundMaterialMap.put(sound, Material.WANDERING_TRADER_SPAWN_EGG);
					break;
				case ENTITY_WITCH_AMBIENT:
					SlotPlugin.soundMaterialMap.put(sound, Material.WITCH_SPAWN_EGG);
					break;
				case ENTITY_WITCH_CELEBRATE:
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
				case ENTITY_ZOGLIN_AMBIENT:
					SlotPlugin.soundMaterialMap.put(sound, Material.ZOGLIN_SPAWN_EGG);
					break;
				case ENTITY_ZOGLIN_ANGRY:
					SlotPlugin.soundMaterialMap.put(sound, Material.ZOGLIN_SPAWN_EGG);
					break;
				case ENTITY_ZOGLIN_ATTACK:
					SlotPlugin.soundMaterialMap.put(sound, Material.ZOGLIN_SPAWN_EGG);
					break;
				case ENTITY_ZOGLIN_DEATH:
					SlotPlugin.soundMaterialMap.put(sound, Material.ZOGLIN_SPAWN_EGG);
					break;
				case ENTITY_ZOGLIN_HURT:
					SlotPlugin.soundMaterialMap.put(sound, Material.ZOGLIN_SPAWN_EGG);
					break;
				case ENTITY_ZOGLIN_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.ZOGLIN_SPAWN_EGG);
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
				case ENTITY_ZOMBIFIED_PIGLIN_AMBIENT:
					SlotPlugin.soundMaterialMap.put(sound, Material.ZOMBIFIED_PIGLIN_SPAWN_EGG);
					break;
				case ENTITY_ZOMBIFIED_PIGLIN_ANGRY:
					SlotPlugin.soundMaterialMap.put(sound, Material.ZOMBIFIED_PIGLIN_SPAWN_EGG);
					break;
				case ENTITY_ZOMBIFIED_PIGLIN_DEATH:
					SlotPlugin.soundMaterialMap.put(sound, Material.ZOMBIFIED_PIGLIN_SPAWN_EGG);
					break;
				case ENTITY_ZOMBIFIED_PIGLIN_HURT:
					SlotPlugin.soundMaterialMap.put(sound, Material.ZOMBIFIED_PIGLIN_SPAWN_EGG);
					break;
				case EVENT_RAID_HORN:
					SlotPlugin.soundMaterialMap.put(sound, Material.BLACK_BANNER);
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
				case ITEM_ARMOR_EQUIP_NETHERITE:
					SlotPlugin.soundMaterialMap.put(sound, Material.NETHERITE_CHESTPLATE);
					break;
				case ITEM_ARMOR_EQUIP_TURTLE:
					SlotPlugin.soundMaterialMap.put(sound, Material.TURTLE_HELMET);
					break;
				case ITEM_AXE_STRIP:
					SlotPlugin.soundMaterialMap.put(sound, Material.DIAMOND_AXE);
					break;
				case ITEM_BOOK_PAGE_TURN:
					SlotPlugin.soundMaterialMap.put(sound, Material.BOOK);
					break;
				case ITEM_BOOK_PUT:
					SlotPlugin.soundMaterialMap.put(sound, Material.BOOK);
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
				case ITEM_CROP_PLANT:
					SlotPlugin.soundMaterialMap.put(sound, Material.WHEAT_SEEDS);
					break;
				case ITEM_CROSSBOW_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.CROSSBOW);
					break;
				case ITEM_CROSSBOW_LOADING_END:
					SlotPlugin.soundMaterialMap.put(sound, Material.CROSSBOW);
					break;
				case ITEM_CROSSBOW_LOADING_MIDDLE:
					SlotPlugin.soundMaterialMap.put(sound, Material.CROSSBOW);
					break;
				case ITEM_CROSSBOW_LOADING_START:
					SlotPlugin.soundMaterialMap.put(sound, Material.CROSSBOW);
					break;
				case ITEM_CROSSBOW_QUICK_CHARGE_1:
					SlotPlugin.soundMaterialMap.put(sound, Material.CROSSBOW);
					break;
				case ITEM_CROSSBOW_QUICK_CHARGE_2:
					SlotPlugin.soundMaterialMap.put(sound, Material.CROSSBOW);
					break;
				case ITEM_CROSSBOW_QUICK_CHARGE_3:
					SlotPlugin.soundMaterialMap.put(sound, Material.CROSSBOW);
					break;
				case ITEM_CROSSBOW_SHOOT:
					SlotPlugin.soundMaterialMap.put(sound, Material.CROSSBOW);
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
				case ITEM_HONEY_BOTTLE_DRINK:
					SlotPlugin.soundMaterialMap.put(sound, Material.HONEY_BOTTLE);
					break;
				case ITEM_LODESTONE_COMPASS_LOCK:
					SlotPlugin.soundMaterialMap.put(sound, Material.COMPASS);
					break;
				case ITEM_NETHER_WART_PLANT:
					SlotPlugin.soundMaterialMap.put(sound, Material.NETHER_WART);
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
				/*case ITEM_SWEET_BERRIES_PICK_FROM_BUSH:
					SlotPlugin.soundMaterialMap.put(sound, Material.SWEET_BERRIES);
					break;*/
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
				case MUSIC_DISC_PIGSTEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.MUSIC_DISC_PIGSTEP);
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
				case MUSIC_NETHER_BASALT_DELTAS:
					SlotPlugin.soundMaterialMap.put(sound, Material.JUKEBOX);
					break;
				case MUSIC_NETHER_CRIMSON_FOREST:
					SlotPlugin.soundMaterialMap.put(sound, Material.JUKEBOX);
					break;
				case MUSIC_NETHER_NETHER_WASTES:
					SlotPlugin.soundMaterialMap.put(sound, Material.JUKEBOX);
					break;
				case MUSIC_NETHER_SOUL_SAND_VALLEY:
					SlotPlugin.soundMaterialMap.put(sound, Material.JUKEBOX);
					break;
				case MUSIC_NETHER_WARPED_FOREST:
					SlotPlugin.soundMaterialMap.put(sound, Material.JUKEBOX);
					break;
				case MUSIC_UNDER_WATER:
					SlotPlugin.soundMaterialMap.put(sound, Material.JUKEBOX);
					break;
				case PARTICLE_SOUL_ESCAPE:
					SlotPlugin.soundMaterialMap.put(sound, Material.SOUL_SAND);
					break;
				case UI_BUTTON_CLICK:
					SlotPlugin.soundMaterialMap.put(sound, Material.NOTE_BLOCK);
					break;
				case UI_CARTOGRAPHY_TABLE_TAKE_RESULT:
					SlotPlugin.soundMaterialMap.put(sound, Material.CARTOGRAPHY_TABLE);
					break;
				case UI_LOOM_SELECT_PATTERN:
					SlotPlugin.soundMaterialMap.put(sound, Material.LOOM);
					break;
				case UI_LOOM_TAKE_RESULT:
					SlotPlugin.soundMaterialMap.put(sound, Material.LOOM);
					break;
				case UI_STONECUTTER_SELECT_RECIPE:
					SlotPlugin.soundMaterialMap.put(sound, Material.STONECUTTER);
					break;
				case UI_STONECUTTER_TAKE_RESULT:
					SlotPlugin.soundMaterialMap.put(sound, Material.STONECUTTER);
					break;
				case UI_TOAST_CHALLENGE_COMPLETE:
				case UI_TOAST_IN:
				case UI_TOAST_OUT:
				case WEATHER_RAIN:
				case WEATHER_RAIN_ABOVE:
					SlotPlugin.soundMaterialMap.put(sound, Material.NOTE_BLOCK);
					break;
				case BLOCK_AMETHYST_BLOCK_BREAK:
				case BLOCK_AMETHYST_BLOCK_CHIME:
				case BLOCK_AMETHYST_BLOCK_FALL:
				case BLOCK_AMETHYST_BLOCK_HIT:
				case BLOCK_AMETHYST_BLOCK_PLACE:
				case BLOCK_AMETHYST_BLOCK_RESONATE:
				case BLOCK_AMETHYST_BLOCK_STEP:
				case BLOCK_AMETHYST_CLUSTER_BREAK:
				case BLOCK_AMETHYST_CLUSTER_FALL:
				case BLOCK_AMETHYST_CLUSTER_HIT:
				case BLOCK_AMETHYST_CLUSTER_PLACE:
				case BLOCK_AMETHYST_CLUSTER_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.AMETHYST_BLOCK);
					break;
				case BLOCK_AZALEA_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.AZALEA);
					break;
				case BLOCK_AZALEA_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.AZALEA);
					break;
				case BLOCK_AZALEA_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.AZALEA);
					break;
				case BLOCK_AZALEA_LEAVES_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.AZALEA_LEAVES);
					break;
				case BLOCK_AZALEA_LEAVES_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.AZALEA_LEAVES);
					break;
				case BLOCK_AZALEA_LEAVES_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.AZALEA_LEAVES);
					break;
				case BLOCK_AZALEA_LEAVES_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.AZALEA_LEAVES);
					break;
				case BLOCK_AZALEA_LEAVES_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.AZALEA_LEAVES);
					break;
				case BLOCK_AZALEA_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.AZALEA);
					break;
				case BLOCK_AZALEA_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.AZALEA);
					break;
				case BLOCK_BIG_DRIPLEAF_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.BIG_DRIPLEAF);
					break;
				case BLOCK_BIG_DRIPLEAF_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.BIG_DRIPLEAF);
					break;
				case BLOCK_BIG_DRIPLEAF_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.BIG_DRIPLEAF);
					break;
				case BLOCK_BIG_DRIPLEAF_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.BIG_DRIPLEAF);
					break;
				case BLOCK_BIG_DRIPLEAF_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.BIG_DRIPLEAF);
					break;
				case BLOCK_BIG_DRIPLEAF_TILT_DOWN:
					SlotPlugin.soundMaterialMap.put(sound, Material.BIG_DRIPLEAF);
					break;
				case BLOCK_BIG_DRIPLEAF_TILT_UP:
					SlotPlugin.soundMaterialMap.put(sound, Material.BIG_DRIPLEAF);
					break;
				case BLOCK_CAKE_ADD_CANDLE:
					SlotPlugin.soundMaterialMap.put(sound, Material.CAKE); // CHANGETO : cande cake
					break;
				case BLOCK_CALCITE_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.CALCITE);
					break;
				case BLOCK_CALCITE_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.CALCITE);
					break;
				case BLOCK_CALCITE_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.CALCITE);
					break;
				case BLOCK_CALCITE_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.CALCITE);
					break;
				case BLOCK_CALCITE_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.CALCITE);
					break;
				case BLOCK_CANDLE_AMBIENT:
					SlotPlugin.soundMaterialMap.put(sound, Material.CANDLE);
					break;
				case BLOCK_CANDLE_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.CANDLE);
					break;
				case BLOCK_CANDLE_EXTINGUISH:
					SlotPlugin.soundMaterialMap.put(sound, Material.CANDLE);
					break;
				case BLOCK_CANDLE_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.CANDLE);
					break;
				case BLOCK_CANDLE_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.CANDLE);
					break;
				case BLOCK_CANDLE_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.CANDLE);
					break;
				case BLOCK_CANDLE_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.CANDLE);
					break;
				case BLOCK_CAVE_VINES_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.VINE); // CHANGETO : CAVE_VINES
					break;
				case BLOCK_CAVE_VINES_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.VINE); // CHANGETO : CAVE_VINES
					break;
				case BLOCK_CAVE_VINES_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.VINE); // CHANGETO : CAVE_VINES
					break;
				case BLOCK_CAVE_VINES_PICK_BERRIES:
					SlotPlugin.soundMaterialMap.put(sound, Material.GLOW_BERRIES);
					break;
				case BLOCK_CAVE_VINES_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.VINE); // CHANGETO : CAVE_VINES
					break;
				case BLOCK_CAVE_VINES_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.VINE); // CHANGETO : CAVE_VINES
					break;
				case BLOCK_COPPER_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.COPPER_INGOT);
					break;
                case BLOCK_COPPER_BULB_BREAK:
                case BLOCK_COPPER_BULB_FALL:
                case BLOCK_COPPER_BULB_HIT:
                case BLOCK_COPPER_BULB_PLACE:
                case BLOCK_COPPER_BULB_STEP:
                case BLOCK_COPPER_BULB_TURN_OFF:
                case BLOCK_COPPER_BULB_TURN_ON:
					SlotPlugin.soundMaterialMap.put(sound, Material.COPPER_BULB);
                    break;
                case BLOCK_COPPER_DOOR_CLOSE:
                case BLOCK_COPPER_DOOR_OPEN:
					SlotPlugin.soundMaterialMap.put(sound, Material.COPPER_DOOR);
                    break;
                case BLOCK_COPPER_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.COPPER_INGOT);
					break;
                case BLOCK_COPPER_GRATE_BREAK:
                case BLOCK_COPPER_GRATE_FALL:
                case BLOCK_COPPER_GRATE_HIT:
                case BLOCK_COPPER_GRATE_PLACE:
                case BLOCK_COPPER_GRATE_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.COPPER_GRATE);
                    break;
                case BLOCK_COPPER_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.COPPER_INGOT);
					break;
				case BLOCK_COPPER_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.COPPER_INGOT);
					break;
				case BLOCK_COPPER_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.COPPER_INGOT);
					break;
				case BLOCK_DEEPSLATE_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.DEEPSLATE);
					break;
				case BLOCK_DEEPSLATE_BRICKS_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.DEEPSLATE_BRICKS);
					break;
				case BLOCK_DEEPSLATE_BRICKS_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.DEEPSLATE_BRICKS);
					break;
				case BLOCK_DEEPSLATE_BRICKS_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.DEEPSLATE_BRICKS);
					break;
				case BLOCK_DEEPSLATE_BRICKS_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.DEEPSLATE_BRICKS);
					break;
				case BLOCK_DEEPSLATE_BRICKS_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.DEEPSLATE_BRICKS);
					break;
				case BLOCK_DEEPSLATE_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.DEEPSLATE);
					break;
				case BLOCK_DEEPSLATE_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.DEEPSLATE);
					break;
				case BLOCK_DEEPSLATE_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.DEEPSLATE);
					break;
				case BLOCK_DEEPSLATE_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.DEEPSLATE);
					break;
				case BLOCK_DEEPSLATE_TILES_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.DEEPSLATE_TILES);
					break;
				case BLOCK_DEEPSLATE_TILES_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.DEEPSLATE_TILES);
					break;
				case BLOCK_DEEPSLATE_TILES_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.DEEPSLATE_TILES);
					break;
				case BLOCK_DEEPSLATE_TILES_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.DEEPSLATE_TILES);
					break;
				case BLOCK_DEEPSLATE_TILES_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.DEEPSLATE_TILES);
					break;
				case BLOCK_DRIPSTONE_BLOCK_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.DRIPSTONE_BLOCK);
					break;
				case BLOCK_DRIPSTONE_BLOCK_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.DRIPSTONE_BLOCK);
					break;
				case BLOCK_DRIPSTONE_BLOCK_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.DRIPSTONE_BLOCK);
					break;
				case BLOCK_DRIPSTONE_BLOCK_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.DRIPSTONE_BLOCK);
					break;
				case BLOCK_DRIPSTONE_BLOCK_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.DRIPSTONE_BLOCK);
					break;
				case BLOCK_FLOWERING_AZALEA_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.FLOWERING_AZALEA);
					break;
				case BLOCK_FLOWERING_AZALEA_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.FLOWERING_AZALEA);
					break;
				case BLOCK_FLOWERING_AZALEA_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.FLOWERING_AZALEA);
					break;
				case BLOCK_FLOWERING_AZALEA_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.FLOWERING_AZALEA);
					break;
				case BLOCK_FLOWERING_AZALEA_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.FLOWERING_AZALEA);
					break;
				case BLOCK_HANGING_ROOTS_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.HANGING_ROOTS);
					break;
				case BLOCK_HANGING_ROOTS_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.HANGING_ROOTS);
					break;
				case BLOCK_HANGING_ROOTS_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.HANGING_ROOTS);
					break;
				case BLOCK_HANGING_ROOTS_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.HANGING_ROOTS);
					break;
				case BLOCK_HANGING_ROOTS_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.HANGING_ROOTS);
					break;
				case BLOCK_LARGE_AMETHYST_BUD_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.LARGE_AMETHYST_BUD);
					break;
				case BLOCK_LARGE_AMETHYST_BUD_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.LARGE_AMETHYST_BUD);
					break;
				case BLOCK_MANGROVE_ROOTS_BREAK:
				case BLOCK_MANGROVE_ROOTS_FALL:
				case BLOCK_MANGROVE_ROOTS_HIT:
				case BLOCK_MANGROVE_ROOTS_PLACE:
				case BLOCK_MANGROVE_ROOTS_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.MANGROVE_ROOTS);
					break;
				case BLOCK_MEDIUM_AMETHYST_BUD_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.MEDIUM_AMETHYST_BUD);
					break;
				case BLOCK_MEDIUM_AMETHYST_BUD_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.MEDIUM_AMETHYST_BUD);
					break;
				case BLOCK_MOSS_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.MOSS_BLOCK);
					break;
				case BLOCK_MOSS_CARPET_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.MOSS_CARPET);
					break;
				case BLOCK_MOSS_CARPET_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.MOSS_CARPET);
					break;
				case BLOCK_MOSS_CARPET_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.MOSS_CARPET);
					break;
				case BLOCK_MOSS_CARPET_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.MOSS_CARPET);
					break;
				case BLOCK_MOSS_CARPET_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.MOSS_CARPET);
					break;
				case BLOCK_MOSS_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.MOSS_BLOCK);
					break;
				case BLOCK_MOSS_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.MOSS_BLOCK);
					break;
				case BLOCK_MOSS_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.MOSS_BLOCK);
					break;
				case BLOCK_MOSS_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.MOSS_BLOCK);
					break;
				case BLOCK_POINTED_DRIPSTONE_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.POINTED_DRIPSTONE);
					break;
				case BLOCK_POINTED_DRIPSTONE_DRIP_LAVA:
					SlotPlugin.soundMaterialMap.put(sound, Material.POINTED_DRIPSTONE);
					break;
				case BLOCK_POINTED_DRIPSTONE_DRIP_LAVA_INTO_CAULDRON:
					SlotPlugin.soundMaterialMap.put(sound, Material.POINTED_DRIPSTONE);
					break;
				case BLOCK_POINTED_DRIPSTONE_DRIP_WATER:
					SlotPlugin.soundMaterialMap.put(sound, Material.POINTED_DRIPSTONE);
					break;
				case BLOCK_POINTED_DRIPSTONE_DRIP_WATER_INTO_CAULDRON:
					SlotPlugin.soundMaterialMap.put(sound, Material.POINTED_DRIPSTONE);
					break;
				case BLOCK_POINTED_DRIPSTONE_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.POINTED_DRIPSTONE);
					break;
				case BLOCK_POINTED_DRIPSTONE_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.POINTED_DRIPSTONE);
					break;
				case BLOCK_POINTED_DRIPSTONE_LAND:
					SlotPlugin.soundMaterialMap.put(sound, Material.POINTED_DRIPSTONE);
					break;
				case BLOCK_POINTED_DRIPSTONE_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.POINTED_DRIPSTONE);
					break;
				case BLOCK_POINTED_DRIPSTONE_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.POINTED_DRIPSTONE);
					break;
				case BLOCK_POLISHED_DEEPSLATE_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.POLISHED_DEEPSLATE);
					break;
				case BLOCK_POLISHED_DEEPSLATE_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.POLISHED_DEEPSLATE);
					break;
				case BLOCK_POLISHED_DEEPSLATE_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.POLISHED_DEEPSLATE);
					break;
				case BLOCK_POLISHED_DEEPSLATE_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.POLISHED_DEEPSLATE);
					break;
				case BLOCK_POLISHED_DEEPSLATE_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.POLISHED_DEEPSLATE);
					break;
				case BLOCK_POWDER_SNOW_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.POWDER_SNOW_BUCKET); // CHANGETO : powder_snow
					break;
				case BLOCK_POWDER_SNOW_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.POWDER_SNOW_BUCKET); // CHANGETO : powder_snow
					break;
				case BLOCK_POWDER_SNOW_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.POWDER_SNOW_BUCKET); // CHANGETO : powder_snow
					break;
				case BLOCK_POWDER_SNOW_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.POWDER_SNOW_BUCKET); // CHANGETO : powder_snow
					break;
				case BLOCK_POWDER_SNOW_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.POWDER_SNOW_BUCKET); // CHANGETO : powder_snow
					break;
				case BLOCK_ROOTED_DIRT_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.ROOTED_DIRT);
					break;
				case BLOCK_ROOTED_DIRT_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.ROOTED_DIRT);
					break;
				case BLOCK_ROOTED_DIRT_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.ROOTED_DIRT);
					break;
				case BLOCK_ROOTED_DIRT_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.ROOTED_DIRT);
					break;
				case BLOCK_ROOTED_DIRT_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.ROOTED_DIRT);
					break;
				case BLOCK_SCULK_SENSOR_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.SCULK_SENSOR);
					break;
				case BLOCK_SCULK_SENSOR_CLICKING:
					SlotPlugin.soundMaterialMap.put(sound, Material.SCULK_SENSOR);
					break;
				case BLOCK_SCULK_SENSOR_CLICKING_STOP:
					SlotPlugin.soundMaterialMap.put(sound, Material.SCULK_SENSOR);
					break;
				case BLOCK_SCULK_SENSOR_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.SCULK_SENSOR);
					break;
				case BLOCK_SCULK_SENSOR_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.SCULK_SENSOR);
					break;
				case BLOCK_SCULK_SENSOR_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.SCULK_SENSOR);
					break;
				case BLOCK_SCULK_SENSOR_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.SCULK_SENSOR);
					break;
				case BLOCK_SMALL_AMETHYST_BUD_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.SMALL_AMETHYST_BUD);
					break;
				case BLOCK_SMALL_AMETHYST_BUD_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.SMALL_AMETHYST_BUD);
					break;
				case BLOCK_SMALL_DRIPLEAF_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.SMALL_DRIPLEAF);
					break;
				case BLOCK_SMALL_DRIPLEAF_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.SMALL_DRIPLEAF);
					break;
				case BLOCK_SMALL_DRIPLEAF_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.SMALL_DRIPLEAF);
					break;
				case BLOCK_SMALL_DRIPLEAF_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.SMALL_DRIPLEAF);
					break;
				case BLOCK_SMALL_DRIPLEAF_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.SMALL_DRIPLEAF);
					break;
				case BLOCK_SPORE_BLOSSOM_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.SPORE_BLOSSOM);
					break;
				case BLOCK_SPORE_BLOSSOM_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.SPORE_BLOSSOM);
					break;
				case BLOCK_SPORE_BLOSSOM_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.SPORE_BLOSSOM);
					break;
				case BLOCK_SPORE_BLOSSOM_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.SPORE_BLOSSOM);
					break;
				case BLOCK_SPORE_BLOSSOM_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.SPORE_BLOSSOM);
					break;
				case BLOCK_SWEET_BERRY_BUSH_PICK_BERRIES:
					SlotPlugin.soundMaterialMap.put(sound, Material.SWEET_BERRIES);
					break;
				case BLOCK_TUFF_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.TUFF);
					break;
				case BLOCK_TUFF_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.TUFF);
					break;
				case BLOCK_TUFF_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.TUFF);
					break;
				case BLOCK_TUFF_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.TUFF);
					break;
				case BLOCK_TUFF_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.TUFF);
					break;
				case BLOCK_VINE_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.VINE);
					break;
				case BLOCK_VINE_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.VINE);
					break;
				case BLOCK_VINE_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.VINE);
					break;
				case BLOCK_VINE_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.VINE);
					break;
				case ENTITY_AXOLOTL_ATTACK:
					SlotPlugin.soundMaterialMap.put(sound, Material.AXOLOTL_BUCKET);
					break;
				case ENTITY_AXOLOTL_DEATH:
					SlotPlugin.soundMaterialMap.put(sound, Material.AXOLOTL_BUCKET);
					break;
				case ENTITY_AXOLOTL_HURT:
					SlotPlugin.soundMaterialMap.put(sound, Material.AXOLOTL_BUCKET);
					break;
				case ENTITY_AXOLOTL_IDLE_AIR:
					SlotPlugin.soundMaterialMap.put(sound, Material.AXOLOTL_BUCKET);
					break;
				case ENTITY_AXOLOTL_IDLE_WATER:
					SlotPlugin.soundMaterialMap.put(sound, Material.AXOLOTL_BUCKET);
					break;
				case ENTITY_AXOLOTL_SPLASH:
					SlotPlugin.soundMaterialMap.put(sound, Material.AXOLOTL_BUCKET);
					break;
				case ENTITY_AXOLOTL_SWIM:
					SlotPlugin.soundMaterialMap.put(sound, Material.AXOLOTL_BUCKET);
					break;
				case ENTITY_GLOW_ITEM_FRAME_ADD_ITEM:
					SlotPlugin.soundMaterialMap.put(sound, Material.GLOW_ITEM_FRAME);
					break;
				case ENTITY_GLOW_ITEM_FRAME_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.GLOW_ITEM_FRAME);
					break;
				case ENTITY_GLOW_ITEM_FRAME_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.GLOW_ITEM_FRAME);
					break;
				case ENTITY_GLOW_ITEM_FRAME_REMOVE_ITEM:
					SlotPlugin.soundMaterialMap.put(sound, Material.GLOW_ITEM_FRAME);
					break;
				case ENTITY_GLOW_ITEM_FRAME_ROTATE_ITEM:
					SlotPlugin.soundMaterialMap.put(sound, Material.GLOW_ITEM_FRAME);
					break;
				case ENTITY_GLOW_SQUID_AMBIENT:
					SlotPlugin.soundMaterialMap.put(sound, Material.GLOW_SQUID_SPAWN_EGG);
					break;
				case ENTITY_GLOW_SQUID_DEATH:
					SlotPlugin.soundMaterialMap.put(sound, Material.GLOW_SQUID_SPAWN_EGG);
					break;
				case ENTITY_GLOW_SQUID_HURT:
					SlotPlugin.soundMaterialMap.put(sound, Material.GLOW_SQUID_SPAWN_EGG);
					break;
				case ENTITY_GLOW_SQUID_SQUIRT:
					SlotPlugin.soundMaterialMap.put(sound, Material.GLOW_SQUID_SPAWN_EGG);
					break;
				case ENTITY_GOAT_AMBIENT:
					SlotPlugin.soundMaterialMap.put(sound, Material.GOAT_SPAWN_EGG);
					break;
				case ENTITY_GOAT_DEATH:
					SlotPlugin.soundMaterialMap.put(sound, Material.GOAT_SPAWN_EGG);
					break;
				case ENTITY_GOAT_EAT:
					SlotPlugin.soundMaterialMap.put(sound, Material.GOAT_SPAWN_EGG);
					break;
				case ENTITY_GOAT_HURT:
					SlotPlugin.soundMaterialMap.put(sound, Material.GOAT_SPAWN_EGG);
					break;
				case ENTITY_GOAT_LONG_JUMP:
					SlotPlugin.soundMaterialMap.put(sound, Material.GOAT_SPAWN_EGG);
					break;
				case ENTITY_GOAT_MILK:
					SlotPlugin.soundMaterialMap.put(sound, Material.GOAT_SPAWN_EGG);
					break;
				case ENTITY_GOAT_PREPARE_RAM:
					SlotPlugin.soundMaterialMap.put(sound, Material.GOAT_SPAWN_EGG);
					break;
				case ENTITY_GOAT_RAM_IMPACT:
					SlotPlugin.soundMaterialMap.put(sound, Material.GOAT_SPAWN_EGG);
					break;
				case ENTITY_GOAT_SCREAMING_AMBIENT:
					SlotPlugin.soundMaterialMap.put(sound, Material.GOAT_SPAWN_EGG);
					break;
				case ENTITY_GOAT_SCREAMING_DEATH:
					SlotPlugin.soundMaterialMap.put(sound, Material.GOAT_SPAWN_EGG);
					break;
				case ENTITY_GOAT_SCREAMING_EAT:
					SlotPlugin.soundMaterialMap.put(sound, Material.GOAT_SPAWN_EGG);
					break;
				case ENTITY_GOAT_SCREAMING_HURT:
					SlotPlugin.soundMaterialMap.put(sound, Material.GOAT_SPAWN_EGG);
					break;
				case ENTITY_GOAT_SCREAMING_LONG_JUMP:
					SlotPlugin.soundMaterialMap.put(sound, Material.GOAT_SPAWN_EGG);
					break;
				case ENTITY_GOAT_SCREAMING_MILK:
					SlotPlugin.soundMaterialMap.put(sound, Material.GOAT_SPAWN_EGG);
					break;
				case ENTITY_GOAT_SCREAMING_PREPARE_RAM:
					SlotPlugin.soundMaterialMap.put(sound, Material.GOAT_SPAWN_EGG);
					break;
				case ENTITY_GOAT_SCREAMING_RAM_IMPACT:
					SlotPlugin.soundMaterialMap.put(sound, Material.GOAT_SPAWN_EGG);
					break;
				case ENTITY_GOAT_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.GOAT_SPAWN_EGG);
					break;
				case ENTITY_MINECART_INSIDE_UNDERWATER:
					SlotPlugin.soundMaterialMap.put(sound, Material.MINECART);
					break;
				case ENTITY_PARROT_IMITATE_PIGLIN_BRUTE:
					SlotPlugin.soundMaterialMap.put(sound, Material.PARROT_SPAWN_EGG);
					break;
				case ENTITY_PIGLIN_BRUTE_AMBIENT:
					SlotPlugin.soundMaterialMap.put(sound, Material.PIGLIN_BRUTE_SPAWN_EGG);
					break;
				case ENTITY_PIGLIN_BRUTE_ANGRY:
					SlotPlugin.soundMaterialMap.put(sound, Material.PIGLIN_BRUTE_SPAWN_EGG);
					break;
				case ENTITY_PIGLIN_BRUTE_CONVERTED_TO_ZOMBIFIED:
					SlotPlugin.soundMaterialMap.put(sound, Material.PIGLIN_BRUTE_SPAWN_EGG);
					break;
				case ENTITY_PIGLIN_BRUTE_DEATH:
					SlotPlugin.soundMaterialMap.put(sound, Material.PIGLIN_BRUTE_SPAWN_EGG);
					break;
				case ENTITY_PIGLIN_BRUTE_HURT:
					SlotPlugin.soundMaterialMap.put(sound, Material.PIGLIN_BRUTE_SPAWN_EGG);
					break;
				case ENTITY_PIGLIN_BRUTE_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.PIGLIN_BRUTE_SPAWN_EGG);
					break;
				case ENTITY_PLAYER_HURT_FREEZE:
					SlotPlugin.soundMaterialMap.put(sound, Material.POWDER_SNOW_BUCKET);
					break;
				case ENTITY_SKELETON_CONVERTED_TO_STRAY:
					SlotPlugin.soundMaterialMap.put(sound, Material.STRAY_SPAWN_EGG);
					break;
				case ITEM_AXE_SCRAPE:
					SlotPlugin.soundMaterialMap.put(sound, Material.IRON_AXE);
					break;
				case ITEM_AXE_WAX_OFF:
					SlotPlugin.soundMaterialMap.put(sound, Material.IRON_AXE);
					break;
				case ITEM_BONE_MEAL_USE:
					SlotPlugin.soundMaterialMap.put(sound, Material.BONE_MEAL);
					break;
				case ITEM_BUCKET_EMPTY_AXOLOTL:
					SlotPlugin.soundMaterialMap.put(sound, Material.AXOLOTL_BUCKET);
					break;
				case ITEM_BUCKET_EMPTY_POWDER_SNOW:
					SlotPlugin.soundMaterialMap.put(sound, Material.POWDER_SNOW_BUCKET);
					break;
				case ITEM_BUCKET_FILL_AXOLOTL:
					SlotPlugin.soundMaterialMap.put(sound, Material.AXOLOTL_BUCKET);
					break;
				case ITEM_BUCKET_FILL_POWDER_SNOW:
					SlotPlugin.soundMaterialMap.put(sound, Material.POWDER_SNOW_BUCKET);
					break;
				case ITEM_DYE_USE:
					SlotPlugin.soundMaterialMap.put(sound, Material.BLUE_DYE);
					break;
				case ITEM_GLOW_INK_SAC_USE:
					SlotPlugin.soundMaterialMap.put(sound, Material.GLOW_INK_SAC);
					break;
				case ITEM_HONEYCOMB_WAX_ON:
					SlotPlugin.soundMaterialMap.put(sound, Material.HONEYCOMB);
					break;
				case ITEM_INK_SAC_USE:
					SlotPlugin.soundMaterialMap.put(sound, Material.INK_SAC);
					break;
				case ITEM_SPYGLASS_STOP_USING:
					SlotPlugin.soundMaterialMap.put(sound, Material.SPYGLASS);
					break;
				case ITEM_SPYGLASS_USE:
					SlotPlugin.soundMaterialMap.put(sound, Material.SPYGLASS);
					break;
				case BLOCK_GROWING_PLANT_CROP:
					SlotPlugin.soundMaterialMap.put(sound, Material.ACACIA_LEAVES);
					break;
				case ITEM_BUNDLE_DROP_CONTENTS:
					SlotPlugin.soundMaterialMap.put(sound, Material.BUNDLE);
					break;
				case ITEM_BUNDLE_INSERT:
					SlotPlugin.soundMaterialMap.put(sound, Material.BUNDLE);
					break;
				case ITEM_BUNDLE_REMOVE_ONE:
					SlotPlugin.soundMaterialMap.put(sound, Material.BUNDLE);
					break;
				case MUSIC_DISC_OTHERSIDE:
					SlotPlugin.soundMaterialMap.put(sound, Material.MUSIC_DISC_OTHERSIDE);
					break;
				case MUSIC_OVERWORLD_DRIPSTONE_CAVES:
					SlotPlugin.soundMaterialMap.put(sound, Material.POINTED_DRIPSTONE);
					break;
				case MUSIC_OVERWORLD_FROZEN_PEAKS:
					SlotPlugin.soundMaterialMap.put(sound, Material.BLUE_ICE);
					break;
				case MUSIC_OVERWORLD_GROVE:
					SlotPlugin.soundMaterialMap.put(sound, Material.ACACIA_LEAVES);
					break;
				case MUSIC_OVERWORLD_JAGGED_PEAKS:
					SlotPlugin.soundMaterialMap.put(sound, Material.STONE);
					break;
				case MUSIC_OVERWORLD_LUSH_CAVES:
					SlotPlugin.soundMaterialMap.put(sound, Material.ACACIA_LEAVES);
					break;
				case MUSIC_OVERWORLD_MEADOW:
					SlotPlugin.soundMaterialMap.put(sound, Material.VINE);
					break;
				case MUSIC_OVERWORLD_SNOWY_SLOPES:
					SlotPlugin.soundMaterialMap.put(sound, Material.SNOW_BLOCK);
					break;
				case MUSIC_OVERWORLD_STONY_PEAKS:
					SlotPlugin.soundMaterialMap.put(sound, Material.STONE);
					break;
				case BLOCK_MUDDY_MANGROVE_ROOTS_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.MANGROVE_ROOTS);
					break;
				case BLOCK_MUDDY_MANGROVE_ROOTS_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.MANGROVE_ROOTS);
					break;
				case BLOCK_MUDDY_MANGROVE_ROOTS_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.MANGROVE_ROOTS);
					break;
				case BLOCK_MUDDY_MANGROVE_ROOTS_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.MANGROVE_ROOTS);
					break;
				case BLOCK_MUDDY_MANGROVE_ROOTS_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.MANGROVE_ROOTS);
					break;
				case BLOCK_MUD_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.MUD_BRICKS);
					break;
				case BLOCK_MUD_BRICKS_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.MUD_BRICKS);
					break;
				case BLOCK_MUD_BRICKS_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.MUD_BRICKS);
					break;
				case BLOCK_MUD_BRICKS_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.MUD_BRICKS);
					break;
				case BLOCK_MUD_BRICKS_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.MUD_BRICKS);
					break;
				case BLOCK_MUD_BRICKS_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.MUD_BRICKS);
					break;
				case BLOCK_MUD_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.MUD);
					break;
				case BLOCK_MUD_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.MUD);
					break;
				case BLOCK_MUD_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.MUD);
					break;
				case BLOCK_MUD_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.MUD);
					break;
				case BLOCK_PACKED_MUD_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.PACKED_MUD);
					break;
				case BLOCK_PACKED_MUD_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.PACKED_MUD);
					break;
				case BLOCK_PACKED_MUD_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.PACKED_MUD);
					break;
				case BLOCK_PACKED_MUD_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.PACKED_MUD);
					break;
				case BLOCK_PACKED_MUD_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.PACKED_MUD);
					break;
				case BLOCK_SCULK_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.SCULK);
					break;
				case BLOCK_SCULK_CATALYST_BLOOM:
					SlotPlugin.soundMaterialMap.put(sound, Material.SCULK_CATALYST);
					break;
				case BLOCK_SCULK_CATALYST_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.SCULK_CATALYST);
					break;
				case BLOCK_SCULK_CATALYST_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.SCULK_CATALYST);
					break;
				case BLOCK_SCULK_CATALYST_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.SCULK_CATALYST);
					break;
				case BLOCK_SCULK_CATALYST_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.SCULK_CATALYST);
					break;
				case BLOCK_SCULK_CATALYST_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.SCULK_CATALYST);
					break;
				case BLOCK_SCULK_CHARGE:
					SlotPlugin.soundMaterialMap.put(sound, Material.SCULK);
					break;
				case BLOCK_SCULK_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.SCULK);
					break;
				case BLOCK_SCULK_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.SCULK);
					break;
				case BLOCK_SCULK_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.SCULK);
					break;
				case BLOCK_SCULK_SHRIEKER_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.SCULK_SHRIEKER);
					break;
				case BLOCK_SCULK_SHRIEKER_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.SCULK_SHRIEKER);
					break;
				case BLOCK_SCULK_SHRIEKER_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.SCULK_SHRIEKER);
					break;
				case BLOCK_SCULK_SHRIEKER_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.SCULK_SHRIEKER);
					break;
				case BLOCK_SCULK_SHRIEKER_SHRIEK:
					SlotPlugin.soundMaterialMap.put(sound, Material.SCULK_SHRIEKER);
					break;
				case BLOCK_SCULK_SHRIEKER_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.SCULK_SHRIEKER);
					break;
				case BLOCK_SCULK_SPREAD:
					SlotPlugin.soundMaterialMap.put(sound, Material.SCULK);
					break;
				case BLOCK_SCULK_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.SCULK);
					break;
				case BLOCK_SCULK_VEIN_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.SCULK_VEIN);
					break;
				case BLOCK_SCULK_VEIN_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.SCULK_VEIN);
					break;
				case BLOCK_SCULK_VEIN_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.SCULK_VEIN);
					break;
				case BLOCK_SCULK_VEIN_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.SCULK_VEIN);
					break;
				case BLOCK_SCULK_VEIN_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.SCULK_VEIN);
					break;
				case ENTITY_ALLAY_AMBIENT_WITHOUT_ITEM:
					SlotPlugin.soundMaterialMap.put(sound, Material.ALLAY_SPAWN_EGG);
					break;
				case ENTITY_ALLAY_AMBIENT_WITH_ITEM:
					SlotPlugin.soundMaterialMap.put(sound, Material.ALLAY_SPAWN_EGG);
					break;
				case ENTITY_ALLAY_DEATH:
					SlotPlugin.soundMaterialMap.put(sound, Material.ALLAY_SPAWN_EGG);
					break;
				case ENTITY_ALLAY_HURT:
					SlotPlugin.soundMaterialMap.put(sound, Material.ALLAY_SPAWN_EGG);
					break;
				case ENTITY_ALLAY_ITEM_GIVEN:
					SlotPlugin.soundMaterialMap.put(sound, Material.ALLAY_SPAWN_EGG);
					break;
				case ENTITY_ALLAY_ITEM_TAKEN:
					SlotPlugin.soundMaterialMap.put(sound, Material.ALLAY_SPAWN_EGG);
					break;
				case ENTITY_ALLAY_ITEM_THROWN:
					SlotPlugin.soundMaterialMap.put(sound, Material.ALLAY_SPAWN_EGG);
					break;
				case ENTITY_FROG_AMBIENT:
					SlotPlugin.soundMaterialMap.put(sound, Material.FROG_SPAWN_EGG);
					break;
				case ENTITY_FROG_DEATH:
					SlotPlugin.soundMaterialMap.put(sound, Material.FROG_SPAWN_EGG);
					break;
				case ENTITY_FROG_EAT:
					SlotPlugin.soundMaterialMap.put(sound, Material.FROG_SPAWN_EGG);
					break;
				case ENTITY_FROG_HURT:
					SlotPlugin.soundMaterialMap.put(sound, Material.FROG_SPAWN_EGG);
					break;
				case ENTITY_FROG_LAY_SPAWN:
					SlotPlugin.soundMaterialMap.put(sound, Material.FROG_SPAWN_EGG);
					break;
				case ENTITY_FROG_LONG_JUMP:
					SlotPlugin.soundMaterialMap.put(sound, Material.FROG_SPAWN_EGG);
					break;
				case ENTITY_FROG_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.FROG_SPAWN_EGG);
					break;
				case ENTITY_FROG_TONGUE:
					SlotPlugin.soundMaterialMap.put(sound, Material.FROG_SPAWN_EGG);
					break;
				case ENTITY_GOAT_HORN_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.GOAT_HORN);
					break;
				case ENTITY_GOAT_SCREAMING_HORN_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.GOAT_HORN);
					break;
				case ENTITY_PARROT_IMITATE_WARDEN:
					SlotPlugin.soundMaterialMap.put(sound, Material.PARROT_SPAWN_EGG);
					break;
				case ENTITY_TADPOLE_DEATH:
					SlotPlugin.soundMaterialMap.put(sound, Material.TADPOLE_SPAWN_EGG);
					break;
				case ENTITY_TADPOLE_FLOP:
					SlotPlugin.soundMaterialMap.put(sound, Material.TADPOLE_SPAWN_EGG);
					break;
				case ENTITY_TADPOLE_GROW_UP:
					SlotPlugin.soundMaterialMap.put(sound, Material.TADPOLE_SPAWN_EGG);
					break;
				case ENTITY_TADPOLE_HURT:
					SlotPlugin.soundMaterialMap.put(sound, Material.TADPOLE_SPAWN_EGG);
					break;
				case ENTITY_WARDEN_AGITATED:
					SlotPlugin.soundMaterialMap.put(sound, Material.WARDEN_SPAWN_EGG);
					break;
				case ENTITY_WARDEN_AMBIENT:
					SlotPlugin.soundMaterialMap.put(sound, Material.WARDEN_SPAWN_EGG);
					break;
				case ENTITY_WARDEN_ANGRY:
					SlotPlugin.soundMaterialMap.put(sound, Material.WARDEN_SPAWN_EGG);
					break;
				case ENTITY_WARDEN_ATTACK_IMPACT:
					SlotPlugin.soundMaterialMap.put(sound, Material.WARDEN_SPAWN_EGG);
					break;
				case ENTITY_WARDEN_DEATH:
					SlotPlugin.soundMaterialMap.put(sound, Material.WARDEN_SPAWN_EGG);
					break;
				case ENTITY_WARDEN_DIG:
					SlotPlugin.soundMaterialMap.put(sound, Material.WARDEN_SPAWN_EGG);
					break;
				case ENTITY_WARDEN_EMERGE:
					SlotPlugin.soundMaterialMap.put(sound, Material.WARDEN_SPAWN_EGG);
					break;
				case ENTITY_WARDEN_HEARTBEAT:
					SlotPlugin.soundMaterialMap.put(sound, Material.WARDEN_SPAWN_EGG);
					break;
				case ENTITY_WARDEN_HURT:
					SlotPlugin.soundMaterialMap.put(sound, Material.WARDEN_SPAWN_EGG);
					break;
				case ENTITY_WARDEN_LISTENING:
					SlotPlugin.soundMaterialMap.put(sound, Material.WARDEN_SPAWN_EGG);
					break;
				case ENTITY_WARDEN_LISTENING_ANGRY:
					SlotPlugin.soundMaterialMap.put(sound, Material.WARDEN_SPAWN_EGG);
					break;
				case ENTITY_WARDEN_NEARBY_CLOSE:
					SlotPlugin.soundMaterialMap.put(sound, Material.WARDEN_SPAWN_EGG);
					break;
				case ENTITY_WARDEN_NEARBY_CLOSER:
					SlotPlugin.soundMaterialMap.put(sound, Material.WARDEN_SPAWN_EGG);
					break;
				case ENTITY_WARDEN_NEARBY_CLOSEST:
					SlotPlugin.soundMaterialMap.put(sound, Material.WARDEN_SPAWN_EGG);
					break;
				case ENTITY_WARDEN_ROAR:
					SlotPlugin.soundMaterialMap.put(sound, Material.WARDEN_SPAWN_EGG);
					break;
				case ENTITY_WARDEN_SNIFF:
					SlotPlugin.soundMaterialMap.put(sound, Material.WARDEN_SPAWN_EGG);
					break;
				case ENTITY_WARDEN_SONIC_BOOM:
					SlotPlugin.soundMaterialMap.put(sound, Material.WARDEN_SPAWN_EGG);
					break;
				case ENTITY_WARDEN_SONIC_CHARGE:
					SlotPlugin.soundMaterialMap.put(sound, Material.WARDEN_SPAWN_EGG);
					break;
				case ENTITY_WARDEN_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.WARDEN_SPAWN_EGG);
					break;
				case ENTITY_WARDEN_TENDRIL_CLICKS:
					SlotPlugin.soundMaterialMap.put(sound, Material.WARDEN_SPAWN_EGG);
					break;
				case ITEM_BUCKET_EMPTY_TADPOLE:
					SlotPlugin.soundMaterialMap.put(sound, Material.TADPOLE_BUCKET);
					break;
				case ITEM_BUCKET_FILL_TADPOLE:
					SlotPlugin.soundMaterialMap.put(sound, Material.TADPOLE_BUCKET);
					break;
				case ITEM_GOAT_HORN_PLAY:
					SlotPlugin.soundMaterialMap.put(sound, Material.GOAT_HORN);
					break;
				case ITEM_GOAT_HORN_SOUND_0:
					SlotPlugin.soundMaterialMap.put(sound, Material.GOAT_HORN);
					break;
				case ITEM_GOAT_HORN_SOUND_1:
					SlotPlugin.soundMaterialMap.put(sound, Material.GOAT_HORN);
					break;
				case ITEM_GOAT_HORN_SOUND_2:
					SlotPlugin.soundMaterialMap.put(sound, Material.GOAT_HORN);
					break;
				case ITEM_GOAT_HORN_SOUND_3:
					SlotPlugin.soundMaterialMap.put(sound, Material.GOAT_HORN);
					break;
				case ITEM_GOAT_HORN_SOUND_4:
					SlotPlugin.soundMaterialMap.put(sound, Material.GOAT_HORN);
					break;
				case ITEM_GOAT_HORN_SOUND_5:
					SlotPlugin.soundMaterialMap.put(sound, Material.GOAT_HORN);
					break;
				case ITEM_GOAT_HORN_SOUND_6:
					SlotPlugin.soundMaterialMap.put(sound, Material.GOAT_HORN);
					break;
				case ITEM_GOAT_HORN_SOUND_7:
					SlotPlugin.soundMaterialMap.put(sound, Material.GOAT_HORN);
					break;
				case MUSIC_DISC_5:
					SlotPlugin.soundMaterialMap.put(sound, Material.MUSIC_DISC_5);
					break;
				case MUSIC_OVERWORLD_DEEP_DARK:
					SlotPlugin.soundMaterialMap.put(sound, Material.JUKEBOX);
					break;
				case MUSIC_OVERWORLD_OLD_GROWTH_TAIGA:
					SlotPlugin.soundMaterialMap.put(sound, Material.JUKEBOX);
					break;
				case MUSIC_OVERWORLD_SWAMP:
					SlotPlugin.soundMaterialMap.put(sound, Material.JUKEBOX);
					break;
				case BLOCK_BAMBOO_WOOD_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.BAMBOO_BLOCK);
					break;
				case BLOCK_BAMBOO_WOOD_BUTTON_CLICK_OFF:
					SlotPlugin.soundMaterialMap.put(sound, Material.BAMBOO_BUTTON);
					break;
				case BLOCK_BAMBOO_WOOD_BUTTON_CLICK_ON:
					SlotPlugin.soundMaterialMap.put(sound, Material.BAMBOO_BUTTON);
					break;
				case BLOCK_BAMBOO_WOOD_DOOR_CLOSE:
					SlotPlugin.soundMaterialMap.put(sound, Material.BAMBOO_DOOR);
					break;
				case BLOCK_BAMBOO_WOOD_DOOR_OPEN:
					SlotPlugin.soundMaterialMap.put(sound, Material.BAMBOO_DOOR);
					break;
				case BLOCK_BAMBOO_WOOD_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.BAMBOO_BLOCK);
					break;
				case BLOCK_BAMBOO_WOOD_FENCE_GATE_CLOSE:
					SlotPlugin.soundMaterialMap.put(sound, Material.BAMBOO_FENCE_GATE);
					break;
				case BLOCK_BAMBOO_WOOD_FENCE_GATE_OPEN:
					SlotPlugin.soundMaterialMap.put(sound, Material.BAMBOO_FENCE_GATE);
					break;
				case BLOCK_BAMBOO_WOOD_HANGING_SIGN_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.BAMBOO_HANGING_SIGN);
					break;
				case BLOCK_BAMBOO_WOOD_HANGING_SIGN_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.BAMBOO_HANGING_SIGN);
					break;
				case BLOCK_BAMBOO_WOOD_HANGING_SIGN_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.BAMBOO_HANGING_SIGN);
					break;
				case BLOCK_BAMBOO_WOOD_HANGING_SIGN_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.BAMBOO_HANGING_SIGN);
					break;
				case BLOCK_BAMBOO_WOOD_HANGING_SIGN_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.BAMBOO_HANGING_SIGN);
					break;
				case BLOCK_BAMBOO_WOOD_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.BAMBOO_BLOCK);
					break;
				case BLOCK_BAMBOO_WOOD_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.BAMBOO_BLOCK);
					break;
				case BLOCK_BAMBOO_WOOD_PRESSURE_PLATE_CLICK_OFF:
					SlotPlugin.soundMaterialMap.put(sound, Material.BAMBOO_PRESSURE_PLATE);
					break;
				case BLOCK_BAMBOO_WOOD_PRESSURE_PLATE_CLICK_ON:
					SlotPlugin.soundMaterialMap.put(sound, Material.BAMBOO_PRESSURE_PLATE);
					break;
				case BLOCK_BAMBOO_WOOD_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.BAMBOO_BLOCK);
					break;
				case BLOCK_BAMBOO_WOOD_TRAPDOOR_CLOSE:
					SlotPlugin.soundMaterialMap.put(sound, Material.BAMBOO_TRAPDOOR);
					break;
				case BLOCK_BAMBOO_WOOD_TRAPDOOR_OPEN:
					SlotPlugin.soundMaterialMap.put(sound, Material.BAMBOO_TRAPDOOR);
					break;
				case BLOCK_CHERRY_LEAVES_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.CHERRY_LEAVES);
					break;
				case BLOCK_CHERRY_LEAVES_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.CHERRY_LEAVES);
					break;
				case BLOCK_CHERRY_LEAVES_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.CHERRY_LEAVES);
					break;
				case BLOCK_CHERRY_LEAVES_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.CHERRY_LEAVES);
					break;
				case BLOCK_CHERRY_LEAVES_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.CHERRY_LEAVES);
					break;
				case BLOCK_CHERRY_SAPLING_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.CHERRY_SAPLING);
					break;
				case BLOCK_CHERRY_SAPLING_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.CHERRY_SAPLING);
					break;
				case BLOCK_CHERRY_SAPLING_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.CHERRY_SAPLING);
					break;
				case BLOCK_CHERRY_SAPLING_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.CHERRY_SAPLING);
					break;
				case BLOCK_CHERRY_SAPLING_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.CHERRY_SAPLING);
					break;
				case BLOCK_CHERRY_WOOD_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.CHERRY_WOOD);
					break;
				case BLOCK_CHERRY_WOOD_BUTTON_CLICK_OFF:
					SlotPlugin.soundMaterialMap.put(sound, Material.CHERRY_BUTTON);
					break;
				case BLOCK_CHERRY_WOOD_BUTTON_CLICK_ON:
					SlotPlugin.soundMaterialMap.put(sound, Material.CHERRY_BUTTON);
					break;
				case BLOCK_CHERRY_WOOD_DOOR_CLOSE:
					SlotPlugin.soundMaterialMap.put(sound, Material.CHERRY_DOOR);
					break;
				case BLOCK_CHERRY_WOOD_DOOR_OPEN:
					SlotPlugin.soundMaterialMap.put(sound, Material.CHERRY_DOOR);
					break;
				case BLOCK_CHERRY_WOOD_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.CHERRY_WOOD);
					break;
				case BLOCK_CHERRY_WOOD_FENCE_GATE_CLOSE:
					SlotPlugin.soundMaterialMap.put(sound, Material.CHERRY_FENCE_GATE);
					break;
				case BLOCK_CHERRY_WOOD_FENCE_GATE_OPEN:
					SlotPlugin.soundMaterialMap.put(sound, Material.CHERRY_FENCE_GATE);
					break;
				case BLOCK_CHERRY_WOOD_HANGING_SIGN_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.CHERRY_HANGING_SIGN);
					break;
				case BLOCK_CHERRY_WOOD_HANGING_SIGN_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.CHERRY_HANGING_SIGN);
					break;
				case BLOCK_CHERRY_WOOD_HANGING_SIGN_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.CHERRY_HANGING_SIGN);
					break;
				case BLOCK_CHERRY_WOOD_HANGING_SIGN_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.CHERRY_HANGING_SIGN);
					break;
				case BLOCK_CHERRY_WOOD_HANGING_SIGN_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.CHERRY_HANGING_SIGN);
					break;
				case BLOCK_CHERRY_WOOD_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.CHERRY_WOOD);
					break;
				case BLOCK_CHERRY_WOOD_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.CHERRY_WOOD);
					break;
				case BLOCK_CHERRY_WOOD_PRESSURE_PLATE_CLICK_OFF:
					SlotPlugin.soundMaterialMap.put(sound, Material.CHERRY_PRESSURE_PLATE);
					break;
				case BLOCK_CHERRY_WOOD_PRESSURE_PLATE_CLICK_ON:
					SlotPlugin.soundMaterialMap.put(sound, Material.CHERRY_PRESSURE_PLATE);
					break;
				case BLOCK_CHERRY_WOOD_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.CHERRY_WOOD);
					break;
				case BLOCK_CHERRY_WOOD_TRAPDOOR_CLOSE:
					SlotPlugin.soundMaterialMap.put(sound, Material.CHERRY_TRAPDOOR);
					break;
				case BLOCK_CHERRY_WOOD_TRAPDOOR_OPEN:
					SlotPlugin.soundMaterialMap.put(sound, Material.CHERRY_TRAPDOOR);
					break;
				case BLOCK_CHISELED_BOOKSHELF_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.CHISELED_BOOKSHELF);
					break;
				case BLOCK_CHISELED_BOOKSHELF_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.CHISELED_BOOKSHELF);
					break;
				case BLOCK_CHISELED_BOOKSHELF_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.CHISELED_BOOKSHELF);
					break;
				case BLOCK_CHISELED_BOOKSHELF_INSERT:
					SlotPlugin.soundMaterialMap.put(sound, Material.CHISELED_BOOKSHELF);
					break;
				case BLOCK_CHISELED_BOOKSHELF_INSERT_ENCHANTED:
					SlotPlugin.soundMaterialMap.put(sound, Material.CHISELED_BOOKSHELF);
					break;
				case BLOCK_CHISELED_BOOKSHELF_PICKUP:
					SlotPlugin.soundMaterialMap.put(sound, Material.CHISELED_BOOKSHELF);
					break;
				case BLOCK_CHISELED_BOOKSHELF_PICKUP_ENCHANTED:
					SlotPlugin.soundMaterialMap.put(sound, Material.CHISELED_BOOKSHELF);
					break;
				case BLOCK_CHISELED_BOOKSHELF_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.CHISELED_BOOKSHELF);
					break;
				case BLOCK_CHISELED_BOOKSHELF_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.CHISELED_BOOKSHELF);
					break;
				case BLOCK_DECORATED_POT_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.DECORATED_POT);
					break;
				case BLOCK_DECORATED_POT_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.DECORATED_POT);
					break;
				case BLOCK_DECORATED_POT_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.DECORATED_POT);
					break;
				case BLOCK_DECORATED_POT_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.DECORATED_POT);
					break;
				case BLOCK_DECORATED_POT_SHATTER:
					SlotPlugin.soundMaterialMap.put(sound, Material.DECORATED_POT);
					break;
				case BLOCK_DECORATED_POT_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.DECORATED_POT);
					break;
				case BLOCK_HANGING_SIGN_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.OAK_HANGING_SIGN);
					break;
				case BLOCK_HANGING_SIGN_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.OAK_HANGING_SIGN);
					break;
				case BLOCK_HANGING_SIGN_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.OAK_HANGING_SIGN);
					break;
				case BLOCK_HANGING_SIGN_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.OAK_HANGING_SIGN);
					break;
				case BLOCK_HANGING_SIGN_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.OAK_HANGING_SIGN);
					break;
				case BLOCK_NETHER_WOOD_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.CRIMSON_PLANKS);
					break;
				case BLOCK_NETHER_WOOD_BUTTON_CLICK_OFF:
					SlotPlugin.soundMaterialMap.put(sound, Material.CRIMSON_BUTTON);
					break;
				case BLOCK_NETHER_WOOD_BUTTON_CLICK_ON:
					SlotPlugin.soundMaterialMap.put(sound, Material.CRIMSON_BUTTON);
					break;
				case BLOCK_NETHER_WOOD_DOOR_CLOSE:
					SlotPlugin.soundMaterialMap.put(sound, Material.CRIMSON_DOOR);
					break;
				case BLOCK_NETHER_WOOD_DOOR_OPEN:
					SlotPlugin.soundMaterialMap.put(sound, Material.CRIMSON_DOOR);
					break;
				case BLOCK_NETHER_WOOD_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.CRIMSON_PLANKS);
					break;
				case BLOCK_NETHER_WOOD_FENCE_GATE_CLOSE:
					SlotPlugin.soundMaterialMap.put(sound, Material.CRIMSON_FENCE_GATE);
					break;
				case BLOCK_NETHER_WOOD_FENCE_GATE_OPEN:
					SlotPlugin.soundMaterialMap.put(sound, Material.CRIMSON_FENCE_GATE);
					break;
				case BLOCK_NETHER_WOOD_HANGING_SIGN_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.CRIMSON_HANGING_SIGN);
					break;
				case BLOCK_NETHER_WOOD_HANGING_SIGN_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.CRIMSON_HANGING_SIGN);
					break;
				case BLOCK_NETHER_WOOD_HANGING_SIGN_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.CRIMSON_HANGING_SIGN);
					break;
				case BLOCK_NETHER_WOOD_HANGING_SIGN_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.CRIMSON_HANGING_SIGN);
					break;
				case BLOCK_NETHER_WOOD_HANGING_SIGN_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.CRIMSON_HANGING_SIGN);
					break;
				case BLOCK_NETHER_WOOD_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.CRIMSON_PLANKS);
					break;
				case BLOCK_NETHER_WOOD_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.CRIMSON_PLANKS);
					break;
				case BLOCK_NETHER_WOOD_PRESSURE_PLATE_CLICK_OFF:
					SlotPlugin.soundMaterialMap.put(sound, Material.CRIMSON_PRESSURE_PLATE);
					break;
				case BLOCK_NETHER_WOOD_PRESSURE_PLATE_CLICK_ON:
					SlotPlugin.soundMaterialMap.put(sound, Material.CRIMSON_PRESSURE_PLATE);
					break;
				case BLOCK_NETHER_WOOD_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.CRIMSON_PLANKS);
					break;
				case BLOCK_NETHER_WOOD_TRAPDOOR_CLOSE:
					SlotPlugin.soundMaterialMap.put(sound, Material.CRIMSON_TRAPDOOR);
					break;
				case BLOCK_NETHER_WOOD_TRAPDOOR_OPEN:
					SlotPlugin.soundMaterialMap.put(sound, Material.CRIMSON_TRAPDOOR);
					break;
				case BLOCK_NOTE_BLOCK_IMITATE_CREEPER:
					SlotPlugin.soundMaterialMap.put(sound, Material.CREEPER_HEAD);
					break;
				case BLOCK_NOTE_BLOCK_IMITATE_ENDER_DRAGON:
					SlotPlugin.soundMaterialMap.put(sound, Material.DRAGON_HEAD);
					break;
				case BLOCK_NOTE_BLOCK_IMITATE_PIGLIN:
					SlotPlugin.soundMaterialMap.put(sound, Material.PIGLIN_HEAD);
					break;
				case BLOCK_NOTE_BLOCK_IMITATE_SKELETON:
					SlotPlugin.soundMaterialMap.put(sound, Material.SKELETON_SKULL);
					break;
				case BLOCK_NOTE_BLOCK_IMITATE_WITHER_SKELETON:
					SlotPlugin.soundMaterialMap.put(sound, Material.WITHER_SKELETON_SKULL);
					break;
				case BLOCK_NOTE_BLOCK_IMITATE_ZOMBIE:
					SlotPlugin.soundMaterialMap.put(sound, Material.ZOMBIE_HEAD);
					break;
				case BLOCK_PINK_PETALS_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.PINK_PETALS);
					break;
				case BLOCK_PINK_PETALS_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.PINK_PETALS);
					break;
				case BLOCK_PINK_PETALS_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.PINK_PETALS);
					break;
				case BLOCK_PINK_PETALS_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.PINK_PETALS);
					break;
				case BLOCK_PINK_PETALS_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.PINK_PETALS);
					break;
				case BLOCK_SIGN_WAXED_INTERACT_FAIL:
					SlotPlugin.soundMaterialMap.put(sound, Material.BAMBOO_SIGN);
					break;
				case BLOCK_SNIFFER_EGG_CRACK:
					SlotPlugin.soundMaterialMap.put(sound, Material.SNIFFER_EGG);
					break;
				case BLOCK_SNIFFER_EGG_HATCH:
					SlotPlugin.soundMaterialMap.put(sound, Material.SNIFFER_EGG);
					break;
				case BLOCK_SNIFFER_EGG_PLOP:
					SlotPlugin.soundMaterialMap.put(sound, Material.SNIFFER_EGG);
					break;
				case BLOCK_SUSPICIOUS_GRAVEL_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.SUSPICIOUS_GRAVEL);
					break;
				case BLOCK_SUSPICIOUS_GRAVEL_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.SUSPICIOUS_GRAVEL);
					break;
				case BLOCK_SUSPICIOUS_GRAVEL_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.SUSPICIOUS_GRAVEL);
					break;
				case BLOCK_SUSPICIOUS_GRAVEL_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.SUSPICIOUS_GRAVEL);
					break;
				case BLOCK_SUSPICIOUS_GRAVEL_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.SUSPICIOUS_GRAVEL);
					break;
				case BLOCK_SUSPICIOUS_SAND_BREAK:
					SlotPlugin.soundMaterialMap.put(sound, Material.SUSPICIOUS_SAND);
					break;
				case BLOCK_SUSPICIOUS_SAND_FALL:
					SlotPlugin.soundMaterialMap.put(sound, Material.SUSPICIOUS_SAND);
					break;
				case BLOCK_SUSPICIOUS_SAND_HIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.SUSPICIOUS_SAND);
					break;
				case BLOCK_SUSPICIOUS_SAND_PLACE:
					SlotPlugin.soundMaterialMap.put(sound, Material.SUSPICIOUS_SAND);
					break;
				case BLOCK_SUSPICIOUS_SAND_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.SUSPICIOUS_SAND);
					break;
				case ENTITY_CAMEL_AMBIENT:
					SlotPlugin.soundMaterialMap.put(sound, Material.CAMEL_SPAWN_EGG);
					break;
				case ENTITY_CAMEL_DASH:
					SlotPlugin.soundMaterialMap.put(sound, Material.CAMEL_SPAWN_EGG);
					break;
				case ENTITY_CAMEL_DASH_READY:
					SlotPlugin.soundMaterialMap.put(sound, Material.CAMEL_SPAWN_EGG);
					break;
				case ENTITY_CAMEL_DEATH:
					SlotPlugin.soundMaterialMap.put(sound, Material.CAMEL_SPAWN_EGG);
					break;
				case ENTITY_CAMEL_EAT:
					SlotPlugin.soundMaterialMap.put(sound, Material.CAMEL_SPAWN_EGG);
					break;
				case ENTITY_CAMEL_HURT:
					SlotPlugin.soundMaterialMap.put(sound, Material.CAMEL_SPAWN_EGG);
					break;
				case ENTITY_CAMEL_SADDLE:
					SlotPlugin.soundMaterialMap.put(sound, Material.CAMEL_SPAWN_EGG);
					break;
				case ENTITY_CAMEL_SIT:
					SlotPlugin.soundMaterialMap.put(sound, Material.CAMEL_SPAWN_EGG);
					break;
				case ENTITY_CAMEL_STAND:
					SlotPlugin.soundMaterialMap.put(sound, Material.CAMEL_SPAWN_EGG);
					break;
				case ENTITY_CAMEL_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.CAMEL_SPAWN_EGG);
					break;
				case ENTITY_CAMEL_STEP_SAND:
					SlotPlugin.soundMaterialMap.put(sound, Material.CAMEL_SPAWN_EGG);
					break;
				case ENTITY_SNIFFER_DEATH:
					SlotPlugin.soundMaterialMap.put(sound, Material.SNIFFER_SPAWN_EGG);
					break;
				case ENTITY_SNIFFER_DIGGING:
					SlotPlugin.soundMaterialMap.put(sound, Material.SNIFFER_SPAWN_EGG);
					break;
				case ENTITY_SNIFFER_DIGGING_STOP:
					SlotPlugin.soundMaterialMap.put(sound, Material.SNIFFER_SPAWN_EGG);
					break;
				case ENTITY_SNIFFER_DROP_SEED:
					SlotPlugin.soundMaterialMap.put(sound, Material.SNIFFER_SPAWN_EGG);
					break;
				case ENTITY_SNIFFER_EAT:
					SlotPlugin.soundMaterialMap.put(sound, Material.SNIFFER_SPAWN_EGG);
					break;
				case ENTITY_SNIFFER_HAPPY:
					SlotPlugin.soundMaterialMap.put(sound, Material.SNIFFER_SPAWN_EGG);
					break;
				case ENTITY_SNIFFER_HURT:
					SlotPlugin.soundMaterialMap.put(sound, Material.SNIFFER_SPAWN_EGG);
					break;
				case ENTITY_SNIFFER_IDLE:
					SlotPlugin.soundMaterialMap.put(sound, Material.SNIFFER_SPAWN_EGG);
					break;
				case ENTITY_SNIFFER_SCENTING:
					SlotPlugin.soundMaterialMap.put(sound, Material.SNIFFER_SPAWN_EGG);
					break;
				case ENTITY_SNIFFER_SEARCHING:
					SlotPlugin.soundMaterialMap.put(sound, Material.SNIFFER_SPAWN_EGG);
					break;
				case ENTITY_SNIFFER_SNIFFING:
					SlotPlugin.soundMaterialMap.put(sound, Material.SNIFFER_SPAWN_EGG);
					break;
				case ENTITY_SNIFFER_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.SNIFFER_SPAWN_EGG);
					break;
				case INTENTIONALLY_EMPTY:
					SlotPlugin.soundMaterialMap.put(sound, Material.DIRT);
					break;
				case ITEM_BRUSH_BRUSHING_GENERIC:
					SlotPlugin.soundMaterialMap.put(sound, Material.BRUSH);
					break;
				case ITEM_BRUSH_BRUSHING_GRAVEL:
					SlotPlugin.soundMaterialMap.put(sound, Material.BRUSH);
					break;
				case ITEM_BRUSH_BRUSHING_GRAVEL_COMPLETE:
					SlotPlugin.soundMaterialMap.put(sound, Material.BRUSH);
					break;
				case ITEM_BRUSH_BRUSHING_SAND:
					SlotPlugin.soundMaterialMap.put(sound, Material.BRUSH);
					break;
				case ITEM_BRUSH_BRUSHING_SAND_COMPLETE:
					SlotPlugin.soundMaterialMap.put(sound, Material.BRUSH);
					break;
				case MUSIC_DISC_RELIC:
					SlotPlugin.soundMaterialMap.put(sound, Material.MUSIC_DISC_RELIC);
					break;
				case MUSIC_OVERWORLD_BADLANDS:
					SlotPlugin.soundMaterialMap.put(sound, Material.NOTE_BLOCK);
					break;
				case MUSIC_OVERWORLD_BAMBOO_JUNGLE:
					SlotPlugin.soundMaterialMap.put(sound, Material.NOTE_BLOCK);
					break;
				case MUSIC_OVERWORLD_CHERRY_GROVE:
					SlotPlugin.soundMaterialMap.put(sound, Material.NOTE_BLOCK);
					break;
				case MUSIC_OVERWORLD_DESERT:
					SlotPlugin.soundMaterialMap.put(sound, Material.NOTE_BLOCK);
					break;
				case MUSIC_OVERWORLD_FLOWER_FOREST:
					SlotPlugin.soundMaterialMap.put(sound, Material.NOTE_BLOCK);
					break;
				case MUSIC_OVERWORLD_FOREST:
					SlotPlugin.soundMaterialMap.put(sound, Material.NOTE_BLOCK);
					break;
				case MUSIC_OVERWORLD_JUNGLE:
					SlotPlugin.soundMaterialMap.put(sound, Material.NOTE_BLOCK);
					break;
				case MUSIC_OVERWORLD_SPARSE_JUNGLE:
					SlotPlugin.soundMaterialMap.put(sound, Material.NOTE_BLOCK);
					break;
                case BLOCK_COPPER_TRAPDOOR_CLOSE:
                case BLOCK_COPPER_TRAPDOOR_OPEN:
					SlotPlugin.soundMaterialMap.put(sound, Material.COPPER_TRAPDOOR);
                    break;
                case BLOCK_CRAFTER_CRAFT:
                case BLOCK_CRAFTER_FAIL:
					SlotPlugin.soundMaterialMap.put(sound, Material.CRAFTER);
                    break;
                case BLOCK_DECORATED_POT_INSERT:
                case BLOCK_DECORATED_POT_INSERT_FAIL:
					SlotPlugin.soundMaterialMap.put(sound, Material.DECORATED_POT);
                    break;
                case BLOCK_HANGING_SIGN_WAXED_INTERACT_FAIL:
					SlotPlugin.soundMaterialMap.put(sound, Material.OAK_HANGING_SIGN);
                    break;
                case BLOCK_POLISHED_TUFF_BREAK:
                case BLOCK_POLISHED_TUFF_FALL:
                case BLOCK_POLISHED_TUFF_HIT:
                case BLOCK_POLISHED_TUFF_PLACE:
                case BLOCK_POLISHED_TUFF_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.POLISHED_TUFF);
                    break;
                case BLOCK_SPONGE_ABSORB:
                case BLOCK_SPONGE_BREAK:
                case BLOCK_SPONGE_FALL:
                case BLOCK_SPONGE_HIT:
                case BLOCK_SPONGE_PLACE:
                case BLOCK_SPONGE_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.SPONGE);
                    break;
                /*case BLOCK_TRIAL_SPAWNER_AMBIENT_OMINOUS:
                case BLOCK_TRIAL_SPAWNER_OMINOUS_ACTIVATE:
                case BLOCK_TRIAL_SPAWNER_ABOUT_TO_SPAWN_ITEM:
                case BLOCK_TRIAL_SPAWNER_SPAWN_ITEM:
                case BLOCK_TRIAL_SPAWNER_SPAWN_ITEM_BEGIN:*/
                case BLOCK_TRIAL_SPAWNER_AMBIENT:
                case BLOCK_TRIAL_SPAWNER_BREAK:
                case BLOCK_TRIAL_SPAWNER_CLOSE_SHUTTER:
                case BLOCK_TRIAL_SPAWNER_DETECT_PLAYER:
                case BLOCK_TRIAL_SPAWNER_EJECT_ITEM:
                case BLOCK_TRIAL_SPAWNER_FALL:
                case BLOCK_TRIAL_SPAWNER_HIT:
                case BLOCK_TRIAL_SPAWNER_OPEN_SHUTTER:
                case BLOCK_TRIAL_SPAWNER_PLACE:
                case BLOCK_TRIAL_SPAWNER_SPAWN_MOB:
                case BLOCK_TRIAL_SPAWNER_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.TRIAL_SPAWNER);
                    break;
                case BLOCK_TUFF_BRICKS_BREAK:
                case BLOCK_TUFF_BRICKS_FALL:
                case BLOCK_TUFF_BRICKS_HIT:
                case BLOCK_TUFF_BRICKS_PLACE:
                case BLOCK_TUFF_BRICKS_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.TUFF_BRICKS);
                    break;
                case BLOCK_WET_SPONGE_BREAK:
                case BLOCK_WET_SPONGE_FALL:
                case BLOCK_WET_SPONGE_HIT:
                case BLOCK_WET_SPONGE_PLACE:
                case BLOCK_WET_SPONGE_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.WET_SPONGE);
                    break;
                case ENTITY_BREEZE_DEATH:
                case ENTITY_BREEZE_HURT:
                case ENTITY_BREEZE_IDLE_AIR:
                case ENTITY_BREEZE_IDLE_GROUND:
                case ENTITY_BREEZE_INHALE:
                case ENTITY_BREEZE_JUMP:
                case ENTITY_BREEZE_LAND:
                case ENTITY_BREEZE_SHOOT:
                case ENTITY_BREEZE_SLIDE:
					SlotPlugin.soundMaterialMap.put(sound, Material.BREEZE_SPAWN_EGG);
                    break;
                case ENTITY_PARROT_IMITATE_BREEZE:
					SlotPlugin.soundMaterialMap.put(sound, Material.PARROT_SPAWN_EGG);
                    break;
                case ENTITY_PLAYER_TELEPORT:
					SlotPlugin.soundMaterialMap.put(sound, Material.PLAYER_HEAD);
                    break;
                /*case BLOCK_VAULT_REJECT_REWARDED_PLAYER:
                case BLOCK_VAULT_ACTIVATE:
                case BLOCK_VAULT_AMBIENT:
                case BLOCK_VAULT_BREAK:
                case BLOCK_VAULT_CLOSE_SHUTTER:
                case BLOCK_VAULT_DEACTIVATE:
                case BLOCK_VAULT_EJECT_ITEM:
                case BLOCK_VAULT_FALL:
                case BLOCK_VAULT_HIT:
                case BLOCK_VAULT_INSERT_ITEM:
                case BLOCK_VAULT_INSERT_ITEM_FAIL:
                case BLOCK_VAULT_OPEN_SHUTTER:
                case BLOCK_VAULT_PLACE:
                case BLOCK_VAULT_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.VAULT);
                    break;
                case BLOCK_WET_SPONGE_DRIES:
					SlotPlugin.soundMaterialMap.put(sound, Material.SPONGE);
                    break;
                case ENTITY_ARMADILLO_AMBIENT:
                case ENTITY_ARMADILLO_BRUSH:
                case ENTITY_ARMADILLO_DEATH:
                case ENTITY_ARMADILLO_EAT:
                case ENTITY_ARMADILLO_HURT:
                case ENTITY_ARMADILLO_HURT_REDUCED:
                case ENTITY_ARMADILLO_LAND:
                case ENTITY_ARMADILLO_PEEK:
                case ENTITY_ARMADILLO_ROLL:
                case ENTITY_ARMADILLO_SCUTE_DROP:
                case ENTITY_ARMADILLO_STEP:
                case ENTITY_ARMADILLO_UNROLL_FINISH:
                case ENTITY_ARMADILLO_UNROLL_START:
					SlotPlugin.soundMaterialMap.put(sound, Material.ARMADILLO_SPAWN_EGG);
                    break;
                case ENTITY_BOGGED_AMBIENT:
                case ENTITY_BOGGED_DEATH:
                case ENTITY_BOGGED_HURT:
                case ENTITY_BOGGED_SHEAR:
                case ENTITY_BOGGED_STEP:
					SlotPlugin.soundMaterialMap.put(sound, Material.BOGGED_SPAWN_EGG);
                    break;
                case ENTITY_BREEZE_CHARGE:
                case ENTITY_BREEZE_DEFLECT:
                case ENTITY_BREEZE_WHIRL:
                case ENTITY_BREEZE_WIND_BURST:
					SlotPlugin.soundMaterialMap.put(sound, Material.BREEZE_SPAWN_EGG);
                    break;
                case ENTITY_DONKEY_JUMP:
					SlotPlugin.soundMaterialMap.put(sound, Material.DONKEY_SPAWN_EGG);
                    break;
                case ENTITY_MULE_JUMP:
					SlotPlugin.soundMaterialMap.put(sound, Material.MULE_SPAWN_EGG);
                    break;
                case ENTITY_PARROT_IMITATE_BOGGED:
					SlotPlugin.soundMaterialMap.put(sound, Material.PARROT_SPAWN_EGG);
                    break;
                case ENTITY_WIND_CHARGE_THROW:
                case ENTITY_WIND_CHARGE_WIND_BURST:
					SlotPlugin.soundMaterialMap.put(sound, Material.WIND_CHARGE);
                    break;
                case EVENT_MOB_EFFECT_BAD_OMEN:
                case EVENT_MOB_EFFECT_RAID_OMEN:
                case EVENT_MOB_EFFECT_TRIAL_OMEN:
					SlotPlugin.soundMaterialMap.put(sound, Material.TOTEM_OF_UNDYING);
                    break;
                case ITEM_ARMOR_EQUIP_WOLF:
                case ITEM_ARMOR_UNEQUIP_WOLF:
					SlotPlugin.soundMaterialMap.put(sound, Material.WOLF_ARMOR);
                    break;
                case ITEM_MACE_SMASH_AIR:
                case ITEM_MACE_SMASH_GROUND:
                case ITEM_MACE_SMASH_GROUND_HEAVY:
					SlotPlugin.soundMaterialMap.put(sound, Material.MACE);
                    break;
                case ITEM_OMINOUS_BOTTLE_DISPOSE:
					SlotPlugin.soundMaterialMap.put(sound, Material.OMINOUS_BOTTLE);
                    break;
                case ITEM_WOLF_ARMOR_BREAK:
                case ITEM_WOLF_ARMOR_CRACK:
                case ITEM_WOLF_ARMOR_DAMAGE:
                case ITEM_WOLF_ARMOR_REPAIR:
					SlotPlugin.soundMaterialMap.put(sound, Material.WOLF_ARMOR);
                    break;
                case MUSIC_DISC_CREATOR:
					SlotPlugin.soundMaterialMap.put(sound, Material.MUSIC_DISC_CREATOR);
                    break;
                case MUSIC_DISC_CREATOR_MUSIC_BOX:
					SlotPlugin.soundMaterialMap.put(sound, Material.MUSIC_DISC_CREATOR_MUSIC_BOX);
                    break;
                case MUSIC_DISC_PRECIPICE:
					SlotPlugin.soundMaterialMap.put(sound, Material.MUSIC_DISC_PRECIPICE);
                    break;*/
            }
		}
	}
}
