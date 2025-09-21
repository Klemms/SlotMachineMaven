package fr.klemms.slotmachine.utils;

import fr.klemms.slotmachine.SlotPlugin;
import fr.klemms.slotmachine.exceptioncollector.ExceptionCollector;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerProfile;
import org.bukkit.profile.PlayerTextures;
import org.json.JSONObject;

import java.net.URL;
import java.util.Base64;
import java.util.UUID;

public class PlayerHeadsUtil {

    public static final ItemStack PLAY_BUTTON = getPlayerHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTY3OWQ2MzBmODUxYzU4OTdkYTgzYTY0MjUxNzQzM2Y2NWRjZmIzMmIxYmFiYjFmZWMzMmRhNzEyNmE5ZjYifX19");
    public static final ItemStack INFINITY_SYMBOL = getPlayerHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWQ4NmQwOTZiOGMwOGFkNWRhNmVhY2E5YTEwMTc1MWZiYzY4NzcwZDY3ZDUzMWU4Y2ZhNTYzYmE3M2Y5N2EzYyJ9fX0=");
    public static final ItemStack EARTH = getPlayerHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTI4OWQ1YjE3ODYyNmVhMjNkMGIwYzNkMmRmNWMwODVlODM3NTA1NmJmNjg1YjVlZDViYjQ3N2ZlODQ3MmQ5NCJ9fX0=");
    public static final ItemStack QUEUE_SCREEN = getPlayerHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTk0ODY3N2RlYjk0YzM3NDNlY2UxYWJlOGYwN2M5MmMwM2UyMDRiZjBlZTkzYzgyOWU3NzRkNWRiYjUxODVkOSJ9fX0=");
    public static final ItemStack LUCKY_BLOCK = getPlayerHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODEyYWI3NGVlZTY2ZmUzYzkzMmVlODc4ODFiOWE2ODVjYWEzNWNmNjY4MzAxZDA1ZjRkYWNhNjFjNTFjZiJ9fX0=");
    public static final ItemStack LUCKY_BLOCK_RED = getPlayerHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjE4MTJiNGUwZjAxYmIxOTM3ZGY5Mzg5ZmU2N2UyNWZhNWQ4NzYxMjQ4NTk4MzcwMTZjNDUxNTRiZWQzY2QxZSJ9fX0=");
    public static final ItemStack TRASH_CAN = getPlayerHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzI1MThkMDRmOWMwNmM5NWRkMGVkYWQ2MTdhYmI5M2QzZDg2NTdmMDFlNjU5MDc5ZDMzMGNjYTZmNjViY2NmNyJ9fX0=");
    public static final ItemStack PLUS_SIGN = getPlayerHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWEyZDg5MWM2YWU5ZjZiYWEwNDBkNzM2YWI4NGQ0ODM0NGJiNmI3MGQ3ZjFhMjgwZGQxMmNiYWM0ZDc3NyJ9fX0=");
    public static final ItemStack MINUS_SIGN = getPlayerHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTM1ZTRlMjZlYWZjMTFiNTJjMTE2NjhlMWQ2NjM0ZTdkMWQwZDIxYzQxMWNiMDg1ZjkzOTQyNjhlYjRjZGZiYSJ9fX0=");
    public static final ItemStack INFOS = getPlayerHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTY0MzlkMmUzMDZiMjI1NTE2YWE5YTZkMDA3YTdlNzVlZGQyZDUwMTVkMTEzYjQyZjQ0YmU2MmE1MTdlNTc0ZiJ9fX0=");
    public static final ItemStack SMALL_INFOS = getPlayerHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDAxYWZlOTczYzU0ODJmZGM3MWU2YWExMDY5ODgzM2M3OWM0MzdmMjEzMDhlYTlhMWEwOTU3NDZlYzI3NGEwZiJ9fX0=");
    public static final ItemStack BACK = getPlayerHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzdhZWU5YTc1YmYwZGY3ODk3MTgzMDE1Y2NhMGIyYTdkNzU1YzYzMzg4ZmYwMTc1MmQ1ZjQ0MTlmYzY0NSJ9fX0=");
    public static final ItemStack LEFT = getPlayerHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODY0Zjc3OWE4ZTNmZmEyMzExNDNmYTY5Yjk2YjE0ZWUzNWMxNmQ2NjllMTljNzVmZDFhN2RhNGJmMzA2YyJ9fX0=");
    public static final ItemStack RIGHT = getPlayerHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDllY2NjNWMxYzc5YWE3ODI2YTE1YTdmNWYxMmZiNDAzMjgxNTdjNTI0MjE2NGJhMmFlZjQ3ZTVkZTlhNWNmYyJ9fX0=");
    public static final ItemStack HEADSET = getPlayerHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2RkYjFlM2VjMzg2ZjhkMTg0YzI5ZmMwNGI4ZjZiNzZiMTg3OTVjMzI1YzQyOWM0OGIzNDgzNDMzMDA2N2FjZSJ9fX0=");
    public static final ItemStack HEADSET_MUTED = getPlayerHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGVhNmU2YzRmMjkyZmNjODJiZWZlOTEyYjM5MjE3ODQ3MzA4MDZiZGM0YjA0OTE2MzhlNDYzODExMDg4MjdlYiJ9fX0=");
    public static final ItemStack THUMBS_UP = getPlayerHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTFjMmY5MjhjNGFiZTMxZTM0MmM4MGM3MWZlZjcyM2U5OTA1NzE3ZjQ5OGRkNzQ2ZWJmOTQxNzk4ODlhNzVjMyJ9fX0=");
    public static final ItemStack CLOCK = getPlayerHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWNiZjMwMjlkYWVkNDExYmYyYjAwMzI5MDc4MGVlOGEwYmNmNTllZjZkM2EyZTM4YWMzMjMwNmFhNWI0M2M1YyJ9fX0=");
    public static final ItemStack COPY = getPlayerHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTgyNTQxOWU0MjlhZmMwNDBjOWU2OGIxMDUyM2I5MTdkN2I4MDg3ZDYzZTc2NDhiMTA4MDdkYThiNzY4ZWUifX19");
    public static final ItemStack PASTE = getPlayerHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGYzZWI4OWYzZmU5M2ViY2JjY2RhOWQwYTE5YjY0MGRkYTcxZTI4NGVhZjQ5NzgzMmZmNDdhZDJlYWM4ODIxIn19fQ==");
    public static final ItemStack SMOOTH_STONE = getPlayerHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDMwNjdhOTMzM2VjMjE5ODc2ZTY1ZmM3MWQzNWU2NmVhNWVmNDBjMzYzN2NmNTA4YzhkNGQ2ODgwYzhlMDQzNyJ9fX0=");
    public static final ItemStack SLOT_MACHINE = getPlayerHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGUxYzJhNWM0ZDRjMzVmYzM3NTQyOTVmMzljMzMzZWY2NzhiZDJlZGFkOWM4OGYyZDE2ODBmMTQ5NjgyIn19fQ==");
    public static final ItemStack CUSTOMIZE = getPlayerHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWI4MTk4YjM5NjBiNzVhNGZmNDQ4NjhlNzUyZjAzYTQ1YjFkMTIwZjc0NDYyNGY4M2MxODJmZDA1NThiOTk0ZSJ9fX0=");
    public static final ItemStack DUMBBELL = getPlayerHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjY2MGQ2NWYwOTdlYTE0MjNiNDgwMmE3Y2U5Y2VlZjgxODEzMGFhYzNiNmZkODc4YWVmNzE2ZTQwMjkxYmFjYiJ9fX0=");
    public static final ItemStack COMMAND_BLOCK = getPlayerHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2Q4YWY5ODBmZjYwODg2N2I2YmMzNzM2MTExYzRjMDFiNDFmMWZmNjg5OWEyMWU1OTg1OGJjZTNkNDRkY2Y4NiJ9fX0=");
    public static final ItemStack ARROW_LEFT = getPlayerHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTJmMDQyNWQ2NGZkYzg5OTI5MjhkNjA4MTA5ODEwYzEyNTFmZTI0M2Q2MGQxNzViZWQ0MjdjNjUxY2JlIn19fQ==");
    public static final ItemStack ARROW_RIGHT = getPlayerHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmQ4NjVhYWUyNzQ2YTliOGU5YTRmZTYyOWZiMDhkMThkMGE5MjUxZTVjY2JlNWZhNzA1MWY1M2VhYjliOTQifX19");
    public static final ItemStack CHECKMARK = getPlayerHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDk5ODBjMWQyMTE4MDlhOWI2NTY1MDg4ZjU2YTM4ZjJlZjQ5MTE1YzEwNTRmYTY2MjQ1MTIyZTllZWVkZWNjMiJ9fX0=");
	public static final ItemStack ALLAY = getPlayerHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODkyZmQ1OTcwM2NkZmU3ZGI1ZWEwYjM1YjY3OTIzMDhiMzVmYzM3MzY4NzYwYWE5OTc3MjZhZTEyZThiZDY5NiJ9fX0=");

