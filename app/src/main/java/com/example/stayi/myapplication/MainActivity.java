package com.example.stayi.myapplication;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.stayi.myapplication.BASIC_MENU.CONDITIONS_MILL.MILL_calc_detail;
import com.example.stayi.myapplication.BASIC_MENU.CONDITIONS_MILL.MILL_calc_simple;
import com.example.stayi.myapplication.BASIC_MENU.MAIN_MENU_CONDITIONS;
import com.example.stayi.myapplication.BASIC_MENU.BlankFragment2;
import com.example.stayi.myapplication.BASIC_MENU.BlankFragment3;
import com.example.stayi.myapplication.BASIC_MENU.BlankFragment4;
import com.example.stayi.myapplication.BASIC_MENU.BlankFragment5;
import com.example.stayi.myapplication.BASIC_MENU.BlankFragment6;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MAIN_MENU_CONDITIONS.OnFragmentInteractionListener, BlankFragment2.OnFragmentInteractionListener,
        BlankFragment3.OnFragmentInteractionListener, BlankFragment4.OnFragmentInteractionListener, BlankFragment5.OnFragmentInteractionListener,
        BlankFragment6.OnFragmentInteractionListener, MILL_calc_simple.OnFragmentInteractionListener, MILL_calc_detail.OnFragmentInteractionListener{

    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
        Toolbar toolbar = findViewById (R.id.toolbar);
        setSupportActionBar (toolbar);
        navController = Navigation.findNavController (this, R.id.fragment);

        DrawerLayout drawer = findViewById (R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle (
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener (toggle);
        toggle.syncState ();

        //Инициализируем навигационный контроллер для боковой панели навигации.

        NavigationView navigationView = findViewById (R.id.nav_view);
        navigationView.setNavigationItemSelectedListener (this);
        NavigationUI.setupWithNavController (navigationView, navController);
        NavigationUI.setupActionBarWithNavController(this, navController, drawer);

        //Устанавливаем значения навигационных переменных для фрагментов меню по умолчанию при первом запуске программы.

        nav_var_storage.init(this);
        boolean  hasVisited = nav_var_storage.getProperty("hasVisited", true);
        if (!hasVisited) {
            nav_var_storage.addProperty("hasVisited", false);
        }
        Toast.makeText(this, "visit_status: " + hasVisited, Toast.LENGTH_SHORT).show();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (Objects.requireNonNull(navController.getCurrentDestination()).getId () == navController.getGraph ().getStartDestination ()) {
                super.onBackPressed();
            }
            navController.popBackStack ();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        switch (item.getItemId()) {
            case android.R.id.home:
                drawer.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onClick() {

    }
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}