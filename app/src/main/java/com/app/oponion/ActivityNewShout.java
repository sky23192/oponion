package com.app.oponion;

import android.*;
import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LevelListDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.os.ResultReceiver;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;

import firebasemodels.Feed;
import gun0912.tedbottompicker.TedBottomPicker;

public class ActivityNewShout extends AppCompatActivity implements View.OnClickListener
{


    private static final String TAG = Oponion.APP_TAG + ActivityNewShout.class.getSimpleName();
    private static final int REQUEST_CAMERA = 1212;
    private static final int SELECT_FILE = 1234;

    private String userChosenTask;

    private ImageView ivSelectedImage, ivLocation;

    private static final int REQUEST_LOCATION = 6;

    private AddressResultReceiver mResultReceiver;

    TextView tvLocation;

    EditText etBody;

    TedBottomPicker tedBottomPicker;

    final PermissionListener permissionListener = new PermissionListener()
    {
        @Override
        public void onPermissionGranted()
        {

            tedBottomPicker = new TedBottomPicker.Builder(ActivityNewShout.this)
                    .setOnImageSelectedListener(new TedBottomPicker.OnImageSelectedListener() {
                        @Override
                        public void onImageSelected(Uri uri) {

                        }
                    })
                    .create();


        }

        @Override
        public void onPermissionDenied(ArrayList<String> deniedPermissions)
        {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_new_shout);

        findViewById(R.id.iv_close).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });

        findViewById(R.id.iv_gallery).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                tedBottomPicker.show(getSupportFragmentManager());
                //selectImage();
            }
        });

        findViewById(R.id.btn_postFeed).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Feed.postFeed("9409210488", "23.0225,72.5714", "cover image url", "Title goes here",
                        "<h2>Title</h2><br><p>Description here</p><img src='https://pbs.twimg.com/profile_images/762369348300251136/5Obhonwa.jpg'/>");
            }
        });


        etBody = (EditText) findViewById(R.id.tv_feedBody);

        Html.ImageGetter imageGetter = new Html.ImageGetter()
        {
            Drawable d = null;

            @Override
            public Drawable getDrawable(final String source)
            {

                LevelListDrawable d = new LevelListDrawable();
                Drawable empty = getResources().getDrawable(R.drawable.icon);
                d.addLevel(0, 0, empty);
                d.setBounds(0, 0, empty.getIntrinsicWidth(), empty.getIntrinsicHeight());

                new LoadImage().execute(source, d);

                return d;
            }

        };

        etBody.setText(Html.fromHtml("<h2>Title</h2><br><p>Description here</p><img src='https://pbs.twimg.com/profile_images/762369348300251136/5Obhonwa.jpg'/>", imageGetter, null));

        ivSelectedImage = (ImageView) findViewById(R.id.iv_selectedImage);

        tvLocation = (TextView) findViewById(R.id.tv_location);

        ivLocation = (ImageView) findViewById(R.id.iv_location);

        ivLocation.setOnClickListener(this);

        mResultReceiver = new AddressResultReceiver(null);

        Intent intent = new Intent(this, GeocodeAddressIntentService.class);
        intent.putExtra(Constants.RECEIVER, mResultReceiver);
        intent.putExtra(Constants.FETCH_TYPE_EXTRA, Constants.USE_ADDRESS_LOCATION);

        intent.putExtra(Constants.LOCATION_LATITUDE_DATA_EXTRA,
                Double.parseDouble("23.0225"));
        intent.putExtra(Constants.LOCATION_LONGITUDE_DATA_EXTRA,
                Double.parseDouble("72.5714"));

        startService(intent);

        checkForPermission();



    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        Log.i(TAG, "onActivityResult: ON ACTIVITY RESULT");

        Log.i(TAG, "onActivityResult: RESULT CODE IS OK!!!");

        Log.i(TAG, "onActivityResult: REQUEST CODE IS: " + resultCode);

        switch (requestCode)
        {
            case SELECT_FILE:
                onSelectFromGalleryResult(data);
                break;

            case REQUEST_CAMERA:
                onCaptureImageResult(data);
                break;

            case REQUEST_LOCATION:
                if (data != null)
                {
                    Intent intent = new Intent(this, GeocodeAddressIntentService.class);
                    intent.putExtra(Constants.RECEIVER, mResultReceiver);
                    intent.putExtra(Constants.FETCH_TYPE_EXTRA, Constants.USE_ADDRESS_LOCATION);

                    intent.putExtra(Constants.LOCATION_LATITUDE_DATA_EXTRA, data.getDoubleExtra("lat", 0));
                    intent.putExtra(Constants.LOCATION_LONGITUDE_DATA_EXTRA, data.getDoubleExtra("lng", 0));
                } else
                {
                    Log.i(TAG, "onActivityResult: DATA is NULL!!!!!");
                }
                break;

            default:
                super.onActivityResult(requestCode, resultCode, data);


        }


    }


    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data)
    {
        Bitmap bm = null;
        if (data != null)
        {
            try
            {
                bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        ivSelectedImage.setImageBitmap(bm);
    }


    private void onCaptureImageResult(Intent data)
    {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");
        FileOutputStream fo;
        try
        {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        ivSelectedImage.setImageBitmap(thumbnail);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
    {
        switch (requestCode)
        {
            case Utility.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    if (userChosenTask.equals("Take Photo"))
                    {
                        cameraIntent();
                    } else if (userChosenTask.equals("Choose from Library"))
                    {
                        galleryIntent();
                    }
                } else
                {
                    //code for deny
                }
                break;
        }
    }


    private void selectImage()
    {
        final CharSequence[] items = {"Take Photo", "Choose from Library",
                "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int item)
            {
                boolean result = Utility.checkPermission(ActivityNewShout.this);

                if (items[item].equals("Take Photo"))
                {
                    userChosenTask = "Take Photo";
                    if (result)
                    {
                        cameraIntent();
                    }
                } else if (items[item].equals("Choose from Library"))
                {
                    userChosenTask = "Choose from Library";
                    if (result)
                    {
                        galleryIntent();
                    }
                } else if (items[item].equals("Cancel"))
                {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }


    private void cameraIntent()
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    private void galleryIntent()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE);
    }

    @Override
    public void onClick(View v)
    {
        Intent i = new Intent(ActivityNewShout.this, MapsActivity.class);
        Log.i(TAG, "onClick: REQUEST_LOCATION CODE IS: " + REQUEST_LOCATION);
        startActivityForResult(i, REQUEST_LOCATION);
    }

    @SuppressLint("ParcelCreator")
    class AddressResultReceiver extends ResultReceiver
    {
        public AddressResultReceiver(Handler handler)
        {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, final Bundle resultData)
        {
            if (resultCode == Constants.SUCCESS_RESULT)
            {
                //final Address address = resultData.getParcelable(Constants.RESULT_ADDRESS);
                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        String address = resultData.getString(Constants.RESULT_DATA_KEY);
                        address = address.substring(0, address.lastIndexOf(" "));
                        tvLocation.setText(address);
                        tvLocation.setVisibility(View.VISIBLE);
                    }
                });
            } else
            {
                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {

                    }
                });
            }
        }
    }


    class LoadImage extends AsyncTask<Object, Void, Bitmap>
    {

        private LevelListDrawable mDrawable;

        @Override
        protected Bitmap doInBackground(Object... params)
        {
            String source = (String) params[0];
            mDrawable = (LevelListDrawable) params[1];
            Log.d(TAG, "doInBackground " + source);
            try
            {
                InputStream is = new URL(source).openStream();
                return BitmapFactory.decodeStream(is);
            } catch (FileNotFoundException e)
            {
                e.printStackTrace();
            } catch (MalformedURLException e)
            {
                e.printStackTrace();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap)
        {
            Log.d(TAG, "onPostExecute drawable " + mDrawable);
            Log.d(TAG, "onPostExecute bitmap " + bitmap);
            if (bitmap != null)
            {
                BitmapDrawable d = new BitmapDrawable(bitmap);
                mDrawable.addLevel(1, 1, d);
                mDrawable.setBounds(0, 0, bitmap.getWidth(), bitmap.getHeight());
                mDrawable.setLevel(1);
                // i don't know yet a better way to refresh TextView
                // mTv.invalidate() doesn't work as expected
                CharSequence t = etBody.getText();
                etBody.setText(t);
            }
        }
    }


    void checkForPermission()
    {
        new TedPermission(this)
                .setPermissionListener(permissionListener)
                .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .check();
    }


}
