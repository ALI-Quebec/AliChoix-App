package ca.ulaval.ima.ali_choix.domain.history;

import android.content.Context;

public interface HistoryRepositoryCollector {
    void saveHistory(HistoryRepository historyRepository, Context context);

    HistoryRepository loadHistory(Context context);
}
