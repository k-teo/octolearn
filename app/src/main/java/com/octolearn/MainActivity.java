package com.octolearn;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.octolearn.ui.data.CatalogDialog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener, CatalogDialog.DialogListener {

    private DrawerLayout drawer;
    private Button newButton;
    FirebaseAuth fAuth = FirebaseAuth.getInstance();
    LinearLayout layoutButtons;
    CatalogDialog catalogDialog = new CatalogDialog();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layoutButtons = (LinearLayout) findViewById(R.id.buttons_layout);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                                                        R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(this);

        NavigationView navigationView = findViewById(R.id.nav_view);

        View headerLayout = navigationView.getHeaderView(0);
        TextView whatEmail = headerLayout.findViewById(R.id.userEmail);
        whatEmail.setText(getEmail());


        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed(){
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        } else{
            super.onBackPressed();
        }
        super.onBackPressed();
    }

    public String getEmail() {
        return fAuth.getCurrentUser().getEmail();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_import:
                startActivity(new Intent(getApplicationContext(), CreateFlashcardActivity.class));
                break;
            case R.id.nav_export:
                startActivity(new Intent(getApplicationContext(), CreateCatalogActivity.class));
                break;
            case R.id.nav_statistics:
                startActivity(new Intent(getApplicationContext(), Statistics.class));
                break;
            case R.id.nav_friends:
                Toast.makeText(MainActivity.this, "Not available", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_settings:
                startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
                break;
            case R.id.nav_profile:
                startActivity(new Intent(getApplicationContext(), Profile.class));
                break;
            case R.id.nav_logout:
                logout();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.fab:
                openDialog();
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void logout(){
        FirebaseAuth.getInstance().signOut();
        LoginActivity.logout();
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
        finish();
    }

    public void addButton(String name){
        final View buttonView = getLayoutInflater().inflate(R.layout.row_catalog, null, false);
        Button button = (Button) buttonView.findViewById(R.id.button_catalog);
        button.setText(name);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, WayOfLearningActivity.class));
            }
        });
        layoutButtons.addView(buttonView);
    }

    public void openDialog(){
        catalogDialog.show(getSupportFragmentManager(), "catalogDialog");
    }

    @Override
    public void onYesClicked() {
        String name = catalogDialog.getName();
        if (name.equals("")){
            Toast.makeText(getApplicationContext(), "Name cannot be empty", Toast.LENGTH_SHORT).show();
        }
        else{
            addButton(name);
        }

    }
}