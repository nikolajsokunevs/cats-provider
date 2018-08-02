package lv.sh.dto;

import java.util.List;

public class CatGetAll {

    public CatGetAll(List<Cat_> cats){
        this.cats=cats;
    }

    private int totalCatCount;
    private List<Cat_> cats;

    public int getTotalCatCount() {
        if (cats!=null){
            return cats.size();
        }
        return 0;
    }

    public List<Cat_> getCats() {
        return cats;
    }

    public void setCats(List<Cat_> cats) {
        this.cats = cats;
    }

    }
