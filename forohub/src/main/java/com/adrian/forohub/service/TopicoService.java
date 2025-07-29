package com.adrian.forohub.service;

import com.adrian.forohub.model.topico.*;
import com.adrian.forohub.repository.TopicoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    public Page<DatosListadoTopico>listar(Pageable pageable){
        return topicoRepository.findAll(pageable).map(DatosListadoTopico::new);
    }

    public Optional<DatosListadoTopico>buscarPorId(Long id){
        return topicoRepository.findById(id).map(DatosListadoTopico::new);
    }

    @Transactional
    public Optional<DatosRespuestaTopico>actualizar(DatosActualizacionTopico datos){
        Optional<Topico> optionalTopico = topicoRepository.findById(datos.id());
        if(optionalTopico.isPresent()){
            Topico topico = optionalTopico.get();
            topico.setTitulo(datos.titulo());
            topico.setMensaje(datos.mensaje());
            topico.setAutor(datos.autor());
            topico.setCurso(datos.curso());

            topicoRepository.save(topico);
            return  Optional.of(DatosRespuestaTopico.from(topico));
        }else {
            return Optional.empty();
        }
    }

    public void eliminarTopico(Long id){
        Optional<Topico> topico = topicoRepository.findById(id);
        if(topico.isEmpty()){
            throw new EntityNotFoundException("Tópico no encontrado con este id: " + id);
        }
        topicoRepository.deleteById(id);
    }
}
