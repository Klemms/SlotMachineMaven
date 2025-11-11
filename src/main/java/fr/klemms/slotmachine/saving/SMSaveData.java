package fr.klemms.slotmachine.saving;

public interface SMSaveData<T, U> {

	public String getKey();

	public boolean shouldSave(T obj);

	public U save(T obj);

	public void load(T obj, U data);
}
