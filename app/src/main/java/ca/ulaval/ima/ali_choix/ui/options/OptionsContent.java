package ca.ulaval.ima.ali_choix.ui.options;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import ca.ulaval.ima.ali_choix.R;
import ca.ulaval.ima.ali_choix.ui.about.AboutFragment;

public class OptionsContent {
    public static final List<OptionsItem> OPTIONS_ITEMS = new ArrayList<OptionsItem>();

    static {
        addItem(new OptionsItem(R.drawable.ali_choix_logo, "À propos", "Lien vers la page à propos", new AboutFragment()));
    }

    private static void addItem(OptionsItem item) {
        OPTIONS_ITEMS.add(item);
    }

    public static class OptionsItem {
        public final int icon;
        public final String content;
        public final String details;
        public final Fragment target;

        public OptionsItem(int icon, String content, String details, Fragment target) {
            this.icon = icon;
            this.content = content;
            this.details = details;
            this.target = target;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}