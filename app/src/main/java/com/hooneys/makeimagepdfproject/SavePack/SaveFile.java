package com.hooneys.makeimagepdfproject.SavePack;

import android.graphics.Bitmap;
import android.graphics.pdf.PdfDocument;
import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SaveFile {
    private static final String SAVE_URL =
            Environment.getExternalStorageDirectory().getAbsolutePath() +
            "/MyConvertApp";

    public static boolean saveBitmap(Bitmap bitmap){
        if(bitmap != null){
            boolean flag = false;
            FileOutputStream fOut = null;
            try{
                File dir = new File(SAVE_URL);
                if(!dir.exists())
                    dir.mkdirs();
                File file = new File(dir, makeFileName() + ".png");
                fOut = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
                fOut.flush();
                flag = true;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (fOut != null) {
                        fOut.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return flag;
            }
        }else{
            return false;
        }
    }
     private static String makeFileName(){
         Date date = new Date(System.currentTimeMillis());
         SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd_hhmmss", Locale.KOREA);
         return format.format(date);
     }

    public static boolean savePdf(PdfDocument pdfDocument){
        if(pdfDocument != null){
            boolean flag = false;
            FileOutputStream fOut = null;
            try{
                File dir = new File(SAVE_URL);
                if(!dir.exists())
                    dir.mkdirs();
                File file = new File(dir, makeFileName() + ".pdf");
                fOut = new FileOutputStream(file);
                pdfDocument.writeTo(fOut);
                fOut.flush();
                flag = true;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                pdfDocument.close();
                try {
                    if (fOut != null) {
                        fOut.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return flag;
            }
        }else{
            return false;
        }
    }
}
