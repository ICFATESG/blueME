package net.icfatesg.blueme.services;

/**
 * Created by minerthal on 12/06/17.
 */
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class BluetoothService extends Service {
    private BluetoothAdapter mBluetoothAdapter;
    public BluetoothService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if (mBluetoothAdapter.disable()) {
            mBluetoothAdapter.enable();
            Toast.makeText(this, "BluetoothService Ligado com sucesso", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onStart(Intent intent, int startId) {
        // For time consuming an long tasks you can launch a new thread here...
        // Do your BluetoothService Work Here
        Toast.makeText(this, " Service Started", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onDestroy() {
        mBluetoothAdapter.disable();
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();

    }
}
