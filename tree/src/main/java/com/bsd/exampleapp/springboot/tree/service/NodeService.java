package com.bsd.exampleapp.springboot.tree.service;

import java.util.Optional;

import com.bsd.exampleapp.springboot.tree.dto.NodeDto;
import com.bsd.exampleapp.springboot.tree.model.Node;

/**
 * Service to make CRUD DB operations for Node.
 * Allow add new Node, remove, change and get Node from DB.
 * 
 * @author JS 2018-10-13
 */
public interface NodeService {
	
	/**
	 * Add new Node to DB.
	 * Id is auto generated.
	 * 
	 * @param newNode new Node (without id)
	 * @return saved Node
	 */
	public Node add(Node newNode);
	
	/**
	 * Remove existed Node with all dependent Nodes (childs and lower levels).
	 * 
	 * @param nodeId existed note id expected to remove 
	 */
	public void remove(Long nodeId);
	
	/**
	 * To change any data of existed Node.
	 * 
	 * @param changedNode new data of existed node
	 * @return saved changed Node
	 */
	public Optional<Node> change(Node changedNode);
	
	/**
	 * To get root Node with all dependents Nodes (all childs and lower levels).
	 * 
	 * @return root Node with all linked ones
	 */
	public Optional<Node> getRootTree();

}
