package dt.com.round;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.FileNotFoundException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView imageView;
    private Button button;
    private static final int ALBUM_OK = 0;
    private static final int GETPIC_OK = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.iv);
        button = (Button) findViewById(R.id.bt);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent, ALBUM_OK);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case ALBUM_OK:
                Intent intent = new Intent();
                intent.setClass(this, CropActivity.class);
                intent.setDataAndType(data.getData(), "image/*");
                Log.d("tagtag",data.getData()+"");
                startActivityForResult(intent, GETPIC_OK);
                break;
            case GETPIC_OK:
                if (ALBUM_OK == requestCode) {
                    Bitmap bitmap = null;
                    ContentResolver contentResolver = this.getContentResolver();
                    Uri uri = data.getData();
                    try {
                        bitmap = BitmapFactory.decodeStream(contentResolver.openInputStream(uri));
                        imageView.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;

                }
            default:

        }
    }

}
