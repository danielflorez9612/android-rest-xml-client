package co.edu.unibague.tercerproyecto.activities;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

import co.edu.unibague.tercerproyecto.R;
import co.edu.unibague.tercerproyecto.lugoparser.LugoParser;
import co.edu.unibague.tercerproyecto.services.BookService;

public class SearchActivity extends FindActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EditText etFindBook = findViewById(R.id.etFindBook);
        etFindBook.setHint(R.string.book_author);
        findViewById(R.id.btnFind).setOnClickListener(v -> {
            String autor = etFindBook.getText().toString();
            if (autor.isEmpty()) {
                etFindBook.setError(getString(R.string.error_find_no_name));
                etFindBook.requestFocus();
            } else {
                BookService.getInstance(this).search(autor, response -> {
                    try {
                        refreshBook(new LugoParser().parseBook(response));
                    } catch (IOException | XmlPullParserException e) {
                        refreshBook(null);
                        Toast.makeText(this, R.string.book_not_found, Toast.LENGTH_SHORT).show();
                    }
                }, error -> Toast.makeText(this, R.string.book_search_error, Toast.LENGTH_SHORT).show());
            }
        });
    }
}
