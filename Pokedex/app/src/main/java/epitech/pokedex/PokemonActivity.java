package epitech.pokedex;

import android.app.FragmentManager;
import android.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import epitech.pokedex.fragments.BerryDetailFragment;
import epitech.pokedex.fragments.BerryListFragment;
import epitech.pokedex.fragments.ItemDetailFragment;
import epitech.pokedex.fragments.ItemListFragment;
import epitech.pokedex.fragments.PokemonDetailFragment;
import epitech.pokedex.fragments.PokemonListFragment;

public class PokemonActivity extends AppCompatActivity {

    private ListView mDrawerList;
    private ArrayAdapter<String> mAdapter;
    private String[] pkArray = { "Pokemon", "Items", "Berries", "Berry"};
    private DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon);
        mDrawerList = (ListView)findViewById(R.id.navList);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        addDrawerItems();
        getSupportActionBar().setTitle("Pokedex");
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (pkArray[((int) id)] == "Items") {
                    Fragment fragment = new ItemListFragment();
                    Bundle args = new Bundle();
                    fragment.setArguments(args);
                    selectView(position, fragment, "Items");
                } else if (pkArray[((int) id)] == "Berries") {
                    Fragment fragment = new BerryListFragment();
                    Bundle args = new Bundle();
                    fragment.setArguments(args);
                    selectView(position, fragment, "Berries");
                } else if (pkArray[((int) id)] == "Pokemon") {
                    Fragment fragment = new PokemonListFragment();
                    Bundle args = new Bundle();
                    fragment.setArguments(args);
                    selectView(position, fragment, "Pokemon");
                }
                else if (pkArray[((int) id)] == "Berry") {
                    Fragment fragment = new BerryDetailFragment();
                    Bundle args = new Bundle();
                    fragment.setArguments(args);
                    selectView(position, fragment, "Berry Example");
                }
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);

                invalidateOptionsMenu();
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        return super.onPrepareOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void addDrawerItems() {
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, pkArray);
        mDrawerList.setAdapter(mAdapter);
    }

    private void selectView(int position, Fragment fragment, String title) {

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();

        mDrawerList.setItemChecked(position, true);
        getSupportActionBar().setTitle(title);
        mDrawerLayout.closeDrawer(mDrawerList);
    }
}
