package pl.lenarczyk.rafal.pendingexam;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

public class BaseActivity extends AppCompatActivity {

    private ActionBarDrawerToggle mDrawerToogle;

    private DrawerLayout mFullView;

    private final NavigationView.OnNavigationItemSelectedListener mNavItemListener = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(final MenuItem item) {
            switch (item.getItemId()) {
                case R.id.nav_home:
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    break;
                case R.id.nav_exam_info:
                    startActivity(new Intent(getApplicationContext(), ExamInfo.class));
                    break;
                default:
                    return false;
            }
            mFullView.closeDrawers();
            item.setChecked(true);
            setTitle(item.getTitle());
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID) {
        mFullView = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_base, null);
        final FrameLayout activityContent = (FrameLayout) mFullView.findViewById(R.id.activity_content);
        getLayoutInflater().inflate(layoutResID, activityContent, true);
        super.setContentView(mFullView);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerToogle = new ActionBarDrawerToggle(this, mFullView, toolbar, R.string.drawer_open, R.string.drawer_close);
        mFullView.setDrawerListener(mDrawerToogle);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeButtonEnabled(true);
        final NavigationView navigationView = (NavigationView) findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(mNavItemListener);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_base, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mFullView.openDrawer(GravityCompat.START);
                return true;
        }
        if (mDrawerToogle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToogle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToogle.onConfigurationChanged(newConfig);
    }
}
