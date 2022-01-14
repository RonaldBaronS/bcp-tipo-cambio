package com.bcp.ms.tipocambio.dao;

import com.bcp.ms.tipocambio.entity.TipoCambioEntity;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface TipoCambioDao.
 * @author Ronald Baron.
 * @version 1.0
 */
public interface TipoCambioDao  extends JpaRepository<TipoCambioEntity, Long>{

}
