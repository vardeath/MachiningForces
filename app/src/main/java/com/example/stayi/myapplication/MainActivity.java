package com.example.stayi.myapplication;

import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.stayi.myapplication.BASIC_MENU.BlankFragment2;
import com.example.stayi.myapplication.BASIC_MENU.BlankFragment3;
import com.example.stayi.myapplication.BASIC_MENU.BlankFragment4;
import com.example.stayi.myapplication.BASIC_MENU.BlankFragment5;
import com.example.stayi.myapplication.BASIC_MENU.BlankFragment6;
import com.example.stayi.myapplication.BASIC_MENU.CONDITIONS_MILL.MAIN_MILL_MENU;
import com.example.stayi.myapplication.BASIC_MENU.CONDITIONS_MILL.MILL_calc_detail;
import com.example.stayi.myapplication.BASIC_MENU.CONDITIONS_MILL.MILL_calc_simple;
import com.example.stayi.myapplication.BASIC_MENU.MAIN_MENU_CONDITIONS;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.navigation.NavigationView;

import java.lang.reflect.Field;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener, MAIN_MENU_CONDITIONS.OnFragmentInteractionListener, BlankFragment2.OnFragmentInteractionListener,
        BlankFragment3.OnFragmentInteractionListener, BlankFragment4.OnFragmentInteractionListener, BlankFragment5.OnFragmentInteractionListener,
        BlankFragment6.OnFragmentInteractionListener, MILL_calc_simple.OnFragmentInteractionListener, MILL_calc_detail.OnFragmentInteractionListener, MAIN_MILL_MENU.OnFragmentInteractionListener {

    BottomSheetBehavior behavior; //Обработчик поведения нижней панели с кастомной клавиатурой.
    private NavController navController; //Контроллер навигации по всему приложению.

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Стандартные инициализации.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //Инициализация обработчика поведения выдвижной панели с кастомной клавиатурой.
        LinearLayout llBottomSheet = findViewById(R.id.bottom_sheet);
        behavior = BottomSheetBehavior.from(llBottomSheet);

        //Инициализируем навигационный контроллер для боковой панели навигации и всех фрагментов в графе.
        navController = Navigation.findNavController(this, R.id.fragment);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        NavigationUI.setupWithNavController(navigationView, navController);
        NavigationUI.setupActionBarWithNavController(this, navController, drawer);


        //Вешаем слушатель позиции на навигационный контроллер.
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                //Toast.makeText(getBaseContext(), "вот тут должно скрывать панель", Toast.LENGTH_SHORT).show();
                behavior.setPeekHeight(0);
                behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
            /*@Override
            public void onNavigated(@NonNull NavController controller, @NonNull NavDestination destination) {
                //Toast.makeText(getBaseContext(), "вот тут должно скрывать панель", Toast.LENGTH_SHORT).show();
                behavior.setPeekHeight(0);
                behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }*/
        });

        //Устанавливаем значения навигационных переменных для фрагментов меню по умолчанию при первом запуске программы.
        nav_var_storage.init(this);
        boolean hasVisited = nav_var_storage.getProperty("hasVisited", true);
        if (!hasVisited) {
            nav_var_storage.addProperty("hasVisited", false);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (Objects.requireNonNull(navController.getCurrentDestination()).getId() == navController.getGraph().getStartDestination()) {
                super.onBackPressed();
            }
            navController.popBackStack();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                navController.popBackStack();
                //drawer.openDrawer(GravityCompat.START);
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

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onClick() {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onClick(View v) {

    }
}