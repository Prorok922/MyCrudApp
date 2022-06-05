package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {
    private EntityManager entityManager;

    @Autowired
    public UserDaoImp(EntityManager sessionFactory) {
        this.entityManager = sessionFactory;
    }

    @Transactional
    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Transactional
    @Override
    public User getUserById(long id) {
        return entityManager.find(User.class, id);
    }
    @Transactional
    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }
    @Transactional
    @Override
    public void removeUser(long id) {
        entityManager.remove(getUserById(id));
    }
    @Transactional
    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }
}