    public static final ItemStack ZERO_BLACKBG = getPlayerHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmQ2ODM0M2JkMGIxMjlkZTkzY2M4ZDNiYmEzYjk3YTJmYWE3YWRlMzhkOGE2ZTJiODY0Y2Q4NjhjZmFiIn19fQ==");
    public static final ItemStack ONE_BLACKBG = getPlayerHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDJhNmYwZTg0ZGFlZmM4YjIxYWE5OTQxNWIxNmVkNWZkYWE2ZDhkYzBjM2NkNTkxZjQ5Y2E4MzJiNTc1In19fQ==");
    public static final ItemStack TWO_BLACKBG = getPlayerHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTZmYWI5OTFkMDgzOTkzY2I4M2U0YmNmNDRhMGI2Y2VmYWM2NDdkNDE4OWVlOWNiODIzZTljYzE1NzFlMzgifX19");
    public static final ItemStack THREE_BLACKBG = getPlayerHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2QzMTliOTM0M2YxN2EzNTYzNmJjYmMyNmI4MTk2MjVhOTMzM2RlMzczNjExMWYyZTkzMjgyN2M4ZTc0OSJ9fX0=");
    public static final ItemStack FOUR_BLACKBG = getPlayerHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDE5OGQ1NjIxNjE1NjExNDI2NTk3M2MyNThmNTdmYzc5ZDI0NmJiNjVlM2M3N2JiZTgzMTJlZTM1ZGI2In19fQ==");
    public static final ItemStack FIVE_BLACKBG = getPlayerHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2ZiOTFiYjk3NzQ5ZDZhNmVlZDQ0NDlkMjNhZWEyODRkYzRkZTZjMzgxOGVlYTVjN2UxNDlkZGRhNmY3YzkifX19");
    public static final ItemStack SIX_BLACKBG = getPlayerHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWM2MTNmODBhNTU0OTE4YzdhYjJjZDRhMjc4NzUyZjE1MTQxMmE0NGE3M2Q3YTI4NmQ2MWQ0NWJlNGVhYWUxIn19fQ==");
    public static final ItemStack SEVEN_BLACKBG = getPlayerHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWUxOThmZDgzMWNiNjFmMzkyN2YyMWNmOGE3NDYzYWY1ZWEzYzdlNDNiZDNlOGVjN2QyOTQ4NjMxY2NlODc5In19fQ==");
    public static final ItemStack EIGHT_BLACKBG = getPlayerHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODRhZDEyYzJmMjFhMTk3MmYzZDJmMzgxZWQwNWE2Y2MwODg0ODlmY2ZkZjY4YTcxM2IzODc0ODJmZTkxZTIifX19");
    public static final ItemStack NINE_BLACKBG = getPlayerHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWY3YWEwZDk3OTgzY2Q2N2RmYjY3YjdkOWQ5YzY0MWJjOWFhMzRkOTY2MzJmMzcyZDI2ZmVlMTlmNzFmOGI3In19fQ==");


    public static ItemStack getPlayerHead(String texture) {
        try {
            ItemStack head = new ItemStack(Material.PLAYER_HEAD);
            SkullMeta skull = (SkullMeta) head.getItemMeta();

            if (skull != null) {
                String headURL = new JSONObject(new String(Base64.getDecoder().decode(texture)))
                        .getJSONObject("textures")
                        .getJSONObject("SKIN")
                        .getString("url");

                PlayerProfile playerProfile = SlotPlugin.pl.getServer().createPlayerProfile(UUID.randomUUID());
                PlayerTextures playerTextures = playerProfile.getTextures();
                playerTextures.setSkin(new URL(headURL));

                skull.setOwnerProfile(playerProfile);
                head.setItemMeta(skull);
            }

            return head;
        } catch (Exception e) {
            ExceptionCollector.sendException(SlotPlugin.pl, e);
        }
        return new ItemStack(Material.PLAYER_HEAD);
    }
}
