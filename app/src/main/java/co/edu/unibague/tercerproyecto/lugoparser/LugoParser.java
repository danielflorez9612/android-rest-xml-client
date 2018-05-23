package co.edu.unibague.tercerproyecto.lugoparser;

import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import co.edu.unibague.tercerproyecto.models.Book;

public class LugoParser {
    private static final String ns = null;
    private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }
    // For the tags title and summary, extracts their text values.
    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }
    private String readStringTag(XmlPullParser parser, String tag)throws IOException, XmlPullParserException  {
        parser.require(XmlPullParser.START_TAG, ns, tag);
        String stringTag = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, tag);
        return stringTag;
    }
    // Parses the contents of an entry. If it encounters a title, summary, or link tag, hands them off
// to their respective "read" methods for processing. Otherwise, skips the tag.
    private Book readBook(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "Libro");
        Book book = new Book();
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            switch (name) {
                case "titulo":
                    book.setName(readStringTag(parser,"titulo"));
                    break;
                case "noPaginas":
                    book.setNumPages(Integer.parseInt(readStringTag(parser,"noPaginas")));
                    break;
                case "precio":
                    book.setPrice(Double.parseDouble(readStringTag(parser,"precio")));
                    break;
                case "autor":
                    book.setAuthor(readStringTag(parser,"autor"));
                    break;
                default:
                    skip(parser);
                    break;
            }
        }
        return book;
    }

    private List<Book> readBooks(XmlPullParser parser) throws XmlPullParserException, IOException {
        List<Book> books = new ArrayList<>();
        parser.require(XmlPullParser.START_TAG, ns, "libroes");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            // Starts by looking for the entry tag
            if (name.equals("Libro")) {
                books.add(readBook(parser));
            } else {
                skip(parser);
            }
        }
        return books;
    }
    public Book parseBook(String response) throws IOException, XmlPullParserException {
        try (InputStream in = new ByteArrayInputStream(response.getBytes(StandardCharsets.UTF_8))) {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            return readBook(parser);
        }
    }
    public List<Book> parseList(String response) throws XmlPullParserException, IOException {
        try (InputStream in = new ByteArrayInputStream(response.getBytes(StandardCharsets.UTF_8))) {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            return readBooks(parser);
        }
    }
}
