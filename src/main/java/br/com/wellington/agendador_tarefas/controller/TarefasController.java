package br.com.wellington.agendador_tarefas.controller;

import br.com.wellington.agendador_tarefas.business.TarefasService;
import br.com.wellington.agendador_tarefas.business.dto.TarefaDto;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

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
    @GetMapping("/eventos")
    public ResponseEntity<List<TarefaDto>> buscarlistadeTarefaPeriodo(

            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime dataFinal){

        return ResponseEntity.ok(tarefasService.tarefaAgendadasPorPeriodo(dataInicial, dataFinal));
    }
    @GetMapping
    public ResponseEntity <List<TarefaDto>> buscarTarefaEmail(@RequestHeader("Authorization")String token){
        return ResponseEntity.ok(tarefasService.buscaTarefaEmail(token));
    }
}
