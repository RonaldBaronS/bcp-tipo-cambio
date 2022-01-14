package com.bcp.ms.tipocambio.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Clase TipoCambioEntity.
 * @author Ronald Baron.
 * @version 1.0
 */
@Entity
@Table(name = "tipocambio")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TipoCambioEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private String monedaOrigen;
	
	@Column(nullable=false)
	private String monedaDestino;
	
	@Column(nullable=false)
	private Double tipoCambio;

}
