package com.hooneys.makeimagepdfproject.SavePack;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.view.View;

import com.hooneys.makeimagepdfproject.R;

public class ConvertFile {
    public static Bitmap convertVtoB(View view){
        Bitmap bit = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bit);
        view.draw(c);
        return bit;
    }

    public static PdfDocument convertBtoP(Bitmap bitmap){
        PdfDocument pdfDocument = new PdfDocument();

        //Page 정보 가로, 세로, 총 페이지
        PdfDocument.PageInfo info = new PdfDocument.PageInfo.Builder(bitmap.getWidth(), bitmap.getHeight(), 1).create();

        //Page 생성
        PdfDocument.Page page_1 = pdfDocument.startPage(info);

        //흰 바탕 깔기!
        Canvas canvas = page_1.getCanvas();
        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#FFFFFF"));
        canvas.drawPaint(paint);

        //Bitmap 그리기
        bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth(), bitmap.getHeight(), true);
        paint.setColor(Color.BLUE);
        canvas.drawBitmap(bitmap, 0, 0, null);

        //pdf 종료
        pdfDocument.finishPage(page_1);
        return pdfDocument;
    }
}
