package fr.klemms.slotmachine.clipboard;

import javax.annotation.Nullable;
import java.util.List;

public interface CopyPastable {

    public boolean disableCopyPaste();

    @Nullable
    public List<String> disableReason();
}
