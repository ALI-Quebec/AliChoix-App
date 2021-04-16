package ca.ulaval.ima.ali_choix;

import android.content.Context;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import ca.ulaval.ima.ali_choix.services.HistoryService;
import ca.ulaval.ima.ali_choix.services.ServiceLocator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setup();
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_scan, R.id.navigation_scanned_product, R.id.navigation_history, R.id.navigation_options)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

    private void setup(){
        ServiceLocator serviceLocator = ServiceLocator.getInstance();
        serviceLocator.subscribeService(HistoryService.class, new HistoryService(getApplicationContext()));

        HistoryService historyService = (HistoryService) serviceLocator.get(HistoryService.class);
        //TODO remove when testing is done
//        historyService.addHistoryElement("3017620422003","https://static.openfoodfacts.org/images/products/301/762/042/2003/front_fr.260.full.jpg","Nutella - Ferrero - 400 g");
//        historyService.addHistoryElement("3229820100234","https://static.openfoodfacts.org/images/products/322/982/010/0234/front_fr.115.full.jpg","Fourrés Chocolat noir - bjorg - 225 g");
//        historyService.addHistoryElement("3392460480827","https://static.openfoodfacts.org/images/products/339/246/048/0827/front_en.72.full.jpg","Biscottes heudebert - 300 g");
//        historyService.addHistoryElement("8002270014901","https://static.openfoodfacts.org/images/products/800/227/001/4901/front_fr.164.full.jpg","san Pellegrino - 1 L");
//        historyService.addHistoryElement("7503018092775","https://static.openfoodfacts.org/images/products/750/301/809/2775/front_es.8.full.jpg","Hongo shiitake Morimoto - 50 g");
//        historyService.addHistoryElement("0011110844149",null,"Private selection, mukimame");
//        historyService.addHistoryElement("3274080005003","https://static.openfoodfacts.org/images/products/327/408/000/5003/front_en.640.full.jpg","Spring water - Cristaline - 1,5 l");
//        historyService.addHistoryElement("737628064502","https://static.openfoodfacts.org/images/products/073/762/806/4502/front_en.6.full.jpg","Thai peanut noodle kit includes stir-fry rice noodles & thai peanut seasoning - Thai Kitchen - 155 g");

    }
}