package fr.klemms.slotmachine.clipboard;

import java.util.List;

public interface CopyPastable {

    public boolean disableCopyPaste();

    public List<String> disableReason();
}
