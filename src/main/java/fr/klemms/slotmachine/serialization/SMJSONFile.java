package fr.klemms.slotmachine.serialization;

import com.google.common.base.Charsets;
import fr.klemms.slotmachine.SlotPlugin;
import fr.klemms.slotmachine.exceptioncollector.ExceptionCollector;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class SMJSONFile {

	public String fileName;
	public JSONObject json;

	public SMJSONFile(String fileName) {
		this.fileName = fileName;
		this.json = new JSONObject();
	}

	public void write(Path path) {
		try (BufferedWriter writer = Files.newBufferedWriter(path.resolve(this.fileName + ".json"), Charsets.UTF_8, StandardOpenOption.WRITE, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
			this.json.write(writer, 2, 0);
		} catch (IOException e) {
			e.printStackTrace();
			ExceptionCollector.sendException(SlotPlugin.pl, e);
		}
	}
}
