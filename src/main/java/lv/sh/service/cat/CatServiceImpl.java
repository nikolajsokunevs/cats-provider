package lv.sh.service.cat;

import lv.sh.dto.Cat;
import lv.sh.dto.Cat_;
import lv.sh.repository.CatRepository;

import java.util.List;

public class CatServiceImpl implements ICatService {

    private CatRepository catRepository = CatRepository.getInstance();

    @Override
    public Cat addCat(Cat cat) {
        catRepository.insertCat(cat);
        return null;
    }

    @Override
    public List<Cat_> getAllCats() {
        return catRepository.getAllCats();
    }
}
