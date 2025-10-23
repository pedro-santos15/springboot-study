package com.pedrosantos15.libraryapi.repository;

import com.pedrosantos15.libraryapi.model.Autor;
import com.pedrosantos15.libraryapi.model.GeneroLivro;
import com.pedrosantos15.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LivroRepositoryTest {


    @Autowired
    LivroRepository livroRepository;

    @Autowired
    AutorRepository autorRepository;


    @Test
    public void salvarTest(){
        Livro livro = new Livro();

        livro.setIsbn("98743-8585");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("UFO");
        livro.setDataPublicacao(LocalDate.of(1980, 1, 2));

        Autor autor = autorRepository
                .findById(UUID.fromString("exemploID"))
                .orElse(null);

        livro.setAutor(autor);

        livroRepository.save(livro);
    }

    @Test
    public void salvarCascadeTest(){
        //Explicando um pouquinho esse method
        //Normalmente eu não escrevo via comentários as explicaçoes dos metodos, mas esse acho interessante eu comentar
        //Basicamente esse method por padrão não funcionaria, porém na classe Livro, eu adicionei um valor cascade
        //dentro da anotação @ManyToOne, devido a isso, observe q nesse method eu não tenho q in
        //(Atualmente eu desativei o cascade pois é uma operação perigosa se manipulada errôneamente)


        Livro livro = new Livro();

        livro.setIsbn("98743-8585");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("UFO");
        livro.setDataPublicacao(LocalDate.of(1980, 1, 2));

        Autor autor = new Autor();
        autor.setNome("Pedro");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1950, 1, 29));

        livro.setAutor(autor);

        livroRepository.save(livro);
    }

    @Test
    public void atualizarAutorDoLivroTest(){
        Livro livro = livroRepository.findById(UUID.fromString("ExemploID")).orElse(null);
        Autor autor = autorRepository.findById(UUID.fromString("ExemploID")).orElse(null);


        livro.setAutor(autor);

        livroRepository.save(livro);
    }

    @Test
    public void deletarTest(){
        livroRepository.deleteById(UUID.fromString("ExemploID"));
    }

}