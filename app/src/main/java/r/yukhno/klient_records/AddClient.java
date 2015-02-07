package r.yukhno.klient_records;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class AddClient extends ActionBarActivity {

    private EditText etFirstName;
    private EditText etLastName;
    private EditText etPhone;
    private EditText etNote;

    private MyDBHelper dbHelper;

    private String firstName;
    private String lastName;
    private String phone;
    private String note;

    final String LOG_TAG = "MyLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_client);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etFirstName = (EditText) findViewById(R.id.etFirstName);
        etLastName = (EditText) findViewById(R.id.etLastName);
        etPhone = (EditText) findViewById(R.id.etPhone);
        etNote = (EditText) findViewById(R.id.etNote);

        dbHelper = new MyDBHelper(this);

    }

    public void onClickAdd(MenuItem item) {

        firstName = etFirstName.getText().toString();
        lastName = etLastName.getText().toString();
        phone = etPhone.getText().toString();
        note = etNote.getText().toString();

        if (!firstName.equals("") || firstName.length() != 0) {

            ContentValues cv = new ContentValues();

            SQLiteDatabase db = dbHelper.getWritableDatabase();

            Log.d(LOG_TAG, "--- Insert in mytable: ---");
            cv.put(MyDBHelper.getColumnFirstName(), firstName);
            cv.put(MyDBHelper.getColumnLastName(), lastName);
            cv.put(MyDBHelper.getColumnPhone(), phone);
            cv.put(MyDBHelper.getColumnNote(), note);
            long rowID = db.insert(MyDBHelper.getDatabaseTable(), null, cv);
            Log.d("MyLogs", "row inserted, ID = " + rowID);

            dbHelper.close();

            Toast.makeText(this, "Контакт добавлен в БД", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_add_client, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                finish();
                return true;

        }

        return super.onOptionsItemSelected(item);
    }

}
