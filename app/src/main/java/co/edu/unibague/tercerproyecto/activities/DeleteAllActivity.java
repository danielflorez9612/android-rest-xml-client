package co.edu.unibague.tercerproyecto.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import co.edu.unibague.tercerproyecto.R;
import co.edu.unibague.tercerproyecto.services.BookService;

public class DeleteAllActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_all);
        findViewById(R.id.btnDeleteAll).setOnClickListener(v -> BookService.getInstance(this).deleteAll(response -> {
            Toast.makeText(this, R.string.delete_all_success,Toast.LENGTH_LONG).show();
            finish();
        }, error -> Toast.makeText(this, R.string.delete_all_fail,Toast.LENGTH_SHORT).show()));
    }
}
