package fr.klemms.slotmachine;

import fr.klemms.slotmachine.fr.minuskube.inv.InventoryListener;
import fr.klemms.slotmachine.fr.minuskube.inv.SmartInventory;
import fr.klemms.slotmachine.fr.minuskube.inv.content.InventoryProvider;
import fr.klemms.slotmachine.layouts.CSGOLayout;
import fr.klemms.slotmachine.layouts.CSGOVerticalLayout;
import fr.klemms.slotmachine.layouts.SlotMachineLayout;
import fr.klemms.slotmachine.tokens.Token;
import fr.klemms.slotmachine.translation.Language;
import fr.klemms.slotmachine.utils.ItemStackUtil;
import fr.klemms.slotmachine.utils.sounds.SSound;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;

public abstract class SlotMachine {

	public static volatile List<SlotMachine> slotMachines = new ArrayList<SlotMachine>();

	/**
	 * Returns true if the player is rolling in a machine with the 'LIMITED_PLAYER_GLOBAL' play mode
	 * @param player
	 * @return
	 */
	public static boolean isPlayerRollingGlobally(Player player) {
		for (SlotMachine sm : SlotMachine.getSlotMachines()) {
			if (sm.getPlayMode() == PlayMode.LIMITED_PLAYER_GLOBAL && sm.isPlayerRolling(player))
				return true;
		}

		return false;
	}

	public static synchronized SlotMachine getSlotMachineByUUID(UUID machineUUID) {
		for(SlotMachine slotMachine : slotMachines) {
			if(slotMachine.getMachineUUID().compareTo(machineUUID) == 0) {
				return slotMachine;
			}
		}
		return null;
	}

	public static synchronized void addSlotMachine(SlotMachine slotMachineToAdd) {
		if (SlotMachine.getSlotMachineByUUID(slotMachineToAdd.getMachineUUID()) == null) {
			slotMachines.add(slotMachineToAdd);
		} else {
			SlotPlugin.pl.getLogger().log(Level.SEVERE, "Slot Machine " + slotMachineToAdd.getMachineUUID().toString() + " is duplicated ! Ignoring this one...");
			Issue.newIssue(Issue.IssueType.MACHINE_READING_ISSUE, "Slot Machine " + slotMachineToAdd.getMachineUUID().toString() + " is duplicated ! Ignoring this one...", true);
		}
	}

	public static synchronized void removeSlotMachine(SlotMachine slotMachineToRemove) {
		if(slotMachineToRemove != null) {
			slotMachines.remove(slotMachineToRemove);
		}
	}

	public static synchronized void removeSlotMachine(UUID machineUUID) {
		removeSlotMachine(getSlotMachineByUUID(machineUUID));
	}

	public static synchronized int getSlotMachineCount() {
		return slotMachines.size();
	}

	public static synchronized List<SlotMachine> getSlotMachines() {
		return slotMachines;
	}

	/**
	 * Get all machines that are not LINKs
	 * @return A SlotMachine List
	 */
	public static synchronized @NotNull List<SlotMachine> getOriginalSlotMachines() {
		List<SlotMachine> returnMachines = new ArrayList<SlotMachine>();

		for (SlotMachine slotMachine : slotMachines) {
			if (slotMachine.getSlotMachineType() == SlotMachineType.BLOCK || slotMachine.getSlotMachineType() == SlotMachineType.ENTITY) {
				returnMachines.add(slotMachine);
			}
		}

		return returnMachines;
	}

	/**
	 * Get all machines that are LINKs
	 * @return A SlotMachine List
	 */
	public static synchronized @NotNull List<SlotMachine> getLinks() {
		List<SlotMachine> returnMachines = new ArrayList<SlotMachine>();

		for (SlotMachine slotMachine : slotMachines) {
			if (slotMachine.getSlotMachineType() == SlotMachineType.BLOCK_LINK || slotMachine.getSlotMachineType() == SlotMachineType.ENTITY_LINK) {
				returnMachines.add(slotMachine);
			}
		}

		return returnMachines;
	}

