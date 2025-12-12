package fr.klemms.slotmachine.dialogs.callbacks;

public interface ResettableCallback<T> {

	void validateCallback(T text);

	void resetCallback();

	void removeCallback();
}
