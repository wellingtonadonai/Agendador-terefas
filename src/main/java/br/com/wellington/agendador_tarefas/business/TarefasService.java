package br.com.wellington.agendador_tarefas.business;

import br.com.wellington.agendador_tarefas.business.dto.TarefaDto;
import br.com.wellington.agendador_tarefas.business.mapper.TarefasConverter;
import br.com.wellington.agendador_tarefas.infraestructure.entity.TarefasEntity;
import br.com.wellington.agendador_tarefas.infraestructure.enuns.StatusNotificacao;
import br.com.wellington.agendador_tarefas.infraestructure.repository.TarefasRepository;
import br.com.wellington.agendador_tarefas.infraestructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final TarefasConverter tarefasConverter;
    private final TarefasRepository tarefasRepository;
    private final JwtUtil jwtUtil;

    public TarefaDto gravarTarefa(String token, TarefaDto tarefaDto){

        String email = jwtUtil.extractUsername(token.substring(7));
        tarefaDto.setDataCriacao(LocalDateTime.now());
        tarefaDto.setStatusNotificacaoEnum(StatusNotificacao.PENDENTE);
        tarefaDto.setEmailUsuario(email);
        TarefasEntity tarefaEntity = tarefasConverter.paraTarefaEntity(tarefaDto);

        return tarefasConverter.paraTarefaDto(tarefasRepository.save(tarefaEntity));
    }




}
