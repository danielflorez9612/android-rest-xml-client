package co.edu.unibague.tercerproyecto.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import co.edu.unibague.tercerproyecto.R;
import co.edu.unibague.tercerproyecto.models.Book;
import co.edu.unibague.tercerproyecto.services.BookService;

public class SaveActivity extends AppCompatActivity {
    EditText etPrice, etName, etAuthor, etPageNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        etPrice  = findViewById(R.id.etPrice);
        etName  = findViewById(R.id.etBookName);
        etAuthor  = findViewById(R.id.etAuthorName);
        etPageNumber = findViewById(R.id.etPageNumber);
        findViewById(R.id.btnCreateBook).setOnClickListener(v -> this.createBook());
    }

    private void createBook() {
        try {
            String name = etName.getText().toString();
            check(name, etName, getString(R.string.book_name_missing));
            String author = etAuthor.getText().toString();
            check(author, etAuthor, getString(R.string.book_author_missing));
            String numPages = etPageNumber.getText().toString();
            check(numPages, etPageNumber, getString(R.string.book_page_number_mising));
            String price = etPrice.getText().toString();
            check(price, etPrice, getString(R.string.book_price_missing));
            Book book = new Book();
            book.setName(name);
            book.setAuthor(author);
            book.setNumPages(Integer.parseInt(numPages));
            book.setPrice(Double.parseDouble(price));
            BookService.getInstance(this).create(book,response -> {
                Toast.makeText(this, R.string.save_book_success, Toast.LENGTH_SHORT).show();
                finish();
            },error -> Toast.makeText(this, R.string.save_book_error, Toast.LENGTH_SHORT).show());
        } catch (Exception e) {
            // end
        }
    }
    private void check(String value, EditText view, String error) throws Exception {
        if(value.isEmpty()) {
            view.setError(error);
            view.requestFocus();
            throw new Exception();
        }
    }
}