	public static synchronized @NotNull List<SlotMachine> getAllSlotMachineEntities() {
		List<SlotMachine> returnMachines = new ArrayList<SlotMachine>();

		for (SlotMachine slotMachine : slotMachines) {
			if (slotMachine.getSlotMachineType() == SlotMachineType.ENTITY || slotMachine.getSlotMachineType() == SlotMachineType.ENTITY_LINK) {
				returnMachines.add(slotMachine);
			}
		}

		return returnMachines;
	}

	public static synchronized @NotNull List<SlotMachine> getAllSlotMachineBlocks() {
		List<SlotMachine> returnMachines = new ArrayList<SlotMachine>();

		for (SlotMachine slotMachine : slotMachines) {
			if (slotMachine.getSlotMachineType() == SlotMachineType.BLOCK || slotMachine.getSlotMachineType() == SlotMachineType.BLOCK_LINK) {
				returnMachines.add(slotMachine);
			}
		}

		return returnMachines;
	}

	public static synchronized @NotNull List<SlotMachine> getSlotMachinesByType(SlotMachineType slotMachineType) {
		List<SlotMachine> typeSlotMachines = new ArrayList<SlotMachine>();
		for(SlotMachine slotMachine : slotMachines) {
			if(slotMachine.getSlotMachineType() == slotMachineType) {
				typeSlotMachines.add(slotMachine);
			}
		}
		return typeSlotMachines;
	}

	private SlotMachineType slotMachineType;
	private SmartInventory inventory;
	private UUID machineUUID;
	private String guiPermission;
	private String slotMachineName;
	private String winMessage;
	private boolean hasWinMessage;
	private String lossMessage;
	private boolean hasLossMessage;
	private String leverTitle;
	private String leverDescription;
	private VisualType visualType;
	private PriceType priceType;
	private PlayMode playMode;
	private double pullPrice;
	private double chanceToWin;
	private boolean isLeverCustom;
	private boolean isAffectedByLuck;
	private boolean allowContentPreview;
	private boolean showItemWeightOnPreview;
	private boolean showChanceOfItemOnPreview;
	private boolean isCitizensNPC;
	private boolean displayWonItemInChat;
	private boolean broadcastWonItem;
	private boolean needsSaving;
	private int secondsBeforePrize;
	private int spinSpeed;
	private int timesUsed;
	private int cooldown;
	private List<MachineItem> slotMachineItems;
	private String tokenIdentifier;
	private HashMap<UUID, Boolean> isRolling;
	private HashMap<UUID, List<MachineItem>> row_0 = new HashMap<UUID, List<MachineItem>>();
	private HashMap<UUID, List<MachineItem>> row_1 = new HashMap<UUID, List<MachineItem>>();
	private HashMap<UUID, List<MachineItem>> row_2 = new HashMap<UUID, List<MachineItem>>();

	private ItemStack backgroundItem;
	private ItemStack emphasisItem;
	private ItemStack leverItem;
	private ItemStack leverItemActivated;
	private ItemStack itemListItem;

	private SSound machineOpeningSound;
	private SSound leverSound;
	private SSound slotmachineSpinSound;
	private SSound errorSound;
	private SSound csgoSpinSound;
	private SSound winSound;
	private SSound lossSound;

	private String lastFileName;
	private String coinsEngineCurrencyName;

	public SlotMachine(SlotMachineType slotMachineType) {
		this.slotMachineType = slotMachineType;
		this.machineUUID = UUID.randomUUID();
		this.visualType = VisualType.SLOTMACHINE;
		this.priceType = PriceType.MONEY;
		this.playMode = PlayMode.LIMITED_PLAYER;
		this.tokenIdentifier = "default";
		this.lastFileName = "";
		this.pullPrice = 10D;
		this.chanceToWin = 0.40D;
		this.secondsBeforePrize = 3;
		this.isAffectedByLuck = false;
		this.isLeverCustom = false;
		this.allowContentPreview = true;
		this.showItemWeightOnPreview = true;
		this.showChanceOfItemOnPreview = true;
		this.winMessage = "";
		this.hasWinMessage = true;
		this.lossMessage = "";
		this.hasLossMessage = true;
		this.displayWonItemInChat = true;
		this.broadcastWonItem = false;
		this.spinSpeed = 6;
		this.timesUsed = 0;
		this.cooldown = 0;
		this.needsSaving = true;
		this.setPriceType(this.getPriceType());
		this.setSlotMachineName("Slot Machine");
		this.guiPermission = "slotmachine.access.default";
		this.slotMachineItems = new ArrayList<MachineItem>();
		this.isRolling = new HashMap<UUID, Boolean>();
		this.isCitizensNPC = false;
		this.coinsEngineCurrencyName = null;

		this.resetBackgroundCustomization();
		this.resetSoundCustomization();

		if(SlotPlugin.econ == null) {
			this.setPriceType(PriceType.TOKEN);
		}
		this.makeInventory();
	}

