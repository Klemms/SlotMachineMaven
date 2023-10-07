package fr.klemms.slotmachine.threads;

import fr.klemms.slotmachine.*;
import fr.klemms.slotmachine.Issue.IssueType;
import fr.klemms.slotmachine.exceptioncollector.ExceptionCollector;
import fr.klemms.slotmachine.fr.minuskube.inv.content.InventoryContents;
import fr.klemms.slotmachine.interraction.InterractionCallback;
import fr.klemms.slotmachine.placeholders.Variables;
import fr.klemms.slotmachine.translation.Language;
import fr.klemms.slotmachine.utils.PlayerUtil;
import fr.klemms.slotmachine.utils.PotionUtil;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;

public class ThreadPullLever extends Thread {

	private SlotMachine machine;
	private Player player;
	private InventoryContents contents;
	private InterractionCallback callback;

	public ThreadPullLever( Player player, SlotMachine machine, InventoryContents contents, InterractionCallback callback) {
		this.machine = machine;
		this.player = player;
		this.contents = contents;
		this.callback = callback;
	}

	@Override
	public void run() {
		try {
			double chance = ThreadLocalRandom.current().nextDouble(0D, 1D);
			double chanceToWin = machine.getChanceToWin();
			if(machine.getVisualType() == VisualType.CSGOWHEEL || machine.getVisualType() == VisualType.CSGOWHEEL_VERTICAL) {
				chanceToWin = 1D;
			}

			if (machine.isAffectedByLuck() && player.hasPotionEffect(PotionEffectType.LUCK))
				chanceToWin += (PotionUtil.getPotionEffect(player, PotionEffectType.LUCK).getAmplifier() + 1) * Config.luckLevelToPercentConversion / 100;
			if (machine.isAffectedByLuck() && player.hasPotionEffect(PotionEffectType.UNLUCK))
				chanceToWin += (PotionUtil.getPotionEffect(player, PotionEffectType.UNLUCK).getAmplifier() + 1) * Config.badLuckLevelToPercentConversion / 100;

			boolean hasWon = chance >= 0D && chance < chanceToWin && machine.canAnItemBeWon();
			MachineItem wonItem = machine.getRandomItemFromPoolWithWeight();

			if(machine.getVisualType() == VisualType.SLOTMACHINE) {
				for(int a = 0; a < machine.getSecondsBeforePrize() * 2; a++) {
					List<MachineItem> row0 = null;
					if(a == machine.getSecondsBeforePrize() * 2 - 2) {
						if(hasWon) {
							row0 = new ArrayList<MachineItem>();
							row0.add(0, wonItem);
							row0.add(1, wonItem);
							row0.add(2, wonItem);
						} else {
							row0 = machine.createRandomItemPool(true);
						}
					} else {
						row0 = machine.createRandomItemPool(false);
					}
					List<MachineItem> row1 = machine.getPlayerRow1(player);
					List<MachineItem> row2 = machine.getPlayerRow2(player);
					Bukkit.getScheduler().scheduleSyncDelayedTask(SlotPlugin.pl, new ThreadPlaySound(machine.getSlotmachineSpinSound(), 0.4F, 0.8F, player), 0);
					Bukkit.getScheduler().scheduleSyncDelayedTask(SlotPlugin.pl, new ThreadPlaySound(machine.getSlotmachineSpinSound(), 0.4F, 0.9F, player), 0 + machine.getSpinSpeed());
					Bukkit.getScheduler().scheduleSyncDelayedTask(SlotPlugin.pl, new ThreadPlaySound(machine.getSlotmachineSpinSound(), 0.4F, 1.0F, player), 0 + machine.getSpinSpeed() * 2);
					Bukkit.getScheduler().scheduleSyncDelayedTask(SlotPlugin.pl, new ThreadMachineInvUpdate(player, machine, 1, 1, row0.get(0), contents), 0);
					Bukkit.getScheduler().scheduleSyncDelayedTask(SlotPlugin.pl, new ThreadMachineInvUpdate(player, machine, 1, 2, row1.get(0), contents), 0);
					Bukkit.getScheduler().scheduleSyncDelayedTask(SlotPlugin.pl, new ThreadMachineInvUpdate(player, machine, 1, 3, row2.get(0), contents), 0);
					Bukkit.getScheduler().scheduleSyncDelayedTask(SlotPlugin.pl, new ThreadMachineInvUpdate(player, machine, 3, 1, row0.get(1), contents), 0 + machine.getSpinSpeed());
					Bukkit.getScheduler().scheduleSyncDelayedTask(SlotPlugin.pl, new ThreadMachineInvUpdate(player, machine, 3, 2, row1.get(1), contents), 0 + machine.getSpinSpeed());
					Bukkit.getScheduler().scheduleSyncDelayedTask(SlotPlugin.pl, new ThreadMachineInvUpdate(player, machine, 3, 3, row2.get(1), contents), 0 + machine.getSpinSpeed());
					Bukkit.getScheduler().scheduleSyncDelayedTask(SlotPlugin.pl, new ThreadMachineInvUpdate(player, machine, 5, 1, row0.get(2), contents), 0 + machine.getSpinSpeed() * 2);
					Bukkit.getScheduler().scheduleSyncDelayedTask(SlotPlugin.pl, new ThreadMachineInvUpdate(player, machine, 5, 2, row1.get(2), contents), 0 + machine.getSpinSpeed() * 2);
					Bukkit.getScheduler().scheduleSyncDelayedTask(SlotPlugin.pl, new ThreadMachineInvUpdate(player, machine, 5, 3, row2.get(2), contents), 0 + machine.getSpinSpeed() * 2);
					machine.setPlayerRow1(player, row0);
					machine.setPlayerRow2(player, row1);
					try {
						Thread.sleep(100 + 50 * machine.getSpinSpeed());
					} catch (InterruptedException e) {
						e.printStackTrace();
						ExceptionCollector.sendException(SlotPlugin.pl, e);
					}
				}
				try {
					Thread.sleep(250);
				} catch (InterruptedException e) {
					e.printStackTrace();
					ExceptionCollector.sendException(SlotPlugin.pl, e);
				}
			} else if(machine.getVisualType() == VisualType.CSGOWHEEL) {
				for(int a = 0; a < machine.getSecondsBeforePrize() * 10; a++) {
					List<MachineItem> newLine = machine.getPlayerRow0(player);
					if(hasWon && a == machine.getSecondsBeforePrize() * 10 - 4) {
						newLine.add(0, wonItem);
					} else {
						newLine.add(0, machine.getRandomItemFromPool());
					}
					Bukkit.getScheduler().scheduleSyncDelayedTask(SlotPlugin.pl, new ThreadPlaySound(machine.getCsgoSpinSound(), 0.7F, 0.9F, player), 0);
					Bukkit.getScheduler().scheduleSyncDelayedTask(SlotPlugin.pl, new ThreadMachineInvUpdate(player, machine, 1, 2, newLine.get(0), contents), 0);
					Bukkit.getScheduler().scheduleSyncDelayedTask(SlotPlugin.pl, new ThreadMachineInvUpdate(player, machine, 2, 2, newLine.get(1), contents), 0);
					Bukkit.getScheduler().scheduleSyncDelayedTask(SlotPlugin.pl, new ThreadMachineInvUpdate(player, machine, 3, 2, newLine.get(2), contents), 0);
					Bukkit.getScheduler().scheduleSyncDelayedTask(SlotPlugin.pl, new ThreadMachineInvUpdate(player, machine, 4, 2, newLine.get(3), contents), 0);
					Bukkit.getScheduler().scheduleSyncDelayedTask(SlotPlugin.pl, new ThreadMachineInvUpdate(player, machine, 5, 2, newLine.get(4), contents), 0);
					Bukkit.getScheduler().scheduleSyncDelayedTask(SlotPlugin.pl, new ThreadMachineInvUpdate(player, machine, 6, 2, newLine.get(5), contents), 0);
					Bukkit.getScheduler().scheduleSyncDelayedTask(SlotPlugin.pl, new ThreadMachineInvUpdate(player, machine, 7, 2, newLine.get(6), contents), 0);
					machine.setPlayerRow0(player, newLine);

					int finishTime = 0;
					if(a >= machine.getSecondsBeforePrize() * 10 - 10) {
						finishTime = (40 - (6 * 4 - machine.getSpinSpeed() * 4)) * (a - (machine.getSecondsBeforePrize() * 10 - 10));
					}
					try {
						Thread.sleep(8 + 15 * machine.getSpinSpeed() + finishTime);
					} catch (InterruptedException e) {
						e.printStackTrace();
						ExceptionCollector.sendException(SlotPlugin.pl, e);
					}
				}
			} else if(machine.getVisualType() == VisualType.CSGOWHEEL_VERTICAL) {
				for(int a = 0; a < machine.getSecondsBeforePrize() * 10; a++) {
					List<MachineItem> newLine = machine.getPlayerRow0(player);
					if(hasWon && a == machine.getSecondsBeforePrize() * 10 - 3) {
						newLine.add(0, wonItem);
					} else {
						newLine.add(0, machine.getRandomItemFromPool());
					}
					Bukkit.getScheduler().scheduleSyncDelayedTask(SlotPlugin.pl, new ThreadPlaySound(machine.getCsgoSpinSound(), 0.7F, 0.9F, player), 0);
					Bukkit.getScheduler().scheduleSyncDelayedTask(SlotPlugin.pl, new ThreadMachineInvUpdate(player, machine, 2, 0, newLine.get(0), contents), 0);
					Bukkit.getScheduler().scheduleSyncDelayedTask(SlotPlugin.pl, new ThreadMachineInvUpdate(player, machine, 2, 1, newLine.get(1), contents), 0);
					Bukkit.getScheduler().scheduleSyncDelayedTask(SlotPlugin.pl, new ThreadMachineInvUpdate(player, machine, 2, 2, newLine.get(2), contents), 0);
					Bukkit.getScheduler().scheduleSyncDelayedTask(SlotPlugin.pl, new ThreadMachineInvUpdate(player, machine, 2, 3, newLine.get(3), contents), 0);
					Bukkit.getScheduler().scheduleSyncDelayedTask(SlotPlugin.pl, new ThreadMachineInvUpdate(player, machine, 2, 4, newLine.get(4), contents), 0);
					machine.setPlayerRow0(player, newLine);

					int finishTime = 0;
					if(a >= machine.getSecondsBeforePrize() * 10 - 10) {
						finishTime = (40 - (6 * 4 - machine.getSpinSpeed() * 4)) * (a - (machine.getSecondsBeforePrize() * 10 - 10));
					}
					try {
						Thread.sleep(10 + 15 * machine.getSpinSpeed() + finishTime);
					} catch (InterruptedException e) {
						e.printStackTrace();
						ExceptionCollector.sendException(SlotPlugin.pl, e);
					}
				}
			}
			if(hasWon) {
				wonItem.itemStats.timesWon++;
				Bukkit.getScheduler().scheduleSyncDelayedTask(SlotPlugin.pl, new ThreadPlaySound(machine.getWinSound(), 1.9F, 0.9F, player), 4);
				givePrize(wonItem);
			}

			if (hasWon && machine.hasWinMessage()) {
				ComponentBuilder finalMessage = new ComponentBuilder(Variables.getFormattedString(machine.getFinalWinMessage(), player, machine));

				if (machine.isDisplayWonItemInChat()) {
					if (wonItem.getRewards().size() == 1) {
						finalMessage.append(new ComponentBuilder(" (")
								.color(ChatColor.AQUA)
								.append(wonItem.getRewardName())
								.append(new ComponentBuilder(" x" + wonItem.getItemStack().getAmount()).color(ChatColor.GRAY).create())
								.append(new ComponentBuilder(")").color(ChatColor.AQUA).create())
								.create());
					} else {
						finalMessage.append(new ComponentBuilder(" (")
								.color(ChatColor.AQUA)
								.append(new ComponentBuilder("Multiple Rewards").color(ChatColor.GRAY).create())
								.append(new ComponentBuilder(")").color(ChatColor.AQUA).create())
								.create());
					}
				}

				player.spigot().sendMessage(finalMessage.create());
			}
			if (hasWon && machine.shouldBroadcastWonItem()) {
				ComponentBuilder finalMessage = new ComponentBuilder(Variables.getFormattedString(Language.translate("wonitem.broadcast").replace("%playerName%", player.getDisplayName()).replace("%item%", ""), player, machine));

				if (wonItem.getRewards().size() == 1) {
					finalMessage.append(new ComponentBuilder(" (")
							.color(ChatColor.AQUA)
							.append(wonItem.getRewardName())
							.append(new ComponentBuilder(" x" + wonItem.getItemStack().getAmount()).color(ChatColor.GRAY).create())
							.append(new ComponentBuilder(")").color(ChatColor.AQUA).create())
							.create());
				} else {
					finalMessage.append(new ComponentBuilder(" (")
							.color(ChatColor.AQUA)
							.append(new ComponentBuilder("Multiple Rewards").color(ChatColor.GRAY).create())
							.append(new ComponentBuilder(")").color(ChatColor.AQUA).create())
							.create());
				}

				for (Player p : Bukkit.getOnlinePlayers()) {
					//if (p != player) {
						p.spigot().sendMessage(finalMessage.create());
					//}
				}
			}

			if (!hasWon && machine.hasLossMessage()) {
				Bukkit.getScheduler().scheduleSyncDelayedTask(SlotPlugin.pl, new ThreadPlaySound(machine.getLossSound(), 0.3F, 0.7F, player), 4);
				ComponentBuilder finalMessage = new ComponentBuilder(Variables.getFormattedString(machine.getFinalLossMessage(), player, machine));

				player.spigot().sendMessage(finalMessage.create());
			}

			callback.callback(true);
		} catch(Exception globalException) {
			globalException.printStackTrace();
			ExceptionCollector.sendException(SlotPlugin.pl, globalException);
		}
	}

	public void givePrize(MachineItem wonItem) {
		try {
			for(MachineItem.Reward reward : wonItem.getRewards()) {
				switch (reward.rewardType) {
					case ITEM:
						PlayerUtil.givePlayerItem(player, new ItemStack(reward.itemReward));
						break;
					case COMMAND:
						Bukkit.getScheduler().scheduleSyncDelayedTask(SlotPlugin.pl, () -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), Variables.replaceVariable(player, machine, reward.commandReward)));
						break;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
			Issue.newIssue(IssueType.REWARD_EXCEPTION, e.getMessage() + " // Reward : " + wonItem.getRealName() + " to " + player.getName(), true);
			SlotPlugin.pl.getLogger().log(Level.WARNING, "Couldn't give reward (" + wonItem.getRealName() + ") to " + player.getName());
			ExceptionCollector.sendException(SlotPlugin.pl, e);
		}
	}
}
