package com.example.zero.drawer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener {


    private Toolbar mToolbar;
    FragmentDrawer drawerFragment;
    private DrawerLayout mDrawerLayout;
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    ArrayList<parent> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    private Integer[] icon = { R.drawable.lap,
            R.drawable.acs,
            R.drawable.app,
            R.drawable.mtb,
            R.drawable.kap,
            R.drawable.ic_shoes,
            R.drawable.ic_sports,
            R.drawable.ic_bags,

    };

    private String[] name = { "Item 1","Item 2","Item 3","Item 4","Item 5","Item 6","Item 7","Item 8"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /* this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);*/

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        listDataHeader = new ArrayList<parent>();

        for (int i = 0; i < name.length; i++) {

            parent nav = new parent(icon[i],name[i]);

            listDataHeader.add(nav);
        }


        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        expListView = (ExpandableListView)findViewById(R.id.lvExp);

        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        //expListView.setOnChildClickListener(this);

        drawerFragment = (FragmentDrawer)
                getSupportFragmentManager()
                        .findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);

        // display the first navigation drawer view on app launch
        displayView(0);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        if(id == R.id.action_search){
             Toast.makeText(getApplicationContext(), "Search action is selected!", Toast.LENGTH_SHORT).show();
            //  right_drawer.(Gravity.RIGHT);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
    }

    private void displayView(int position) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        switch (position) {
            case 0:
                fragment = new HomeFragment();
                title = getString(R.string.title_home);
                break;

            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();

            // set the toolbar title
//            getSupportActionBar().setTitle(title);
        }
    }


    private void prepareListData() {
        //listDataHeader = new ArrayList<>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        List<String> cat1 = new ArrayList<String>();
        cat1.add("item");
        cat1.add("item");
        cat1.add("item");


        List<String> cat2 = new ArrayList<String>();
        cat2.add("item");
        cat2.add("item");
        cat2.add("item");
        cat2.add("item");
        cat2.add("item");
        cat2.add("item");
        cat2.add("item");
        cat2.add("item");


        List<String> cat3 = new ArrayList<String>();
        cat3.add("item");
        cat3.add("item");
        cat2.add("item");
        cat2.add("item");
        cat2.add("item");
        cat2.add("item");


        List<String> cat4 = new ArrayList<String>();
        cat4.add("item");
        cat4.add("item");
        cat4.add("item");
        cat4.add("item");
        cat4.add("item");
        cat4.add("item");
        cat4.add("item");
        cat4.add("item");


        List<String> cat5 = new ArrayList<String>();
        cat5.add("Pepsi");
        cat5.add("Cocacola");
        cat5.add("Thumpsup");

        List<String> cat6 = new ArrayList<String>();
        cat6.add("Vannila");
        cat6.add("Black forest");

        List<String> cat7 = new ArrayList<String>();
        cat7.add("item");
        cat7.add("item");
        cat7.add("item");

        List<String> cat8 = new ArrayList<String>();
        cat8.add("item");
        cat8.add("item");



        listDataChild.put(listDataHeader.get(0).getName(), cat1); // Header, Child data
        listDataChild.put(listDataHeader.get(1).getName(), cat2);
        listDataChild.put(listDataHeader.get(2).getName(), cat3);
        listDataChild.put(listDataHeader.get(3).getName(), cat4);
        listDataChild.put(listDataHeader.get(6).getName(), cat5);
        listDataChild.put(listDataHeader.get(7).getName(), cat6);
        listDataChild.put(listDataHeader.get(4).getName(), cat7);
        listDataChild.put(listDataHeader.get(5).getName(), cat8);


    }

}