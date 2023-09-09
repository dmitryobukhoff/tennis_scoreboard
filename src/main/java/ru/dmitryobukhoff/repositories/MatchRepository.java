package ru.dmitryobukhoff.repositories;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.dmitryobukhoff.models.Match;
import ru.dmitryobukhoff.utils.HibernateSessionFactoryUtil;

import java.util.ArrayList;
import java.util.List;

public class MatchRepository implements CrudRepository<Match> {
    @Override
    public void create(Match match) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.persist(match);
        session.getTransaction().commit();
    }

    @Override
    public Match read(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Match match = session.get(Match.class, id);
        session.getTransaction().commit();
        return match;
    }

    @Override
    public void update(Match match) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Match matchToBeUpdate = session.get(Match.class, match.getId());
        matchToBeUpdate.setWinner(match.getWinner());
        session.getTransaction().commit();
    }

    @Override
    public void delete(Match match) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.remove(match);
        session.getTransaction().commit();
    }

    public List<Match> findMatches(int page){
        List<Match> matches = new ArrayList<>();
        String hql = "FROM Match";
        Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query<Match> query = session.createQuery(hql, Match.class);
        query.setFirstResult((page - 1) * 5);
        query.setMaxResults(5);
        matches = query.getResultList();
        session.getTransaction().commit();
        return matches;
    }

    public List<Match> findMatches(int page, String name){
        List<Match> matches = new ArrayList<>();
        String hql = "SELECT m FROM Match m " +
                "INNER JOIN m.player1 p1 " +
                "INNER JOIN m.player2 p2 " +
                "INNER JOIN m.winner w " +
                "WHERE p1.name = :playerName OR p2.name = :playerName";
        Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query<Match> query = session.createQuery(hql, Match.class);
        query.setParameter("playerName", name);
        query.setFirstResult((page - 1) * 5);
        query.setMaxResults(5);
        matches = query.getResultList();
        session.getTransaction().commit();
        return matches;
    }

}
