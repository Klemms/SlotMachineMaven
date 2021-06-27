package fr.klemms.slotmachine.clipboard;

import fr.klemms.slotmachine.clipboard.content.Content;
import fr.klemms.slotmachine.clipboard.content.ContentItemList;
import fr.klemms.slotmachine.clipboard.content.ContentSlotMachine;

public enum ClipboardContent {

	SLOTMACHINE(new ContentSlotMachine(), "Slot Machine", "Will copy all settings, items and weights, excluding machine and items stats."),
	ITEMLIST(new ContentItemList(), "Items", "Will copy all items and their weight, excluding their stats.");
	
	public Content contentCopier;
	public String contentTitle;
	public String contentDescription;
	
	ClipboardContent(Content contentCopier, String contentTitle, String contentDescription) {
		this.contentCopier = contentCopier;
		this.contentTitle = contentTitle;
		this.contentDescription = contentDescription;
	}
}
