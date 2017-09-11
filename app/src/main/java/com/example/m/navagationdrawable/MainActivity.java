package com.example.m.navagationdrawable;

import android.content.Context;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private TextView tvMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvMsg = (TextView) findViewById(R.id.tv_msg);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setHomeAsUpIndicator(R.mipmap.ic_menu);

        if (drawerToggle == null) {
            drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.mipmap.ic_menu, R.string.open, R.string.close) {
                public void onDrawerClosed(View view) {
                }

                public void onDrawerOpened(View drawerView) {

                }

                public void onDrawerSlide (View drawerView, float slideOffset) {
                }

                public void onDrawerStateChanged(int newState) {

                }

            };
            drawerLayout.setDrawerListener(drawerToggle);
        }

        drawerToggle.syncState();

        ListView rvNumbers = (ListView) findViewById(R.id.list);

        String [] numbers = {"One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
        ItemArrayAdapter itemArrayAdapter = new ItemArrayAdapter(this, R.layout.listitem, numbers);
        rvNumbers.setAdapter(itemArrayAdapter);
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle action buttons
        switch (item.getItemId()) {
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public class ItemArrayAdapter extends ArrayAdapter<String> {
        String[] itemList;
        private int listItemLayout;
        public ItemArrayAdapter(Context context, int layoutId, String[] itemList) {
            super(context, layoutId, itemList);
            listItemLayout = layoutId;
            this.itemList = itemList;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            int pos = position;
            final String item = getItem(pos);

            ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(listItemLayout, parent, false);
                viewHolder.item = (TextView) convertView.findViewById(R.id.tv_text);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.item.setText(item);
            viewHolder.item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tvMsg.setText(item);
                    drawerLayout.closeDrawer(Gravity.LEFT);
                }
            });
            return convertView;
        }
        class ViewHolder {
            TextView item;
        }
    }
}