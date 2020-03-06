package com.example.edelhome;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private TextView textViewname;
    private TextView textViewemail;
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    private AppBarConfiguration mAppBarConfiguration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        textViewname = findViewById(R.id.textView_name);
        textViewemail = findViewById(R.id.textView_email);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Hier ist das Pop Up Menu deklariert
        final FloatingActionButton fab = findViewById(R.id.fab_menu);
        final FloatingActionButton fab_scene = findViewById(R.id.fab_scene);
        final FloatingActionButton fab_geraet = findViewById(R.id.fab_geraetadd);

        fab.setOnClickListener(new View.OnClickListener() {
            boolean fabOpen = false;

            @Override
            public void onClick(View view) {

                if (fabOpen == false) {
                    showFab();

                } else if (fabOpen == true) {
                    closeFab();
                }
            }

            private void closeFab() {
                fabOpen = false;
                fab_scene.animate().translationY(0);
                fab_geraet.animate().translationY(0);

            }

            private void showFab() {
                fabOpen = true;
                fab_scene.animate().translationY(-getResources().getDimension(R.dimen.standard_55));
                fab_geraet.animate().translationY(-getResources().getDimension(R.dimen.standard_105));
            }

        });

        fab_scene.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }

            private void openDialog() {
                ExampleDialog exampleDialog = new ExampleDialog();
                exampleDialog.show(getSupportFragmentManager(), "example dialog");

            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        if (firebaseAuth.getCurrentUser() == null) {
            mAppBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.nav_home, R.id.nav_registieren,
                    R.id.nav_geraeteliste,
                    R.id.nav_abmelden, R.id.nav_share, R.id.nav_send, R.id.nav_anmelden)
                    .setDrawerLayout(drawer)
                    .build();
            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
            NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
            NavigationUI.setupWithNavController(navigationView, navController);


            navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
                @Override
                public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {

                    //TODO Einen Listener erstellen für das Navigations View !

                }
            });

        }
        if (firebaseAuth.getCurrentUser() != null) {
            DrawerLayout drawerLayout = findViewById(R.id.drawer_layout_1);
            mAppBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.nav_home, R.id.nav_registieren,
                    R.id.nav_geraeteliste,
                    R.id.nav_abmelden, R.id.nav_share, R.id.nav_send, R.id.nav_anmelden)
                    .setDrawerLayout(drawerLayout)
                    .build();
            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
            NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
            NavigationUI.setupWithNavController(navigationView, navController);
            navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
                @Override
                public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {

                    //TODO Einen Listener erstellen für das Navigations View !

                }
            });
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        NavigationView navigationView = findViewById(R.id.nav_view);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if(firebaseAuth.getCurrentUser() ==null) {
            mAppBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.nav_home, R.id.nav_registieren,
                    R.id.nav_geraeteliste,
                    R.id.nav_abmelden, R.id.nav_share, R.id.nav_send, R.id.nav_anmelden)
                    .setDrawerLayout(drawer)
                    .build();
            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
            NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
            NavigationUI.setupWithNavController(navigationView, navController);


            navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
                @Override
                public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {

                    //TODO Einen Listener erstellen für das Navigations View !

                }
            });

        }
        if (firebaseAuth.getCurrentUser() != null){
            DrawerLayout drawerLayout = findViewById(R.id.drawer_layout_1);
            mAppBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.nav_home, R.id.nav_registieren,
                    R.id.nav_geraeteliste,
                    R.id.nav_abmelden, R.id.nav_share, R.id.nav_send, R.id.nav_anmelden)
                    .setDrawerLayout(drawerLayout)
                    .build();
            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
            NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
            NavigationUI.setupWithNavController(navigationView, navController);
            navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
                @Override
                public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {

                    //TODO Einen Listener erstellen für das Navigations View !

                }
            });
        }

    }

}
