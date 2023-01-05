package com.example.recyclick;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.recyclick.API.APIRequestData;
import com.example.recyclick.API.serverRetrofit;
import com.example.recyclick.Adapter.AdapterKategoriEB;
import com.example.recyclick.Model.DataBarang.EditBarang;
import com.example.recyclick.Model.Kategori.KategoriEditData;
import com.example.recyclick.Model.Kategori.KategoriEditInfo;
import com.example.recyclick.Model.Kategori.KategoriInfo;
import com.example.recyclick.Model.Kategori.KategoriItem;
import com.google.android.material.textfield.TextInputEditText;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class editBarangActivity extends AppCompatActivity {
    TextView btnBack;
    CardView btnSimpan;
    String pesan;
    public static editBarangActivity eba;
    TextInputEditText txtId, txtNama, txtJenis, txtStok, txtHarga, txtDesk;
    int ratingbr;
    Uri ur;
    ImageView imgBarang,imgkgr;
    PopupWindow popupWindow2;
    APIRequestData API;
    private int kategoriid;
    private List<KategoriItem> sup;
    private ScrollView scroll;
//    private LinearLayout imglayout;

    public List<KategoriEditData> dataKat = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_barang);
        scroll = findViewById(R.id.aeb_scroll);
        btnBack = (TextView) findViewById(R.id.btn_back);
        txtId = findViewById(R.id.inputId);
        txtNama = findViewById(R.id.inputNama);
        txtHarga = findViewById(R.id.inputHarga);
        txtJenis = findViewById(R.id.kategoriprd);
        eba = this;
        txtStok = findViewById(R.id.inputStok);
        txtDesk = findViewById(R.id.inputDesk);
        ratingbr = 1;
        imgBarang = findViewById(R.id.ed_image);
//        imglayout = findViewById(R.id.aeb_linear1);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView2 = inflater.inflate(R.layout.popup_kategori, null);
        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = false;
        popupWindow2 = new PopupWindow(popupView2, width, height, focusable);

        imgkgr = findViewById(R.id.btnkategori);
        imgkgr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showKategoriEdit(view,popupView2);
            }
        });



        Intent i = getIntent();
        String idbr = i.getStringExtra("ID");
        txtId.setText(idbr);

        String namabr = i.getStringExtra("NAMAPROD");
        txtNama.setText(namabr);

        String hargabr = i.getStringExtra("HARGAPROD");
        txtHarga.setText(hargabr);

        String stokbr = i.getStringExtra("STOKPROD");
        txtStok.setText(stokbr);

        String deskbr = i.getStringExtra("DESKPROD");
        txtDesk.setText(deskbr);

        String jenisbr = i.getStringExtra("JENISPROD");
        kategoriid = Integer.parseInt(jenisbr);
        tampilKategori(kategoriid);


        String gambarbr = i.getStringExtra("GAMBARPROD");
//        if(gambarbr != null){
//            ViewGroup.LayoutParams params = imglayout.getLayoutParams();
//            params.height = 420;
//            params.width = 420;
//            imglayout.setLayoutParams(params);
//        }
        Glide.with(getApplicationContext()).load("https://workshopjti.com/RecyclickA3/"+gambarbr).thumbnail(0.5f).centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL).error(R.drawable.photo_library_48px).into(imgBarang);


        Log.d(String.valueOf(ur), "nilai ur: ");


        btnSimpan = findViewById(R.id.btnSimpan);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtId.equals("") || txtNama.length() == 0 || txtStok.length() == 0 || txtHarga.length() == 0 || txtDesk.length() == 0 || txtJenis.length() == 0) {
                    Toast.makeText(editBarangActivity.this, "Data Tidak Boleh Kosong !", Toast.LENGTH_SHORT).show();
                }else if(txtHarga.length() > 10 && txtStok.length()>10){
                    Toast.makeText(editBarangActivity.this, "Input Harga dan Stok Terlalu Banyak !", Toast.LENGTH_SHORT).show();
                } else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getRootView().getContext(), R.style.AlertDialog);
                    View view2 = LayoutInflater.from(view.getRootView().getContext()).inflate(
                            R.layout.layout_hapus_data, null);
                    builder.setView(view2);
                    ((TextView) view2.findViewById(R.id.textJudul)).setText("Apakah Anda Yakin dengan Perubahan Ini ?");
                    ((TextView) view2.findViewById(R.id.textMessage)).setText("Klik Simpan jika Setuju!");
                    ((Button) view2.findViewById(R.id.btnHapus)).setText("Simpan");
                    ((Button) view2.findViewById(R.id.btnKembali)).setText("Kembali");
