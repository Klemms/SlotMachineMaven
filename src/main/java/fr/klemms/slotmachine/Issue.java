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

	public enum IssueType {
		MACHINE_REMOVAL_EXCEPTION("An important exception occurred when removing a machine : "),
		MACHINE_READING_ISSUE("An issue occurred when loading a machine : "),
		REWARD_EXCEPTION("An important exception occured giving a reward to a player : "),
		WRITE_EXCEPTION("An important exception occured when saving files : "),
		MACHINES_BACKUP("An important exception occured while making backups for your machines : "),
		CONVERSION_ISSUE("An issue occurred while converting data : "),
		VERSION_ISSUE("An issue occurred with the version of Slot Machine you are using : ");

		public String title;

		IssueType(String title) {
			this.title = title;
		}
	}
}
