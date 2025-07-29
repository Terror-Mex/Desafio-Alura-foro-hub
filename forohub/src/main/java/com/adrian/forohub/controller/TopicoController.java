package com.adrian.forohub.controller;

import com.adrian.forohub.model.topico.*;
import com.adrian.forohub.repository.TopicoRepository;
import com.adrian.forohub.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topics")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    @Autowired
    private TopicoService topicoService;

    @Transactional
    @PostMapping
    public ResponseEntity<DatosRespuestaTopico> registroDeTopico (@RequestBody @Valid DatosRegistroTopico datos, UriComponentsBuilder uriComponentsBuilder){
        var topico = new Topico(datos);
        repository.save(topico);
        var datosRespuesta = DatosRespuestaTopico.from(topico);
        var uri = uriComponentsBuilder.path("/topics/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(datosRespuesta);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoTopico>> listar(
            @PageableDefault(size = 10, sort = "fechaCreacion", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return ResponseEntity.ok(topicoService.listar(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosListadoTopico>buscarPorId(@PathVariable Long id){
        return topicoService.buscarPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopico>actualizar(@PathVariable Long id, @RequestBody @Valid DatosActualizacionTopico datos){
        if(!id.equals(datos.id())){
            return ResponseEntity.badRequest().build();
        }
        return  topicoService.actualizar(datos).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>eliminar(@PathVariable Long id){
        topicoService.eliminarTopico(id);
        return ResponseEntity.noContent().build();
    }
}
