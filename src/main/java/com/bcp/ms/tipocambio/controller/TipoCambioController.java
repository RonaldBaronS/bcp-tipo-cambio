package com.bcp.ms.tipocambio.controller;

import com.bcp.ms.tipocambio.config.JwtTokenUtil;
import com.bcp.ms.tipocambio.entity.TipoCambioEntity;
import com.bcp.ms.tipocambio.model.JwtRequest;
import com.bcp.ms.tipocambio.model.JwtResponse;
import com.bcp.ms.tipocambio.model.TipoCambioRequest;
import com.bcp.ms.tipocambio.model.TipoCambioResponse;
import com.bcp.ms.tipocambio.service.TipoCambioService;
import com.bcp.ms.tipocambio.util.TipoCambioConstantes;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import rx.Observable;

@RestController
@RequestMapping("/api")
@Slf4j
public class TipoCambioController {

	@Autowired
	private TipoCambioService tipoCambioService;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService userDetailsService;
	
	 /**
     * Metodo para registrar tipoCambio.
     */
	@PostMapping(value = "/tipocambio", produces = TipoCambioConstantes.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public Observable<TipoCambioEntity> registrarTipoCambio(@RequestBody TipoCambioEntity request) {
		log.info("TipoCambioController.registrarTipoCambio");
		return tipoCambioService.guardarTipoCambio(request);
	}
	
	 /**
     * Metodo para obtener monto por tipo de cambio.
     */
	@GetMapping(value = "/tipocambio/{id}", produces = TipoCambioConstantes.APPLICATION_JSON_UTF8_VALUE)
	public Observable<TipoCambioResponse> obtenerTipoCambio(@RequestBody TipoCambioRequest request, @PathVariable Long id) {
		log.info("TipoCambioController.obtenerTipoCambio");
		return tipoCambioService.obtenerTipoCambioMonto(request, id);
	}
	
	 /**
     * Metodo para actualizar tipoCambio.
     */
	@PutMapping(value = "/tipocambio/{id}", produces = TipoCambioConstantes.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public Observable<TipoCambioEntity> actualizarTipoCambio(@RequestBody TipoCambioEntity request, @PathVariable Long id) {
		log.info("TipoCambioController.actualizarTipoCambio");
		return tipoCambioService.actualizarTipoCambio(request, id);
	}
	
	 /**
     * Metodo para obtener token.
     */
	@PostMapping(value = "/token", produces = TipoCambioConstantes.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest jwtRequest)
			throws Exception {
        log.info("JwtAuthenticationController.createAuthenticationToken");
		authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
		final UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(new JwtResponse(token));
	}

	private void authenticate(String username, String password) throws Exception {
		log.info("JwtAuthenticationController.authenticate");
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			log.info("JwtAuthenticationController.DisabledException");
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			log.info("JwtAuthenticationController.BadCredentialsException");
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
	
}