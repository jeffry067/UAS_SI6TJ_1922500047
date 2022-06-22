package com.example.uassi6tj1922500047;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class EdosenActivity extends AppCompatActivity {
    private EditText txtnidn;
    private EditText txtnamadosen;
    private EditText txtjabatan;
    private EditText txtgolpang;
    private EditText txtkeahlian;
    private EditText txtprogramstudi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edosen);

        txtnidn = (EditText) findViewById(R.id.txtnidn);
        txtnamadosen = (EditText) findViewById(R.id.txtnamadosen);
        txtjabatan  = (EditText) findViewById(R.id.txtjabatan);
        txtgolpang = (EditText) findViewById(R.id.txtgolpangkat);
        txtkeahlian = (EditText) findViewById(R.id.txtbidang);
        txtprogramstudi = (EditText) findViewById(R.id.txtprogramstudi);
    }

    public void simpan(View view) {
        final String nidn = txtnidn.getText().toString().trim();
        final String nama_dosen = txtnamadosen.getText().toString().trim();
        final String jabatan = txtjabatan.getText().toString().trim();
        final String gol_pang = txtgolpang.getText().toString().trim();
        final String keahlian = txtkeahlian.getText().toString().trim();
        final String program_studi = txtprogramstudi.getText().toString().trim();


        class simpan2 extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(EdosenActivity.this,
                        "Add", "wait", false, false);
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String, String> params = new HashMap<>();
                params.put("nidn", nidn);
                params.put("nama_dosen", nama_dosen);
                params.put("jabatan", jabatan);
                params.put("gol_pang", gol_pang);
                params.put("keahlian", keahlian);
                params.put("program_studi", program_studi);
                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest("http:/192.168.43.97/UasSI6TJ/tambah_dosen.php", params);
                return res;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();

                //pindah ke menu utama
                if (s.equals("berhasil")) {
                    Intent i = new Intent(EdosenActivity.this, MainActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(EdosenActivity.this,
                            "Data harus lengkap", Toast.LENGTH_SHORT).show();
                }
            }
        }
        simpan2 ae = new simpan2();
        ae.execute();
    }

    public void dashboard(View view) {
        Intent i = new Intent( EdosenActivity.this, MainActivity.class);
        startActivity(i);
    }
}