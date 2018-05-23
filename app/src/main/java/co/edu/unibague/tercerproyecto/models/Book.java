package co.edu.unibague.tercerproyecto.models;

import java.io.Serializable;


public class Book implements Serializable{
    private String name;
    private String author;
    private int numPages;
    private double price;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getNumPages() {
        return numPages;
    }

    public void setNumPages(int numPages) {
        this.numPages = numPages;
    }

    public String getDescription() {
        return this.getAuthor()+" Precio:$"+this.getPrice()+" Paginas:"+this.getNumPages();
    }
}