	public void resetBackgroundCustomization() {
		this.backgroundItem = new ItemStack(Material.BLUE_STAINED_GLASS_PANE, 1);
		this.emphasisItem = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE, 1);
		this.leverItem = new ItemStack(Material.TRIPWIRE_HOOK, 1);
		this.leverItemActivated = new ItemStack(Material.TRIPWIRE_HOOK, 1);
		this.itemListItem = new ItemStack(Material.ENDER_CHEST, 1);
	}

	public void resetSoundCustomization() {
		this.machineOpeningSound = new SSound(Sound.ENTITY_EXPERIENCE_ORB_PICKUP);
		this.leverSound = new SSound(Sound.BLOCK_LAVA_POP);
		this.slotmachineSpinSound = new SSound(Sound.ENTITY_EXPERIENCE_ORB_PICKUP);
		this.errorSound = new SSound(Sound.ENTITY_VILLAGER_NO);
		this.csgoSpinSound = new SSound(Sound.ENTITY_HORSE_STEP_WOOD);
		this.winSound = new SSound(Sound.ENTITY_PLAYER_LEVELUP);
		this.lossSound = new SSound(Sound.BLOCK_ANVIL_LAND);
	}

	public void resetSoundMachineOpening() {
		this.machineOpeningSound = new SSound(Sound.ENTITY_EXPERIENCE_ORB_PICKUP);
	}

	public void resetSoundLever() {
		this.leverSound = new SSound(Sound.BLOCK_LAVA_POP);
	}

	public void resetSoundSlotMachineSpin() {
		this.slotmachineSpinSound = new SSound(Sound.ENTITY_EXPERIENCE_ORB_PICKUP);
	}

	public void resetSoundCSGOSpin() {
		this.csgoSpinSound = new SSound(Sound.ENTITY_HORSE_STEP_WOOD);
	}

	public void resetSoundWin() {
		this.winSound = new SSound(Sound.ENTITY_PLAYER_LEVELUP);
	}

	public void resetSoundLoss() {
		this.lossSound = new SSound(Sound.BLOCK_ANVIL_LAND);
	}

	public void resetSoundError() {
		this.errorSound = new SSound(Sound.ENTITY_VILLAGER_NO);
	}

	public boolean canPlay(Player player) {
		switch (this.getPlayMode()) {
			case LIMITED_MACHINE:
				return !this.hasSomeoneRolling();
			case LIMITED_PLAYER:
				return !this.isPlayerRolling(player);
			case LIMITED_PLAYER_GLOBAL:
				return !SlotMachine.isPlayerRollingGlobally(player);
			case UNLIMITED:
				return true;
			default:
				return true;
		}
	}

	public void makeInventory() {
		InventoryProvider provider = null;

		switch(this.getVisualType()) {
			case CSGOWHEEL:
				provider = new CSGOLayout(this);
				break;
			case CSGOWHEEL_VERTICAL:
				provider = new CSGOVerticalLayout(this);
				break;
			case SLOTMACHINE:
				provider = new SlotMachineLayout(this);
				break;
			default:
				provider = new SlotMachineLayout(this);
		}

		String machineName = this.getSlotMachineName();

		/*if (player != null && SlotPlugin.isPlaceholderAPIEnabled) {
			machineName = PlaceholderAPI.setPlaceholders(player, machineName);
		}*/

		this.inventory = SmartInventory.builder()
				.manager(SlotPlugin.invManager)
				.title(machineName)
				.size(this.getVisualType().rows, this.getVisualType().columns)
				.provider(provider)
				.listener(new InventoryListener<InventoryCloseEvent>(InventoryCloseEvent.class, event -> {
					if (!event.getPlayer().hasMetadata("slotmachine_soundremovalprevention")) {
						if (event.getPlayer() instanceof Player) {
							Player pl = (Player)event.getPlayer();

							pl.stopSound(this.getMachineOpeningSound().getKey());
						}
					}
				}))
				.build();
	}

	public SmartInventory getInventories() {
		return this.inventory;
	}

	public void openMachine(Player player, boolean updateItems) {
		String machineName = this.getSlotMachineName();

		if (player != null && SlotPlugin.isPlaceholderAPIEnabled) {
			machineName = PlaceholderAPI.setPlaceholders(player, machineName);
		}

		if (updateItems) {
			this.row_0.put(player.getUniqueId(), this.createRandomItemPool(false));
			this.row_1.put(player.getUniqueId(), this.createRandomItemPool(false));
			this.row_2.put(player.getUniqueId(), this.createRandomItemPool(false));
		}
		this.inventory.setTitle(machineName);
		this.inventory.open(player);
	}

	public List<MachineItem> createRandomItemPool(boolean isLastRound) {
		List<MachineItem> poolList = new ArrayList<MachineItem>();
		if(this.getVisualType() == VisualType.SLOTMACHINE) {
			poolList.add(0, this.getRandomItemFromPool());
			poolList.add(1, this.getRandomItemFromPool());
			poolList.add(2, this.getRandomItemFromPool());
			if(poolList.get(0).equals(poolList.get(1)) && poolList.get(0).equals(poolList.get(2)) && isLastRound && this.getSlotMachineItems().size() > 1) {
				poolList.set(0, this.getRandomItemFromPool(poolList.get(0)));
				poolList.set(2, this.getRandomItemFromPool(poolList.get(2)));
			}
		} else if(this.getVisualType() == VisualType.CSGOWHEEL) {
			poolList.add(0, this.getRandomItemFromPool());
			poolList.add(1, this.getRandomItemFromPool());
			poolList.add(2, this.getRandomItemFromPool());
			poolList.add(3, this.getRandomItemFromPool());
			poolList.add(4, this.getRandomItemFromPool());
			poolList.add(5, this.getRandomItemFromPool());
			poolList.add(6, this.getRandomItemFromPool());
		} else if(this.getVisualType() == VisualType.CSGOWHEEL_VERTICAL) {
			poolList.add(0, this.getRandomItemFromPool());
			poolList.add(1, this.getRandomItemFromPool());
			poolList.add(2, this.getRandomItemFromPool());
			poolList.add(3, this.getRandomItemFromPool());
			poolList.add(4, this.getRandomItemFromPool());
			poolList.add(5, this.getRandomItemFromPool());
			poolList.add(6, this.getRandomItemFromPool());
		}
		return poolList;
	}

	public ItemStack getToken() {
		if (Config.tokens.get(this.getTokenIdentifier()) == null) {
			Token.getDefaultToken(); // This makes sure a default token always exists
			this.setTokenIdentifier("default");
			this.save();
		}

		return Config.tokens.get(this.getTokenIdentifier());
	}

	public double getItemChance(MachineItem item) {
		int totalWeight = getAllWeightCombined();

		return 1F / totalWeight * item.getWeight() * this.getChanceToWin();
	}

	public int getAllWeightCombined() {
		int weight = 0;

		for (MachineItem item : this.getSlotMachineItems()) {
			weight += item.getWeight();
		}

		return weight;
	}

	public boolean canAnItemBeWon() {
		for(MachineItem machineItem : this.getSlotMachineItems()) {
			if(machineItem.getWeight() > 0) {
				return true;
			}
		}

		return false;
	}

	public MachineItem getRandomItemFromPoolWithWeight() {
		List<MachineItem> itemListWithWeight = new ArrayList<MachineItem>();
		for(MachineItem machineItem : this.slotMachineItems) {
			for(int a = 0; a < machineItem.getWeight(); a++) {
				itemListWithWeight.add(machineItem);
			}
		}
		double random = ThreadLocalRandom.current().nextDouble(0D, 1D);
		double itemChance = 1D / itemListWithWeight.size();
		for(double a = 0; a < itemListWithWeight.size(); a++) {
			if(random >= (a * itemChance) && random <= (a * itemChance + itemChance)) {
				return itemListWithWeight.get((int)a);
			}
		}
		return null;
	}

	public MachineItem getRandomItemFromPool(MachineItem... itemsToExclude) {
		List<MachineItem> itemList = this.getSlotMachineItems(itemsToExclude);
		double random = ThreadLocalRandom.current().nextDouble(0D, 1D);
		double itemChance = 1D / itemList.size();
		for(double a = 0; a < itemList.size(); a++) {
			if(random >= (a * itemChance) && random <= (a * itemChance + itemChance)) {
				return itemList.get((int)a);
			}
		}

		MachineItem machineItem = new MachineItem(ItemStackUtil.changeItemStackName(new ItemStack(Material.NETHER_STAR, 1), ChatContent.RED + ChatContent.ITALIC + SlotPlugin.CHAT_PREFIX + "No item available to create an item pool."), 1);
		return machineItem;
	}

	public String getGuiPermission() {
		return guiPermission;
	}

	public SlotMachine setGuiPermission(String guiPermission) {
		this.guiPermission = guiPermission;
		return this;
	}

	public List<MachineItem> getSlotMachineItems(MachineItem... itemsToExclude) {
		if(itemsToExclude.length > 0) {
			List<MachineItem> finalItems = new ArrayList<MachineItem>(this.slotMachineItems);

			for(MachineItem mi : itemsToExclude) {
				finalItems.remove(mi);
			}

			return finalItems;
		}

		return slotMachineItems;
	}

	public boolean playerHasPermission(Player player) {
		return player.hasPermission(this.getGuiPermission()) || player.isOp() ||
                (
                        this.getGuiPermission().equals("slotmachine.access.default") &&
                        Config.noDefaultPermission
				);
	}

	public SlotMachine setSlotMachineItems(List<MachineItem> slotMachineItems) {
		this.slotMachineItems = slotMachineItems;
		return this;
	}

	public SlotMachine addSlotMachineItems(List<MachineItem> slotMachineItems) {
		for(int a = 0; a < slotMachineItems.size(); a++) {
			this.slotMachineItems.add(slotMachineItems.get(a));
		}
		return this;
	}

	public SlotMachine addItem(MachineItem item) {
		this.slotMachineItems.add(item);
		return this;
	}

	public SlotMachine removeItem(MachineItem item) {
		this.slotMachineItems.remove(item);
		return this;
	}

	public boolean isPlayerRolling(Player player) {
		return this.isRolling.containsKey(player.getUniqueId()) ? this.isRolling.get(player.getUniqueId()) : false;
	}

	public void setPlayerRolling(Player player, boolean rolling) {
		this.isRolling.put(player.getUniqueId(), rolling);
	}

	public boolean hasSomeoneRolling() {
		for (Boolean bool : this.isRolling.values()) {
			if (bool)
				return true;
		}

		return false;
	}

	public String getChatName() {
		return "[" + slotMachineName + "] ";
	}

	public String getSlotMachineName() {
		return slotMachineName;
	}

	public SlotMachine setSlotMachineName(String slotMachineName) {
		this.slotMachineName = slotMachineName;
		this.makeInventory();
		return this;
	}

	public double getPullPrice() {
		if(this.getPriceType() == PriceType.TOKEN) {
			return (int)pullPrice;
		}
		return pullPrice;
	}

	public SlotMachine setPullPrice(double pullPrice) {
		this.pullPrice = pullPrice;
		return this;
	}

	public double getChanceToWin() {
		return chanceToWin;
	}

	public SlotMachine setChanceToWin(double chanceToWin) {
		this.chanceToWin = chanceToWin;
		return this;
	}

	public int getSecondsBeforePrize() {
		return secondsBeforePrize;
	}

	public SlotMachine setSecondsBeforePrize(int secondsBeforePrize) {
		this.secondsBeforePrize = secondsBeforePrize;
		return this;
	}

	public HashMap<UUID, List<MachineItem>> getRow0() {
		return this.row_0;
	}

	public List<MachineItem> getPlayerRow0(Player player) {
		return this.row_0.get(player.getUniqueId());
	}

	public void setPlayerRow0(Player player, List<MachineItem> list) {
		this.row_0.put(player.getUniqueId(), list);
	}

	public HashMap<UUID, List<MachineItem>> getRow1() {
		return this.row_1;
	}

	public List<MachineItem> getPlayerRow1(Player player) {
		return this.row_1.get(player.getUniqueId());
	}

	public void setPlayerRow1(Player player, List<MachineItem> list) {
		this.row_1.put(player.getUniqueId(), list);
	}

	public HashMap<UUID, List<MachineItem>> getRow2() {
		return this.row_2;
	}

	public List<MachineItem> getPlayerRow2(Player player) {
		return this.row_2.get(player.getUniqueId());
	}

	public void setPlayerRow2(Player player, List<MachineItem> list) {
		this.row_2.put(player.getUniqueId(), list);
	}

	public String getWinMessage() {
		return winMessage;
	}

	public void setWinMessage(String winMessage) {
		this.winMessage = winMessage;
	}

	public String getLossMessage() {
		return lossMessage;
	}

	public void setLossMessage(String lossMessage) {
		this.lossMessage = lossMessage;
	}

	public String getLeverTitle() {
		return leverTitle;
	}

	public void setLeverTitle(String leverTitle) {
		this.leverTitle = leverTitle;
	}

	public String getLeverDescription() {
		return leverDescription;
	}

	public void setLeverDescription(String leverDescription) {
		this.leverDescription = leverDescription;
	}

	public SlotMachineType getSlotMachineType() {
		return slotMachineType;
	}

	public UUID getMachineUUID() {
		return machineUUID;
	}

	public void setMachineUUID(UUID machineUUID) {
		this.machineUUID = machineUUID;
	}

	public PriceType getPriceType() {
		return priceType;
	}

	public void setPriceType(PriceType priceType) {
		this.priceType = priceType;
		if(priceType == PriceType.TOKEN) {
			if(!this.isLeverCustom()) {
				this.setLeverTitle(Language.translate("slotmachine.lever.title.token"));
				this.setLeverDescription(Language.translate("slotmachine.lever.description.token"));
			}
		}
		if(priceType == PriceType.MONEY) {
			if(!this.isLeverCustom()) {
				this.setLeverTitle(Language.translate("slotmachine.lever.title.money"));
				this.setLeverDescription(Language.translate("slotmachine.lever.description.money"));
			}
		}
		if(priceType == PriceType.EXPERIENCE) {
			if(!this.isLeverCustom()) {
				this.setLeverTitle(Language.translate("slotmachine.lever.title.experience"));
				this.setLeverDescription(Language.translate("slotmachine.lever.description.experience"));
			}
		}
		if(priceType == PriceType.VOTINGPLUGIN) {
			if(!this.isLeverCustom()) {
				this.setLeverTitle(Language.translate("slotmachine.lever.title.votingplugin"));
				this.setLeverDescription(Language.translate("slotmachine.lever.description.votingplugin"));
			}
		}
		if(priceType == PriceType.GAMEPOINTS) {
			if(!this.isLeverCustom()) {
				this.setLeverTitle(Language.translate("slotmachine.lever.title.gamepoints"));
				this.setLeverDescription(Language.translate("slotmachine.lever.description.gamepoints"));
			}
		}
		if(priceType == PriceType.PLAYERPOINTS) {
			if(!this.isLeverCustom()) {
				this.setLeverTitle(Language.translate("slotmachine.lever.title.playerpoints"));
				this.setLeverDescription(Language.translate("slotmachine.lever.description.playerpoints"));
			}
		}
		if(priceType == PriceType.TOKENMANAGER) {
			if(!this.isLeverCustom()) {
				this.setLeverTitle(Language.translate("slotmachine.lever.title.tokenmanager"));
				this.setLeverDescription(Language.translate("slotmachine.lever.description.tokenmanager"));
			}
		}
	}

	public VisualType getVisualType() {
		return visualType;
	}

	public void setVisualType(VisualType visualType) {
		this.visualType = visualType;
		this.makeInventory();
	}

	public boolean isLeverCustom() {
		return isLeverCustom;
	}

	public void setLeverCustom(boolean isLeverCustom) {
		this.isLeverCustom = isLeverCustom;
	}

	public String getTokenIdentifier() {
		return tokenIdentifier;
	}

	public void setTokenIdentifier(String tokenIdentifier) {
		this.tokenIdentifier = tokenIdentifier;
	}

	public boolean isAffectedByLuck() {
		return isAffectedByLuck;
	}

	public void setAffectedByLuck(boolean isAffectedByLuck) {
		this.isAffectedByLuck = isAffectedByLuck;
	}

	public boolean allowContentPreview() {
		return this.allowContentPreview;
	}

	public SlotMachine allowContentPreview(boolean allowContentPreview) {
		this.allowContentPreview = allowContentPreview;
		return this;
	}

	public boolean showItemWeightOnPreview() {
		return this.showItemWeightOnPreview;
	}

	public SlotMachine showItemWeightOnPreview(boolean showItemWeightOnPreview) {
		this.showItemWeightOnPreview = showItemWeightOnPreview;
		return this;
	}

	public boolean isCitizensNPC() {
		return isCitizensNPC;
	}

	public void setCitizensNPC(boolean isCitizensNPC) {
		this.isCitizensNPC = isCitizensNPC;
	}

	public String getFinalWinMessage() {
		if (!this.hasWinMessage())
			return ChatContent.DARK_PURPLE + ChatContent.ITALIC + "None";

		if (this.getWinMessage().equals(""))
			return Config.defaultWinMessage;

		return this.getWinMessage();
	}

	public String getFinalLossMessage() {
		if (!this.hasLossMessage())
			return ChatContent.DARK_PURPLE + ChatContent.ITALIC + "None";

		if (this.getLossMessage().equals(""))
			return Config.defaultLossMessage;

		return this.getLossMessage();
	}

	public boolean hasWinMessage() {
		return hasWinMessage;
	}

	public void setHasWinMessage(boolean hasWinMessage) {
		this.hasWinMessage = hasWinMessage;
	}

	public boolean hasLossMessage() {
		return hasLossMessage;
	}

	public void setHasLossMessage(boolean hasLossMessage) {
		this.hasLossMessage = hasLossMessage;
	}

	public boolean isDisplayWonItemInChat() {
		return displayWonItemInChat;
	}

	public void setDisplayWonItemInChat(boolean displayWonItemInChat) {
		this.displayWonItemInChat = displayWonItemInChat;
	}

	public boolean shouldBroadcastWonItem() {
		return broadcastWonItem;
	}

	public void setBroadcastWonItem(boolean broadcastWonItem) {
		this.broadcastWonItem = broadcastWonItem;
	}

	public int getSpinSpeed() {
		return spinSpeed;
	}

	public void setSpinSpeed(int spinSpeed) {
		this.spinSpeed = spinSpeed;
	}

	public ItemStack getBackgroundItem() {
		return backgroundItem;
	}

	public void setBackgroundItem(ItemStack backgroundItem) {
		this.backgroundItem = backgroundItem;
	}

	public ItemStack getEmphasisItem() {
		return emphasisItem;
	}

	public void setEmphasisItem(ItemStack emphasisItem) {
		this.emphasisItem = emphasisItem;
	}

	public ItemStack getLeverItem() {
		return leverItem;
	}

	public void setLeverItem(ItemStack leverItem) {
		this.leverItem = leverItem;
	}

	public ItemStack getLeverItemActivated() {
		return leverItemActivated;
	}

	public void setLeverItemActivated(ItemStack leverItem) {
		this.leverItemActivated = leverItem;
	}

	public ItemStack getItemListItem() {
		return itemListItem;
	}

	public void setItemListItem(ItemStack itemListItem) {
		this.itemListItem = itemListItem;
	}

	public SSound getMachineOpeningSound() {
		return machineOpeningSound;
	}

	public void setMachineOpeningSound(SSound machineOpeningSound) {
		this.machineOpeningSound = machineOpeningSound;
	}

	public SSound getLeverSound() {
		return leverSound;
	}

	public void setLeverSound(SSound leverSound) {
		this.leverSound = leverSound;
	}

	public SSound getErrorSound() {
		return errorSound;
	}

	public void setErrorSound(SSound errorSound) {
		this.errorSound = errorSound;
	}

	public SSound getSlotmachineSpinSound() {
		return slotmachineSpinSound;
	}

	public void setSlotmachineSpinSound(SSound slotmachineSpinSound) {
		this.slotmachineSpinSound = slotmachineSpinSound;
	}

	public SSound getCsgoSpinSound() {
		return csgoSpinSound;
	}

	public void setCsgoSpinSound(SSound csgoSpinSound) {
		this.csgoSpinSound = csgoSpinSound;
	}

	public SSound getWinSound() {
		return winSound;
	}

	public void setWinSound(SSound winSound) {
		this.winSound = winSound;
	}

	public SSound getLossSound() {
		return lossSound;
	}

	public void setLossSound(SSound lossSound) {
		this.lossSound = lossSound;
	}

	public int getTimesUsed() {
		return timesUsed;
	}

	public void setTimesUsed(int timesUsed) {
		this.timesUsed = timesUsed;
	}

	public void addUse() {
		this.setTimesUsed(this.getTimesUsed() + 1);
		this.save();
	}

	public PlayMode getPlayMode() {
		return playMode;
	}

	public void setPlayMode(PlayMode playMode) {
		this.playMode = playMode;
	}

	public boolean showChanceOfItemOnPreview() {
		return showChanceOfItemOnPreview;
	}

	public void showChanceOfItemOnPreview(boolean showChanceOfItemOnPreview) {
		this.showChanceOfItemOnPreview = showChanceOfItemOnPreview;
	}

	public void updateCooldown(Player player) {
		int playerCooldown = this.getPlayerCooldown(player);
		int machineCooldown = this.getCooldown();

		if (playerCooldown > 0 && playerCooldown > machineCooldown) {
			this.setPlayerCooldown(player, machineCooldown);
		}
	}

	public boolean isPlayerInCooldown(Player player) {
		int playerCooldown = this.getPlayerCooldown(player);
		int machineCooldown = this.getCooldown();

		if (playerCooldown > 0 && playerCooldown > machineCooldown) {
			this.setPlayerCooldown(player, machineCooldown);
			return machineCooldown > 0;
		}

		return playerCooldown > 0;
	}

	public int getPlayerCooldown(Player player) {
		SMPlayerConfig plc = PlayerConfig.getSMPlayerConfig(player, this);

		if (plc != null) {
			return plc.getCooldown();
		}

		return 0;
	}

	public void setPlayerCooldown(Player player, int cooldown) {
		SMPlayerConfig plc = PlayerConfig.getOrCreateSMPlayerConfig(player, this, true);

		plc.setCooldown(cooldown);
		plc.saveToDisk();
	}

	public int getCooldown() {
		return cooldown;
	}

	public void setCooldown(int cooldown) {
		this.cooldown = cooldown;
	}

	public String getLastFileName() {
		return lastFileName;
	}

	public void setLastFileName(String lastFileName) {
		this.lastFileName = lastFileName;
	}

	public boolean needsSaving() {
		return this.needsSaving;
	}

	public void save() {
		this.needsSaving = true;
	}

	public void setSaved() {
		this.needsSaving = false;
	}

	public String getCoinsEngineCurrencyName() {
		return coinsEngineCurrencyName;
	}

	public void setCoinsEngineCurrencyName(String coinsEngineCurrencyName) {
		this.coinsEngineCurrencyName = coinsEngineCurrencyName;
	}
}
