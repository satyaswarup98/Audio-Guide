package com.example.satya.audioguide;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaRecorder;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.print.PrintHelper;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.UUID;


public class MainActivity extends AppCompatActivity {

    public final static int QRcodeWidth = 500;
    private static final String IMAGE_DIRECTORY = "/Audio Guide";
    private static final String LOG_TAG = "Record_String";
    Bitmap bitmap;
    StorageReference riversRef;
    int a = 0;
    String downloadUUID;
    String QRImgName="";
    String contents;

    private Button mSendData, mDownload;
    private ImageView iv;
    private StorageReference mStorageRef;
    private MediaRecorder mRecorder;
    private String mFileName = null;
    private ProgressDialog mProgress;

    public static final int REQUEST_CODE=100;
    public static final int PERMISSION_REQUEST=200;
    public static File rootPath;
    TextView result;

    static TabLayout tabLayout;
    private String m_Text = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        getSupportActionBar().setElevation(0);

        // Find the view pager that will allow the user to swipe between fragments
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        // Create an adapter that knows which fragment should be shown on each page
        Category_Adapter adapter = new Category_Adapter(getSupportFragmentManager());


        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(1);

        // Give the TabLayout the ViewPager
         tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);



//        mProgress = new ProgressDialog(this);
//
//        mFileName = Environment.getExternalStorageDirectory().getAbsolutePath();
//        mFileName += "/rec.3gp";
//
//
//        mStorageRef = FirebaseStorage.getInstance().getReference();
//
//
//        mSendData = (Button) findViewById(R.id.button);
//
//        mSendData.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//                if (a == 0) {
//                    a = 1;
//                    startRecording();
//                    mSendData.setText("Stop");
//
//                } else {
//                    a = 0;
//                    stopRecording();
//                    mSendData.setText("Start");
//                    uploadAudio();
//
//                }
//
//
//            }
//        });

//        mDownload = (Button) findViewById(R.id.button2);
//        result = (TextView) findViewById(R.id.resut);
//
//        if(ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
//            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA},PERMISSION_REQUEST);
//        }
//
//        mDownload.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent intent = new Intent(MainActivity.this,ScanActivity.class);
//                startActivityForResult(intent,REQUEST_CODE);
//
//
//            }
//        });


    }


//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK){
//            if(data != null){
//                final Barcode barcode = data.getParcelableExtra("barcode");
//                result.post(new Runnable() {
//                    @Override
//                    public void run() {
////                        contents = barcode.displayValue.toString();
////                         result.setText(contents);
//                       ScanFragment.AlertBoxInput(barcode.displayValue.toString());
////                        Toast.makeText(MainActivity.this,barcode.displayValue.toString(),Toast.LENGTH_LONG).show();
//
//
//                    }
//                });
//
//            }
//        }
//    }

//    public void uploadAudio() {
//
//        mProgress.setMessage("Uploading Audio...");
//        mProgress.show();
//        iv = (ImageView) findViewById(R.id.iv);
//        Uri file = Uri.fromFile(new File(mFileName));
//        downloadUUID = UUID.randomUUID().toString();
//        riversRef = mStorageRef.child("Audio").child(downloadUUID);
//        riversRef.putFile(file)
//                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                    @Override
//                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
////                         Get a URL to the uploaded content
//                        mProgress.dismiss();
//
//
//                        try {
//                            bitmap = TextToImageEncode(downloadUUID);
//                            iv.setImageBitmap(bitmap);
//                            String path = saveImage(bitmap);  //give read write permission
////                            Toast.makeText(MainActivity.this, "QRCode saved to -> " + path, Toast.LENGTH_SHORT).show();
//                            shareImage();
//                        } catch (WriterException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                });
//
//    }
//
//    public void shareImage()
//    {
//        Bitmap icon = bitmap;
//        Intent share = new Intent(Intent.ACTION_SEND);
//        share.setType("image/jpeg");
//        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//        icon.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
//        File f = new File(Environment.getExternalStorageDirectory() + File.separator + QRImgName);
//        try {
//            f.createNewFile();
//            FileOutputStream fo = new FileOutputStream(f);
//            fo.write(bytes.toByteArray());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        share.putExtra(Intent.EXTRA_STREAM, Uri.parse(Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY+"/QR Images/"+QRImgName));
//        startActivity(Intent.createChooser(share, "Share Image"));
//    }

