package fr.klemms.slotmachine.fr.minuskube.inv;

import fr.klemms.slotmachine.SlotPlugin;
import fr.klemms.slotmachine.fr.minuskube.inv.content.InventoryContents;
import fr.klemms.slotmachine.fr.minuskube.inv.opener.ChestInventoryOpener;
import fr.klemms.slotmachine.fr.minuskube.inv.opener.InventoryOpener;
import fr.klemms.slotmachine.fr.minuskube.inv.opener.SpecialInventoryOpener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;
import java.util.logging.Level;

public class InventoryManager {

    private JavaPlugin plugin;
    private PluginManager pluginManager;

    private Map<UUID, SmartInventory> inventories;
    private Map<UUID, InventoryContents> contents;

    private List<InventoryOpener> defaultOpeners;
    private List<InventoryOpener> openers;

    public InventoryManager(JavaPlugin plugin) {
        this.plugin = plugin;
        this.pluginManager = Bukkit.getPluginManager();

        this.inventories = new HashMap<>();
        this.contents = new HashMap<>();

        this.defaultOpeners = Arrays.asList(
                new ChestInventoryOpener(),
                new SpecialInventoryOpener()
        );

        this.openers = new ArrayList<>();
    }

    public void init() {
        pluginManager.registerEvents(new InvListener(), plugin);

        new InvTask().runTaskTimer(plugin, 1, 1);
    }

    public Optional<InventoryOpener> findOpener(InventoryType type) {
        Optional<InventoryOpener> opInv = this.openers.stream()
                .filter(opener -> opener.supports(type))
                .findAny();

        if (!opInv.isPresent()) {
            opInv = this.defaultOpeners.stream()
                    .filter(opener -> opener.supports(type))
                    .findAny();
        }

        return opInv;
    }

    public void registerOpeners(InventoryOpener... openers) {
        this.openers.addAll(Arrays.asList(openers));
    }

    public List<Player> getOpenedPlayers(SmartInventory inv) {
        List<Player> list = new ArrayList<>();

        this.inventories.forEach((player, playerInv) -> {
            if (inv.equals(playerInv))
                list.add(Bukkit.getPlayer(player));
        });

        return list;
    }

    public Optional<SmartInventory> getInventory(Player p) {
        return Optional.ofNullable(this.inventories.get(p.getUniqueId()));
    }

    protected void setInventory(Player p, SmartInventory inv) {
        if (inv == null)
            this.inventories.remove(p.getUniqueId());
        else
            this.inventories.put(p.getUniqueId(), inv);
    }

    public Optional<InventoryContents> getContents(Player p) {
        return Optional.ofNullable(this.contents.get(p.getUniqueId()));
    }

    protected void setContents(Player p, InventoryContents contents) {
        if (contents == null)
            this.contents.remove(p.getUniqueId());
        else
            this.contents.put(p.getUniqueId(), contents);
    }

    public void handleInventoryOpenError(SmartInventory inventory, Player player, Exception exception) {
    	inventory.close(player);

    	SlotPlugin.pl.getLogger().log(Level.SEVERE, "Error while opening Inventory:", exception);
    }

    public void handleInventoryUpdateError(SmartInventory inventory, Player player, Exception exception) {
    	inventory.close(player);

    	SlotPlugin.pl.getLogger().log(Level.SEVERE, "Error while updating Inventory:", exception);
    }

    @SuppressWarnings("unchecked")
    class InvListener implements Listener {

        @EventHandler(priority = EventPriority.HIGHEST)
        public void onInventoryClick(InventoryClickEvent e) {
            Player p = (Player) e.getWhoClicked();

            if (!inventories.containsKey(p.getUniqueId()))
                return;

            Inventory clickedInventory = e.getClickedInventory();

            if (clickedInventory == p.getOpenInventory().getBottomInventory()) {
                if(e.getAction() == InventoryAction.COLLECT_TO_CURSOR || e.getAction() == InventoryAction.MOVE_TO_OTHER_INVENTORY) {
                    e.setCancelled(true);
                    return;
                }

                if(e.getAction() == InventoryAction.NOTHING && e.getClick() != ClickType.MIDDLE) {
                    e.setCancelled(true);
                    return;
                }
            }

            if (clickedInventory == p.getOpenInventory().getTopInventory()) {
                e.setCancelled(true);

                int row = e.getSlot() / 9;
                int column = e.getSlot() % 9;

                if (row < 0 || column < 0)
                    return;

                SmartInventory inv = inventories.get(p.getUniqueId());

                if (row >= inv.getRows() || column >= inv.getColumns())
                    return;

                for(InventoryListener<? extends Event> listener : inv.getListeners()) {
                	if (listener.getType() == InventoryClickEvent.class) {
                		((InventoryListener<InventoryClickEvent>) listener).accept(e);
                	}
                }

                // Removed for performance reasons
                /*inv.getListeners().stream()
                        .filter(listener -> listener.getType() == InventoryClickEvent.class)
                        .forEach(listener -> ((InventoryListener<InventoryClickEvent>) listener).accept(e));*/

                contents.get(p.getUniqueId()).get(row, column).ifPresent(item -> item.run(e));

                p.updateInventory();
            }
        }

