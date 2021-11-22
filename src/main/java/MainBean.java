import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import database.models.Entry;

public class MainBean implements Serializable {
    UUID session_id = UUID.randomUUID();
    @Inject
    private EntryDAO entryDAO;
    private Entry newEntry;

    public MainBean() {
        newEntry = new Entry();
        newEntry.setX(1.0);
        newEntry.setR(1.0);
    }

    public Entry getNewEntry() {
        return newEntry;
    }

    public void setNewEntry(Entry newEntry) {
        this.newEntry = newEntry;
    }

    public List<Entry> getEntries() {
        return entryDAO.getEntries(this.session_id.toString());
    }

    public void addEntry() {
        newEntry.setSession_id(session_id.toString());
        newEntry.checkHit();
        entryDAO.add(newEntry);

        newEntry = new Entry();
        newEntry.setX(1.0);
        newEntry.setR(1.0);
    }

}
