package ru.mirea.practice15.services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mirea.practice15.models.Market;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

@Service
public class MarketService {
    private SessionFactory sessionFactory;
    private Session session;

    @PostConstruct
    void init() {
        session = sessionFactory.openSession();
    }

    public void save(Market market) {
        session.beginTransaction();
        session.save(market);
        session.getTransaction().commit();
    }

    public void remove(int id) {
        session.beginTransaction();
        Market market = session.find(Market.class, id);
        session.remove(market);
        session.getTransaction().commit();
    }

    public List<Market> getAll() {
        List<Market> list = session.createQuery("select m from Market m", Market.class).getResultList();
        return list;
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @PreDestroy
    void destroy() {
        sessionFactory.close();
    }
}
