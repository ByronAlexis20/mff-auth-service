package mff.auth.seguridad.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "seg_opcion")
public class Opcion implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_opcion", columnDefinition = "int4")
	private Integer idMenu;

	@Column(name = "id_opcion_padre", columnDefinition = "int4")
	private Integer idMenuPadre;

	@Size(max = 150)
	@Column(name = "descripcion", length = 150)
	private String descripcion;

	@Size(max = 100)
	@Column(name = "vista", length = 150)
	private String formulario;

	@Size(max = 100)
	@Column(name = "icono", length = 100)
	private String icono;

	@Column(name = "posicion", columnDefinition = "int4")
	private Integer posicion;

	@Size(max = 1)
	@Column(name = "estado", length = 1)
	private String estado;

	public Opcion() {
		super();
	}

	public Opcion(Integer idMenu, Integer idMenuPadre, String descripcion,
			String formulario, String icono, Integer posicion,
			String estado) {
		super();
		this.idMenu = idMenu;
		this.idMenuPadre = idMenuPadre;
		this.descripcion = descripcion;
		this.formulario = formulario;
		this.icono = icono;
		this.posicion = posicion;
		this.estado = estado;
	}

	public Integer getIdMenu() {
		return idMenu;
	}

	public void setIdMenu(Integer idMenu) {
		this.idMenu = idMenu;
	}

	public Integer getIdMenuPadre() {
		return idMenuPadre;
	}

	public void setIdMenuPadre(Integer idMenuPadre) {
		this.idMenuPadre = idMenuPadre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getFormulario() {
		return formulario;
	}

	public void setFormulario(String formulario) {
		this.formulario = formulario;
	}

	public String getIcono() {
		return icono;
	}

	public void setIcono(String icono) {
		this.icono = icono;
	}

	public Integer getPosicion() {
		return posicion;
	}

	public void setPosicion(Integer posicion) {
		this.posicion = posicion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
}
