package com.telkom.telkom_app;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ADJ on 5/17/2017.
 */
public class ReadAllData extends AppCompatActivity {

    private ListView listView;
    private ArrayList < MyDataModel > list;
    private MyArrayAdapter adapter;
    private Button readAll;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_all);

        readAll = (Button) findViewById(R.id.readAll_btn1);
        list = new ArrayList < > ();
        adapter = new MyArrayAdapter(this, list);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

        readAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ReadData1().execute();
            }
        });



    }


    class ReadData1 extends AsyncTask < Void, Void, Void > {

        ProgressDialog dialog;
        int jIndex;
        int x;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            /**
             * Progress Dialog for User Interaction
             */

            x = list.size();

            if (x == 0)
                jIndex = 0;
            else
                jIndex = x;

            dialog = new ProgressDialog(ReadAllData.this);
            dialog.setTitle("Mohon Tunggu!");
            dialog.setMessage("Sedang memuat data..");
            dialog.show();
        }

        @Nullable
        @Override
        protected Void doInBackground(Void...params) {
            JSONObject jsonObject = Controller.readAllData();
            try {
                /**
                 * Check Whether Its NULL???
                 */
                if (jsonObject != null) {
                    /**
                     * Check Length...
                     */
                    if (jsonObject.length() > 0) {
                        /**
                         * Getting Array named "records" From MAIN Json Object
                         */
                        JSONArray array = jsonObject.getJSONArray("records");

                        /**
                         * Check Length of Array...
                         */


                        int lenArray = array.length();
                        if (lenArray > 0) {
                            for (; jIndex < lenArray; jIndex++) {

                                /**
                                 * Creating Every time New Object
                                 * and
                                 * Adding into List
                                 */
                                MyDataModel model = new MyDataModel();

                                /**
                                 * Getting Inner Object from contacts array...
                                 * and
                                 * From that We will get Name of that Contact
                                 *
                                 */
                                JSONObject innerObject = array.getJSONObject(jIndex);

                                String Id = innerObject.getString("Id");
                                String Nama = innerObject.getString("Nama");
                                String Perusahaan = innerObject.getString("Perusahaan");
                                String Kota = innerObject.getString("Kota");
                                //  String image = innerObject.getString(Keys.KEY_IMAGE);
                                /**
                                 * Getting Object from Object "phone"
                                 */
                                //JSONObject phoneObject = innerObject.getJSONObject(Keys.KEY_PHONE);
                                //String phone = phoneObject.getString(Keys.KEY_MOBILE);

                                model.setNama(Nama);
                                model.setId(Id);
                                model.setPerusahaan(Perusahaan);
                                model.setKota(Kota);
                                //                              model.setImage(image);

                                /**
                                 * Adding name and phone concatenation in List...
                                 */
                                list.add(model);
                            }
                        }
                    }
                } else {

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
            /**
             * Checking if List size if more than zero then
             * Update ListView
             */
            if (list.size() > 0) {
                adapter.notifyDataSetChanged();
            } else {
                Toast.makeText(getApplicationContext(), "Tidak ada data yang ditemukan!", Toast.LENGTH_LONG).show();
            }
        }
    }
}
