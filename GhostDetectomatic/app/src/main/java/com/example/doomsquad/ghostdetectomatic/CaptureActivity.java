package com.example.doomsquad.ghostdetectomatic;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by gtc3509 on 10/5/2015.
 */
public class CaptureActivity extends AppCompatActivity {

    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private Uri fileUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //create Intent to take picture and return control to controlling app
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        //Save image
        fileUri = getOutputMediaFileUri();
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

        //start the image capture intent
        startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (resultCode == RESULT_OK){
            //Image captured and saved to fileUri specified
            Toast.makeText(this, "Image saved to:\n" + data.getData(), Toast.LENGTH_LONG).show();
        }
        else if (resultCode == RESULT_CANCELED){
            Toast.makeText(this, "Image canceled", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "Capture failed, check your camera", Toast.LENGTH_LONG).show();
        }
    }

    //Create file Uri for our image
    private static Uri getOutputMediaFileUri() {
        return Uri.fromFile(getOutputMediaFile());
    }

    //Create File for our image
    private static File getOutputMediaFile(){
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory
                (Environment.DIRECTORY_PICTURES), "Ghost Detect-o-matic");

        //Create directory if it doesn't exist
        if (!mediaStorageDir.exists()){
            if (!mediaStorageDir.mkdirs()){
                Log.d("Ghost Detect-o-matic", "failed to create directory");
                return null;
            }
        }

        //Create a filename
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile = new File(mediaStorageDir.getPath()+File.separator+
                "GhostCap_"+timeStamp+".jpg");

        return mediaFile;
    }
}
