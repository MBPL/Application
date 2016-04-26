package mbpl.graphical.passwords;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import mbpl.graphical.passwords.R;
import mbpl.graphical.passwords.sqlite.DejaVu;
import mbpl.graphical.passwords.sqlite.DejaVuManager;
import mbpl.graphical.passwords.sqlite.Passfaces;
import mbpl.graphical.passwords.sqlite.PassfacesManager;
import mbpl.graphical.passwords.sqlite.Passpoint;
import mbpl.graphical.passwords.sqlite.PasspointManager;


public class Accueil extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // On crée les tables en BDD si elles n'existent pas
        DejaVuManager dejaVuManager = new DejaVuManager(getApplicationContext());
        dejaVuManager.open();
        if (!dejaVuManager.exist()) {
            dejaVuManager.addDejaVu(new DejaVu());
        }
        dejaVuManager.close();
        PasspointManager passpointManager = new PasspointManager(getApplicationContext());
        passpointManager.open();
        if (!passpointManager.exist()) {
            passpointManager.addPasspoint(new Passpoint());
        }
        passpointManager.close();
        PassfacesManager passfacesManager = new PassfacesManager(getApplicationContext());
        passfacesManager.open();
        if (!passfacesManager.exist()) {
            passfacesManager.addPassfaces(new Passfaces());
        }
        passfacesManager.close();

        setContentView(R.layout.menu_accueil);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Choix mdp"));
        tabLayout.addTab(tabLayout.newTab().setText("Stats user"));
        tabLayout.addTab(tabLayout.newTab().setText("Stats TER"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_accueil, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}