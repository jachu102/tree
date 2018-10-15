package com.bsd.exampleapp.springboot.tree.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bsd.exampleapp.springboot.tree.model.Node;
import com.bsd.exampleapp.springboot.tree.repository.NodeRepository;

@Service
public class NodeServiceImpl implements NodeService {
	
	@Autowired
	NodeRepository nodeRepository;

	@Override
	public Node add(Node newNode) {
		return nodeRepository.save( newNode );
	}

	@Override
	public void remove(Long nodeId) {
		nodeRepository.deleteById(nodeId);
	}

	@Override
	public Optional<Node> change(Node changedNode) {
	
		return Optional.ofNullable( nodeRepository.save(changedNode) );
	}

	@Override
	public Optional<Node> getRootTree() {
		
		return Optional.ofNullable( nodeRepository.findByParentId(null).get(0) );
	}

}
