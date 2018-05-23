package co.edu.unibague.tercerproyecto.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import co.edu.unibague.tercerproyecto.R;
import co.edu.unibague.tercerproyecto.models.Book;


/**
 * Created by agile-monkey-g3 on 3/14/18.
 */

public class BookArrayAdapter extends ArrayAdapter<Book> {
    private final Context context;
    private final Book[] values;

    public BookArrayAdapter(@NonNull Context context, Book[] values) {
        super(context,-1, values);
        this.context = context;
        this.values = values;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        Book book = values[position];
        LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View bookListItemView = inflater.inflate(R.layout.book_list_item,parent,false);
        bookListItemView.setOnClickListener(v -> {
            //TODO
            Log.d("Click","on object");
        });
        TextView tvName = bookListItemView.findViewById(R.id.tvBookName);
        TextView tvBookDescription = bookListItemView.findViewById(R.id.tvBookDescription);
        ImageView imageView = bookListItemView.findViewById(R.id.bookIcon);

        tvName.setText(book.getName());
        tvBookDescription.setText(book.getDescription());
        return bookListItemView;
    }
}
