package com.example.devan.remedaily.View;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.devan.remedaily.R;
import com.example.devan.remedaily.datalayer.AppDatabase;

import java.util.ArrayList;

import static com.example.devan.remedaily.businesslayer.HamburgerBusinessLayer.ShowUserInfo;

public class Hamburger extends AppCompatActivity {
    private TextView txtView,userName;
    public Button userDetailsBtn;
    private static String TAG = Hamburger.class.getSimpleName();
    ListView drawerListView;
    RelativeLayout drawerPane;
    private ActionBarDrawerToggle drawerToggle;
    private DrawerLayout drawerLayout;
    public AppDatabase appData;


    ArrayList<NavigationItem> navigationItems = new ArrayList<NavigationItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtView = findViewById(R.id.txtView);
        appData = AppDatabase.getInMemoryDatabase(getApplicationContext());
        userName = findViewById(R.id.userName);
        userDetailsBtn = findViewById(R.id.userDetailsBtn);
        try {
            /*Gets the user data from DB. */
            userName.setText(ShowUserInfo(appData));
        } catch (Exception e) {
            e.printStackTrace();
        }
        userDetailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Hamburger.this, UserDetails.class);
                startActivity(intent);
            }
        });
        navigationItems.add(new NavigationItem("My Meds", "", R.drawable.pill_icon_white_128));
        navigationItems.add(new NavigationItem("Calender", "", R.drawable.calender_icon_white_128));
        navigationItems.add(new NavigationItem("Settings", "", R.drawable.settings_icon_white_128));

        // DrawerLayout
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        // Populate the Navigtion Drawer with options
        drawerPane = (RelativeLayout) findViewById(R.id.drawerPane);

        drawerListView = (ListView) findViewById(R.id.navList);
        DrawerListAdapter drawerListAdapter = new DrawerListAdapter(this, navigationItems);
        drawerListView.setAdapter(drawerListAdapter);

        // Drawer Item click listeners
        drawerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItemFromDrawer(position);
            }
        });

        // click on hamburger icon or back arrow
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                Log.d(TAG, "onDrawerClosed: " + getTitle());

                invalidateOptionsMenu();
            }
        };

        drawerLayout.addDrawerListener(drawerToggle);

//        // Called when invalidateOptionsMenu() is invoked
//        public boolean onPrepareOptionsMenu(Menu menu) {
//            // If the nav drawer is open, hide action items related to the content view
//            boolean drawerOpen = drawerLayout.isDrawerOpen(drawerList);
//            menu.findItem(R.id.action_search).setVisible(!drawerOpen);
//            return super.onPrepareOptionsMenu(menu);
//        }

        // More info: http://codetheory.in/difference-between-setdisplayhomeasupenabled-sethomebuttonenabled-and-setdisplayshowhomeenabled/
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle
        // If it returns true, then it has handled
        // the nav drawer indicator touch event
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    /*
     * Called when a particular item from the navigation drawer
     * is selected.
     * */
    private void selectItemFromDrawer(int position) {
        switch (position){
            case 0 :
                // first menu item clicked
                // create appropriate intention
                break;
            case 1 :
                // second menu item clicked
                // create appropriate intention
                break;
            case 2 :
                // thirdt menu item clicked
                // create appropriate intention
                break;
        }

//        drawerListView.setItemChecked(position, true);
//        setTitle(navigationItems.get(position).title);

        // start new activity

        // Close the drawer
        drawerLayout.closeDrawer(drawerPane);
    }

    // This class is used to represent drawer menu items
    class NavigationItem {
        String title;
        String subtitle;
        int icon;

        public NavigationItem(String _title, String _subtitle, int _iconId) {
            title = _title;
            subtitle = _subtitle;
            icon = _iconId;
        }
    }

    // This class is binded to ListView for the sake of drawer menu population
    class DrawerListAdapter extends BaseAdapter {

        Context context;
        ArrayList<NavigationItem> navigationItems;

        public DrawerListAdapter(Context _context, ArrayList<NavigationItem> _navigationItems) {
            context = _context;
            navigationItems = _navigationItems;
        }

        @Override
        public int getCount() {
            return navigationItems.size();
        }

        @Override
        public Object getItem(int position) {
            return navigationItems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;

            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.drawer_item, null);
            }
            else {
                view = convertView;
            }

            TextView titleView = (TextView) view.findViewById(R.id.drawerMenuItemTitle);
            ImageView iconView = (ImageView) view.findViewById(R.id.drawerMenuItemIcon);

            titleView.setText( navigationItems.get(position).title );
            iconView.setImageResource(navigationItems.get(position).icon);

            return view;
        }
    }
}

