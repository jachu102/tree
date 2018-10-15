package com.bsd.exampleapp.springboot.tree.dto;

import java.io.Serializable;

import com.bsd.exampleapp.springboot.tree.model.Node;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * To transfer {@link Node} data.
 * 
 * @author JS 2018-10-14
 *
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class NodeDto implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@NonNull
	private Long id;
	
	@NonNull
	private String value;
	
	@NonNull
	private Long parentId;

}
