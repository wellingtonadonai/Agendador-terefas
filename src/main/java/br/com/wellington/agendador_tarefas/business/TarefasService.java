package br.com.wellington.agendador_tarefas.business;

import br.com.wellington.agendador_tarefas.business.dto.TarefaDto;
import br.com.wellington.agendador_tarefas.business.mapper.TarefaUpdateConverter;
import br.com.wellington.agendador_tarefas.business.mapper.TarefasConverter;
import br.com.wellington.agendador_tarefas.infraestructure.entity.TarefasEntity;
import br.com.wellington.agendador_tarefas.infraestructure.enuns.StatusNotificacao;
import br.com.wellington.agendador_tarefas.infraestructure.excepition.ResourceNotFoundExcepition;
import br.com.wellington.agendador_tarefas.infraestructure.repository.TarefasRepository;
import br.com.wellington.agendador_tarefas.infraestructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final TarefasConverter tarefasConverter;
    private final TarefasRepository tarefasRepository;
    private final JwtUtil jwtUtil;
    private final TarefaUpdateConverter converter;

    public TarefaDto gravarTarefa(String token, TarefaDto tarefaDto){

        String email = jwtUtil.extractUsername(token.substring(7));
        tarefaDto.setDataCriacao(LocalDateTime.now());
        tarefaDto.setStatusNotificacaoEnum(StatusNotificacao.PENDENTE);
        tarefaDto.setEmailUsuario(email);
        TarefasEntity tarefaEntity = tarefasConverter.paraTarefaEntity(tarefaDto);

        return tarefasConverter.paraTarefaDto(tarefasRepository.save(tarefaEntity));
    }
    public List<TarefaDto> tarefaAgendadasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal){
        return tarefasConverter.paraListaTarefaDto(tarefasRepository.findByDataEventoBetween(dataInicial, dataFinal));
    }

    public List<TarefaDto> buscaTarefaEmail(String token){
        String email = jwtUtil.extractUsername(token.substring(7));
        List<TarefasEntity> listaTarefa = tarefasRepository.findByEmailUsuario(email);

        return tarefasConverter.paraListaTarefaDto(listaTarefa);
    }

    public void deletaTarefaPorId(String id){
        try {
            tarefasRepository.deleteById(id);
        }catch (ResourceNotFoundExcepition e){
            throw new ResourceNotFoundExcepition("Erro ao deletar id, Id inexistente "+ id,
            e.getCause());
        }

    }
    public TarefaDto alteraStatus(StatusNotificacao statusNotificacao, String id){

        try{
        TarefasEntity entity = tarefasRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundExcepition("Tarefa não encontrada " + id));
        entity.setStatusNotificacaoEnum(statusNotificacao);
        tarefasRepository.save(entity);
        return tarefasConverter.paraTarefaDto(tarefasRepository.save(entity));

        }catch (ResourceNotFoundExcepition e){
            throw new ResourceNotFoundExcepition("Erro ao alterar status da tarefa " + e.getCause());
        }

    }
    public TarefaDto updateTarefas(TarefaDto dto, String id) {
        try {
            TarefasEntity entity = tarefasRepository.findById(id).orElseThrow(()
                    -> new ResourceNotFoundExcepition("Tarefa não encontrada " + id));
            converter.updateDeTarefas(dto, entity);
            return tarefasConverter.paraTarefaDto(tarefasRepository.save(entity));

        } catch (ResourceNotFoundExcepition e) {
            throw new ResourceNotFoundExcepition("Erro ao alterar status da tarefa " + e.getCause());
        }

    }




}
