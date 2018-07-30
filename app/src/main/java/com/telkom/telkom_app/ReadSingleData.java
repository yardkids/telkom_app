package com.telkom.telkom_app;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ADJ on 5/14/2017.
 */
public class ReadSingleData extends AppCompatActivity {

    private Button read;
    String id;
    String name;
    String perusahaan;
    String kota;
    private EditText uid1ET;
    private TextView id_l, name_l, perusahaan_l, kota_l, id_v, name_v, perusahaan_v, kota_v;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_data);
        read = (Button) findViewById(R.id.insert_btn);
        uid1ET = (EditText) findViewById(R.id.uid);

        id_l = (TextView) findViewById(R.id.id_l);
        name_l = (TextView) findViewById(R.id.name_l);
        perusahaan_l = (TextView) findViewById(R.id.perusahaan_l);
        kota_l = (TextView) findViewById(R.id.kota_l);
        id_v = (TextView) findViewById(R.id.id_v);
        name_v = (TextView) findViewById(R.id.name_v);
        perusahaan_v = (TextView) findViewById(R.id.perusahaan_v);
        kota_v = (TextView) findViewById(R.id.kota_v);


        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                id = uid1ET.getText().toString();

                new ReadDataActivity().execute();
            }
        });
    }


    class ReadDataActivity extends AsyncTask < Void, Void, Void > {

        ProgressDialog dialog;
        int jIndex;
        int x;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            dialog = new ProgressDialog(ReadSingleData.this);
            dialog.setTitle("Mohon Tunggu!");
            dialog.setMessage("Sedang memuat data yang dicari..");
            dialog.show();

        }

        @Nullable
        @Override
        protected Void doInBackground(Void...params) {
            Log.i(Controller.TAG, "IDVALUE" + id);
            JSONObject jsonObject = Controller.readData(id);
            Log.i(Controller.TAG, "Json obj " + jsonObject);

            try {
                /**
                 * Check Whether Its NULL???
                 */
                if (jsonObject != null) {

                    JSONObject user = jsonObject.getJSONObject("user");
                    name = user.getString("Nama");
                    perusahaan = user.getString("Perusahaan");
                    kota = user.getString("Kota");

                }
            } catch (JSONException je) {
                Log.i(Controller.TAG, "" + je.getLocalizedMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();
            if (name != null) {
                id_l.setText("ID : ");
                name_l.setText("NAMA : ");
                perusahaan_l.setText("PERUSAHAAN : ");
                kota_l.setText("KOTA : ");
                id_v.setText(id);
                name_v.setText(name);
                perusahaan_v.setText(perusahaan);
                kota_v.setText(kota);

            } else
                Toast.makeText(getApplicationContext(), "ID tidak ditemukan !", Toast.LENGTH_LONG).show();
        }
    }
}