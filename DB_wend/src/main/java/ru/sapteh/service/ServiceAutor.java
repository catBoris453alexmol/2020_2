package ru.sapteh.service;

import com.sun.istack.NotNull;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.sapteh.dao.Dao;
import ru.sapteh.model.Autor;
import ru.sapteh.model.Genre;

import javax.persistence.Query;
import java.util.List;

public class ServiceAutor implements Dao<Autor, Long>{

    private final SessionFactory factory;

     public ServiceAutor(SessionFactory factory){
        this.factory = factory;
    }

        @Override
        public void create(@NotNull final Autor autor) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.save(autor);
            session.getTransaction().commit();
        }
    }

        @Override
        public Autor read(@NotNull final Long id) {
        try(Session session = factory.openSession()) {
            return session.get(Autor.class, id);
        }
    }

        @Override
        public List<Autor> readByAll() {
        try(Session session = factory.openSession()){
            String sql = "SELECT * FROM autor";
            Query query = session.createNativeQuery(sql).addEntity(Autor.class);
            return query.getResultList();
        }
    }

        @Override
        public void update(@NotNull final Autor autor) {
        try(Session session = factory.openSession()) {
            session.beginTransaction();
            session.update(autor);
            session.getTransaction().commit();
        }
    }

        @Override
        public void delete(@NotNull final Autor autor) {
        try(Session session = factory.openSession()) {
            session.beginTransaction();
            session.delete(autor);
            session.getTransaction().commit();
        }
    }
}
