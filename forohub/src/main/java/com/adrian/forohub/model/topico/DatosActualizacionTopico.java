package com.adrian.forohub.model.topico;


import jakarta.validation.constraints.NotNull;

public record DatosActualizacionTopico(

        @NotNull
        Long id,
        @NotNull
        String titulo,
        @NotNull
        String mensaje,
        @NotNull
        String autor,
        @NotNull
        String curso) {
}
