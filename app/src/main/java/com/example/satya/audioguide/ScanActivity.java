package com.example.satya.audioguide;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ScanActivity extends AppCompatActivity {

//    SurfaceView cameraView;
//    BarcodeDetector barcode;
//    CameraSource cameraSource;
//    SurfaceHolder holder;
//
//    String LOG_TAG="ScanActivity";
//
//    Button choose;
    public static final int PICK_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

//        cameraView = (SurfaceView) findViewById(R.id.cameraView);
//        cameraView.setZOrderMediaOverlay(true);
//        holder = cameraView.getHolder();
//        barcode = new BarcodeDetector.Builder(this)
//                .setBarcodeFormats(Barcode.QR_CODE)
//                .build();
//        if (!barcode.isOperational()) {
//            Toast.makeText(getApplicationContext(), "Could't Setup Decorder", Toast.LENGTH_LONG).show();
//            this.finish();
//        }
//        cameraSource = new CameraSource.Builder(this, barcode)
//                .setFacing(CameraSource.CAMERA_FACING_BACK)
//                .setRequestedFps(24)
//                .setAutoFocusEnabled(true)
//                .setRequestedPreviewSize(1920, 1024)
//                .build();
//        cameraView.getHolder().addCallback(new SurfaceHolder.Callback() {
//            @Override
//            public void surfaceCreated(SurfaceHolder surfaceHolder) {
//                try {
//                    if (ContextCompat.checkSelfPermission(ScanActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
//                        cameraSource.start(cameraView.getHolder());
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
//
//            }
//        });
//        barcode.setProcessor(new Detector.Processor<Barcode>() {
//            @Override
//            public void release() {
//
//            }
//
//            @Override
//            public void receiveDetections(Detector.Detections<Barcode> detections) {
//                final SparseArray<Barcode> barcodes = detections.getDetectedItems();
//                setBarcodeCustom(barcodes);
//
//            }
//        });
//
//
//        choose = (Button) findViewById(R.id.choose);
//        choose.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
//                getIntent.setType("image/*");
//
//                Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                pickIntent.setType("image/*");
//
//                Intent chooserIntent = Intent.createChooser(getIntent, "Select Image");
//                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{pickIntent});
//
//                startActivityForResult(chooserIntent, PICK_IMAGE);
//            }
//        });


    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == PICK_IMAGE) {
//            try {
//                final Uri imageUri = data.getData();
//                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
//                Bitmap bitmap = BitmapFactory.decodeStream(imageStream);
//
//                Frame frame = new Frame.Builder().setBitmap(bitmap).build();
//                BarcodeDetector barcodeDetector = new BarcodeDetector.Builder(getApplicationContext())
//                        .build();
//                if(barcode.isOperational()){
//                    SparseArray<Barcode> sparseArray = barcodeDetector.detect(frame);
//                    setBarcodeCustom(sparseArray);
//
//                }else{
//                    Log.e(LOG_TAG, "Detector dependencies are not yet downloaded");
//                }
//
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//
//        }
//    }
//
//    //custum funfion to reduce code repetation
//
//    public void setBarcodeCustom(SparseArray<Barcode> barcodes)
//    {
//        if (barcodes.size() > 0) {
//            Intent intent = new Intent();
//            intent.putExtra("barcode", barcodes.valueAt(0));
//            setResult(RESULT_OK, intent);
//            finish();
//        }
//    }
}