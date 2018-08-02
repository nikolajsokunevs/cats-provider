package lv.sh.service.cat;

import lv.sh.dto.Cat;
import lv.sh.dto.Cat_;

import java.util.List;

public interface ICatService {
    public Cat addCat(Cat cat);
    public List<Cat_> getAllCats();
}
