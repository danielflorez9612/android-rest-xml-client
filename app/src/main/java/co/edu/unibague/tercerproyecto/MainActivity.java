package co.edu.unibague.tercerproyecto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import co.edu.unibague.tercerproyecto.activities.SaveActivity;
import co.edu.unibague.tercerproyecto.activities.DeleteActivity;
import co.edu.unibague.tercerproyecto.activities.DeleteAllActivity;
import co.edu.unibague.tercerproyecto.activities.FindActivity;
import co.edu.unibague.tercerproyecto.activities.ListActivity;
import co.edu.unibague.tercerproyecto.activities.SearchActivity;
import co.edu.unibague.tercerproyecto.activities.UpdateActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btnList).setOnClickListener(v -> startActivity(new Intent(this, ListActivity.class)));
        findViewById(R.id.btnDeleteAllActivity).setOnClickListener(v -> startActivity(new Intent(this, DeleteAllActivity.class)));
        findViewById(R.id.btnFindActivity).setOnClickListener(v -> startActivity(new Intent(this, FindActivity.class)));
        findViewById(R.id.btnDeleteActivity).setOnClickListener(v -> startActivity(new Intent(this, DeleteActivity.class)));
        findViewById(R.id.btnUpdateActivity).setOnClickListener(v -> startActivity(new Intent(this, UpdateActivity.class)));
        findViewById(R.id.btnCreateActivity).setOnClickListener(v -> startActivity(new Intent(this, SaveActivity.class)));
        findViewById(R.id.btnSearch).setOnClickListener(v -> startActivity(new Intent(this, SearchActivity.class)));
    }
}
