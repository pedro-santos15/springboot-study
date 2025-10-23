package com.pedrosantos15.libraryapi.repository;

import com.pedrosantos15.libraryapi.model.Autor;
import com.pedrosantos15.libraryapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID> {


    //Query Method
    //select * from livro where id_autor = id
    List<Livro> findByAutor(Autor autor);

    //Essa porra é bizarra, o próprio JPA identifica que eu estou querendo buscar dentre todos os livros um id de autor em comum
    //graças a especificação que a gnt faz no ManyToOne
}
