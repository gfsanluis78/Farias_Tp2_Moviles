package com.farias.farias_tp2_moviles;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.IBinder;
import android.provider.Telephony;
import android.util.Log;

public class ServicioMensajes extends Service {
    public ServicioMensajes() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }


    //
    @SuppressLint("Range")
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        // Declaro el hilo de manera anonima
        Thread tarea = new Thread(new Runnable() {
            @Override
            public void run() {


                Uri mensajes = Uri.parse("content://sms/inbox");
                ContentResolver cr = getContentResolver();

                while (true) {
                    Cursor c = cr.query(mensajes, null, null, null, null);

                    String dia = null;
                    String mensaje = null;

                    if (c != null && c.getCount() > 0) {
                        int i = 1;
                        while (c.moveToNext() && i < 6) {
                            dia = c.getString((c.getColumnIndex(Telephony.Sms._ID)));
                            mensaje = c.getString(c.getColumnIndex(Telephony.Sms.BODY));
                            Log.d("mensajes", dia + " " + mensaje);
                            i++;
                        }
                        try {
                            Thread.sleep(9000);
                        } catch (InterruptedException e) {
                            //e.printStackTrace();
                            break;
                        }
                    }
                    c.close();

                }


            }
        });
        tarea.start();  // Comienza el hilo
        return START_STICKY;
    }
}