package edu.gitt.is.magiclibrary.controller;

import java.sql.Date;

import edu.gitt.is.magiclibrary.model.entities.Book;
import edu.gitt.is.magiclibrary.view.BookForm;

public class pruebaBinding {
	private static BookForm form;
    private static Book book;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		book =  new Book("Ingeniería del Software","Ian Sommerville", new Date(111,0,1), "miisbn", 500);
		form=new BookForm();
		form.setBook(book,true);
		form.setVisible(true);

	}

}
