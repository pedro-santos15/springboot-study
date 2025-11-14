package com.pedrosantos15.libraryapi.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class TrasacoesTest {

    @Autowired
    AutorRepository autorRepository;

    /**
     * Commit -> Confirma as alterações
     * Rollback -> Desfaz as alterações
     */

    @Test
    @Transactional
    void transacaoSimples(){

    }
}
