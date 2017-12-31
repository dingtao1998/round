package dt.com.round;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import java.io.FileNotFoundException;


/**
 * Created by Administrator on 2017/12/30.
 */

public class CropActivity extends Activity {
    private ImageView  img;
    private Button bt;
    private RoundImgCreatorView roundImgCreatorView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crop);
        img  = findViewById(R.id.iv1);
        bt = findViewById(R.id.bt1);
        roundImgCreatorView  = findViewById(R.id.rcv);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent  = getIntent();
        Uri  uri  = intent.getData();
        Bitmap  bitmap  = null ;
        try {
            bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
            roundImgCreatorView.setBitmap(bitmap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }
/*    public void setpic(View view){
        Bitmap bitmap = roundImgCreatorView.extractBitmap(200);
        img.setImageBitmap(bitmap);*/
//
        //
        //
    }

