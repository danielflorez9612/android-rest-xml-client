package co.edu.unibague.tercerproyecto.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

import co.edu.unibague.tercerproyecto.R;
import co.edu.unibague.tercerproyecto.lugoparser.LugoParser;
import co.edu.unibague.tercerproyecto.models.Book;
import co.edu.unibague.tercerproyecto.services.BookService;

public class FindActivity extends AppCompatActivity {
    private TextView tvBookName ;
    private TextView tvBookPrice;
    private TextView tvBookAuthor ;
    private TextView tvBookPageNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);
        tvBookName = findViewById(R.id.tvBookName);
        tvBookPrice = findViewById(R.id.tvBookPrice);
        tvBookAuthor = findViewById(R.id.tvBookDescription);
        tvBookPageNumber = findViewById(R.id.tvBookNumPages);
        findViewById(R.id.btnFind).setOnClickListener(v -> {
            EditText etFindBook = findViewById(R.id.etFindBook);
            String id = etFindBook.getText().toString();
            if (id.isEmpty()) {
               etFindBook.setError(getString(R.string.error_find_no_name));
               etFindBook.requestFocus();
            } else {
                BookService.getInstance(this).find(id, response -> {
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
    protected void refreshBook(Book book) {
        if (book!=null) {
            tvBookPageNumber.setText(String.valueOf(book.getNumPages()));
            tvBookPrice.setText(String.valueOf(book.getPrice()));
            tvBookAuthor.setText(String.valueOf(book.getAuthor()));
            tvBookPageNumber.setText(String.valueOf(book.getNumPages()));
            tvBookName.setText(String.valueOf(book.getName()));
        } else {
            tvBookPageNumber.setText("");
            tvBookPrice.setText("");
            tvBookAuthor.setText("");
            tvBookPageNumber.setText("");
            tvBookName.setText("");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.refreshBook(null);
    }
}
