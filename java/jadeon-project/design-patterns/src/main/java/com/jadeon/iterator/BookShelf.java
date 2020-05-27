package com.jadeon.iterator;

import java.util.ArrayList;

/**
 * @author WooKong
 * @deprecated 书架：分别使用数组和ArrayList存放书的大小
 */
public class BookShelf implements Aggregate {
//    使用数组时
//    private Book[] books;
//    private int last = 0;
//    public BookShelf(int maxsize){
//        books = new Book [maxsize];
//    }
//    public Book getBookAt(int index){
//        return this.books[index];
//    }
//    public void appendBook(Book book){
//        this.books[last] = book;
//        this.last++;
//    }
//    public int getLength(){
//        return this.last;
//    }

//    使用ArrayList时
    private ArrayList<Book> books;
    private int index;
    public BookShelf(int initialize) {
        this.books = new ArrayList<Book>(initialize);
    }

    public Book getBookAt(int index){
        return books.get(index);
    }

    public int getLength(){
        return books.size();
    }

    public void appendBook(Book book) {
        books.add(book);
    }

    @Override
    public Iterator iterator() {
        return new BookShelfIterator(this);
    }
}