//                    ((ImageView) view2.findViewById(R.id.imageIcon)).setImageResource(R.drawable.ic_baseline_help_24);

                    AlertDialog alertDialog = builder.create();

                    view2.findViewById(R.id.btnHapus).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String idbr = txtId.getText().toString();
                            String namabr = txtNama.getText().toString();
                            int stokbr = Integer.parseInt(String.valueOf(txtStok.getText()));
                            int hargabr = Integer.parseInt(String.valueOf(txtHarga.getText()));
                            String deskbr = txtDesk.getText().toString();
                            int rating = 5;

                            EditDataBarang(idbr, namabr, stokbr, hargabr, deskbr, kategoriid, rating, gambarbr);

                        }
                    });
                    view2.findViewById(R.id.btnKembali).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            alertDialog.dismiss();
                        }
                    });
                    if (alertDialog.getWindow() != null) {
                        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                    }
                    alertDialog.show();

                }
            }
        });

        imgBarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getImg();
            }
        });

        txtHarga.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(txtHarga.length() ==10){
                    Toast.makeText(eba, "Harga tidak boleh lebih dari 10 digit !", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        txtStok.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(txtStok.length() == 10 ){
                    Toast.makeText(eba, "Stok tidak boleh lebih dari 10 digit !", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        scroll.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                popupWindow2.dismiss();
            }
        });

    }

    public void EditDataBarang(String id, String nama, int stok, int harga, String desk, int jenis, int rating, String gambarOld) {
        APIRequestData api = serverRetrofit.koneksiRetrofit().create(APIRequestData.class);

        Call<EditBarang> call;
        if (ur != null) {
            String path = getRealPathFromUri(this, ur);
            File file = new File(path);
            RequestBody idbr = RequestBody.create(MediaType.parse("text/plain"), id);
            RequestBody namabr = RequestBody.create(MediaType.parse("text/plain"), nama);
            RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), file);
            RequestBody deskripsibr = RequestBody.create(MediaType.parse("text/plain"), desk);
            RequestBody gambarOld2 = RequestBody.create(MediaType.parse("text/plain"), gambarOld);
            RequestBody isnull = RequestBody.create(MediaType.parse("text/plain"), "false");
            MultipartBody.Part body = MultipartBody.Part.createFormData("imageup", file.getName(), requestFile);
            call = api.postEditBarangWithImg(body, idbr, namabr, stok, harga, gambarOld2, deskripsibr, jenis,rating, isnull);
        } else {
            call = api.postEditBarangNoImg(id, nama, stok, harga, gambarOld, desk, jenis,rating, "true");
        }
        View view = getLayoutInflater().inflate(R.layout.toast_loading, null);
        view.findViewById(R.id.toast_noConnection);
        Toast toast = new Toast(getApplicationContext());
        toast.setView(view);
        toast.show();
        toast.setGravity(Gravity.CENTER,0,0);
        call.enqueue(new Callback<EditBarang>() {
            @Override
            public void onResponse(Call<EditBarang> call, Response<EditBarang> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String pesan = response.body().getPesan();
                    toast.cancel();
                    if (response.body().isKondisi() == true) {
                        View view = getLayoutInflater().inflate(R.layout.toast_edit_produk, null);
                        view.findViewById(R.id.toast_succesRegist);
                        Toast toast = new Toast(getApplicationContext());
                        toast.setDuration(Toast.LENGTH_LONG);
                        toast.setView(view);
                        toast.show();
                        toast.setGravity(Gravity.TOP | Gravity.CENTER,0,0);
                        startActivity(new Intent(editBarangActivity.this, DataBarangActivity.class));
                    } else {
                        Toast.makeText(editBarangActivity.this, pesan, Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<EditBarang> call, Throwable t) {
                toast.cancel();
                View view = getLayoutInflater().inflate(R.layout.toast_no_internet, null);
                view.findViewById(R.id.toast_noConnection);
                Toast toast = new Toast(getApplicationContext());
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(view);
                toast.show();
                toast.setGravity(Gravity.TOP | Gravity.CENTER,0,0);
                Log.e("error", "onFailure: " + t.getMessage());
            }

        });
    }


    public void getImg() {
//        final CharSequence[] opsiImg = {"Gallery", "Camera"};
//        AlertDialog.Builder builder = new AlertDialog.Builder(editBarangActivity.this);
//        builder.setTitle("Pilih gambar dari");
//        builder.setItems(opsiImg, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                switch (i) {
//                    case 0:
                        Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(pickPhoto, 0);
//                        break;
//                    case 1:
//                        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                        startActivityForResult(takePicture, 1);
//                        break;
//                }
//            }
//        });
//        builder.create().show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            ur = data.getData();
            imgBarang.setImageURI(ur);
        }
    }

    public static String getRealPathFromUri(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public void showKategoriEdit(View view, View view2) {
        int[] loc_int = new int[2];
        txtJenis.getLocationOnScreen(loc_int);
        Rect location = new Rect();
        location.left = loc_int[0];
        location.top = loc_int[1];
        location.right = location.left + txtJenis.getWidth();
        location.bottom = location.top + txtJenis.getHeight();

        APIRequestData api = serverRetrofit.koneksiRetrofit().create(APIRequestData.class);

        RecyclerView recyclerView = view2.findViewById(R.id.popuprcy);
        Call<KategoriEditInfo> call = api.getKatEdit();
        call.enqueue(new Callback<KategoriEditInfo>() {
            @Override
            public void onResponse(Call<KategoriEditInfo> call, Response<KategoriEditInfo> response) {
                pesan = response.body().getPesan();
                dataKat = response.body().getData();
                AdapterKategoriEB adapter = new AdapterKategoriEB(dataKat);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(editBarangActivity.this));
                Log.d(pesan, "onResponseShowKategori: ");
            }

            @Override
            public void onFailure(Call<KategoriEditInfo> call, Throwable t) {
                View view = getLayoutInflater().inflate(R.layout.toast_no_internet, null);
                view.findViewById(R.id.toast_noConnection);
                Toast toast = new Toast(getApplicationContext());
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(view);
                toast.show();
                toast.setGravity(Gravity.TOP | Gravity.CENTER,0,0);
                Log.d(pesan, "onFailureShow: ");
            }
        });

        popupWindow2.showAtLocation(view, Gravity.TOP | Gravity.LEFT , location.left,location.bottom);
    }

    public void closepopkategori() {
        popupWindow2.dismiss();
    }

    public void setkategori(int id, String kategoriname) {
        txtJenis.setText(kategoriname);
        kategoriid = id;

    }


    public void tampilKategori(int ktid) {
        API = serverRetrofit.koneksiRetrofit().create(APIRequestData.class);
        Call<KategoriInfo> call = API.getKategoriData();
        call.enqueue(new Callback<KategoriInfo>() {
            @Override
            public void onResponse(retrofit2.Call<KategoriInfo> call, Response<KategoriInfo> response) {
                    if(response.body()!= null && response.isSuccessful()){
                       sup = response.body().getData();
                       KategoriItem item = sup.get(ktid);
                       txtJenis.setText(item.getNamaKategori());
                    }
            }
            @Override
            public void onFailure(retrofit2.Call<KategoriInfo> call, Throwable t) {
                View view = getLayoutInflater().inflate(R.layout.toast_no_internet, null);
                view.findViewById(R.id.toast_noConnection);
                Toast toast = new Toast(getApplicationContext());
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(view);
                toast.show();
                toast.setGravity(Gravity.TOP | Gravity.CENTER,0,0);
            }
        });

    }

}