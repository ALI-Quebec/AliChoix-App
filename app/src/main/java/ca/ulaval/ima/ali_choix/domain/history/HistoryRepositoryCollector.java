package ca.ulaval.ima.ali_choix.domain.history;

import android.content.Context;

public interface HistoryRepositoryCollector {
    public void saveHistory(HistoryRepository historyRepository, Context context);

    public HistoryRepository loadHistory(Context context);
}
