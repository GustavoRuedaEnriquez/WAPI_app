package com.iteso.wapi;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.TabLayout;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.iteso.wapi.fragments.FragmentConfiguration;
import com.iteso.wapi.fragments.FragmentGrades;
import com.iteso.wapi.fragments.FragmentHomework;
import com.iteso.wapi.fragments.FragmentPayment;
import com.iteso.wapi.fragments.FragmentSchedule;
import com.iteso.wapi.fragments.FragmentUsers;

public class ActivityHome extends AppCompatActivity {

    private FragmentUsers fragmentUsuario;
    private FragmentSchedule fragmentSchedule;
    private FragmentGrades fragmentCalificacion;
    private FragmentPayment fragmentPayment;
    private FragmentHomework fragmentTarea;
    private FragmentConfiguration fragmentConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.activity_home_toolbar);
        TabLayout tabLayout = findViewById(R.id.activity_home_tabs);

        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        SectionsPagerAdapter mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        ViewPager mViewPager = findViewById(R.id.activity_home_container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        tabLayout.setupWithViewPager(mViewPager);

    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position) {
                case 0:
                    if (fragmentUsuario == null)
                        fragmentUsuario= new FragmentUsers();
                    return fragmentUsuario;
                case 1:
                    if (fragmentSchedule == null)
                        fragmentSchedule = new FragmentSchedule();
                    return fragmentSchedule;
                case 2:
                    if (fragmentCalificacion == null)
                        fragmentCalificacion = new FragmentGrades();
                    return fragmentCalificacion;
                case 3:
                    if (fragmentPayment == null)
                        fragmentPayment = new FragmentPayment();
                    return fragmentPayment;
                case 4:
                    if (fragmentTarea == null)
                        fragmentTarea = new FragmentHomework();
                    return fragmentTarea;
                case 5:
                    if (fragmentConfiguration == null)
                        fragmentConfiguration = new FragmentConfiguration();
                    return fragmentConfiguration;
                default:
                    return new FragmentUsers();
            }
        }

        @Override
        public int getCount() {
            return 6;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.section1);
                case 1:
                    return getString(R.string.section2);
                case 2:
                    return getString(R.string.section3);
                case 3:
                    return getString(R.string.section4);
                case 4:
                    return getString(R.string.section5);
                case 5:
                    return getString(R.string.section6);
            }
            return null;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //El adapter tiene que mandar el codigo correcto
        if(requestCode == 999){
                if(resultCode == RESULT_OK){
                    if(data.getExtras() != null) {
                        ///el adapter tiene que mandar la etiqueta que en este caso debe ser el de fragmento y el numero de frag
                        int fragment = data.getExtras().getInt("Fragment");
                        switch (fragment) {
                            case 0:
                                fragmentUsuario.onActivityResult(requestCode, resultCode, data);
                                break;
                            case 1:
                                fragmentSchedule.onActivityResult(requestCode, resultCode, data);
                                break;
                            case 2:
                                fragmentCalificacion.onActivityResult(requestCode, resultCode, data);
                                break;
                            case 3:
                                fragmentPayment.onActivityResult(requestCode, resultCode, data);
                                break;
                            case 4:
                                fragmentTarea.onActivityResult(requestCode, resultCode, data);
                                break;
                            case 5:
                                fragmentConfiguration.onActivityResult(requestCode, resultCode, data);
                                break;
                        }
                    }
                }
        }
    }
}
