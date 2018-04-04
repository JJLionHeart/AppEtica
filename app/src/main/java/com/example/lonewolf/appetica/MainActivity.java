package com.example.lonewolf.appetica;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        TutorialsFragment.OnFragmentInteractionListener,
        QuestionaryFragment.OnFragmentInteractionListener,
        HelpFragment.OnFragmentInteractionListener,
        QuestionsFragment.OnFragmentInteractionListener,
        TutorialMeditacion.OnFragmentInteractionListener,
        TutorialRespiracion.OnFragmentInteractionListener,
        ResultsFragment.OnFragmentInteractionListener {

    private Fragment active_fragment = null;

    private TutorialsFragment tutorials_fragment = null;
    private HelpFragment help_fragment = null;
    private QuestionaryFragment questionary_fragment = null;

    private CharSequence title_tutorials = "Tutoriales";
    private CharSequence title_questionary = "Autodiagnóstico";
    private CharSequence title_help = "Buscar ayuda";

    private CharSequence title_active = null;

    private boolean selected_fragment = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        tutorials_fragment = new TutorialsFragment();
        questionary_fragment = new QuestionaryFragment();
        help_fragment = new HelpFragment();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_container, tutorials_fragment).commit();

        title_active = title_tutorials;
        setTitle(title_active);
        navigationView.getMenu().getItem(0).setChecked(true);
    }

    @Override
    public void onBackPressed() {
        // Override Transition

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();

        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

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

        if (id == R.id.nav_tutorials) {
            selected_fragment = true;
            active_fragment = tutorials_fragment;
            title_active = title_tutorials;
        } else if (id == R.id.nav_questionary) {
            selected_fragment = true;
            active_fragment = questionary_fragment;
            title_active = title_questionary;
        } else if (id == R.id.nav_help) {
            selected_fragment = true;
            active_fragment  = help_fragment;
            title_active = title_help;
        }

        if(selected_fragment) {
            getSupportFragmentManager().beginTransaction().replace(R.id.main_container, active_fragment).commit();
            selected_fragment = false;
            setTitle(title_active);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public TutorialsFragment getTutorials_fragment() {
        return tutorials_fragment;
    }

    public HelpFragment getHelp_fragment() {
        return help_fragment;
    }

}
