package fr.klemms.slotmachine;

import fr.klemms.slotmachine.translation.Language;

public class Issue {
	
	public static void newIssue(IssueType type, String description, boolean checkDuplicate) {
		for (Issue issue : SlotPlugin.issues) {
			if (issue.type == type && issue.description.equals(description)) {
				issue.amount++;
				return;
			}
		}
		SlotPlugin.issues.add(new Issue(type, description));
	}
	
	public IssueType type;
	public String description;
	public int amount;

	public Issue(IssueType type, String description) {
		this(type, description, 1);
	}
	
	public Issue(IssueType type, String description, int amount) {
		this.type = type;
		this.description = description;
		this.amount = amount;
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
