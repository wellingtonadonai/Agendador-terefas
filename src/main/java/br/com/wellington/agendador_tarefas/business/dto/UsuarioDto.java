package br.com.wellington.agendador_tarefas.business.dto;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDto {

    private String email;
    private String senha;

}
