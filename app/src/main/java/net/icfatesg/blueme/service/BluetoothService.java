package net.icfatesg.blueme.service;

import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import static android.content.ContentValues.TAG;

/**
 * Created by harlock on 11/11/17.
 */

public class BluetoothService extends Service {
    private BluetoothAdapter mBluetoothAdapter;
    public static final String SECURE_SETTINGS_BLUETOOTH_ADDRESS = "bluetooth_address";
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

    public static String getBluetoothMacAddress(Context mContext) {

        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        // BluetoothAdapter.getDefaultAdapter().DEFAULT_MAC_ADDRESS;
        // if device does not support Bluetooth
        if (mBluetoothAdapter == null) {
            Log.d(TAG, "device does not support bluetooth");
            return null;
        }

        String address = mBluetoothAdapter.getAddress();
        if (address.equals("02:00:00:00:00:00")) {
            //  System.out.println(">>>>>G fail to get mac address " + address);

            try {

                ContentResolver mContentResolver = mContext.getContentResolver();

                address = Settings.Secure.getString(mContentResolver, SECURE_SETTINGS_BLUETOOTH_ADDRESS);

            } catch (Exception e) {

            }


        } else {

            // System.out.println(">>>>>G sucess to get mac address " + address);
        }
        return address;
    }
}
