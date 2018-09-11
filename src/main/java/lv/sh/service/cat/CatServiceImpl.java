package lv.sh.service.cat;

import lv.sh.dto.Cat;
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
    public List<Cat> getAllCats() {
        return catRepository.getAllCats();
    }

    @Override
    public Cat getCatById(String id) {
        return catRepository.getCatById(id);
    }

    @Override
    public void deleteCat(String id, boolean useId) {
        if (useId){
            catRepository.deleteCat(id);
        }else {
            catRepository.deleteCat();
        }
    }

    @Override
    public void updateCat(String id, Cat cat) {
        catRepository.updateCat(id, cat);
    }
}
