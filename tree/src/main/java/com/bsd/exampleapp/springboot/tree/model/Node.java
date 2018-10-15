package com.bsd.exampleapp.springboot.tree.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Entity which represents one Node, which is elements of e tree.
 * Whereas tree include hierarchy of linked Nodes.
 * 
 * @author JS 2018-10-14
 *
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@EqualsAndHashCode(exclude={"childs"})
public class Node implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	
	@NonNull
	private String value;
	
	@ManyToOne
	@JoinColumn(name="parentId")
	@JsonBackReference
	@NonNull
	private Node parent;
	
	@OneToMany(mappedBy="parent", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JsonManagedReference
	private Set<Node> childs = new HashSet<>();
	
}
