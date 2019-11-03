package app.repository;

import app.domain.entity.Hero;

import java.util.List;

public interface HeroRepository extends GenericRepository<Hero,String> {

    Hero findByName(String name);

    List<Hero> findAllOrderByLevelDescending();

    void delete(String id);
}
