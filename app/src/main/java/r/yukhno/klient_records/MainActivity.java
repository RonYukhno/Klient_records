package r.yukhno.klient_records;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onAddClient(View v) {
        Intent intent = new Intent(this, AddClient.class);
        startActivity(intent);
    }

    public void onSeeClients(View v) {
        startActivity(new Intent(this, ListOfClients.class));
    }
}
