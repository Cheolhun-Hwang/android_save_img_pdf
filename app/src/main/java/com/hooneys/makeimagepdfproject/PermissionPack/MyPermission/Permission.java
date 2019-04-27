package com.hooneys.makeimagepdfproject.PermissionPack.MyPermission;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;

public class Permission {
    private static String[] permissions ={
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
    };

    public boolean checkPermission(Context context, String permission){
        int permissionCheck = ContextCompat.checkSelfPermission(context, permission);
        if(permissionCheck == PackageManager.PERMISSION_DENIED){
            return false;
        }else{
            return true;
        }
    }

    public boolean checkPermission(Context context, String[] ps){
        for(String permission : ps){
            int permissionCheck = ContextCompat.checkSelfPermission(context, permission);
            if(permissionCheck == PackageManager.PERMISSION_DENIED){
                return false;
            }
        }
        return true;
    }

    public static boolean checkAllPermissions(Context context){
        for(String permission : permissions){
            int permissionCheck = ContextCompat.checkSelfPermission(context, permission);
            if(permissionCheck == PackageManager.PERMISSION_DENIED){
                return false;
            }
        }
        return true;
    }


    public void commitPermissions(Fragment fragment, int SIGNAL){
        fragment.requestPermissions(permissions, SIGNAL);
    }

    public static void commitPermissions(Activity activity, int SIGNAL){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.requestPermissions(permissions, SIGNAL);
        }
    }

    public void commitPermission(Fragment fragment, int SIGNAL, String[] ps){
        fragment.requestPermissions(ps, SIGNAL);
    }

    public void commitPermission(Activity activity, int SIGNAL, String[] ps){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.requestPermissions(ps, SIGNAL);
        }
    }
}
