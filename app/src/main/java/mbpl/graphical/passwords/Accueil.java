package mbpl.graphical.passwords;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import mbpl.graphical.passwords.passPoints.ChoixImage;
import mbpl.graphical.passwords.sqlite.DejaVu;
import mbpl.graphical.passwords.sqlite.Methode;
import mbpl.graphical.passwords.sqlite.MethodeManager;
import mbpl.graphical.passwords.sqlite.Passfaces;
import mbpl.graphical.passwords.sqlite.Passpoint;


public class Accueil extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        load();
    }

    private void load() {

        MethodeManager methodeManager = new MethodeManager(getApplicationContext());
        methodeManager.open();

        // TODO AJOUTER VOTRE METHODE A CETTE LISTE (une ligne à ajouter)
        List<Methode> methodeList = new ArrayList<Methode>();
        methodeList.add(new Passpoint());
        methodeList.add(new DejaVu());
        methodeList.add(new Passfaces());

        // On crée les tables en BDD si elles n'existent pas encore
        for (int i = 0; i < methodeList.size(); i++) {
            if (!methodeManager.exist(methodeList.get(i))) {
                methodeManager.addMethode(methodeList.get(i));
            }
        }
        methodeManager.close();

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
    protected void onRestart() {
        super.onRestart();
        load();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_accueil, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String description = "Application développée dans le cadre de l'UE TER/PROJET : " +
                "\"Usable Security - Secure Usability\", pour le M1 informatique de l'Université " +
                "Paul Sabatier de Toulouse.\n\nAuteurs : \n - LACHERAY Benjamin\n " +
                "- JEANMOUGIN Pierre\n - LE QUERE Lilian\n - MOUGEOT Matteo\n" +
                "Remerciements à M. PALANQUE Philippe pour son encadrement.";

        AlertDialog.Builder myAlert = new AlertDialog.Builder(this);
        myAlert.setMessage(description)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setTitle("A propos").create();

        myAlert.show();

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}