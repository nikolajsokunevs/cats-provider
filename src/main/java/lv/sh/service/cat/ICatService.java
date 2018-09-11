package lv.sh.service.cat;

import lv.sh.dto.Cat;

import java.util.List;

public interface ICatService {
    Cat addCat(Cat cat);

    List<Cat> getAllCats();

    Cat getCatById(String id);

    void deleteCat(String id, boolean useId);

    void updateCat(String id, Cat cat);
}
