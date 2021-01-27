package ru.sapteh;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.sapteh.dao.Dao;
import ru.sapteh.model.Autor;
import ru.sapteh.model.Genre;
import ru.sapteh.service.ServiceAutor;
import ru.sapteh.service.ServiceGenre;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Dao<Autor, Long> serviceAutor = new ServiceAutor(factory);
        Dao<Genre, Long> serviceGenre = new ServiceGenre(factory);

        Autor autor = new Autor();
        autor.setFirstName("Цугуми");
        autor.setLastName("Оба");
        autor.setAge(45);

        Genre genre = serviceGenre.read((long)2);


        Set<Autor> autors = new HashSet<>();
        autors.add(autor);
        genre.setAutors(autors);
        Set<Genre> genres = new HashSet<>();
        genres.add(genre);
        autor.setGenres(genres);

        serviceAutor.create((Autor) autor);
        //serviceGenre.create((Genre) genre);

        factory.close();
    }

}
