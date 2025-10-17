package br.com.wellington.agendador_tarefas.business.mapper;

import br.com.wellington.agendador_tarefas.business.dto.TarefaDto;
import br.com.wellington.agendador_tarefas.infraestructure.entity.TarefasEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TarefasConverter {
    TarefasEntity paraTarefaEntity(TarefaDto tarefasDto);
    TarefaDto paraTarefaDto(TarefasEntity tarefasEntity);
}
