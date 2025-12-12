package fr.klemms.slotmachine.dialogs.callbacks;

public interface ResettableCallback {

	void validateCallback(String text);

	void resetCallback();

	void removeCallback();
}
