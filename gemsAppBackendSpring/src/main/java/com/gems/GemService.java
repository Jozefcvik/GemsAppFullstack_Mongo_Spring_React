package com.gems;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GemService {
    @Autowired
    private GemRepository gemRepository;

    public long countGems() {return gemRepository.count();}

    public List<Gem> allGems() {
        return gemRepository.findAll();
    }

    public Optional<Gem> getGemById(String id) {
        return gemRepository.findById(id);
    }

    public Gem createGem(Gem gem) throws IllegalStateException {
        if (countGems()<10) {
            gemRepository.save(gem);
            return gem;
        } else {
            throw new MaximalRecordCountException();
        }
    }


    public Gem updateGem(String id, Gem gem) {

        Optional<Gem> gemData = gemRepository.findById(id);

        if (gemData.isPresent()) {
            Gem _gem = gemData.get();
            _gem.setTitle(gem.getTitle());
            _gem.setOrigin(gem.getOrigin());
            _gem.setReserved(gem.getReserved());
            gemRepository.save(_gem);
            return _gem;
        } else {
            return null;
        }
    }

    public Optional<Gem> deleteGem(String id) {
        Optional<Gem> _gem = gemRepository.findById(id);
        gemRepository.deleteById(id);
        return _gem;
    }

    public String deleteAllGems() {
        gemRepository.deleteAll();
        return ("DB DELETED!!!");
    }

    public List<Gem> findAllReservedGem(boolean reserved) {
        return gemRepository.findByReserved(reserved);
    }

    public List<Gem> findByTitle(String title) {
        return gemRepository.findByTitleContaining(title);
    }
}
