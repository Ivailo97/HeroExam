package app.service;

import app.domain.entity.Hero;
import app.domain.model.service.HeroServiceModel;
import app.repository.HeroRepository;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class HeroServiceImpl implements HeroService {

    private final HeroRepository heroRepository;

    private final ModelMapper modelMapper;

    @Inject
    public HeroServiceImpl(HeroRepository heroRepository, ModelMapper modelMapper) {
        this.heroRepository = heroRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public boolean createHero(HeroServiceModel hero) {

        if (heroRepository.findByName(hero.getName()) != null) {
            return false;
        }

        Hero entity = modelMapper.map(hero, Hero.class);

        heroRepository.save(entity);

        return true;
    }

    @Override
    public List<HeroServiceModel> findAll() {
        return heroRepository.findAll().stream()
                .map(h -> modelMapper.map(h, HeroServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<HeroServiceModel> findAllOrderedByLevel() {

        return heroRepository.findAllOrderByLevelDescending().stream()
                .map(h -> modelMapper.map(h, HeroServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public HeroServiceModel findById(String heroId) {

        Hero hero = heroRepository.findById(heroId);

        if (hero == null) {
            return null;
        }

        return modelMapper.map(hero, HeroServiceModel.class);
    }

    @Override
    public void deleteById(String id) {
        heroRepository.delete(id);
    }
}
