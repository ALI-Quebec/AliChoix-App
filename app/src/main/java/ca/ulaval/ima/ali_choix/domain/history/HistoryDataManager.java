package ca.ulaval.ima.ali_choix.domain.history;

import android.content.Context;

//TODO rename manager BAD
public interface HistoryDataManager {
    public void saveHistory(HistoryRepository historyRepository, Context context);

    public HistoryRepository loadHistory(Context context);
}
