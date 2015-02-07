package r.yukhno.klient_records;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class ListOfClients extends ActionBarActivity {

    private ListView listOfClients;
    private int indexFirstName;
    private int indexLastName;
    private Cursor c;

    public void createList() {
        MyDBHelper dbHelper = new MyDBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        List<String> arrayClients = new ArrayList<String>();
        ArrayAdapter<String> aa = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayClients);
        listOfClients.setAdapter(aa);

        c = db.query(MyDBHelper.getDatabaseTable(), null, null, null, null, null, null);

        if (c.moveToFirst())
        {
            indexFirstName = c.getColumnIndex(MyDBHelper.getColumnFirstName());
            indexLastName = c.getColumnIndex(MyDBHelper.getColumnLastName());
            do
            {
                arrayClients.add(c.getString(indexFirstName) + " " + c.getString(indexLastName));
            } while (c.moveToNext());
        }
        c.close();
    }

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_clients);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        listOfClients = (ListView)findViewById(R.id.listOfClients);

        createList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_list_of_clients, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        createList();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                finish();
                return true;

            case R.id.add_item:
                startActivity(new Intent(this, AddClient.class));
                return true;

        }

        return super.onOptionsItemSelected(item);
    }
}
