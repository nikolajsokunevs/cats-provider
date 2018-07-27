package lv.sh;

public class Book {

    public String ISBN;

    public String getISBN(){
        return ISBN;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Book)
            return ISBN.equals(((Book)obj).getISBN());
        else
            return false;
    }
}