//    public void downloadAudio() {
//
//        mProgress.setMessage("Downloading Audio...");
//        mProgress.show();
//
//        File rootPath = new File(Environment.getExternalStorageDirectory(), IMAGE_DIRECTORY+"/Audio Downloads");
//        if (!rootPath.exists()) {
//            rootPath.mkdirs();
//        }
//        if(m_Text=="")
//        {
//           m_Text=contents;
//        }
//        final File localFile = new File(rootPath, m_Text+".3gp");
//        riversRef = mStorageRef.child("Audio").child(contents);
//        riversRef.getFile(localFile)
//                .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
//                    @Override
//                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
//                        mProgress.dismiss();
//                        // Successfully downloaded data to local file
//                        // ...
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception exception) {
//                mProgress.dismiss();
//                mProgress.setMessage("Download Failed...");
//                mProgress.show();
//                // Handle failed download
//                // ...
//            }
//        });
//    }
//    private String blockCharacterSet = "~#^|$%&*!.";
//
//    private InputFilter filter = new InputFilter() {
//
//        @Override
//        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
//
//            if (source != null && blockCharacterSet.contains(("" + source))) {
//                return "";
//            }
//            return null;
//        }
//    };
//
//
//    private void AlertBoxInput()
//    {
//
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("Save file name as ");
//
//// Set up the input
//        final EditText input = new EditText(this);
//// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
//        input.setInputType(InputType.TYPE_CLASS_TEXT );
//        input.setFilters(new InputFilter[] { filter });
//            builder.setView(input);
//
//// Set up the buttons
//
//            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    m_Text = input.getText().toString();
//                    downloadAudio();
//                }
//            });
//            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    dialog.cancel();
//                }
//            });
//
//            builder.show();
//
//    }

//    private void startRecording() {
//        mRecorder = new MediaRecorder();
//        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
//        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
//        mRecorder.setOutputFile(mFileName);
//        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
//
//        try {
//            mRecorder.prepare();
//        } catch (IOException e) {
//            Log.e(LOG_TAG, "prepare() failed");
//        }
//
//        mRecorder.start();
//    }
//
//    private void stopRecording() {
//        mRecorder.stop();
//        mRecorder.release();
//        mRecorder = null;
//    }
//
//
//    public String saveImage(Bitmap myBitmap) {
//        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
//        File wallpaperDirectory = new File(
//                Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY+"/QR Images");
//        // have the object build the directory structure, if needed.
//
//        if (!wallpaperDirectory.exists()) {
//            Log.d("dirrrrrr", "" + wallpaperDirectory.mkdirs());
//            wallpaperDirectory.mkdirs();
//        }
//
//        try {
//            QRImgName=Calendar.getInstance().getTimeInMillis() + ".jpg";
//            File f = new File(wallpaperDirectory, QRImgName);
//            f.createNewFile();   //give read write permission
//            FileOutputStream fo = new FileOutputStream(f);
//            fo.write(bytes.toByteArray());
//            MediaScannerConnection.scanFile(this,
//                    new String[]{f.getPath()},
//                    new String[]{"image/jpeg"}, null);
//            fo.close();
//            Log.d("TAG", "File Saved::--->" + f.getAbsolutePath());
//
//            return f.getAbsolutePath();
//        } catch (IOException e1) {
//            e1.printStackTrace();
//        }
//        return "";
//
//    }
//
//    private Bitmap TextToImageEncode(String Value) throws WriterException {
//        BitMatrix bitMatrix;
//        try {
//            bitMatrix = new MultiFormatWriter().encode(
//                    Value,
//                    BarcodeFormat.DATA_MATRIX.QR_CODE,
//                    QRcodeWidth, QRcodeWidth, null
//            );
//
//        } catch (IllegalArgumentException Illegalargumentexception) {
//
//            return null;
//        }
//        int bitMatrixWidth = bitMatrix.getWidth();
//
//        int bitMatrixHeight = bitMatrix.getHeight();
//
//        int[] pixels = new int[bitMatrixWidth * bitMatrixHeight];
//
//        for (int y = 0; y < bitMatrixHeight; y++) {
//            int offset = y * bitMatrixWidth;
//
//            for (int x = 0; x < bitMatrixWidth; x++) {
//
//                pixels[offset + x] = bitMatrix.get(x, y) ?
//                        getResources().getColor(R.color.black) : getResources().getColor(R.color.white);
//            }
//        }
//        Bitmap bitmap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_4444);
//
//        bitmap.setPixels(pixels, 0, 500, 0, 0, bitMatrixWidth, bitMatrixHeight);
//        return bitmap;
//    }


}