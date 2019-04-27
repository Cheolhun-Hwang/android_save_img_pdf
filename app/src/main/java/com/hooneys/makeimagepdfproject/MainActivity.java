package com.hooneys.makeimagepdfproject;

import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.pdf.PdfDocument;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.hooneys.makeimagepdfproject.PermissionPack.MyPermission.Permission;
import com.hooneys.makeimagepdfproject.SavePack.ConvertFile;
import com.hooneys.makeimagepdfproject.SavePack.SaveFile;

import java.security.Permissions;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    public static final int SIGNAL_PERMISSION = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.main_save_image:
                start(0);
                return true;
            case R.id.main_save_pdf:
                start(1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    private void start(int type){
        if(Permission.checkAllPermissions(getApplicationContext())){
            if(type == 0){
                saveImage();
            }else if(type == 1){
                savePdf();
            }
        }else{
            //Require Permission
            Permission.commitPermissions(MainActivity.this, SIGNAL_PERMISSION);
        }
    }

    private void savePdf() {
        Bitmap bitmap = ConvertFile.convertVtoB(findViewById(R.id.main_save_view));
        PdfDocument pdfDocument = ConvertFile.convertBtoP(bitmap);
        if(SaveFile.savePdf(pdfDocument)){
            Toast.makeText(this, "성공적으로 저장되었습니다.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "저장에 실패하였습니다.", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveImage() {
        Bitmap bitmap = ConvertFile.convertVtoB(findViewById(R.id.main_save_view));
        if(SaveFile.saveBitmap(bitmap)){
            Toast.makeText(this, "성공적으로 저장되었습니다.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "저장에 실패하였습니다.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == SIGNAL_PERMISSION){
            boolean isAll = true;
            for(int grant : grantResults){
                if(grant == PackageManager.PERMISSION_DENIED){
                    isAll = false;
                    break;
                }
            }
            if(!isAll){
                Toast.makeText(this, "실행을 위해 모든 권한이 필요합니다.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
