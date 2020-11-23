package com.adesurahman.crudsqllite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText npm,nama;
    TextView textView;
    DB_Controller controller;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        npm = (EditText)findViewById(R.id.et_npm);
        nama = (EditText)findViewById(R.id.et_nama);
        textView = (TextView)findViewById(R.id.tv_list);

        controller = new DB_Controller(this,"",null,1);

    }

    public void aksi_simpan(View view) {
        try {
            controller.insert_student(npm.getText().toString(),nama.getText().toString());
            controller.list_all_students(textView);
            npm.setText("");
            nama.setText("");
            Toast.makeText(MainActivity.this, "Data Tersimpan", Toast.LENGTH_SHORT).show();

        }catch (SQLiteException e){
            Toast.makeText(MainActivity.this, "ALREADY EXIST", Toast.LENGTH_SHORT).show();
        }


    }

    public void aksi_ubah(View view) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
        dialog.setTitle("ENTER NEW NPM ");

        final EditText new_npm = new EditText(this);
        dialog.setView(new_npm);

        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i){
                controller.update_student(npm.getText().toString(),new_npm.getText().toString());
            }
        });
        dialog.show();
    }

    public void aksi_list(View view) {
        controller.list_all_students(textView);
    }

    public void aksi_hapus(View view) {
        controller.delete_student(npm.getText().toString());
    }
}