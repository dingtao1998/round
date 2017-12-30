package dt.com.round;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2017/12/29.
 */

public class RoundImgCreatorView extends View {
    private RectF src, dst, clipBounds;//图片尺寸，绘制到哪里尺寸，保留图层尺寸

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

    private Bitmap bitmap;
    private Paint clearPaint;
    private int w;//rcv宽
    private int h;//rcv高

    private void init() {
        clearPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        clearPaint.setColor(Color.GRAY);
        clearPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        w = MeasureSpec.getSize(widthMeasureSpec);
        h = MeasureSpec.getSize(heightMeasureSpec);
    }
    //---------------------------------------------------------------------


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (bitmap != null) {
            dst = getSize();
            canvas.drawBitmap(bitmap, null, dst, null);
            int count = canvas.saveLayer(null, null, Canvas.ALL_SAVE_FLAG);
            canvas.drawColor(0x80666666);
            canvas.drawCircle(w / 2, h / 2, (int) (0.8 * (w / 2)), clearPaint);
        }
    }

    private int bw;//图片宽
    private int bh;//图片高
    private int r;
    private RectF getSize() {
        r = (int)(w/2*0.8);
        bw = bitmap.getWidth();
        bh = bitmap.getHeight();
        if(bw<bh){
            int precent = bw/bh;

            int left = w/2-r;
            int top = h/2 - r/precent;
            int right = w/2 + r;
            int boot = h/2+r/precent;
            src = new RectF(left,top,right,boot);
        }else{
            int precent  = bw/bh;
            int left = w/2 - r*precent;
            int top = h/2 - r;
            int right =  w/2 + r*precent;
            int boot  = h/2 + r;
            src = new RectF(left,top,right,boot);
        }
        circleBounds = new RectF(w / 2 - r, h / 2 - r, w / 2 + r, h / 2 + r);
        return src;
    }
    private RectF circleBounds;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //return super.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                break;
            case MotionEvent.ACTION_MOVE:

                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }
      return  true;
    }
    public Bitmap extractBitmap(int width) {
        Bitmap outBitmap = Bitmap.createBitmap(width, width, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(outBitmap);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setColor(Color.GRAY);
        canvas.drawCircle(width / 2, width / 2, width / 2, p);
        float scale = dst.width() / bitmap.getWidth();
        int w = (int) (circleBounds.width() / scale);
        int l = (int) ((circleBounds.left - dst.left) / scale);
        int r = l + w;
        int t = (int) ((circleBounds.top - dst.top) / scale);
        int b = t + w;
        Rect resRect = new Rect(l, t, r, b);
        Paint paint = new Paint();
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, resRect, canvas.getClipBounds(), paint);

        return outBitmap;

    }
}

