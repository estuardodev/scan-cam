package com.stuarddevapps.scancam;

import android.content.Context;
import android.util.Log;
import java.io.FileOutputStream;

public class Archivos{

    public void Escribir(Context context,String TextoAEscribir, String Archivo){
        FileOutputStream fileOutputStream = null;
        try {
            String textoASalvar = TextoAEscribir + "\n";
            // Abre el archivo en modo MODE_APPEND para añadir contenido al final
            fileOutputStream = context.openFileOutput(Archivo, context.MODE_APPEND);
            // Escribe la nueva línea en el archivo
            fileOutputStream.write(textoASalvar.getBytes());
            Log.d("TAG1", "Fichero salvado:" + context.getFilesDir() + "/" + Archivo);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void Vaciar(Context context, String Archivo){
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = context.openFileOutput(Archivo, context.MODE_PRIVATE);
            Log.d("TAG1", "Archivo vaciado:" + context.getFilesDir() + "/" + Archivo);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
