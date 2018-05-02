package com.example.camk1.app_tienda.Clases;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Ivan on 27/11/2016.
 */

public class Metodos {

    public static boolean compruebaConexion(Context context) {
        boolean connected = false;
        ConnectivityManager connec = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        // Recupera todas las redes (tanto mÃ³viles como wifi)
        NetworkInfo[] redes = connec.getAllNetworkInfo();
        for (int i = 0; i < redes.length; i++) {
            // Si alguna red tiene conexiÃ³n, se devuelve true
            if (redes[i].getState() == NetworkInfo.State.CONNECTED) {
                connected = true;
            }
        }
        return connected;
    }

}
