package co.edu.unibague.tercerproyecto.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import co.edu.unibague.tercerproyecto.R;
import co.edu.unibague.tercerproyecto.services.BookService;

public class UpdateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        findViewById(R.id.btnUpdateBook).setOnClickListener(v -> {
            EditText etFindBook = findViewById(R.id.etUpdateBook);
            EditText etNewPrice = findViewById(R.id.etNewPrice);
            try {
                String id = etFindBook.getText().toString();
                check(id, etFindBook, getString(R.string.book_name_missing));
                String newPrice = etNewPrice.getText().toString();
                check(newPrice, etNewPrice, getString(R.string.book_new_price_missing));
                BookService.getInstance(this).update(id, Double.parseDouble(newPrice),
                        response -> {
                            Toast.makeText(this, R.string.update_succesfull, Toast.LENGTH_SHORT).show();
                            finish();
                        },
                        error -> Toast.makeText(this, R.string.update_error, Toast.LENGTH_SHORT).show());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    private void check(String value, EditText view, String error) throws Exception {
        if(value.isEmpty()) {
            view.setError(error);
            view.requestFocus();
            throw new Exception();
        }
    }
}
