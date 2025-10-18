package br.com.wellington.agendador_tarefas.business.mapper;

import br.com.wellington.agendador_tarefas.business.dto.TarefaDto;
import br.com.wellington.agendador_tarefas.infraestructure.entity.TarefasEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TarefaUpdateConverter {

    void updateDeTarefas(TarefaDto dto, @MappingTarget TarefasEntity entity);
}
