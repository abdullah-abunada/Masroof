package ps.source.abdullah.masroof;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

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

        //set listener for android bottom nav
        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_expenses:
                        Log.i("screen","1");

                        break;

                    case R.id.action_shopping:
                        Log.i("screen","2");
                        break;

                    case R.id.action_saving:
                        Log.i("screen","3");
                        break;


                    case R.id.action_statistics:
                        Log.i("screen","4");

                        break;

                }
                return true;
            }
        });

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
