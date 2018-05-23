package co.edu.unibague.tercerproyecto.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import co.edu.unibague.tercerproyecto.R;
import co.edu.unibague.tercerproyecto.services.BookService;

public class DeleteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        findViewById(R.id.btnDeleteBook).setOnClickListener(v -> {
            EditText etFindBook = findViewById(R.id.etDeleteBook);
            String id = etFindBook.getText().toString();
            if (id.isEmpty()) {
                etFindBook.setError(getString(R.string.error_find_no_name));
                etFindBook.requestFocus();
            } else {
                BookService.getInstance(this).deletePermanent(id, response -> {
                    Toast.makeText(this, R.string.delete_book_success, Toast.LENGTH_SHORT).show();
                    finish();
                }, error -> Toast.makeText(this, R.string.delte_book_error, Toast.LENGTH_SHORT).show());
            }
        });
    }
}
