/**
 * Cada una de las operaciones Crud
 */
package edu.gitt.is.magiclibrary.controller;

/**
 * <p>Enumeración de las operaciones posibles</p>
 * @author Isabel Román
 *
 */
public enum CrudOperation {
	/**
	 * Creación y registro de una entidad nueva
	 */
	CREATE,
	/**
	 * Lectura de los datos registrados sobre una entidad
	 */
	READ,
	/**
	 * Actualización de los datos registrados sobre una entidad
	 */
	UPDATE,
	/**
	 * Eliminación de una entidad
	 */
	DELETE
}
