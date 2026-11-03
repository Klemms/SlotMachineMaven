package fr.klemms.slotmachine.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;

public class Words {
	public static Component mm(String miniMessageString) {
		return MiniMessage.miniMessage().deserialize(miniMessageString);
	}

	public static Component mm(String miniMessageString, TagResolver... tagResolvers) {
		return MiniMessage.miniMessage().deserialize(miniMessageString, tagResolvers);
	}
}
