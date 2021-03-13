package fr.klemms.slotmachine;

import fr.klemms.slotmachine.translation.Language;

public class Issue {
	
	public static void newIssue(IssueType type, String description) {
		SlotPlugin.issues.add(new Issue(type, description));
	}
	
	public IssueType type;
	public String description;

	public Issue(IssueType type, String description) {
		this.type = type;
		this.description = description;
	}
	
	public String getLocalizedTitle() {
		return Language.translate(this.type.title);
	}
	
	enum IssueType {
		WRITE_EXCEPTION("An important exception occured when saving files : ");
		
		public String title;
		
		IssueType(String title) {
			this.title = title;
		}
	}
}
