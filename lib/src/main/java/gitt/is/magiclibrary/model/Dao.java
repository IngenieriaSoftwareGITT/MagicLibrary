package gitt.is.magiclibrary.model;

import java.util.Optional;
import java.util.List;
/**
 * @author Isabel Rom�n
 * @version 0.0
 * <p>Esta interfaz declara un modo gen�rico de manejo de la persistencia de entidades del tipo T</p>
 * <p>B�sicamente declara operaciones CRUD (crear, leer, actualizar y borrar la entidad)</p>
 *
 */
public interface Dao<T> {
	/**
	 * Operaci�n de lectura
	 * Devuelve la entidad de tipo T correspondiente al identificador pasado como par�metro
	 * @param primaryKey identificador un�voco de la entidad buscada
	 * @return el objeto correspondiente al id, encapsulado en un objeto Optional (facilita la gesti�n de la respuesta cuando no se ha encontrado la entidad en el repositorio de persistencia)
	 */
	Optional<T> findById(String primaryKey);
	/**
	 * Operaci�n de lectura
	 * Encuentra todas las entidades de tipo T del repositorio
	 * @return todas las entidades del tipo T encontradas en el repositorio de persistencia
	 */
	List<T> findAll();
	
	/**
	 * Operaci�n de escritura
	 * Almacena en el repositorio la entidad, de tipo T, pasada como par�metro
	 * @param t entidad a persistir
	 */
	void save (T t);
	/**
	 * Operaci�n de escritura, actualiza la entidad pasada como par�metro
	 * @param t entidad a modificar
	 * 
	 */
	void update(T t);
	/**
	 * Operaci�n de borrado
	 * Elimina del repositorio la entidad pasada como par�metro
	 * @param t entidad a eliminar
	 */
	void delete (T t);
	/**
	 * Operaci�n de borrado
	 * Elimina del repositorio la entidad cuyo identificador un�voco corresponde con el par�metro
	 * @param id identificador un�voco de la entidad a borrar
	 */
	void delete (String id);
	

}
