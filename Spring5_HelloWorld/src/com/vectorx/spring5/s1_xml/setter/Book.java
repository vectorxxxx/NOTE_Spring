package com.vectorx.spring5.s1_xml.setter;

public class Book {
    private String bname;
    private String bauthor;

    public void setBname(String bname) {
        this.bname = bname;
    }
    public void setBauthor(String bauthor) {
        this.bauthor = bauthor;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bname='" + bname + '\'' +
                ", bauthor='" + bauthor + '\'' +
                '}';
    }
}
