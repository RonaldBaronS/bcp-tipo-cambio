package com.bcp.ms.tipocambio.util;

/**
 * Clase ipoCambioConstantes.
 * @author Ronald Baron.
 * @version 1.0
 */
public class TipoCambioConstantes {
    
	//General
	public static final String CADENA_VACIA = "";
    // Application
    public static final String APPLICATION_JSON_UTF8_VALUE = "application/json;charset=UTF-8";
    
    //Header
    public static final String HDR_AUTHORIZATION = "Authorization";
    
    // Mensajes de Validacion
    public static final String MENSAJE_EXITOSO = "Registro exitoso.";
    public static final String MENSAJE_ELIMINACION = "Registro eliminado.";
    public static final String MENSAJE_ELIMINACION_TODO = "Se elimino todos los registros.";
    public static final String MENSAJE_ACTUALIZACION = "Registro actualizado";
    public static final String MENSAJE_HEADER_INVALIDO =
            "Debe enviar headers validos: migrationIndicator, originSystem.";
    
    // Formato fecha
    public static final String DATE_TIME_FORMATTER = "dd/MM/yyyy";
    
    // Mensajes de BD
    public static final String MENSAJE_ERROR_CONEXION_BD = "Error de conexión a Base de datos.";
    public static final String MENSAJE_NO_CONTENT = "No existe registro.";
    
    
}
