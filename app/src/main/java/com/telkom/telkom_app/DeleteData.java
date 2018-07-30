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
public class DeleteData extends AppCompatActivity{

    private Button delete;
    String Id;
    String Nama;
    private EditText uid1ET;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_data);
        delete=(Button)findViewById(R.id.delete_btn);
        uid1ET=(EditText)findViewById(R.id.uid);



        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Id = uid1ET.getText().toString();

                new DeleteDataActivity().execute();
            }
        });
    }



    class DeleteDataActivity extends AsyncTask<Void, Void, Void> {

        ProgressDialog dialog;
        int jIndex;
        int x;
        String result=null;



        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            dialog = new ProgressDialog(DeleteData.this);
            dialog.setTitle("Mohon Tunggu!");
            dialog.setMessage("Melakukan penghapusan data.. ");
            dialog.show();

        }

        @Nullable
        @Override
        protected Void doInBackground(Void... params) {
            Log.i(Controller.TAG,"IDVALUE"+Id);
            JSONObject jsonObject = Controller.deleteData(Id);
            Log.i(Controller.TAG, "Json obj "+jsonObject);

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