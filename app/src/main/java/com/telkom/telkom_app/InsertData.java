package com.telkom.telkom_app;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

/**
 * Created by ADJ on 5/14/2017.
 */
public class InsertData extends AppCompatActivity {

    private Button insert;
    String Id;
    String Nama;
    String Perusahaan;
    String Kota;
    private EditText uid1ET, uid2, nameET, perusahaan, kota;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_data);
        insert = (Button) findViewById(R.id.insert_btn);
        uid1ET = (EditText) findViewById(R.id.uid);
        nameET = (EditText) findViewById(R.id.name);
        perusahaan = (EditText) findViewById(R.id.perusahaan);
        kota = (EditText) findViewById(R.id.kota);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Id = uid1ET.getText().toString();
                Nama = nameET.getText().toString();
                Perusahaan = perusahaan.getText().toString();
                Kota = kota.getText().toString();

                if(TextUtils.isEmpty(Id)){
                    uid1ET.setError("Id is required!");
                }
                else if(TextUtils.isEmpty(Nama)){
                    nameET.setError("Nama is required!");
                }
                else if(TextUtils.isEmpty(Perusahaan)){
                    perusahaan.setError("Perusahaan is required!");
                }
                else if(TextUtils.isEmpty(Kota)){
                    kota.setError("Kota is required!");
                }
                else{
                    new InsertDataActivity().execute();
                }

            }
        });
    }



    class InsertDataActivity extends AsyncTask < Void, Void, Void > {

        ProgressDialog dialog;
        int jIndex;
        int x;

        String result = null;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            dialog = new ProgressDialog(InsertData.this);
            dialog.setTitle("Mohon Tunggu!");
            dialog.setMessage("Sedang mengupload data..");
            dialog.show();

        }

        @Nullable
        @Override
        protected Void doInBackground(Void...params) {
            JSONObject jsonObject = Controller.insertData(Id, Nama, Perusahaan, Kota);
            Log.i(Controller.TAG, "Json obj ");

            try {
                /**
                 * Check Whether Its NULL???
                 */
                if (jsonObject != null) {

                    result = jsonObject.getString("result");

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
            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
        }
    }
}