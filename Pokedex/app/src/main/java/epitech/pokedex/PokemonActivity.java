package epitech.pokedex;

import android.app.FragmentManager;
import android.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class PokemonActivity extends AppCompatActivity {

    private ListView mDrawerList;
    private ArrayAdapter<String> mAdapter;
    private String[] pkArray = { "Pokemon", "Items", "Berries"};
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon);
        mDrawerList = (ListView)findViewById(R.id.navList);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        addDrawerItems();
        getSupportActionBar().setTitle("Pokemon");
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (pkArray[((int) id)] == "Items") {
                    Fragment fragment = new ItemListFragment();
                    Bundle args = new Bundle();
                    fragment.setArguments(args);
                    selectView(position, fragment, "Items");
                } else if (pkArray[((int) id)] == "Berries") {
                    
                } else if (pkArray[((int) id)] == "Pokemon") {

                }
            }
        });
    }

    private void addDrawerItems() {
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, pkArray);
        mDrawerList.setAdapter(mAdapter);
    }

    /** Swaps fragments in the main content view */
    private void selectView(int position, Fragment fragment, String title) {


        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();

        // Highlight the selected item, update the title, and close the drawer
        mDrawerList.setItemChecked(position, true);
        getSupportActionBar().setTitle(title);
        mDrawerLayout.closeDrawer(mDrawerList);
    }
}
