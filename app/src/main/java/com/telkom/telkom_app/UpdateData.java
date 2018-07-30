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
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ADJ on 5/14/2017.
 */
public class UpdateData extends AppCompatActivity{

    private Button update;
    String Id;
    String Nama;
    String Perusahaan;
    String Kota;
    private EditText uid1ET,uid2,nameET, perusahaanET, kotaET;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_data);
        update=(Button)findViewById(R.id.update_btn1);
        uid1ET=(EditText)findViewById(R.id.uid);
        nameET=(EditText)findViewById(R.id.name);
        perusahaanET=(EditText)findViewById(R.id.perusahaan);
        kotaET=(EditText)findViewById(R.id.kota);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Id = uid1ET.getText().toString();
                Nama = nameET.getText().toString();
                Perusahaan = perusahaanET.getText().toString();
                Kota = kotaET.getText().toString();
                new UpdateDataActivity().execute();
            }
        });
    }



    class UpdateDataActivity extends AsyncTask<Void, Void, Void> {

        ProgressDialog dialog;
        int jIndex;
        int x;

        String result=null;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            dialog = new ProgressDialog(UpdateData.this);
            dialog.setTitle("Mohon Tunggu!");
            dialog.setMessage("Sedang melakukan update data..");
            dialog.show();

        }

        @Nullable
        @Override
        protected Void doInBackground(Void... params) {
            JSONObject jsonObject = Controller.updateData(Id, Nama, Perusahaan, Kota);
            Log.i(Controller.TAG, "Json obj ");

            try {
                /**
                 * Check Whether Its NULL???
                 */
                if (jsonObject != null) {

                    result=jsonObject.getString("result");

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
            Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
        }
    }
}