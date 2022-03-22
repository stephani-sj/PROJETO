package com.dev.ecommerce.modelos;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "compra")
public class Compra implements Serializable{

	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long comId;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date comDataCompra = new Date();
	private Double comValorTotal=0.;
	
	public Long getComId() {
		return comId;
	}
	public void setComId(Long comId) {
		this.comId = comId;
	}
	public Date getComDataCompra() {
		return comDataCompra;
	}
	public void setComDataCompra(Date comDataCompra) {
		this.comDataCompra = comDataCompra;
	}
	public Double getComValorTotal() {
		return comValorTotal;
	}
	public void setComValorTotal(Double comValorTotal) {
		this.comValorTotal = comValorTotal;
	}

}
