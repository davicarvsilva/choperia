package com.davicarv.choperia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.davicarv.choperia.domain.Funcionario;
import com.davicarv.choperia.domain.Pessoa;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
	@Query("SELECT p FROM Pessoa p WHERE p.email = :email")
	public List<Pessoa> findByEmail(@Param("email") String email);
}
