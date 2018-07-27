package com.tah.touchaheart.app;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;

import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import android.widget.Toast;

import com.tah.touchaheart.R;
import com.tah.touchaheart.app.database.MyDBHandler;
import com.tah.touchaheart.app.database.Products;
import com.tah.touchaheart.app.service.MyService;
import com.tah.touchaheart.app.service.NotificationClicked;
import com.tah.touchaheart.app.service.UpdateMethods;

import me.tatarka.support.job.JobInfo;
import me.tatarka.support.job.JobScheduler;

public class MainTabbedActivity extends AppCompatActivity {
    NotificationCompat.Builder notification;
    private static final int uniqueID = 45;
    private static final int JOB_ID = 100;
    public static String editEmail;
    public static String url_address = "192.168.42.18"; // 192.168.42.57     wireless     192.168.137.8

    private JobScheduler mJobScheduler;
    MyDBHandler dbHandler;


    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tabbed);
        mJobScheduler = JobScheduler.getInstance(this);
        constructJob();


        dbHandler = new MyDBHandler(this, null, null, 1);
        dbHandler.refreshTable();


        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            editEmail = (String) bundle.get("message");

            Products products = new Products(editEmail);
            dbHandler.addProduct(products);

        }


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Touch A Heart");
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

    /*    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

//new method

/*
        UpdateMethods updateMethods = new UpdateMethods(UpdateMethods.context);

        String b = updateMethods.updateDatabaseNotif();

        Toast.makeText(this,b,Toast.LENGTH_LONG).show();

        */



    }


    private void constructJob() {

        JobInfo.Builder builder = new JobInfo.Builder(JOB_ID, new ComponentName(this, MyService.class));
        builder.setPeriodic(5000)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                .setPersisted(true);

        mJobScheduler.schedule(builder.build());

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_tabbed, menu);
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

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    Tab1Needs tab1Needs = new Tab1Needs();
                    return tab1Needs;
                case 1:
                    Tab2Donate tab2Donate = new Tab2Donate();
                    return tab2Donate;
                case 2:
                    Tab3Create tab3Create = new Tab3Create();
                    return tab3Create;
                default:
                    return null;
            }

        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "View";
                case 1:
                    return "Donate";
                case 2:
                    return "Create";
            }
            return null;
        }
    }
}
