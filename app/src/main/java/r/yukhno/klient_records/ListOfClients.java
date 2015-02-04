package r.yukhno.klient_records;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

public class ListOfClients extends ActionBarActivity {

    private ListView listOfClients;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_clients);
        listOfClients = (ListView)findViewById(R.id.listOfClients);

        MyDBHelper dbHelper = new MyDBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        List<String> arrayClients = new ArrayList<String>();
        ArrayAdapter<String> aa = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayClients);
        listOfClients.setAdapter(aa);

        Cursor c = db.query(MyDBHelper.getDatabaseTable(), null, null, null, null, null, null);

        if (c.moveToFirst())
        {
            int indexFirstName = c.getColumnIndex(MyDBHelper.getColumnFirstName());
            do
            {
                arrayClients.add(c.getString(indexFirstName));
            } while (c.moveToNext());
        }
        c.close();
    }
}
