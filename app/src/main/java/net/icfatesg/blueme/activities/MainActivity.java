package net.icfatesg.blueme.activities;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import net.icfatesg.blueme.Fragments.OficinasVisitadasFragment;
import net.icfatesg.blueme.R;
import net.icfatesg.blueme.services.BluetoothService;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private BluetoothAdapter mBluetoothAdapter;
    private FragmentManager manager;
    Fragment fragmento;
    public static String LOAD_HOME = "HOME";
    public static String LOAD_EVENTOS ="EVENTOS";
    public static String LOAD_CONTA = "CONTA";

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    loadFragmentoInicio();
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    loadFRagmentoEventos();
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    loadFragmentoConta();
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService(new Intent(this, BluetoothService.class));
        ButterKnife.bind(this);
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        mBluetoothAdapter.enable();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //
        manager   = this.getSupportFragmentManager();
        fragmento = manager.findFragmentById(R.id.contentMainReplace);
        if (fragmento == null){
            fragmento = new OficinasVisitadasFragment();
            manager.beginTransaction().add(R.id.contentMainReplace,fragmento).commit();
        }

    }

    public void loadFragmentoInicio(){

    }

    public void loadFRagmentoEventos(){

    }
    public void loadFragmentoConta(){

    }

}
