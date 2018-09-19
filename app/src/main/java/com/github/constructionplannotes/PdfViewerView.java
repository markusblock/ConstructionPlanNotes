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
import com.github.constructionplannotes.model.TextOnCanvasObject;
import io.realm.Realm;
import io.realm.RealmResults;

import java.util.ArrayList;
import java.util.List;

public class PdfViewerView extends PDFView {

    private static final String TAG = PdfViewerView.class.getSimpleName();

    private List<TextOnCanvasObject> textOnCanvasObjects;
    private Realm realm;

    public PdfViewerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        realm.close();
    }

    public void init() {
        realm = Realm.getDefaultInstance();
        RealmResults<TextOnCanvasObject> all = realm.where(TextOnCanvasObject.class).findAll();
        textOnCanvasObjects = new ArrayList<>(all);
        invalidate();
    }

    public void resetView(){
//        textOnCanvasObjects.clear();
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(textOnCanvasObjects!=null && !textOnCanvasObjects.isEmpty()) {

            for (TextOnCanvasObject textOnCanvasObject:textOnCanvasObjects) {
                float x = getCurrentXOffset() + textOnCanvasObject.getX() * getZoom();
                float y = getCurrentYOffset() + textOnCanvasObject.getY() * getZoom();

                TextPaint textPaint = new TextPaint();
                textPaint.setColor(Color.GREEN);
                textPaint.setStrokeWidth(1f);
                textPaint.setStyle(Paint.Style.FILL);
                textPaint.setTextSize(30 * getZoom());
                canvas.drawText(textOnCanvasObject.getText(), x, y, textPaint);
                canvas.save();

                Log.e(TAG, "Drawing text "+textOnCanvasObject.getText());
                Log.e(TAG, "Clickedon x/y "+textOnCanvasObject.getX()+"/"+textOnCanvasObject.getY());
                Log.e(TAG, "Calculated x/y "+x+"/"+y);
                Log.e(TAG, "Zoom: "+getZoom());
                Log.e(TAG, "CurrentOffset: "+getCurrentXOffset()+"/"+getCurrentYOffset());
                Log.e(TAG, "Translation: "+getTranslationX()+"/"+getTranslationY());
            }
        }
    }


    public void drawText(String text, float x, float y) {
        TextOnCanvasObject textOnCanvasObject = new TextOnCanvasObject(text, x, y);
        textOnCanvasObjects.add(textOnCanvasObject);

        realm.beginTransaction();
        realm.insertOrUpdate(textOnCanvasObject);
        realm.commitTransaction();

        invalidate();
    }
}
