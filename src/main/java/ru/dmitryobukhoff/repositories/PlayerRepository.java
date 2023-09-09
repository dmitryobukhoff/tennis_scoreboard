package ru.dmitryobukhoff.repositories;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.dmitryobukhoff.models.Player;
import ru.dmitryobukhoff.utils.HibernateSessionFactoryUtil;

import java.util.Optional;

public class PlayerRepository implements CrudRepository<Player> {
    @Override
    public void create(Player player) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.persist(player);
        session.getTransaction().commit();
    }

    @Override
    public Player read(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Player player = session.get(Player.class, id);
        session.getTransaction().commit();
        return player;
    }

    @Override
    public void update(Player player) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Player playerToBeUpdate = session.get(Player.class, player.getId());
        playerToBeUpdate.setName(player.getName());
        session.getTransaction().commit();
    }

    @Override
    public void delete(Player player) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.remove(player);
        session.getTransaction().commit();
    }

    public Optional<Player> findByName(String name){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        String hql = "FROM Player WHERE name = :name";
        Query<Player> query = session.createQuery(hql, Player.class);
        query.setParameter("name", name);
        Optional<Player> player = Optional.ofNullable(query.getSingleResultOrNull());
        session.getTransaction().commit();
        return player;
    }
}
