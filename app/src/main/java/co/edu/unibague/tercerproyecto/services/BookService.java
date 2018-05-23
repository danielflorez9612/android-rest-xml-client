package co.edu.unibague.tercerproyecto.services;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.List;

import co.edu.unibague.tercerproyecto.lugoparser.LugoParser;
import co.edu.unibague.tercerproyecto.models.Book;

public class BookService {
    private String url = "http://179.0.10.16:7101/AplicacionLibreria-ProyectoLibreria-context-root/resources/servicios";
    private RequestQueue queue;
    private static BookService instance;
    public static BookService getInstance(Context context) {
        if(instance == null) {
            instance = new BookService();
        }
        instance.startQueue(context);
        return instance;
    }
    private void startQueue(Context context) {
        queue = Volley.newRequestQueue(context);
    }
    private BookService() {
    }

    public void create(Book book, Response.Listener<String> responseCallback, Response.ErrorListener errorCallBack) {
        String createUrl = url+"/addLibro?titulo="+book.getName()+"&noPaginas="+book.getNumPages()+"&precio="+book.getPrice()+"&autor="+book.getAuthor();
        queue.add(new StringRequest(Request.Method.POST, createUrl ,responseCallback, errorCallBack));
    }
    public void find(String title, Response.Listener<String> responseCallback, Response.ErrorListener errorCallBack) {
        String deleteUrl = url+"/buscarLibro?titulo="+title;
        queue.add(new StringRequest(Request.Method.GET,deleteUrl , responseCallback, errorCallBack));
    }
    public void index(Response.Listener<String> responseCallback,Response.ErrorListener errorCallBack) {
        String listUrl = url+"/getAllLibros";
        queue.add(new StringRequest(Request.Method.GET, listUrl, responseCallback, errorCallBack));
    }
    public void deletePermanent(String title,  Response.Listener<String> responseCallback, Response.ErrorListener errorCallBack) {
        String deleteUrl = url+"/deleteLibro?titulo="+title;
        queue.add(new StringRequest(Request.Method.POST,deleteUrl , responseCallback, errorCallBack));
    }
    public void deleteAll(Response.Listener<String> responseCallback,Response.ErrorListener errorCallBack) {
        String deleteUrl = url+"/deleteAllLibros";
        queue.add(new StringRequest(Request.Method.POST,deleteUrl , responseCallback, errorCallBack));
    }
    public static void calc() {
        //TODO
    }
    public void search(String autor, Response.Listener<String> responseCallback, Response.ErrorListener errorCallBack) {
        String searchUrl = url+"/buscarPorAutor?autor="+autor;
        queue.add(new StringRequest(Request.Method.GET, searchUrl, responseCallback, errorCallBack));
    }

    public void update(String title, double newPrice, Response.Listener<String> responseCallback, Response.ErrorListener errorCallBack) {
        String updateUrl = url+"/updateLibro?titulo="+title+"&nvoPrecio="+newPrice;
        Log.d("url",updateUrl);
        queue.add(new StringRequest(Request.Method.POST, updateUrl , responseCallback, errorCallBack));
    }

}