        @EventHandler(priority = EventPriority.LOW)
        public void onInventoryDrag(InventoryDragEvent e) {
            Player p = (Player) e.getWhoClicked();

            if (!inventories.containsKey(p.getUniqueId()))
                return;

            SmartInventory inv = inventories.get(p.getUniqueId());

            for (int slot : e.getRawSlots()) {
                if (slot >= p.getOpenInventory().getTopInventory().getSize())
                    continue;

                e.setCancelled(true);
                break;
            }

            for(InventoryListener<? extends Event> listener : inv.getListeners()) {
            	if (listener.getType() == InventoryDragEvent.class) {
            		((InventoryListener<InventoryDragEvent>) listener).accept(e);
            	}
            }

            // Removed for performance reasons
            /*inv.getListeners().stream()
                    .filter(listener -> listener.getType() == InventoryDragEvent.class)
                    .forEach(listener -> ((InventoryListener<InventoryDragEvent>) listener).accept(e));*/
        }

        @EventHandler(priority = EventPriority.LOW)
        public void onInventoryOpen(InventoryOpenEvent e) {
            Player p = (Player) e.getPlayer();

            if (!inventories.containsKey(p.getUniqueId()))
                return;

            SmartInventory inv = inventories.get(p.getUniqueId());

            for(InventoryListener<? extends Event> listener : inv.getListeners()) {
            	if (listener.getType() == InventoryOpenEvent.class) {
            		((InventoryListener<InventoryOpenEvent>) listener).accept(e);
            	}
            }

            // Removed for performance reasons
            /*inv.getListeners().stream()
                    .filter(listener -> listener.getType() == InventoryOpenEvent.class)
                    .forEach(listener -> ((InventoryListener<InventoryOpenEvent>) listener).accept(e));*/
        }

        @EventHandler(priority = EventPriority.LOW)
        public void onInventoryClose(InventoryCloseEvent e) {
            Player p = (Player) e.getPlayer();

            if (!inventories.containsKey(p.getUniqueId()))
                return;

            SmartInventory inv = inventories.get(p.getUniqueId());

            for(InventoryListener<? extends Event> listener : inv.getListeners()) {
            	if (listener.getType() == InventoryCloseEvent.class) {
            		((InventoryListener<InventoryCloseEvent>) listener).accept(e);
            	}
            }

            // Removed for performance reasons
            /*inv.getListeners().stream()
                    .filter(listener -> listener.getType() == InventoryCloseEvent.class)
                    .forEach(listener -> ((InventoryListener<InventoryCloseEvent>) listener).accept(e));*/

            if (inv.isCloseable()) {
                e.getInventory().clear();

                inventories.remove(p.getUniqueId());
                contents.remove(p.getUniqueId());
            } else
                Bukkit.getScheduler().runTask(plugin, () -> p.openInventory(e.getInventory()));
        }

        @EventHandler(priority = EventPriority.LOW)
        public void onPlayerQuit(PlayerQuitEvent e) {
            Player p = e.getPlayer();

            if (!inventories.containsKey(p.getUniqueId()))
                return;

            SmartInventory inv = inventories.get(p.getUniqueId());

            for(InventoryListener<? extends Event> listener : inv.getListeners()) {
            	if (listener.getType() == PlayerQuitEvent.class) {
            		((InventoryListener<PlayerQuitEvent>) listener).accept(e);
            	}
            }

            // Removed for performance reasons
            /*inv.getListeners().stream()
                    .filter(listener -> listener.getType() == PlayerQuitEvent.class)
                    .forEach(listener -> ((InventoryListener<PlayerQuitEvent>) listener).accept(e));*/

            inventories.remove(p.getUniqueId());
            contents.remove(p.getUniqueId());
        }

        @EventHandler(priority = EventPriority.LOW)
        public void onPluginDisable(PluginDisableEvent e) {
            new HashMap<>(inventories).forEach((player, inv) -> {
                for(InventoryListener<? extends Event> listener : inv.getListeners()) {
                	if (listener.getType() == PluginDisableEvent.class) {
                		((InventoryListener<PluginDisableEvent>) listener).accept(e);
                	}
                }

                // Removed for performance reasons
                /*inv.getListeners().stream()
                        .filter(listener -> listener.getType() == PluginDisableEvent.class)
                        .forEach(listener -> ((InventoryListener<PluginDisableEvent>) listener).accept(e));*/

                inv.close(Bukkit.getPlayer(player));
            });

            inventories.clear();
            contents.clear();
        }

    }

    class InvTask extends BukkitRunnable {

        @Override
        public void run() {
            new HashMap<>(inventories).forEach((playerUUID, inv) -> {
            	Player player = Bukkit.getPlayer(playerUUID);

            	try {
            		inv.getProvider().update(player, contents.get(playerUUID));
            	} catch (Exception e) {
            		handleInventoryUpdateError(inv, player, e);
            	}
            });
        }

    }

}
