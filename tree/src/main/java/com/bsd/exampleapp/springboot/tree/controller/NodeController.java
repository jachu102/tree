package com.bsd.exampleapp.springboot.tree.controller;

import java.net.URI;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bsd.exampleapp.springboot.tree.dto.NodeDto;
import com.bsd.exampleapp.springboot.tree.model.Node;
import com.bsd.exampleapp.springboot.tree.repository.NodeRepository;
import com.bsd.exampleapp.springboot.tree.service.NodeService;

/**
 * {@link Node}'s management API.
 * 
 * @author JS 2018-10-14
 */
@RestController
@RequestMapping(path="node")
public class NodeController {
	
	@Autowired
	NodeService nodeService;
	
	@Autowired
	NodeRepository nodeRepository;
	
	/**
	 * To add new node.
	 * 
	 * @param newNode new node data (json), without id
	 * @return saved node with auto generated node.id
	 */
	@PostMapping(path="add")
	public ResponseEntity<NodeDto> add(@RequestBody NodeDto dto) {
		Node createdNode = nodeService.add( convertToNode.apply(dto) );
		
		return getResponse( Optional.ofNullable(convertToDto.apply(createdNode)) );
	}
	
	/**
	 * To remove expected node with his childs.
	 * 
	 * @param id node id which is expected to remove. His all childs and lower levels will be removed as well.
	 * @return
	 */
	@DeleteMapping(path="remove/{id}")
	public ResponseEntity<?> remove(@PathVariable Long id) {
		nodeService.remove(id);
		
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	/**
	 * To change existed node.
	 * 
	 * @param id id of existed node
	 * @param changedNode new nodes values (including id not necessary)
	 * @return saved updated node
	 */
	@PutMapping(path="change/{id}")
	public ResponseEntity<NodeDto> change(@PathVariable Long id, @RequestBody NodeDto changedNode) {
		changedNode.setId(id);
		Optional<Node> updatedNode = nodeService.change( convertToNode.apply(changedNode) );
		
		return getResponse( Optional.ofNullable(convertToDto.apply(updatedNode.get())) );
	}
	
	/**
	 * To get root Node with all childs and lower levels.
	 * 
	 * @return Node json with hierarchy
	 */
	@GetMapping(path="getRootTree")
	public ResponseEntity<Node> getRootTree() {
		Optional<Node> nodesTree = nodeService.getRootTree();
		
		return ResponseEntity.ok().body(nodesTree.get());
	}
	
	/**
	 * To prepare response with Node data.
	 * 
	 * @param nodeDto to include to response body
	 * @return
	 */
	private ResponseEntity<NodeDto> getResponse(Optional<NodeDto> nodeDto) {
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
	    		.buildAndExpand(nodeDto.get().getId() ).toUri();
		
		return ResponseEntity.created(location).body( nodeDto.get() );
	}
	
	private Function<NodeDto, Node> convertToNode = new Function<NodeDto, Node>() {
	    public Node apply(NodeDto nodeDto) {
			Node node = new Node();
			node.setValue( nodeDto.getValue() );
			Optional.ofNullable( nodeDto.getParentId() )
				.ifPresent( parentId -> nodeRepository.findById(parentId)
											//TO FIX, protect if parent not found
											.ifPresent( parent -> node.setParent(parent) ) );
			Optional.ofNullable( nodeDto.getId() )
				.ifPresent(  id -> node.setId(id) );
			
			return node;
	    }
	};

	private Function<Node, NodeDto> convertToDto = new Function<Node, NodeDto>() {
		public NodeDto apply(Node node) {
			NodeDto dto = new NodeDto();
			dto.setId(node.getId());
			dto.setValue(node.getValue());
			Optional.ofNullable( node.getParent() )
				.map( parent -> parent.getId() )
				.ifPresent(parentId -> dto.setParentId(parentId) );
			
			return dto;
		}
	};
}
