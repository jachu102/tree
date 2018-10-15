package com.bsd.exampleapp.springboot.tree;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bsd.exampleapp.springboot.tree.model.Node;
import com.bsd.exampleapp.springboot.tree.repository.NodeRepository;

/**
 * Run app with sample initial data.
 * 
 * @author JS 2018-10-14
 *
 */
@SpringBootApplication
public class TreeApplication implements CommandLineRunner {
	
	@Autowired
	NodeRepository nodeRepository;

	public static void main(String[] args) {
		SpringApplication.run(TreeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		saveInitData();
	}

	private void saveInitData() {
		Node root = new Node();
		root.setValue("Father");
		root = nodeRepository.save( root );
		Node son = nodeRepository.save( new Node("Son 1", root) );
		nodeRepository.save( new Node("Grandson 1", son) );
		nodeRepository.save( new Node("Grandson 2", son) );
		Node son2 = nodeRepository.save( new Node("Son 2", root) );
		nodeRepository.save( new Node("Grandson 3", son2) );
		Node grandSon4 = nodeRepository.save( new Node("Grandson 4", son2) );
		nodeRepository.save( new Node("GrandGrandSon", grandSon4) );
	}
}
