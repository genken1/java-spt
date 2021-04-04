package ru.mirea.practice17.services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mirea.practice17.models.Market;
import ru.mirea.practice17.models.Product;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
        session.merge(product);
        session.getTransaction().commit();
    }

    public void remove(int id) {
        session.beginTransaction();
        Product product = session.find(Product.class, id);
        session.remove(product);
        session.getTransaction().commit();
    }

    public List<Product> getSortedProductsByField(String fieldName) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Product> productCriteriaQuery = builder.createQuery(Product.class);
        Root<Product> root = productCriteriaQuery.from(Product.class);
        productCriteriaQuery.select(root).orderBy(builder.asc(root.get(fieldName)));
        Query<Product> query = session.createQuery(productCriteriaQuery);
        return query.getResultList();
    }

    public List<Product> getAll() {
        return session.createQuery("select p from Product p", Product.class).getResultList();
    }

    public Market getMarketByProduct(int productId) {
        return session.createQuery("from Product p where p.id =:id", Product.class)
                .setParameter("id", productId).getSingleResult().getMarket();
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
