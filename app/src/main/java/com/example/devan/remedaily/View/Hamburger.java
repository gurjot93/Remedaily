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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.devan.remedaily.R;
import com.example.devan.remedaily.datalayer.AppDatabase;

import java.util.ArrayList;

import static com.example.devan.remedaily.businesslayer.HamburgerBusinessLayer.ShowUserInfo;
import static com.example.devan.remedaily.businesslayer.UserDetailsBusinessLayer.IsUserPresent;

public class Hamburger extends AppCompatActivity {
    private static String TAG = Hamburger.class.getSimpleName();
    TextView userName;
    ListView drawerListView;
    RelativeLayout drawerPane;
    private ActionBarDrawerToggle drawerToggle;
    private DrawerLayout drawerLayout;
    AppDatabase appData;

    ArrayList<NavigationItem> navigationItems = new ArrayList<NavigationItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.add_new_medicine_activity);
        userName = findViewById(R.id.userName);
//        navigationItems.add(new NavigationItem("My Meds", "", R.drawable.pill_icon_white_128));
        appData = AppDatabase.getInMemoryDatabase(getApplicationContext());

        if (IsUserPresent(appData)) {
            navigationItems.add(new NavigationItem("Edit User", "", R.drawable.user_2));
        } else {
            navigationItems.add(new NavigationItem("Add User", "", R.drawable.new_user));
        }
        /*navigationItems.add(new NavigationItem("Add User", "", R.drawable.new_user));
        navigationItems.add(new NavigationItem("Edit User", "", R.drawable.user_2));*/
        navigationItems.add(new NavigationItem("Calender", "", R.drawable.calendar_icon_white_128));
        navigationItems.add(new NavigationItem("Settings", "", R.drawable.settings_icon_white_128));
        navigationItems.add(new NavigationItem("Help and Support", "", R.drawable.customer_support));
        navigationItems.add(new NavigationItem("About Us", "", R.drawable.about_us_icon));
        appData = AppDatabase.getInMemoryDatabase(getApplicationContext());
        try {
            userName.setText(ShowUserInfo(appData));
        } catch (Exception e) {
            e.printStackTrace();
        }

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

    @Override
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
        Intent intent;
        switch (position) {
//            case 0:
//                // first menu item clicked
//                // create appropriate intention
//
//                break;
            case 0:
                // second menu item clicked
                // create appropriate intention
                if (IsUserPresent(appData)) {
                    intent = new Intent(this, EditUserDetails.class);
                    startActivity(intent);
                }
                else{
                    intent = new Intent(this, UserDetails.class);
                    startActivity(intent);
                }
                break;
            case 1:
                // third menu item clicked
                // create appropriate intention
                intent = new Intent(this, Calender.class);
                startActivity(intent);
                break;
            case 2:
                // third menu item clicked
                // create appropriate intention
                intent = new Intent(this, SettingsHome.class);
                startActivity(intent);
                break;
            case 3:
                intent = new Intent(this, Help.class);
                startActivity(intent);
                break;
            case 4:
                intent = new Intent(this, About_Us.class);
                startActivity(intent);
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
            } else {
                view = convertView;
            }

            TextView titleView = (TextView) view.findViewById(R.id.drawerMenuItemTitle);
            ImageView iconView = (ImageView) view.findViewById(R.id.drawerMenuItemIcon);

            titleView.setText(navigationItems.get(position).title);
            iconView.setImageResource(navigationItems.get(position).icon);

            return view;
        }
    }
}

