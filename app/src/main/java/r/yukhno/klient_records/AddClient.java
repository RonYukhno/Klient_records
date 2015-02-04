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
    private Button btnOK;

    private MyDBHelper dbHelper;

    private String firstName;
    private String lastName;
    private String phone;

    final String LOG_TAG = "MyLogs";

    private TextWatcher textWath = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            checkFields();
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            checkFields();
        }

        @Override
        public void afterTextChanged(Editable s) {
            checkFields();
        }
    };

    public void checkFields() {

        btnOK = (Button) findViewById(R.id.btnOK);
        firstName = etFirstName.getText().toString();

        if (etFirstName.equals("") || etFirstName.length() == 0) {
            btnOK.setEnabled(false);
        } else {
            btnOK.setEnabled(true);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_client);

        etFirstName = (EditText) findViewById(R.id.etFirstName);
        etLastName = (EditText) findViewById(R.id.etLastName);
        etPhone = (EditText) findViewById(R.id.etPhone);

        dbHelper = new MyDBHelper(this);

        etFirstName.addTextChangedListener(textWath);
        checkFields();
    }

    public void onClickAdd(View v) {

        lastName = etLastName.getText().toString();
        phone = etPhone.getText().toString();

        ContentValues cv = new ContentValues();

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Log.d(LOG_TAG, "--- Insert in mytable: ---");
        cv.put(MyDBHelper.getColumnFirstName(), firstName);
        cv.put(MyDBHelper.getColumnLastName(), lastName);
        cv.put(MyDBHelper.getColumnPhone(), phone);
        long rowID = db.insert(MyDBHelper.getDatabaseTable(), null, cv);
        Log.d("MyLogs", "row inserted, ID = " + rowID);

        dbHelper.close();

        Toast.makeText(this, "Контакт добавлен в БД", Toast.LENGTH_SHORT).show();
        finish();
    }

}
