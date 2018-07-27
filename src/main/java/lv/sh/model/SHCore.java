package lv.sh.model;

import lv.sh.dto.Cat;
import lv.sh.repository.CatRepository;

import java.util.List;

public class SHCore {

    private static List<Cat> cats=null;

    public static List<Cat> getCats(){
        if (cats==null) cats= CatRepository.getInstance().getAllCats();
        return cats;
    }

}
