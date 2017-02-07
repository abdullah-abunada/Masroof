package ps.source.abdullah.masroof;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import ps.source.abdullah.masroof.expenses.ExpensesFragment;
import ps.source.abdullah.masroof.shopping.ShoppingFragment;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set custom arabic font
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/droid-sans.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
        //call main app activity layout
        setContentView(R.layout.activity_main);

        //setup Toolbar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        //set listener for android bottom nav
        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();

                switch (item.getItemId()) {
                    case R.id.action_expenses:
                        ExpensesFragment expensesFragment = new ExpensesFragment();
                        transaction.replace(R.id.fragment_container, expensesFragment);
                        transaction.commit();
                        break;

                    case R.id.action_shopping:
                        ShoppingFragment shoppingFragment = new ShoppingFragment();
                        transaction.replace(R.id.fragment_container, shoppingFragment);
                        transaction.commit();
                        break;

                    case R.id.action_saving:
                        Log.i("screen", "3");
                        break;


                    case R.id.action_statistics:
                        Log.i("screen", "4");

                        break;

                }
                return true;
            }
        });

        //set default fragment
        ExpensesFragment expensesFragment = new ExpensesFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, expensesFragment).commit();

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Toast.makeText(this, "EMail", Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }
        return true;
    }


}
