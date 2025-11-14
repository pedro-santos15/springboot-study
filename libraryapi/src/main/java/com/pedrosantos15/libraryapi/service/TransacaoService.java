package com.pedrosantos15.libraryapi.service;

import com.pedrosantos15.libraryapi.model.Autor;
import com.pedrosantos15.libraryapi.model.GeneroLivro;
import com.pedrosantos15.libraryapi.model.Livro;
import com.pedrosantos15.libraryapi.repository.AutorRepository;
import com.pedrosantos15.libraryapi.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

@Service
public class TransacaoService {

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Transactional
    public void atualizacaoSemAtualizar(){
        var livro = livroRepository
                .findById(UUID.fromString("ExemploID"))
                .orElse(null);

        livro.setDataPublicacao(LocalDate.of(2024, 6, 1));

    }

    @Transactional
    public void executar(){

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
