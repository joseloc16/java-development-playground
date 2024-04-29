package org.joseloc.javadevelopmentplayground.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Contract {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private String tipo;
    private String titulo;
    @Column(columnDefinition = "TEXT")
    private String descripcionContrato;
    private String clausula;
}