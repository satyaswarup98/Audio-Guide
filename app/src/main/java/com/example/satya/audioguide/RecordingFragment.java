package com.example.satya.audioguide;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaRecorder;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class RecordingFragment extends Fragment {

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

    TextView result;
    View mainView;

    private String m_Text = "";

    public RecordingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mainView  =  inflater.inflate(R.layout.fragment_recording, container, false);

        mProgress = new ProgressDialog(getActivity());

        mFileName = Environment.getExternalStorageDirectory().getAbsolutePath();
        mFileName += "/rec.3gp";


        mStorageRef = FirebaseStorage.getInstance().getReference();


        mSendData = (Button) mainView.findViewById(R.id.button);

        mSendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (a == 0) {
                    a = 1;
                    startRecording();
                    mSendData.setText("Stop");

                } else {
                    a = 0;
                    stopRecording();
                    mSendData.setText("Start");
                    uploadAudio();

                }


            }
        });



        return mainView;
    }


    private void startRecording() {
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setOutputFile(mFileName);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            mRecorder.prepare();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }

        mRecorder.start();
    }

    private void stopRecording() {
        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;
    }


    public String saveImage(Bitmap myBitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File wallpaperDirectory = new File(
                Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY+"/QR Images");
        // have the object build the directory structure, if needed.

        if (!wallpaperDirectory.exists()) {
            Log.d("dirrrrrr", "" + wallpaperDirectory.mkdirs());
            wallpaperDirectory.mkdirs();
        }

        try {
            QRImgName=Calendar.getInstance().getTimeInMillis() + ".jpg";
            File f = new File(wallpaperDirectory, QRImgName);
            f.createNewFile();   //give read write permission
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(getActivity(),
                    new String[]{f.getPath()},
                    new String[]{"image/jpeg"}, null);
            fo.close();
            Log.d("TAG", "File Saved::--->" + f.getAbsolutePath());

            return f.getAbsolutePath();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "";

    }

    private Bitmap TextToImageEncode(String Value) throws WriterException {
        BitMatrix bitMatrix;
        try {
            bitMatrix = new MultiFormatWriter().encode(
                    Value,
                    BarcodeFormat.DATA_MATRIX.QR_CODE,
                    QRcodeWidth, QRcodeWidth, null
            );

        } catch (IllegalArgumentException Illegalargumentexception) {

            return null;
        }
        int bitMatrixWidth = bitMatrix.getWidth();

        int bitMatrixHeight = bitMatrix.getHeight();

        int[] pixels = new int[bitMatrixWidth * bitMatrixHeight];

        for (int y = 0; y < bitMatrixHeight; y++) {
            int offset = y * bitMatrixWidth;

            for (int x = 0; x < bitMatrixWidth; x++) {

                pixels[offset + x] = bitMatrix.get(x, y) ?
                        getResources().getColor(R.color.black) : getResources().getColor(R.color.white);
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_4444);

        bitmap.setPixels(pixels, 0, 500, 0, 0, bitMatrixWidth, bitMatrixHeight);
        return bitmap;
    }

    public void uploadAudio() {

        mProgress.setMessage("Uploading Audio...");
        mProgress.show();
        iv = (ImageView)mainView.findViewById(R.id.iv);
        Uri file = Uri.fromFile(new File(mFileName));
        downloadUUID = UUID.randomUUID().toString();
        riversRef = mStorageRef.child("Audio").child(downloadUUID);
        riversRef.putFile(file)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                         Get a URL to the uploaded content
                        mProgress.dismiss();


                        try {
                            bitmap = TextToImageEncode(downloadUUID);
                            iv.setImageBitmap(bitmap);
                            String path = saveImage(bitmap);  //give read write permission
//                            Toast.makeText(MainActivity.this, "QRCode saved to -> " + path, Toast.LENGTH_SHORT).show();
                            shareImage();
                        } catch (WriterException e) {
                            e.printStackTrace();
                        }
                    }
                });

    }

    public void shareImage()
    {
        Bitmap icon = bitmap;
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("image/jpeg");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        icon.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        File f = new File(Environment.getExternalStorageDirectory() + File.separator + QRImgName);
        try {
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        share.putExtra(Intent.EXTRA_STREAM, Uri.parse(Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY+"/QR Images/"+QRImgName));
        startActivity(Intent.createChooser(share, "Share Image"));
    }


}
