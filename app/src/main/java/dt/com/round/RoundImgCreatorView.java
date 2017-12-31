package dt.com.round;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/12/29.
 */

public class RoundImgCreatorView extends View {
    private RectF dst, clipBounds;//图片尺寸，绘制到哪里尺寸，保留图层尺寸
    private Rect src;

    public RoundImgCreatorView(Context context) {
        super(context);
        init();
    }

    public RoundImgCreatorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RoundImgCreatorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public Bitmap bitmap;
    private Paint huanPaint,picPaint;
    private int w;//rcv宽
    private int h;//rcv高
    private int bw;
    private  int bh;
    public void setBitmap(Bitmap bit){
        bitmap = bit;
        bw =bitmap.getWidth();
        bh = bitmap.getHeight();
    }
    private void init() {

        huanPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        huanPaint.setColor(Color.GRAY);
        huanPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(bitmap,null,getSize(),null);
        int count = canvas.saveLayer(clipBounds, null, Canvas.ALL_SAVE_FLAG);
        canvas.drawColor(0x80000000);
        canvas.drawCircle(w/2,h/2,(int)(w/2*0.8),huanPaint);
    }

    private Matrix getMatix(){
        Matrix  matrix  = new Matrix();
        float  scalx = 2;
        float  scaly = 2;
        matrix.postScale(scalx,scaly);
        return matrix;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        w =getWidth();
        h =getHeight();
    }

    private RectF getSize(){
        if(bw>=bh){
            float pre = bw/w;
            float pich = bh/pre;
            RectF rectF  = new RectF(0,h/2-pich/2,w,h/2+pich/2);
            return rectF;
        }else{
            float pre = bh/h;
            float picw = bw/pre;
            RectF  rectF  = new RectF(w/2-picw/2,0,w/2+picw/2,h);


            return rectF;
        }



    }
}

