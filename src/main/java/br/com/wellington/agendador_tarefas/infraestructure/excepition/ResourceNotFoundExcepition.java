package br.com.wellington.agendador_tarefas.infraestructure.excepition;

public class ResourceNotFoundExcepition extends RuntimeException{

    public ResourceNotFoundExcepition(String mensagem){

        super(mensagem);
    }

    public ResourceNotFoundExcepition(String mensagem, Throwable throwable){
        super(mensagem, throwable);
    }
}
