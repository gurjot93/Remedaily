package com.example.devan.remedaily;

import android.app.FragmentManager;
import android.content.Context;
import android.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static String TAG = MainActivity.class.getSimpleName();

    ListView drawerListView;
    RelativeLayout drawerPane;
    private ActionBarDrawerToggle drawerToggle;
    private DrawerLayout drawerLayout;

    ArrayList<NavigationItem> navigationItems = new ArrayList<NavigationItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationItems.add(new NavigationItem("Home", "Meetup destination", R.drawable.ic_home));
        navigationItems.add(new NavigationItem("Preferences", "Change your preferences", R.drawable.ic_settings));
        navigationItems.add(new NavigationItem("About", "Get to know about us", R.drawable.ic_info));

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

        drawerLayout.setDrawerListener(drawerToggle);

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
        Fragment fragment = new PreferencesFragment();

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.mainContent, fragment)
                .commit();

        drawerListView.setItemChecked(position, true);
        setTitle(navigationItems.get(position).title);

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
            TextView subtitleView = (TextView) view.findViewById(R.id.drawerMenuItemSubTitle);
            ImageView iconView = (ImageView) view.findViewById(R.id.drawerMenuItemIcon);

            titleView.setText( navigationItems.get(position).title );
            subtitleView.setText( navigationItems.get(position).subtitle );
            iconView.setImageResource(navigationItems.get(position).icon);

            return view;
        }
    }
}
