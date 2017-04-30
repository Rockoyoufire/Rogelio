package com.longisland_219408hotmail.mathematicswolf;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lionsquare.annca.Annca;
import com.lionsquare.annca.internal.configuration.AnncaConfiguration;
import com.longisland_219408hotmail.mathematicswolf.db.DBManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ImageSelectActivity extends AppCompatActivity implements View.OnClickListener {


    private final int SELECCIONA_GALLERY = 100;
    private final int SELECCIONA_CAMERA = 200;
    private Context mContext;
    String imagePath;
    View parentView;
    ImageView imageView;
    TextView textView;
    int numeroAleatorio;
    private int id_chat;
    private int typeChat;
    private DBManager dbManager;
    private String mPath;
    private EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_select);

        mContext = this;
        dbManager = new DBManager(this);
        dbManager.open();
        initToolbar();
        initSetup();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == SELECCIONA_GALLERY) {
            if (data == null) {
                Snackbar.make(parentView, R.string.string_unable_to_pick_image, Snackbar.LENGTH_INDEFINITE).show();
                return;
            }
            Uri selectedImageUri = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImageUri, filePathColumn, null, null, null);

            if (cursor != null) {
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imagePath = cursor.getString(columnIndex);
                Log.e("imagePath", imagePath);

                Glide.with(mContext).load(new File(imagePath)).into(imageView);

                Snackbar.make(parentView, R.string.string_reselect, Snackbar.LENGTH_LONG).show();
                cursor.close();

                textView.setVisibility(View.GONE);
                imageView.setVisibility(View.VISIBLE);
            } else {
                textView.setVisibility(View.VISIBLE);
                imageView.setVisibility(View.GONE);
                Snackbar.make(parentView, R.string.string_unable_to_load_image, Snackbar.LENGTH_LONG).show();
            }
        }
        //tomar foto ok
        if (resultCode == RESULT_OK && requestCode == SELECCIONA_CAMERA) {

            String path = data.getStringExtra(AnncaConfiguration.Arguments.FILE_PATH);
            Log.e("paht", path);
            // String path = Environment.getExternalStorageDirectory() + File.separator + DIRECTORIO + File.separator + IMAGEN_TEMPORAL;
            //Log.e("path", path);
            imagePath = path;

            Glide.with(mContext).load(new File(path)).into(imageView);
            textView.setVisibility(View.GONE);
            imageView.setVisibility(View.VISIBLE);
            Snackbar.make(parentView, R.string.string_reselect, Snackbar.LENGTH_LONG).show();

        }


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent returnIntent = new Intent();
                setResult(Activity.RESULT_CANCELED, returnIntent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        Intent returnIntent = new Intent();
        setResult(Activity.RESULT_CANCELED, returnIntent);
        finish();

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_upload:
                saveImage();
                break;
        }

    }

    void initSetup() {
        // TODO: 08/03/2017  mCommChListner es la interface ára aactualizar el rv desde esta actividad

        textView = (TextView) findViewById(R.id.textView);
        imageView = (ImageView) findViewById(R.id.imageView);
        editText = (EditText) findViewById(R.id.et_name);
        parentView = findViewById(R.id.parentView);
        numeroAleatorio = (int) (Math.random() * 95000546 + 1);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_upload);
        fab.setOnClickListener(this);
    }

    void initToolbar() {

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // getSupportActionBar().setTitle("Mandar imagen");

    }

    void iniciaCamara() {

        AnncaConfiguration.Builder photo = new AnncaConfiguration.Builder(ImageSelectActivity.this, SELECCIONA_CAMERA);
        photo.setMediaAction(AnncaConfiguration.MEDIA_ACTION_PHOTO);
        photo.setMediaQuality(AnncaConfiguration.MEDIA_QUALITY_LOW);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        new Annca(photo.build()).launchCamera();

       /* File file = new File(Environment.getExternalStorageDirectory(), DIRECTORIO);
        boolean isDirectoryCreated = file.exists();
        if (!isDirectoryCreated)
            isDirectoryCreated = file.mkdirs();

        if (isDirectoryCreated) {
            mPath = Environment.getExternalStorageDirectory() + File.separator + DIRECTORIO + File.separator + IMAGEN_TEMPORAL;

            File newFile = new File(mPath);
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_SCREEN_ORIENTATION, 0);
            intent.putExtra(MediaStore.EXTRA_SIZE_LIMIT,  1024*1024);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(newFile));
            startActivityForResult(intent, SELECCIONA_CAMERA);
        }*/

    }

    void iniciaGaleria() {
        // File System.
        final Intent galleryIntent = new Intent();
        galleryIntent.setType("image/*");
        galleryIntent.setAction(Intent.ACTION_PICK);

        // Chooser of file system options.
        final Intent chooserIntent = Intent.createChooser(galleryIntent, getString(R.string.string_choose_image));
        startActivityForResult(chooserIntent, SELECCIONA_GALLERY);

    }


    public void showImagePopup(View view) {

        CharSequence colors[] = new CharSequence[]{"Camara", "Galeria"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Seleccione una");
        builder.setItems(colors, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    iniciaCamara();
                } else if (which == 1) {
                    iniciaGaleria();
                }
            }
        });
        builder.show();
    }


    void saveImage() {
        if (!TextUtils.isEmpty(imagePath)) {

            dbManager.insert(editText.getText().toString().trim(), imagePath);
            Intent returnIntent = new Intent();
            setResult(Activity.RESULT_OK, returnIntent);
            Toast.makeText(this, "La imagen se a guardado en mi Archivos", Toast.LENGTH_LONG).show();
            finish();
        } else {
            Snackbar.make(parentView, R.string.string_message_to_attach_file, Snackbar.LENGTH_INDEFINITE).show();
        }
    }


}
