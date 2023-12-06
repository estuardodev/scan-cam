package com.stuarddevapps.scancam;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

public class Utilidades {


    public static void CopiarPortapapeles(Context context, String texto){
        Toast.makeText(context, context.getString(R.string.CopyScan), Toast.LENGTH_LONG).show();
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("simple text",  texto);
        clipboard.setPrimaryClip(clip);
    }

    public static String ObtenerDatoIntent(Context context, String key){
        try {
            Bundle bundle = ((Activity) context).getIntent().getExtras();
            String datoenlace = bundle.getString(key).toString();
            return datoenlace;
        }catch (Exception e){
            e.printStackTrace();
            return "No se pudo escanear";
        }
    }

}
