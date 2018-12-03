package com.example.devan.remedaily.View;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Camera extends AppCompatActivity {

    static final int REQUEST_TAKE_PHOTO = 1;
    String currentPhotoPath;

    public Camera() {
        super();
        currentPhotoPath = null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dispatchTakePictureIntent();
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        // using getExternalFilesDir() makes photos private to this app only. These photos will be
        // deleted along with app uninstall
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(this.getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                //...
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // source : https://stackoverflow.com/questions/15555750/android-intent-getstringextra-returns-null
        if(requestCode == REQUEST_TAKE_PHOTO){
            Intent resultIntent = new Intent();
            if (resultCode == RESULT_OK) {
                // source : https://stackoverflow.com/questions/6707900/pass-a-string-from-one-activity-to-another-activity-in-android
                // remove previous image
                Intent intent = getIntent();
                String previousPhotoPath = intent.getExtras().getString("previousPhotoPath");
                File previousDrugBoxPhoto = new File(previousPhotoPath);
                if (previousDrugBoxPhoto.exists()) {
                    previousDrugBoxPhoto.delete();
                    }

                resultIntent.putExtra("currentPhotoPath", currentPhotoPath);
                setResult(Activity.RESULT_OK, resultIntent);
            }
            else {
                setResult(Activity.RESULT_CANCELED, resultIntent);
            }
            this.finish();
        }
    }
}
