package app.repository;

import app.domain.entity.Hero;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class HeroRepositoryImpl implements HeroRepository {

    private EntityManager entityManager;

    @Inject
    public HeroRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Hero save(Hero entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
        return entity;
    }

    @Override
    public Hero findById(String id) {
        entityManager.getTransaction().begin();

        Hero hero;
        try {
            hero = entityManager
                    .createQuery("SELECT h FROM Hero h WHERE h.id =:id", Hero.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception ex) {
            hero = null;
        }

        entityManager.getTransaction().commit();
        return hero;
    }

    @Override
    public Hero findByName(String name) {
        entityManager.getTransaction().begin();
        Hero hero;

        try {
            hero = entityManager
                    .createQuery("SELECT h FROM Hero h WHERE h.name =:name", Hero.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (Exception ex) {
            hero = null;
        }

        entityManager.getTransaction().commit();
        return hero;
    }

    @Override
    public List<Hero> findAllOrderByLevelDescending() {
        entityManager.getTransaction().begin();

        List<Hero> heroes = entityManager
                .createQuery("SELECT h FROM Hero h ORDER BY h.level DESC", Hero.class)
                .getResultList();

        entityManager.getTransaction().commit();
        return heroes;
    }

    @Override
    public void delete(String id) {

        entityManager.getTransaction().begin();

        entityManager
                .createQuery("DELETE FROM Hero h WHERE h.id =:id")
                .setParameter("id", id)
                .executeUpdate();

        entityManager.getTransaction().commit();
    }

    @Override
    public List<Hero> findAll() {
        entityManager.getTransaction().begin();

        List<Hero> heroes = entityManager
                .createQuery("SELECT h FROM Hero h", Hero.class)
                .getResultList();

        entityManager.getTransaction().commit();
        return heroes;
    }

    @Override
    public Long count() {
        entityManager.getTransaction().begin();

        Long count = entityManager
                .createQuery("SELECT count(h) FROM Hero h", Long.class)
                .getSingleResult();

        entityManager.getTransaction().commit();
        return count;
    }
}
