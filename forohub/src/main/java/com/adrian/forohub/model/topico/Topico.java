package com.adrian.forohub.model.topico;

import jakarta.persistence.*;
import jakarta.validation.Valid;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "topico")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    @Builder.Default
    private LocalDateTime fechaCreacion = LocalDateTime.now();
    @Builder.Default
    private Boolean status = true;
    private String autor;
    private String curso;

    public Topico(@Valid DatosRegistroTopico datos) {

            this.titulo = datos.titulo();
            this.mensaje = datos.mensaje();
            this.autor = datos.autor();
            this.curso = datos.curso();
            this.fechaCreacion = LocalDateTime.now(); // Establece la fecha al momento actual
            this.status = true; // Activo por defecto
    }
}
