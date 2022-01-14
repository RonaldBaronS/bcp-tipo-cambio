package com.bcp.ms.tipocambio.service;

import com.bcp.ms.tipocambio.entity.TipoCambioEntity;
import com.bcp.ms.tipocambio.model.TipoCambioRequest;
import com.bcp.ms.tipocambio.model.TipoCambioResponse;

import rx.Observable;

/**
 * Interface TipoCambioService.
 * @author Ronald Baron.
 * @version 1.0
 */
public interface TipoCambioService {
	
	Observable<TipoCambioEntity> guardarTipoCambio(TipoCambioEntity tipoCambioEntity);	
	Observable<TipoCambioResponse> obtenerTipoCambioMonto(TipoCambioRequest tipoCambioRequest, Long id);
	Observable<TipoCambioEntity> actualizarTipoCambio(TipoCambioEntity tipoCambioEntity,Long id);
 
}
