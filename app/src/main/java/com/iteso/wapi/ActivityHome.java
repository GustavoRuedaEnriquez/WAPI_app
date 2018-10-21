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

public class ActivityHome extends AppCompatActivity {

    private FragmentUsuario fragmentUsuario;
    private FragmentHorario fragmentHorario;
    private FragmentCalificacion fragmentCalificacion;
    private FragmentPago fragmentPago;
    private FragmentTarea fragmentTarea;
    private FragmentConfiguracion fragmentConfiguracion;

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
        ViewPager mViewPager = findViewById(R.id.container);
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
                        fragmentUsuario= new FragmentUsuario();
                    return fragmentUsuario;
                case 1:
                    if (fragmentHorario == null)
                        fragmentHorario = new FragmentHorario();
                    return fragmentHorario;
                case 2:
                    if (fragmentCalificacion == null)
                        fragmentCalificacion = new FragmentCalificacion();
                    return fragmentCalificacion;
                case 3:
                    if (fragmentPago == null)
                        fragmentPago = new FragmentPago();
                    return fragmentPago;
                case 4:
                    if (fragmentTarea == null)
                        fragmentTarea = new FragmentTarea();
                    return fragmentTarea;
                case 5:
                    if (fragmentConfiguracion == null)
                        fragmentConfiguracion = new FragmentConfiguracion ();
                    return fragmentConfiguracion ;
                default:
                    return new FragmentUsuario();
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
                    return getString(R.string.section1);
                case 4:
                    return getString(R.string.section2);
                case 5:
                    return getString(R.string.section3);
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
                                fragmentHorario.onActivityResult(requestCode, resultCode, data);
                                break;
                            case 2:
                                fragmentCalificacion.onActivityResult(requestCode, resultCode, data);
                                break;
                            case 3:
                                fragmentPago.onActivityResult(requestCode, resultCode, data);
                                break;
                            case 4:
                                fragmentTarea.onActivityResult(requestCode, resultCode, data);
                                break;
                            case 5:
                                fragmentConfiguracion.onActivityResult(requestCode, resultCode, data);
                                break;
                        }
                    }
                }
        }
    }
}
