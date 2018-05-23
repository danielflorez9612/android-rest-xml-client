package co.edu.unibague.tercerproyecto.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.List;

import co.edu.unibague.tercerproyecto.R;
import co.edu.unibague.tercerproyecto.adapters.BookArrayAdapter;
import co.edu.unibague.tercerproyecto.lugoparser.LugoParser;
import co.edu.unibague.tercerproyecto.models.Book;
import co.edu.unibague.tercerproyecto.services.BookService;

public class ListActivity extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        listView =findViewById(R.id.listView);
    }
    public void refreshList(){
        BookService.getInstance(this).index(response -> {
            try {
                List<Book> books = new LugoParser().parseList(response);
                if(books.isEmpty()){
                    Toast.makeText(this, R.string.book_list_empty, Toast.LENGTH_LONG).show();
                }
                BookArrayAdapter adapter = new BookArrayAdapter(this, books.toArray(new Book[books.size()]));
                listView.setAdapter(adapter);
            } catch (XmlPullParserException | IOException e) {
                e.printStackTrace();
            }
        }, error -> Toast.makeText(this, R.string.error_load_books, Toast.LENGTH_SHORT).show());
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshList();
    }

}
