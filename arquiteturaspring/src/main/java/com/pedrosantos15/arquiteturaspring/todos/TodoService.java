package com.pedrosantos15.arquiteturaspring.todos;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class TodoService {

    private TodoRepository todoRepository;
    private TodoValidator validator;
    private MailSender mailSender;

    public TodoService(TodoRepository todoRepository, TodoValidator validator, MailSender mailSender) {
        this.todoRepository = todoRepository;
        this.validator = validator;
        this.mailSender = mailSender;
    }

    public TodoEntity salvar(TodoEntity novoTodo) {
        validator.validar(novoTodo);
        return todoRepository.save(novoTodo);
    }

    public void atualizarStatus(TodoEntity todo){
        todoRepository.save(todo);
        String status = todo.getConcluido() == Boolean.TRUE ? "Concluido" : "Não concluido";
        mailSender.enviar("Todo " + todo.getDescricao() + " foi atualizado");
    }

    public TodoEntity buscaPorId(Integer id){
        return todoRepository.findById(id).orElse(null);
    }
}
