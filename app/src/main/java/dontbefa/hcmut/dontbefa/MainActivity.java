package dontbefa.hcmut.dontbefa;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.LoginFilter;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

//import com.firebase.client.Firebase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("dontbefa_1", "0");
        setContentView(R.layout.activity_main);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(4);
        List<Fragment> lstFm = new ArrayList<>();
        createFragment(lstFm);

        SampleFragmentPagerAdapter pagerAdapter = new SampleFragmentPagerAdapter(getSupportFragmentManager(), lstFm, MainActivity.this);
        viewPager.setAdapter(pagerAdapter);

        // Give the TabLayout the ViewPager
        try {
            TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
            tabLayout.setupWithViewPager(viewPager);

            Log.d("dontbefa_1", "1");

            for (int i = 0; i < tabLayout.getTabCount(); i++) {
                TabLayout.Tab tab = tabLayout.getTabAt(i);
                int id = 0;
                switch (i) {
                    case 0: id = R.drawable.usericon;break;
                    case 1: id = R.drawable.search;break;
                    case 2: id = R.drawable.msg;break;
                    default: break;
                }
                if (tab != null) {
                    tab.setIcon(id);
                }
            }
        } catch (Exception ex) {
            Log.d("dontbefa_1", ex.getMessage());
        }
    }

    private void createFragment(List<Fragment> lstFm) {
        lstFm.add(FirstFragment.newInstance(null, null));
        lstFm.add(SecondFragment.newInstance(null, null));
        lstFm.add(ThirdFragment.newInstance(null, null));
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
