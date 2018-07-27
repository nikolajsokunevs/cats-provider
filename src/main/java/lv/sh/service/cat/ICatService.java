package lv.sh.service.cat;

import lv.sh.dto.Cat;

import java.util.List;

public interface ICatService {
    public Cat addCat(Cat cat);
    public List<Cat> getAllCats();
}
