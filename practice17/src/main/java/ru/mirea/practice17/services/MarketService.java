package ru.mirea.practice17.services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mirea.practice17.models.Market;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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

    public List<Market> getSortedMarketsByField(String fieldName) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Market> marketCriteriaQuery = builder.createQuery(Market.class);
        Root<Market> root = marketCriteriaQuery.from(Market.class);
        marketCriteriaQuery.select(root).orderBy(builder.asc(root.get(fieldName)));
        Query<Market> query = session.createQuery(marketCriteriaQuery);
        return query.getResultList();
    }

    public List<Market> getAll() {
        return session.createQuery("select m from Market m", Market.class).getResultList();
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
