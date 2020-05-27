package com.jadeon.iterator;

/**
 * @author WooKong
 */
public class Main {
    public static void main(String[] args) {
//        使用数组时
          BookShelf bookShelf = new BookShelf(4);

//        bookShelf.appendBook(new Book("《西游记》"));
//        bookShelf.appendBook(new Book("《水浒传》"));
//        bookShelf.appendBook(new Book("《红楼梦》"));
//        bookShelf.appendBook(new Book("《三国演义》"));
//        bookShelf.appendBook(new Book("《Java从入门到放弃》"));

//        使用ArrayList时
        bookShelf.appendBook(new Book("《西游记》"));
        bookShelf.appendBook(new Book("《水浒传》"));
        bookShelf.appendBook(new Book("《红楼梦》"));
        bookShelf.appendBook(new Book("《三国演义》"));
        bookShelf.appendBook(new Book("《Java从入门到放弃》"));

        Iterator it = bookShelf.iterator();
        while (it.hasNext()) {
            Book book = (Book)it.next();
            System.out.format("%s %s%n", "🖤：",book.getName());
        }
    }
}
