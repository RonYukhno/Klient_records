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

    private static final String DATABASE_TABLE = "client_list";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String PHONE = "phone";

    private EditText etFirstName;
    private EditText etLastName;
    private EditText etPhone;
    private Button btnOK;

    MyDBHelper dbHelper;

    String firstName;
    String lastName;
    String phone;

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

        lastName = etLastName.getText().toString();
        phone = etPhone.getText().toString();

        etFirstName.addTextChangedListener(textWath);
        checkFields();
    }

    public void onClickAdd(View v) {

        ContentValues cv = new ContentValues();

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Log.d(LOG_TAG, "--- Insert in mytable: ---");
        cv.put(FIRST_NAME, firstName);
        cv.put(LAST_NAME, lastName);
        cv.put(PHONE, phone);
        long rowID = db.insert(DATABASE_TABLE, null, cv);
        Log.d("MyLogs", "row inserted, ID = " + rowID);

        dbHelper.close();

        Toast.makeText(this, "Контакт добавлен в БД", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_client, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
