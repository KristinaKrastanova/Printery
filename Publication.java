package com.company;

import com.company.Enums.PagesSize;

public class Publication {
    private String name;
    private int numberOfPages;
    private PagesSize pagesSize;

    public Publication(String name, int numberOfPages, PagesSize pagesSize) {
        this.name = name;

        if(numberOfPages > 0) {
            this.numberOfPages = numberOfPages;
        }else{
            this.numberOfPages = 1;
        }

        this.pagesSize = pagesSize;
    }

    public String getName() {
        return name;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public PagesSize getPagesSize() {
        return pagesSize;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumberOfPages(int numberOfPages) {
        if(numberOfPages > 0) {
            this.numberOfPages = numberOfPages;
        }else{
            this.numberOfPages = 1;
        }
    }

    public void setPagesSize(PagesSize pagesSize) {
        this.pagesSize = pagesSize;
    }

    @Override
    public String toString() {
        return "Publication{" +
                "name='" + name + '\'' +
                ", numberOfPages=" + numberOfPages +
                ", pagesSize=" + pagesSize +
                '}';
    }
}
