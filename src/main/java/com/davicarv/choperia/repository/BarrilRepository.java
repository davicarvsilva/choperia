package com.davicarv.choperia.repository;

import java.util.Calendar;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.davicarv.choperia.domain.Barril;

@Repository
public interface BarrilRepository extends JpaRepository<Barril, Long> {
	public List<Barril> findByTamanho(int tamanho);
	//public List<Barril> findByMarcaBarrilEnum(int marcaBarrilEnum);
	public List<Barril> findByValidade(Calendar validade);
}
