package gitt.is.magiclibrary.model;

import java.util.Optional;
import java.util.List;
/**
 * @author Isabel Román
 * @version 0.0
 * <p>Esta interfaz declara un modo genérico de manejo de la persistencia de entidades del tipo T</p>
 * <p>Básicamente declara operaciones CRUD (crear, leer, actualizar y borrar la entidad)</p>
 *
 */
public interface Dao<T> {
	/**
	 * Operación de lectura
	 * Devuelve la entidad de tipo T correspondiente al identificador pasado como parámetro
	 * @param primaryKey identificador unívoco de la entidad buscada
	 * @return el objeto correspondiente al id, encapsulado en un objeto Optional (facilita la gestión de la respuesta cuando no se ha encontrado la entidad en el repositorio de persistencia)
	 */
	Optional<T> findById(String primaryKey);
	/**
	 * Operación de lectura
	 * Encuentra todas las entidades de tipo T del repositorio
	 * @return todas las entidades del tipo T encontradas en el repositorio de persistencia
	 */
	List<T> findAll();
	
	/**
	 * Operación de escritura
	 * Almacena en el repositorio la entidad, de tipo T, pasada como parámetro
	 * @param t entidad a persistir
	 */
	void save (T t);
	/**
	 * Operación de escritura, actualiza la entidad pasada como parámetro
	 * @param t entidad a modificar
	 * 
	 */
	void update(T t);
	/**
	 * Operación de borrado
	 * Elimina del repositorio la entidad pasada como parámetro
	 * @param t entidad a eliminar
	 */
	void delete (T t);
	/**
	 * Operación de borrado
	 * Elimina del repositorio la entidad cuyo identificador unívoco corresponde con el parámetro
	 * @param id identificador unívoco de la entidad a borrar
	 */
	void delete (String id);
	

}
