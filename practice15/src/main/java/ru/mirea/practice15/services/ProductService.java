package ru.mirea.practice15.services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mirea.practice15.models.Product;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

@Service
public class ProductService {
    private SessionFactory sessionFactory;
    private Session session;

    @PostConstruct
    void init() {
        session = sessionFactory.openSession();
    }

    public void save(Product product) {
        session.beginTransaction();
        session.save(product);
        session.getTransaction().commit();
    }

    public void remove(int id) {
        session.beginTransaction();
        Product product = session.find(Product.class, id);
        session.remove(product);
        session.getTransaction().commit();
    }

    public List<Product> getAll() {
        return session.createQuery("select p from Product p", Product.class).getResultList();
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
