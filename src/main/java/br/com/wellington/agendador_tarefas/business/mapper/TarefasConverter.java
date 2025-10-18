package br.com.wellington.agendador_tarefas.business.mapper;

import br.com.wellington.agendador_tarefas.business.dto.TarefaDto;
import br.com.wellington.agendador_tarefas.infraestructure.entity.TarefasEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TarefasConverter {
    TarefasEntity paraTarefaEntity(TarefaDto tarefasDto);
    TarefaDto paraTarefaDto(TarefasEntity tarefasEntity);
    List<TarefasEntity> paraListaTarefaEntity(List<TarefaDto> dtos);
    List<TarefaDto> paraListaTarefaDto(List<TarefasEntity> entity);
}
