package com.bsd.exampleapp.springboot.tree.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bsd.exampleapp.springboot.tree.model.Node;

/**
 * Repository to give possibility to make all operations with {@link Node} data.
 * 
 * @author JS 2018-10-14
 *
 */
public interface NodeRepository extends JpaRepository<Node, Long>{
	
	public List<Node> findByParentId(Long parentId);
}
