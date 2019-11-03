package app.service;

import app.domain.model.service.HeroServiceModel;

import java.util.List;

public interface HeroService {

    boolean createHero(HeroServiceModel hero);

    List<HeroServiceModel> findAll();

    List<HeroServiceModel> findAllOrderedByLevel();

    HeroServiceModel findById(String heroId);

    void deleteById(String id);
}
