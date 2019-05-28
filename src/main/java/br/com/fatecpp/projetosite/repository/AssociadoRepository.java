/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fatecpp.projetosite.repository;

import br.com.fatecpp.projetosite.entidades.Associado;

import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author tadeu
 */
public interface AssociadoRepository extends CrudRepository<Associado, String> {
    
    Associado findById(int id);
}
