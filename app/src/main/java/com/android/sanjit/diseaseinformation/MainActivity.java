package com.android.sanjit.diseaseinformation;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        MainFragment.MainFragmentListner,
        DataFragment.OnFragmentInteractionListener,
        SearchForDisease.SearchFragmentListener{

    NavigationView navigationView = null;
    Toolbar toolbar = null;
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Instance of database handler
        db = DatabaseHandler.getInstance(this);
        inserIntoDB();

        InfoFragment infoFragment = new InfoFragment();
        android.support.v4.app.FragmentTransaction fragmentTransaction =
                getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, infoFragment,infoFragment.getTag());
        fragmentTransaction.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            InfoFragment infoFragment = new InfoFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, infoFragment,infoFragment.getTag());
            fragmentTransaction.commit();
        } else if (id == R.id.nav_gallery) {
            MainFragment fragment = new MainFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment,fragment.getTag());
            fragmentTransaction.commit();

        } else if (id == R.id.nav_slideshow) {
            DeleteDiseaseInfo deleteDiseaseInfo = DeleteDiseaseInfo.newInstance("oyndrila","chowdhury");
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment_container,deleteDiseaseInfo,deleteDiseaseInfo.getTag()).commit();

        } else if (id == R.id.nav_manage) {
            SearchForDisease searchForDisease = SearchForDisease.newInstance("oyndrila","chowdhury");
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment_container,searchForDisease,searchForDisease.getTag()).commit();

        } else if (id == R.id.nav_share) {
            CallDoctor callDoctor = CallDoctor.newInstance("oyndrila","chowdhury");
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment_container,callDoctor,callDoctor.getTag()).commit();

        } else if (id == R.id.nav_send) {
            EmergencyHelp emergencyHelp = EmergencyHelp.newInstance("oyndrila","chowdhury");
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment_container,emergencyHelp,emergencyHelp.getTag()).commit();

        }else if (id == R.id.nav_about) {
            DataFragment dataFragment = DataFragment.newInstance(new Disease());
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment_container,dataFragment,dataFragment.getTag()).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void insertData(String name, String type, String symptoms,String cause, String faid, String treat) {

        if(name.equals("")||type.equals("")||symptoms.equals("")||cause.equals("")||faid.equals("")||treat.equals("")){
            Toast.makeText(this,"Enter All Data",Toast.LENGTH_LONG).show();
            return;
        }
        db.adddisease(new Disease(name,type,symptoms,cause,faid,treat));
    }

    @Override
    public void getDisease(String name) {

        List<Disease> diseases = db.getAlldiseases();
        boolean found = false;
        Disease target = new Disease();
        for(Disease d:diseases){
            if(d.getName().equals(name)){
                found = true;
                target = d;
                break;
            }
        }
        if(found){
            Toast.makeText(this,name,Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
    //Increase the number of data to show more information
    public void inserIntoDB(){
        db.adddisease(new Disease("Fever","Cold","Warm Body","Virus","Rest","Paracetamol"));
        db.adddisease(new Disease("Cough","Cold","Soar Throat"));
        db.adddisease(new Disease("Migraine","Deformity","Headache"));
    }
}
