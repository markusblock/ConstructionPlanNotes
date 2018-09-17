package com.github.constructionplannotes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnDrawListener;

public class PdfViewerView extends PDFView implements OnDrawListener {

    private static final String TAG = PdfViewerView.class.getSimpleName();

    private TextPaint textPaint;
    private float point1X, point1Y;
    private String textToDraw;

    public PdfViewerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {

        resetView();
    }

    public void resetView(){

        textToDraw=null;
        point1X=0;
        point1Y=0;

        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(textToDraw!=null) {
            float x = getCurrentXOffset() + point1X * getZoom();
            float y = getCurrentYOffset() + point1Y * getZoom();
            textPaint.setTextSize(30 * getZoom());
            canvas.drawText(textToDraw, x, y, textPaint);

            Log.e(TAG, "Drawing text "+textToDraw);
            Log.e(TAG, "Clickedon x/y "+point1X+"/"+point1Y);
            Log.e(TAG, "Calculated x/y "+x+"/"+y);
            Log.e(TAG, "Zoom: "+getZoom());
            Log.e(TAG, "CurrentOffset: "+getCurrentXOffset()+"/"+getCurrentYOffset());
            Log.e(TAG, "Translation: "+getTranslationX()+"/"+getTranslationY());
        }
    }


    public void drawText(String text, float x, float y) {
        textPaint = new TextPaint();
        textPaint.setColor(Color.GREEN);
        textPaint.setStrokeWidth(1f);
        textPaint.setStyle(Paint.Style.FILL);

        point1X = x;
        point1Y = y;
        textToDraw = text;

        invalidate();
    }

    @Override
    public void onLayerDrawn(Canvas canvas, float pageWidth, float pageHeight, int displayedPage) {

    }
}
