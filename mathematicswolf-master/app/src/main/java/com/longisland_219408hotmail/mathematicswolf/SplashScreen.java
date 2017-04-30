package com.longisland_219408hotmail.mathematicswolf;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.longisland_219408hotmail.mathematicswolf.permission.MultiplePermissionCallback;
import com.longisland_219408hotmail.mathematicswolf.permission.Permission;
import com.longisland_219408hotmail.mathematicswolf.utlils.PreferencesVar;

import java.util.List;

public class SplashScreen extends PermissionActivity {
    public static final String TAG = SplashScreen.class.getSimpleName();
    private Context context;
    private PreferencesVar preferencesVar;
    private TextView txt_permis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        context = this;
        txt_permis = (TextView) findViewById(R.id.am_txt_permiss);
        preferencesVar = new PreferencesVar(context);
        initPermisis();


    }

    void initSetUp() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);
    }

    void initPermisis() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            permisos();
            if (preferencesVar.getValuePermiss()) initSetUp();
        } else initSetUp();
    }

    private void permisos() {
        Permission[] permissions = new Permission[]{
                Permission.CAMERA, Permission.WRITE_EXTERNAL_STORAGE, Permission.READ_EXTERNAL_STORAGE
        };

        requestPermissions(permissions, new MultiplePermissionCallback() {
            @Override
            public void onPermissionGranted(boolean allPermissionsGranted, List<Permission> grantedPermissions) {
                Log.e(TAG, "Todos los permisos se conceden  = " + allPermissionsGranted);
                if (allPermissionsGranted) {
                    preferencesVar.setValuePermiss(true);
                    initSetUp();
                } else {
                    Toast.makeText(context, getString(R.string.faltan_permisos), Toast.LENGTH_SHORT);
                    txt_permis.setText(getString(R.string.info_permisos));
                }
                for (Permission grantedPermission : grantedPermissions) {
                    Log.e(TAG, "Permisos otorgados " + grantedPermission);

                }
            }

            @Override
            public void onPermissionDenied(List<Permission> deniedPermissions, List<Permission> foreverDeniedPermissions) {
                for (Permission deniedPermission : deniedPermissions) {
                    Log.e(TAG, "Permisos denegados " + deniedPermission);
                    preferencesVar.setValuePermiss(false);
                    Toast.makeText(context, getString(R.string.info_permisos), Toast.LENGTH_SHORT);
                }

                for (Permission deniedPermission : foreverDeniedPermissions) {
                    Log.e(TAG, "Perdidos permisos para siempre" + deniedPermission);
                }
            }
        });
    }
}
