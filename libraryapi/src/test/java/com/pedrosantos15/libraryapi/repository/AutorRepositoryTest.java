package com.pedrosantos15.libraryapi.repository;

import com.pedrosantos15.libraryapi.model.Autor;
import com.pedrosantos15.libraryapi.model.GeneroLivro;
import com.pedrosantos15.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository autorRepository;

    @Autowired
    LivroRepository livroRepository;

    @Test
    public void salvarTest(){
        Autor autor = new Autor();
        autor.setNome("Pedro");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1950, 1, 29));

        Autor autorSalvo = autorRepository.save(autor);
        System.out.println("Autor Salvo: " + autorSalvo);
    }


    @Test
    public void atualizarTest(){

        var id = UUID.fromString("ee220a7f-8f25-4486-981a-307e19cbc1ed");

        Optional<Autor> possivelAutor = autorRepository.findById(id);

        if (possivelAutor.isPresent()){

            Autor autorEncontrado = possivelAutor.get();
            System.out.println("Dados do Autor: ");
            System.out.println(autorEncontrado);

            autorEncontrado.setDataNascimento(LocalDate.of(1960, 1, 30));

            autorRepository.save(autorEncontrado);
        }
    }


    @Test
    public void listarTodosTest(){
        List<Autor> lista = autorRepository.findAll();
        lista.forEach(System.out::println);
    }

    @Test
    public void deletarPorIdTest(){
        var id = UUID.fromString("b31aa983-6abb-43fa-882a-02caa14d9256");

        Optional<Autor> possivelAutor = autorRepository.findById(id);

        if (possivelAutor.isPresent()){

            Autor autorEncontrado = possivelAutor.get();
            autorRepository.deleteById(id);
        }
    }

    @Test
    public void deletarTest(){
        UUID id = UUID.fromString("955f30f8-fb28-4566-b8a2-a596625a38b8");
        Optional<Autor> possivelAutor = autorRepository.findById(id);

        if (possivelAutor.isPresent()){
            Autor autor = possivelAutor.get();

            autorRepository.delete(autor);
        }
    }

    @Test
    public void salvarAutorComLivrosTest(){
        Autor autor = new Autor();
        autor.setNome("Pedro");
        autor.setNacionalidade("Italiano");
        autor.setDataNascimento(LocalDate.of(2003, 9, 15));

        Livro livro = new Livro();
        livro.setIsbn("99999-8585");
        livro.setPreco(BigDecimal.valueOf(250));
        livro.setGenero(GeneroLivro.CIENCIA);
        livro.setTitulo("TesteCiencia");
        livro.setDataPublicacao(LocalDate.of(2000, 1, 8));
        livro.setAutor(autor);

        Livro livro2 = new Livro();
        livro2.setIsbn("99489-1928");
        livro2.setPreco(BigDecimal.valueOf(300));
        livro2.setGenero(GeneroLivro.FICCAO);
        livro2.setTitulo("Harry Potter e a Pedra filosofal");
        livro2.setDataPublicacao(LocalDate.of(2003, 1, 1));
        livro2.setAutor(autor);


        autor.setLivros(new ArrayList<>());
        autor.getLivros().add(livro);
        autor.getLivros().add(livro2);

        autorRepository.save(autor);
        livroRepository.saveAll(autor.getLivros());
    }




}
