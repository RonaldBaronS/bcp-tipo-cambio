package com.bcp.ms.tipocambio.service;

import com.bcp.ms.tipocambio.dao.TipoCambioDao;
import com.bcp.ms.tipocambio.entity.TipoCambioEntity;
import com.bcp.ms.tipocambio.model.TipoCambioRequest;
import com.bcp.ms.tipocambio.model.TipoCambioResponse;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rx.Observable;

/**
 * Class TipoCambioServiceImpl.
 * @author Ronald Baron.
 * @version 1.0
 */
@Service
@Slf4j
public class TipoCambioServiceImpl implements TipoCambioService {

	@Autowired
	private TipoCambioDao tipoCambioDao;
	
	@Override
	public Observable<TipoCambioEntity> guardarTipoCambio(TipoCambioEntity tipoCambioEntity) {
		log.info("TipoCambioServiceImpl.guardarTipoCambio");
		TipoCambioEntity  entity = tipoCambioDao.save(tipoCambioEntity);
		return Observable.just(entity);
	}

	@Override
	public Observable<TipoCambioResponse> obtenerTipoCambioMonto(
			TipoCambioRequest tipoCambioRequest, Long id) {
			log.info("TipoCambioServiceImpl.obtenerTipoCambioMonto");
			TipoCambioEntity entity = tipoCambioDao.findById(id).orElse(null);
			Observable<TipoCambioEntity> obsTipoCambioEntity = Observable.just(entity);
	        return obsTipoCambioEntity.map(tipoCambioEntity -> {
	        	TipoCambioRequest request = new TipoCambioRequest();
	        	request.setMonto(tipoCambioRequest.getMonto());
	    		request.setMonedaOrigen(tipoCambioRequest.getMonedaOrigen());
	    		request.setMonedaDestino(tipoCambioRequest.getMonedaDestino());
	    		String monedaOrigen = request.getMonedaOrigen();
	    		String monedaDestino = request.getMonedaDestino();
	    		TipoCambioResponse response = new TipoCambioResponse();
	    		response.setMonto(request.getMonto());
	    		if(entity.getMonedaOrigen().equals(monedaOrigen) && entity.getMonedaDestino().equals(monedaDestino)) {
	    			response.setMontoTipoCambio(request.getMonto()*tipoCambioEntity.getTipoCambio());
	    			response.setTipoCambio(tipoCambioEntity.getTipoCambio());
	    		}
	    		response.setMonedaOrigen(request.getMonedaOrigen());
	    		response.setMonedaDestino(request.getMonedaDestino());
	        	return response;
	        });
	}

	@Override
	public Observable<TipoCambioEntity> actualizarTipoCambio(TipoCambioEntity tipoCambioEntity, Long id) {
		log.info("TipoCambioServiceImpl.actualizarTipoCambio");
		TipoCambioEntity entity = tipoCambioDao.findById(id).orElse(null);
		Observable<TipoCambioEntity> obsTipoCambioEntityActual = Observable.just(entity);
		return obsTipoCambioEntityActual.map(p-> {
            p.setId(tipoCambioEntity.getId());
            p.setMonedaOrigen(tipoCambioEntity.getMonedaOrigen());
			p.setMonedaDestino(tipoCambioEntity.getMonedaDestino());
			p.setTipoCambio(tipoCambioEntity.getTipoCambio());
			tipoCambioDao.save(p);
			return p;
		});
	}

}
