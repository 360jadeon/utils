package com.jadeon.iterator;

/**
 * @author WooKong
 */
public class BookShelfIterator implements Iterator {
    private int index;
    private BookShelf bookShelf;
    public BookShelfIterator(BookShelf bookShelf){
        this.bookShelf = bookShelf;
        this.index = 0;
    }
    @Override
    public boolean hasNext() {
        return index < bookShelf.getLength();
    }

    @Override
    public Object next() {
        Book book = bookShelf.getBookAt(index);
        index++;
        return book;
    }

}
