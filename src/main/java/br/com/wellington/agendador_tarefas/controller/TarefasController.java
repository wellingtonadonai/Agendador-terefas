package br.com.wellington.agendador_tarefas.controller;

import br.com.wellington.agendador_tarefas.business.TarefasService;
import br.com.wellington.agendador_tarefas.business.dto.TarefaDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
public class TarefasController {

    private final TarefasService tarefasService;

    @PostMapping
    public ResponseEntity<TarefaDto> gravarTarefa(@RequestBody TarefaDto dto,
                                                  @RequestHeader("Authorization")String token){
        return ResponseEntity.ok(tarefasService.gravarTarefa(token, dto));
    }
}
