package net.icfatesg.blueme.activitie;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import net.icfatesg.blueme.R;
import net.icfatesg.blueme.fragment.EventoFragment;
import net.icfatesg.blueme.fragment.InicioFragment;
import net.icfatesg.blueme.fragment.MinhaContaFragment;
import net.icfatesg.blueme.fragment.VerDetalhesEventoFragment;
import net.icfatesg.blueme.model.Evento;
import net.icfatesg.blueme.service.Autenticacao;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivityPrincipal extends AppCompatActivity {

    @BindView(R.id.navigation)
    BottomNavigationView navigation;
    private FragmentManager mFragmentoManager;
    Fragment mFragmento;
    private AlertDialog.Builder mDialog;
    private Intent intent;
    public static ActivityPrincipal mActivityPrincipal;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_inico:
                    loadFragmentInicio();
                    return true;
                case R.id.navigation_eventos:
                    loadFragmentEventos();
                    return true;
                case R.id.navigation_minha_conta:
                    loadFragmentMinhaConta();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        ButterKnife.bind(this);
        mFragmentoManager  = this.getSupportFragmentManager();
        mFragmento = new InicioFragment();
        mFragmentoManager.beginTransaction().add(R.id.frameLayoutPrincipal,mFragmento).commit();
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mDialog = new AlertDialog.Builder(this);
        mActivityPrincipal = this;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_topo, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.deslogar) {
            mDialog.setMessage("Deseja realmente sair?");
            mDialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    new Autenticacao(null).deslogar();
                    intent = new Intent(ActivityPrincipal.this,LoginActivity.class);
                    startActivity(intent);
                }
            });
            mDialog.setNegativeButton("Continuar Logado",null);
            mDialog.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void loadFragmentInicio(){
        mFragmento = new InicioFragment();
        mFragmentoManager.beginTransaction().replace(R.id.frameLayoutPrincipal,mFragmento).commit();
    }
    private void loadFragmentEventos(){
        mFragmento = new EventoFragment();
        mFragmentoManager.beginTransaction().replace(R.id.frameLayoutPrincipal,mFragmento).commit();
    }
    private void loadFragmentMinhaConta(){
        intent = new Intent(this,MinhaContaActivity.class);
        startActivity(intent);
    }
    public void loadDetalhesEvento(Evento evento){
        this.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayoutPrincipal, VerDetalhesEventoFragment.newInstance(evento))
                .addToBackStack(null).commit();
//        this.mFragmentoManager.beginTransaction().replace(R.id.frameLayoutPrincipal,VerDetalhesEventoFragment.newInstance(evento))
//        .addToBackStack("Lista de Eventos").commit();
    }

}
