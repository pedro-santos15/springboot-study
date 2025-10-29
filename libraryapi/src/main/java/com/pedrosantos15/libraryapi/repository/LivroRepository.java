package com.pedrosantos15.libraryapi.repository;

import com.pedrosantos15.libraryapi.model.Autor;
import com.pedrosantos15.libraryapi.model.GeneroLivro;
import com.pedrosantos15.libraryapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * @see LivroRepositoryTest
 */

public interface LivroRepository extends JpaRepository<Livro, UUID> {


    //Query Method
    //select * from livro where id_autor = id
    List<Livro> findByAutor(Autor autor);
    //Essa porra é bizarra, o próprio JPA identifica que eu estou querendo buscar dentre todos os livros um id de autor em comum

    //graças a especificação que a gnt faz no ManyToOne
    List<Livro> findByTitulo(String titulo);

    List<Livro> findByIsbn(String isbn);

    //select * from livro where titulo = ? and preco = ?
    List<Livro> findByTituloAndPreco(String titulo, BigDecimal preco);

    //select * from livro where titulo = ? or isbn = ?
    List<Livro> findByTituloOrIsbn(String titulo, String isbn);


    //JPQL -> referencia as entidades e as propriedades
    @Query(" select l from Livro as l order by l.titulo, l.preco ")
    List<Livro> listarTodosPorOrdemDeTituloAndPreco();


    @Query("select a from Livro l join l.autor a ")
    List<Livro> listarAutoresDosLivros();

    @Query("select distinct l.titulo from Livro l ")
    List<String> listarNomeDiferentesLivros();

    @Query("""
            select l.genero
            from Livro l
            join l.autor a
            where a.nacionalidade = 'Brasileira'
            order by l.genero
            """)
    List<String> listarGenerosAutoresBrasileiros();

    //Mostrando como colocar um parâmetro dentro do method que está utilizando @query
    @Query("select l from Livro l where l.genero = :genero order by :paramOrdenacao ")
    List<Livro> findByGenero(@Param("genero")GeneroLivro generoLivro,
                             @Param("paramOrdenacao") String nomePropriedade);
    //Este method acima, utiliza o @Param para substituir o valor "genero" na query pelo valor passado como parâmetro

    @Query("select l from Livro l where l.genero = ?1 order by ?2 ")
    List<Livro> findByGeneroPositionalParameters(@Param("genero")GeneroLivro generoLivro,
                             @Param("paramOrdenacao") String nomePropriedade);

    @Modifying
    @Transactional
    @Query(" delete from Livro where genero = ?1 ")
    void deleteByGenero(GeneroLivro genero);
}
