package com.example.andrei.wardrobe;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.os.Environment.getExternalStoragePublicDirectory;

public class Shelf1 extends AppCompatActivity {

    private ImageView imageH;
    private ImageView imageT;
    private ImageView imageJ;
    private ImageView imageA;
    private ImageView imageS;
    private ImageButton btnshow;
    private static final int RESULT_LOAD_IMAGEH = 1120;
    private static final int RESULT_LOAD_IMAGET = 1121;
    private static final int RESULT_LOAD_IMAGEJ = 1122;
    private static final int RESULT_LOAD_IMAGEA = 1123;
    private static final int RESULT_LOAD_IMAGES = 1124;
    private String PREFS_NAME = "image";
    private Context mContextH;
    private Context mContextT;
    private Context mContextJ;
    private Context mContextA;
    private Context mContextS;
    private boolean deAstaTrebuieSaRescriemCodu=false;
    private boolean nuapasa=false;
    String pathToFileH;
    String pathToFileT;
    String pathToFileJ;
    String pathToFileA;
    String pathToFileS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelf1);
        if(Build.VERSION.SDK_INT>=23){
            requestPermissions(new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE},2);
        }
        handlePermission();
        imageH= findViewById(R.id.ImgH);
        imageT= findViewById(R.id.ImgT);
        imageJ= findViewById(R.id.ImgJ);
        imageA= findViewById(R.id.ImgA);
        imageS= findViewById(R.id.ImgS);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.action_info:
                startActivity(new Intent(this,Credits.class));
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    public void ShowMe(View view){

        if(deAstaTrebuieSaRescriemCodu==false) {

            deAstaTrebuieSaRescriemCodu=true;

            btnshow = findViewById(R.id.btnShow);
            btnshow.setImageResource(R.drawable.ic_action_openedeye);

            mContextH = this;
            imageH = (ImageView) findViewById(R.id.ImgH);
            String path = getPreference(mContextH, "imagePath1");
            if (path == null || path.length() == 0 || path.equalsIgnoreCase("")) {

            } else {

                Bitmap loadedBitmap = BitmapFactory.decodeFile(path);

                ExifInterface exif = null;
                try {
                    File pictureFile = new File(path);
                    exif = new ExifInterface(pictureFile.getAbsolutePath());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                int orientation = ExifInterface.ORIENTATION_NORMAL;

                if (exif != null)
                    orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

                switch (orientation) {
                    case ExifInterface.ORIENTATION_ROTATE_90:
                        loadedBitmap = rotateBitmap(loadedBitmap, 90);
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_180:
                        loadedBitmap = rotateBitmap(loadedBitmap, 180);
                        break;

                    case ExifInterface.ORIENTATION_ROTATE_270:
                        loadedBitmap = rotateBitmap(loadedBitmap, 270);
                        break;
                    case ExifInterface.ORIENTATION_FLIP_HORIZONTAL:
                        loadedBitmap = flip(loadedBitmap, true, false);

                    case ExifInterface.ORIENTATION_FLIP_VERTICAL:
                        loadedBitmap = flip(loadedBitmap, false, true);

                }

                imageH.setImageBitmap(loadedBitmap);
            }

            mContextT = this;
            imageT = (ImageView) findViewById(R.id.ImgT);
            String path2 = getPreference(mContextT, "imagePath2");

            if (path2 == null || path2.length() == 0 || path2.equalsIgnoreCase("")) {

            } else {

                Bitmap loadedBitmap = BitmapFactory.decodeFile(path2);

                ExifInterface exif = null;
                try {
                    File pictureFile = new File(path2);
                    exif = new ExifInterface(pictureFile.getAbsolutePath());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                int orientation = ExifInterface.ORIENTATION_NORMAL;

                if (exif != null)
                    orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

                switch (orientation) {
                    case ExifInterface.ORIENTATION_ROTATE_90:
                        loadedBitmap = rotateBitmap(loadedBitmap, 90);
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_180:
                        loadedBitmap = rotateBitmap(loadedBitmap, 180);
                        break;

                    case ExifInterface.ORIENTATION_ROTATE_270:
                        loadedBitmap = rotateBitmap(loadedBitmap, 270);
                        break;
                    case ExifInterface.ORIENTATION_FLIP_HORIZONTAL:
                        loadedBitmap = flip(loadedBitmap, true, false);

                    case ExifInterface.ORIENTATION_FLIP_VERTICAL:
                        loadedBitmap = flip(loadedBitmap, false, true);

                }

                imageT.setImageBitmap(loadedBitmap);
            }

            mContextJ = this;
            imageJ = (ImageView) findViewById(R.id.ImgJ);
            String path3 = getPreference(mContextJ, "imagePath3");

            if (path3 == null || path3.length() == 0 || path3.equalsIgnoreCase("")) {

            } else {

                Bitmap loadedBitmap = BitmapFactory.decodeFile(path3);

                ExifInterface exif = null;
                try {
                    File pictureFile = new File(path3);
                    exif = new ExifInterface(pictureFile.getAbsolutePath());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                int orientation = ExifInterface.ORIENTATION_NORMAL;

                if (exif != null)
                    orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

                switch (orientation) {
                    case ExifInterface.ORIENTATION_ROTATE_90:
                        loadedBitmap = rotateBitmap(loadedBitmap, 90);
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_180:
                        loadedBitmap = rotateBitmap(loadedBitmap, 180);
                        break;

                    case ExifInterface.ORIENTATION_ROTATE_270:
                        loadedBitmap = rotateBitmap(loadedBitmap, 270);
                        break;
                    case ExifInterface.ORIENTATION_FLIP_HORIZONTAL:
                        loadedBitmap = flip(loadedBitmap, true, false);

                    case ExifInterface.ORIENTATION_FLIP_VERTICAL:
                        loadedBitmap = flip(loadedBitmap, false, true);

                }

                imageJ.setImageBitmap(loadedBitmap);
            }

            mContextA = this;
            imageA = (ImageView) findViewById(R.id.ImgA);
            String path4 = getPreference(mContextA, "imagePath4");

            if (path4 == null || path4.length() == 0 || path4.equalsIgnoreCase("")) {

            } else {

                Bitmap loadedBitmap = BitmapFactory.decodeFile(path4);

                ExifInterface exif = null;
                try {
                    File pictureFile = new File(path4);
                    exif = new ExifInterface(pictureFile.getAbsolutePath());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                int orientation = ExifInterface.ORIENTATION_NORMAL;

                if (exif != null)
                    orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

                switch (orientation) {
                    case ExifInterface.ORIENTATION_ROTATE_90:
                        loadedBitmap = rotateBitmap(loadedBitmap, 90);
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_180:
                        loadedBitmap = rotateBitmap(loadedBitmap, 180);
                        break;

                    case ExifInterface.ORIENTATION_ROTATE_270:
                        loadedBitmap = rotateBitmap(loadedBitmap, 270);
                        break;
                    case ExifInterface.ORIENTATION_FLIP_HORIZONTAL:
                        loadedBitmap = flip(loadedBitmap, true, false);

                    case ExifInterface.ORIENTATION_FLIP_VERTICAL:
                        loadedBitmap = flip(loadedBitmap, false, true);

                }

                imageA.setImageBitmap(loadedBitmap);
            }

            mContextS = this;
            imageS = (ImageView) findViewById(R.id.ImgS);
            String path5 = getPreference(mContextS, "imagePath5");

            if (path5 == null || path5.length() == 0 || path5.equalsIgnoreCase("")) {

            } else {
                //imageS.setImageBitmap(getScaledBitmap(path5, 280, 280)); O las aici in caz de se intampla ceva


                Bitmap loadedBitmap = BitmapFactory.decodeFile(path5);

                ExifInterface exif = null;
                try {
                    File pictureFile = new File(path5);
                    exif = new ExifInterface(pictureFile.getAbsolutePath());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                int orientation = ExifInterface.ORIENTATION_NORMAL;

                if (exif != null)
                    orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

                switch (orientation) {
                    case ExifInterface.ORIENTATION_ROTATE_90:
                        loadedBitmap = rotateBitmap(loadedBitmap, 90);
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_180:
                        loadedBitmap = rotateBitmap(loadedBitmap, 180);
                        break;

                    case ExifInterface.ORIENTATION_ROTATE_270:
                        loadedBitmap = rotateBitmap(loadedBitmap, 270);
                        break;
                    case ExifInterface.ORIENTATION_FLIP_HORIZONTAL:
                        loadedBitmap = flip(loadedBitmap, true, false);

                    case ExifInterface.ORIENTATION_FLIP_VERTICAL:
                        loadedBitmap = flip(loadedBitmap, false, true);

                }

                imageS.setImageBitmap(loadedBitmap);
            }
        } else {
            if(nuapasa==false)
            {
                nuapasa=true;
                btnshow = findViewById(R.id.btnShow);
                btnshow.setImageResource(R.drawable.ic_action_nuazi);
            }
        }

    }

    private void handlePermission() {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return;
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //ask for permission
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    RESULT_LOAD_IMAGEH);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case RESULT_LOAD_IMAGEH:
                for (int i = 0; i < permissions.length; i++) {
                    String permission = permissions[i];
                    if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                        boolean showRationale = ActivityCompat.shouldShowRequestPermissionRationale(this, permission);
                        if (showRationale) {
                            //  Show your own message here
                        } else {

                        }
                    }
                }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void preloadImagefromGalleryH(View view){
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select photo from gallery",
                "Capture photo from camera" };
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                loadImagefromGalleryH();
                                break;
                            case 1:
                                dispatchPictureTakenActionH();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }
    public void preloadImagefromGalleryT(View view){
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select photo from gallery",
                "Capture photo from camera" };
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                loadImagefromGalleryT();
                                break;
                            case 1:
                                dispatchPictureTakenActionT();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }
    public void preloadImagefromGalleryJ(View view){
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select photo from gallery",
                "Capture photo from camera" };
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                loadImagefromGalleryJ();
                                break;
                            case 1:
                                dispatchPictureTakenActionJ();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }
    public void preloadImagefromGalleryA(View view){
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select photo from gallery",
                "Capture photo from camera" };
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                loadImagefromGalleryA();
                                break;
                            case 1:
                                dispatchPictureTakenActionA();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }
    public void preloadImagefromGalleryS(View view){
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select photo from gallery",
                "Capture photo from camera" };
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                loadImagefromGalleryS();
                                break;
                            case 1:
                                dispatchPictureTakenActionS();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }
    private void dispatchPictureTakenActionH(){
        Intent takePic = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(takePic.resolveActivity(getPackageManager())!=null){
            File photoFile = null;
            photoFile = createPhotoFile();
            if(photoFile!=null) {
                pathToFileH = photoFile.getAbsolutePath();
                //adaugat
                //Toast.makeText(this, pathToFile, Toast.LENGTH_SHORT).show();

                Uri photoURI = FileProvider.getUriForFile(Shelf1.this, "com.example.andrei.wardrobe.fileprovider", photoFile);
                takePic.putExtra(MediaStore.EXTRA_OUTPUT,photoURI);
                startActivityForResult(takePic,1);
            }
        }
    }
    private void dispatchPictureTakenActionT(){
        Intent takePic = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(takePic.resolveActivity(getPackageManager())!=null){
            File photoFile = null;
            photoFile = createPhotoFile();
            if(photoFile!=null) {
                pathToFileT = photoFile.getAbsolutePath();
                //adaugat
                //Toast.makeText(this, pathToFile, Toast.LENGTH_SHORT).show();

                Uri photoURI = FileProvider.getUriForFile(Shelf1.this, "com.example.andrei.wardrobe.fileprovider", photoFile);
                takePic.putExtra(MediaStore.EXTRA_OUTPUT,photoURI);
                startActivityForResult(takePic,2);
            }
        }
    }
    private void dispatchPictureTakenActionJ(){
        Intent takePic = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(takePic.resolveActivity(getPackageManager())!=null){
            File photoFile = null;
            photoFile = createPhotoFile();
            if(photoFile!=null) {
                pathToFileJ = photoFile.getAbsolutePath();
                //adaugat
                //Toast.makeText(this, pathToFile, Toast.LENGTH_SHORT).show();

                Uri photoURI = FileProvider.getUriForFile(Shelf1.this, "com.example.andrei.wardrobe.fileprovider", photoFile);
                takePic.putExtra(MediaStore.EXTRA_OUTPUT,photoURI);
                startActivityForResult(takePic,3);
            }
        }
    }
    private void dispatchPictureTakenActionA(){
        Intent takePic = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(takePic.resolveActivity(getPackageManager())!=null){
            File photoFile = null;
            photoFile = createPhotoFile();
            if(photoFile!=null) {
                pathToFileA = photoFile.getAbsolutePath();
                //adaugat
                //Toast.makeText(this, pathToFile, Toast.LENGTH_SHORT).show();

                Uri photoURI = FileProvider.getUriForFile(Shelf1.this, "com.example.andrei.wardrobe.fileprovider", photoFile);
                takePic.putExtra(MediaStore.EXTRA_OUTPUT,photoURI);
                startActivityForResult(takePic,4);
            }
        }
    }
    private void dispatchPictureTakenActionS(){
        Intent takePic = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(takePic.resolveActivity(getPackageManager())!=null){
            File photoFile = null;
            photoFile = createPhotoFile();
            if(photoFile!=null) {
                pathToFileS = photoFile.getAbsolutePath();
                //adaugat
                //Toast.makeText(this, pathToFile, Toast.LENGTH_SHORT).show();

                Uri photoURI = FileProvider.getUriForFile(Shelf1.this, "com.example.andrei.wardrobe.fileprovider", photoFile);
                takePic.putExtra(MediaStore.EXTRA_OUTPUT,photoURI);
                startActivityForResult(takePic,5);
            }
        }
    }
    private File createPhotoFile()
    {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_MMmmss").format(new Date());
        String name = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File image=null;
        try{
            image = File.createTempFile(name,".jpg",storageDir);
        }catch (Exception e){
            Log.d("myLog","Except : " + e.toString() );
        }
        return image;
    }

    public void loadImagefromGalleryH() {
        try {
            mContextH = this;
            imageH = (ImageView) findViewById(R.id.ImgH);
            String path = getPreference(mContextH, "imagePath1");

            //  if (path == null || path.length() == 0 || path.equalsIgnoreCase("")) {
            Intent i = new Intent(
                    Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(i, RESULT_LOAD_IMAGEH);

            // } else {
            //     imageView.setImageBitmap(getScaledBitmap(path, 280, 280));
            // }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void loadImagefromGalleryT() {
        try {
            mContextT = this;
            imageT = (ImageView) findViewById(R.id.ImgT);
            String path2 = getPreference(mContextT, "imagePath2");

            //  if (path == null || path.length() == 0 || path.equalsIgnoreCase("")) {
            Intent i = new Intent(
                    Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(i, RESULT_LOAD_IMAGET);

            // } else {
            //     imageView.setImageBitmap(getScaledBitmap(path, 280, 280));
            // }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void loadImagefromGalleryJ() {
        try {
            mContextJ = this;
            imageJ = (ImageView) findViewById(R.id.ImgJ);
            String path3 = getPreference(mContextJ, "imagePath3");

            //  if (path == null || path.length() == 0 || path.equalsIgnoreCase("")) {
            Intent i = new Intent(
                    Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(i, RESULT_LOAD_IMAGEJ);

            // } else {
            //     imageView.setImageBitmap(getScaledBitmap(path, 280, 280));
            // }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void loadImagefromGalleryA() {
        try {
            mContextA = this;
            imageA = (ImageView) findViewById(R.id.ImgA);
            String path4 = getPreference(mContextA, "imagePath4");

            //  if (path == null || path.length() == 0 || path.equalsIgnoreCase("")) {
            Intent i = new Intent(
                    Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(i, RESULT_LOAD_IMAGEA);

            // } else {
            //     imageView.setImageBitmap(getScaledBitmap(path, 280, 280));
            // }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void loadImagefromGalleryS() {
        try {
            mContextS = this;
            imageS = (ImageView) findViewById(R.id.ImgS);
            String path5 = getPreference(mContextS, "imagePath5");

            //  if (path == null || path.length() == 0 || path.equalsIgnoreCase("")) {
            Intent i = new Intent(
                    Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(i, RESULT_LOAD_IMAGES);

            // } else {
            //     imageView.setImageBitmap(getScaledBitmap(path, 280, 280));
            // }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static Bitmap rotateBitmap(Bitmap bitmap, int degrees) {
        Matrix matrix = new Matrix();
        matrix.postRotate(degrees);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }
    public static Bitmap flip(Bitmap bitmap, boolean horizontal, boolean vertical) {
        Matrix matrix = new Matrix();
        matrix.preScale(horizontal ? -1 : 1, vertical ? -1 : 1);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if(requestCode==1 && resultCode==RESULT_OK)
            {
                Bitmap bitmap = BitmapFactory.decodeFile(pathToFileH);
                mContextH=this;//tt
                ExifInterface exif = null;
                try {
                    File pictureFile = new File(pathToFileH);
                    exif = new ExifInterface(pictureFile.getAbsolutePath());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                int orientation = ExifInterface.ORIENTATION_NORMAL;

                if (exif != null)
                    orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

                switch (orientation) {
                    case ExifInterface.ORIENTATION_ROTATE_90:
                        bitmap = rotateBitmap(bitmap, 90);
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_180:
                        bitmap = rotateBitmap(bitmap, 180);
                        break;

                    case ExifInterface.ORIENTATION_ROTATE_270:
                        bitmap = rotateBitmap(bitmap, 270);
                        break;
                    case ExifInterface.ORIENTATION_FLIP_HORIZONTAL:
                        bitmap = flip(bitmap, true, false);

                    case ExifInterface.ORIENTATION_FLIP_VERTICAL:
                        bitmap = flip(bitmap, false, true);


                }
                setPreference(mContextH, pathToFileH, "imagePath1");
                //Trebe pus sa fie in pozitia pozata, nu altfel (vezi randul 480)
                imageH.setImageBitmap(bitmap);
            }else if(requestCode==2 && resultCode==RESULT_OK)
            {
                Bitmap bitmap = BitmapFactory.decodeFile(pathToFileT);
                mContextT=this;//tt
                ExifInterface exif = null;
                try {
                    File pictureFile = new File(pathToFileT);
                    exif = new ExifInterface(pictureFile.getAbsolutePath());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                int orientation = ExifInterface.ORIENTATION_NORMAL;

                if (exif != null)
                    orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

                switch (orientation) {
                    case ExifInterface.ORIENTATION_ROTATE_90:
                        bitmap = rotateBitmap(bitmap, 90);
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_180:
                        bitmap = rotateBitmap(bitmap, 180);
                        break;

                    case ExifInterface.ORIENTATION_ROTATE_270:
                        bitmap = rotateBitmap(bitmap, 270);
                        break;
                    case ExifInterface.ORIENTATION_FLIP_HORIZONTAL:
                        bitmap = flip(bitmap, true, false);

                    case ExifInterface.ORIENTATION_FLIP_VERTICAL:
                        bitmap = flip(bitmap, false, true);


                }
                setPreference(mContextT, pathToFileT, "imagePath2");
                //Trebe pus sa fie in pozitia pozata, nu altfel (vezi randul 480)
                imageT.setImageBitmap(bitmap);
            }
            else if(requestCode==3 && resultCode==RESULT_OK)
            {
                Bitmap bitmap = BitmapFactory.decodeFile(pathToFileJ);
                mContextJ=this;//tt
                ExifInterface exif = null;
                try {
                    File pictureFile = new File(pathToFileJ);
                    exif = new ExifInterface(pictureFile.getAbsolutePath());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                int orientation = ExifInterface.ORIENTATION_NORMAL;

                if (exif != null)
                    orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

                switch (orientation) {
                    case ExifInterface.ORIENTATION_ROTATE_90:
                        bitmap = rotateBitmap(bitmap, 90);
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_180:
                        bitmap = rotateBitmap(bitmap, 180);
                        break;

                    case ExifInterface.ORIENTATION_ROTATE_270:
                        bitmap = rotateBitmap(bitmap, 270);
                        break;
                    case ExifInterface.ORIENTATION_FLIP_HORIZONTAL:
                        bitmap = flip(bitmap, true, false);

                    case ExifInterface.ORIENTATION_FLIP_VERTICAL:
                        bitmap = flip(bitmap, false, true);


                }
                setPreference(mContextJ, pathToFileJ, "imagePath3");
                //Trebe pus sa fie in pozitia pozata, nu altfel (vezi randul 480)
                imageJ.setImageBitmap(bitmap);
            }
            else if(requestCode==4 && resultCode==RESULT_OK)
            {
                Bitmap bitmap = BitmapFactory.decodeFile(pathToFileA);
                mContextA=this;//tt
                ExifInterface exif = null;
                try {
                    File pictureFile = new File(pathToFileA);
                    exif = new ExifInterface(pictureFile.getAbsolutePath());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                int orientation = ExifInterface.ORIENTATION_NORMAL;

                if (exif != null)
                    orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

                switch (orientation) {
                    case ExifInterface.ORIENTATION_ROTATE_90:
                        bitmap = rotateBitmap(bitmap, 90);
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_180:
                        bitmap = rotateBitmap(bitmap, 180);
                        break;

                    case ExifInterface.ORIENTATION_ROTATE_270:
                        bitmap = rotateBitmap(bitmap, 270);
                        break;
                    case ExifInterface.ORIENTATION_FLIP_HORIZONTAL:
                        bitmap = flip(bitmap, true, false);

                    case ExifInterface.ORIENTATION_FLIP_VERTICAL:
                        bitmap = flip(bitmap, false, true);


                }
                setPreference(mContextA, pathToFileA, "imagePath4");
                //Trebe pus sa fie in pozitia pozata, nu altfel (vezi randul 480)
                imageA.setImageBitmap(bitmap);
            }
            else if(requestCode==5 && resultCode==RESULT_OK)
            {
                Bitmap bitmap = BitmapFactory.decodeFile(pathToFileS);
                mContextS=this;//tt
                ExifInterface exif = null;
                try {
                    File pictureFile = new File(pathToFileS);
                    exif = new ExifInterface(pictureFile.getAbsolutePath());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                int orientation = ExifInterface.ORIENTATION_NORMAL;

                if (exif != null)
                    orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

                switch (orientation) {
                    case ExifInterface.ORIENTATION_ROTATE_90:
                        bitmap = rotateBitmap(bitmap, 90);
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_180:
                        bitmap = rotateBitmap(bitmap, 180);
                        break;

                    case ExifInterface.ORIENTATION_ROTATE_270:
                        bitmap = rotateBitmap(bitmap, 270);
                        break;
                    case ExifInterface.ORIENTATION_FLIP_HORIZONTAL:
                        bitmap = flip(bitmap, true, false);

                    case ExifInterface.ORIENTATION_FLIP_VERTICAL:
                        bitmap = flip(bitmap, false, true);


                }
                setPreference(mContextS, pathToFileS, "imagePath5");
                //Trebe pus sa fie in pozitia pozata, nu altfel (vezi randul 480)
                imageS.setImageBitmap(bitmap);
            }else if (requestCode == RESULT_LOAD_IMAGEH && resultCode == RESULT_OK && null != data) {
                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(selectedImage,filePathColumn, null, null, null);
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String picturePath = cursor.getString(columnIndex);
                cursor.close();




                Bitmap loadedBitmap = BitmapFactory.decodeFile(picturePath);

                ExifInterface exif = null;
                try {
                    File pictureFile = new File(picturePath);
                    exif = new ExifInterface(pictureFile.getAbsolutePath());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                int orientation = ExifInterface.ORIENTATION_NORMAL;

                if (exif != null)
                    orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

                switch (orientation) {
                    case ExifInterface.ORIENTATION_ROTATE_90:
                        loadedBitmap = rotateBitmap(loadedBitmap, 90);
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_180:
                        loadedBitmap = rotateBitmap(loadedBitmap, 180);
                        break;

                    case ExifInterface.ORIENTATION_ROTATE_270:
                        loadedBitmap = rotateBitmap(loadedBitmap, 270);
                        break;
                    case ExifInterface.ORIENTATION_FLIP_HORIZONTAL:
                        loadedBitmap = flip(loadedBitmap, true, false);

                    case ExifInterface.ORIENTATION_FLIP_VERTICAL:
                        loadedBitmap = flip(loadedBitmap, false, true);


                }
                setPreference(mContextH, picturePath, "imagePath1");
                //imageH.setImageBitmap(getScaledBitmap(picturePath, 280, 280));
                imageH.setImageBitmap(loadedBitmap);

            } else if(requestCode == RESULT_LOAD_IMAGET && resultCode == RESULT_OK && null != data) {

                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String picturePath = cursor.getString(columnIndex);
                cursor.close();

                // setPreference(mContextT, picturePath, "imagePath12");
                // imageT.setImageBitmap(getScaledBitmap(picturePath, 280, 280));
                Bitmap loadedBitmap = BitmapFactory.decodeFile(picturePath);

                ExifInterface exif = null;
                try {
                    File pictureFile = new File(picturePath);
                    exif = new ExifInterface(pictureFile.getAbsolutePath());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                int orientation = ExifInterface.ORIENTATION_NORMAL;

                if (exif != null)
                    orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

                switch (orientation) {
                    case ExifInterface.ORIENTATION_ROTATE_90:
                        loadedBitmap = rotateBitmap(loadedBitmap, 90);
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_180:
                        loadedBitmap = rotateBitmap(loadedBitmap, 180);
                        break;

                    case ExifInterface.ORIENTATION_ROTATE_270:
                        loadedBitmap = rotateBitmap(loadedBitmap, 270);
                        break;
                    case ExifInterface.ORIENTATION_FLIP_HORIZONTAL:
                        loadedBitmap = flip(loadedBitmap, true, false);

                    case ExifInterface.ORIENTATION_FLIP_VERTICAL:
                        loadedBitmap = flip(loadedBitmap, false, true);

                }
                setPreference(mContextT, picturePath, "imagePath2");
                imageT.setImageBitmap(loadedBitmap);


            } else  if(requestCode == RESULT_LOAD_IMAGEJ && resultCode == RESULT_OK && null != data) {

                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String picturePath = cursor.getString(columnIndex);
                cursor.close();

                Bitmap loadedBitmap = BitmapFactory.decodeFile(picturePath);

                ExifInterface exif = null;
                try {
                    File pictureFile = new File(picturePath);
                    exif = new ExifInterface(pictureFile.getAbsolutePath());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                int orientation = ExifInterface.ORIENTATION_NORMAL;

                if (exif != null)
                    orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

                switch (orientation) {
                    case ExifInterface.ORIENTATION_ROTATE_90:
                        loadedBitmap = rotateBitmap(loadedBitmap, 90);
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_180:
                        loadedBitmap = rotateBitmap(loadedBitmap, 180);
                        break;

                    case ExifInterface.ORIENTATION_ROTATE_270:
                        loadedBitmap = rotateBitmap(loadedBitmap, 270);
                        break;
                    case ExifInterface.ORIENTATION_FLIP_HORIZONTAL:
                        loadedBitmap = flip(loadedBitmap, true, false);

                    case ExifInterface.ORIENTATION_FLIP_VERTICAL:
                        loadedBitmap = flip(loadedBitmap, false, true);

                }

                setPreference(mContextJ, picturePath, "imagePath3");
                imageJ.setImageBitmap(loadedBitmap);
            } else  if(requestCode == RESULT_LOAD_IMAGEA && resultCode == RESULT_OK && null != data) {

                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String picturePath = cursor.getString(columnIndex);
                cursor.close();

                Bitmap loadedBitmap = BitmapFactory.decodeFile(picturePath);

                ExifInterface exif = null;
                try {
                    File pictureFile = new File(picturePath);
                    exif = new ExifInterface(pictureFile.getAbsolutePath());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                int orientation = ExifInterface.ORIENTATION_NORMAL;

                if (exif != null)
                    orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

                switch (orientation) {
                    case ExifInterface.ORIENTATION_ROTATE_90:
                        loadedBitmap = rotateBitmap(loadedBitmap, 90);
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_180:
                        loadedBitmap = rotateBitmap(loadedBitmap, 180);
                        break;

                    case ExifInterface.ORIENTATION_ROTATE_270:
                        loadedBitmap = rotateBitmap(loadedBitmap, 270);
                        break;
                    case ExifInterface.ORIENTATION_FLIP_HORIZONTAL:
                        loadedBitmap = flip(loadedBitmap, true, false);

                    case ExifInterface.ORIENTATION_FLIP_VERTICAL:
                        loadedBitmap = flip(loadedBitmap, false, true);

                }

                setPreference(mContextA, picturePath, "imagePath4");
                imageA.setImageBitmap(loadedBitmap);
            } else  if(requestCode == RESULT_LOAD_IMAGES && resultCode == RESULT_OK && null != data) {

                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String picturePath = cursor.getString(columnIndex);
                cursor.close();

                Bitmap loadedBitmap = BitmapFactory.decodeFile(picturePath);

                ExifInterface exif = null;
                try {
                    File pictureFile = new File(picturePath);
                    exif = new ExifInterface(pictureFile.getAbsolutePath());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                int orientation = ExifInterface.ORIENTATION_NORMAL;

                if (exif != null)
                    orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

                switch (orientation) {
                    case ExifInterface.ORIENTATION_ROTATE_90:
                        loadedBitmap = rotateBitmap(loadedBitmap, 90);
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_180:
                        loadedBitmap = rotateBitmap(loadedBitmap, 180);
                        break;

                    case ExifInterface.ORIENTATION_ROTATE_270:
                        loadedBitmap = rotateBitmap(loadedBitmap, 270);
                        break;
                    case ExifInterface.ORIENTATION_FLIP_HORIZONTAL:
                        loadedBitmap = flip(loadedBitmap, true, false);

                    case ExifInterface.ORIENTATION_FLIP_VERTICAL:
                        loadedBitmap = flip(loadedBitmap, false, true);

                }

                setPreference(mContextS, picturePath, "imagePath5");
                imageS.setImageBitmap(loadedBitmap);
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private Bitmap getScaledBitmap(String picturePath, int width, int height) {
        BitmapFactory.Options sizeOptions = null;
        try {
            sizeOptions = new BitmapFactory.Options();
            sizeOptions.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(picturePath, sizeOptions);

            int inSampleSize = calculateInSampleSize(sizeOptions, width, height);

            sizeOptions.inJustDecodeBounds = false;
            sizeOptions.inSampleSize = inSampleSize;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return BitmapFactory.decodeFile(picturePath, sizeOptions);
    }

    private int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        int inSampleSize = 0;
        try {
            // Raw height and width of image
            final int height = options.outHeight;
            final int width = options.outWidth;
            inSampleSize = 1;

            if (height > reqHeight || width > reqWidth) {

                // Calculate ratios of height and width to requested height and
                // width
                final int heightRatio = Math.round((float) height / (float) reqHeight);
                final int widthRatio = Math.round((float) width  / (float) reqWidth);

                // Choose the smallest ratio as inSampleSize value, this will
                // guarantee
                // a final image with both dimensions larger than or equal to
                // the
                // requested height and width.
                inSampleSize = heightRatio < widthRatio ? heightRatio
                        : widthRatio;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return inSampleSize;
    }

    boolean setPreference(Context c, String value, String key) {
        SharedPreferences settings = c.getSharedPreferences(PREFS_NAME, 0);
        settings = c.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        return editor.commit();
    }

    String getPreference(Context c, String key) {
        SharedPreferences settings = c.getSharedPreferences(PREFS_NAME, 0);
        settings = c.getSharedPreferences(PREFS_NAME, 0);
        String value = settings.getString(key, "");
        return value;
    }
}
