package net.icfatesg.blueme.activities;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import net.icfatesg.blueme.Fragments.EventosFragment;
import net.icfatesg.blueme.Fragments.MinhaContaFragment;
import net.icfatesg.blueme.Fragments.OficinasVisitadasFragment;
import net.icfatesg.blueme.R;
import net.icfatesg.blueme.model.OficinaVisitada;
import net.icfatesg.blueme.model.Usuario;
import net.icfatesg.blueme.services.BluetoothService;
import net.icfatesg.blueme.services.FireBase;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private BluetoothAdapter mBluetoothAdapter;
    private FragmentManager manager;
    Fragment fragmento;
    public static String LOAD_HOME = "HOME";
    public static String LOAD_EVENTOS ="EVENTOS";
    public static String LOAD_CONTA = "CONTA";
    private FireBase fireBase;
    private FirebaseAuth mAuth;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:

                    loadFragmentoInicio();
                    return true;
                case R.id.navigation_dashboard:
                    loadFRagmentoEventos();
                    return true;
                case R.id.navigation_notifications:
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
        mAuth = FirebaseAuth.getInstance();

        OficinaVisitada ov = new OficinaVisitada();
        ov.setHoraEntrada("15:00:44");
        ov.setHoraSaida("17:01:32");
        ov.setNomeEvento("Iv Forúm Goiano - FATESG");
        ov.setIDEVENTO("-KmRw9G1kvhRepmUCa03");
        ov.setIDOFICINA("-KmRw9ISTXDS0mMG8Nbn");
        ov.setNomeOFICINA("Machine Learning");
        ov.setMac("5A:8A:0E:F2:F2:77");
        ov.setID("-KmRw9GlFG2Ji1FYi_sO");

        // Insere o MAC do usuário
        fireBase = new FireBase();
        fireBase.salvarOfic(ov);

        fireBase.getUsuario(new FireBase.CallbackUsuario() {
            @Override
            public void getUsuario(Usuario usuario) {
                if(usuario == null){
                    Usuario u = new Usuario();
                    u.setBluetoothMAC(new BluetoothService().getBluetoothMacAddress(MainActivity.this));
                    fireBase.updateUsuario(u);
                }
                if (usuario.getBluetoothMAC() == null){
                    usuario.setBluetoothMAC(new BluetoothService().getBluetoothMacAddress(MainActivity.this));
                    fireBase.updateUsuario(usuario);
                }
            }
        });


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
        fragmento = new OficinasVisitadasFragment();
        manager.beginTransaction().replace(R.id.contentMainReplace,fragmento).commit();
    }

    public void loadFRagmentoEventos(){
        fragmento = new EventosFragment();
        manager.beginTransaction().replace(R.id.contentMainReplace,fragmento).commit();

    }
    public void loadFragmentoConta(){
        fragmento = new MinhaContaFragment();
        manager.beginTransaction().replace(R.id.contentMainReplace,fragmento).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menutopo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.sair) {
            mAuth.signOut();
            startActivity(new Intent(this,Login.class));
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
