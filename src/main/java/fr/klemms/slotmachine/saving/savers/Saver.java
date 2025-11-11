package fr.klemms.slotmachine.saving.savers;

import fr.klemms.slotmachine.saving.SMSaveData;
import fr.klemms.slotmachine.serialization.SMJSONFile;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

public abstract class Saver<T> {

	public List<SMSaveData<T, ?>> saveDatas = Arrays.asList();

	public abstract T prepareLoad(JSONObject data, String fileName);

	public abstract void postLoad(T loadedData);

	public abstract SMJSONFile prepareSave(T data);
}
