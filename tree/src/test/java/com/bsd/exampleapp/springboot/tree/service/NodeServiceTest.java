package com.bsd.exampleapp.springboot.tree.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.bsd.exampleapp.springboot.tree.model.Node;
import com.bsd.exampleapp.springboot.tree.repository.NodeRepository;
import com.bsd.exampleapp.springboot.tree.service.NodeService;
import com.bsd.exampleapp.springboot.tree.service.NodeServiceImpl;

@RunWith(SpringRunner.class)
public class NodeServiceTest {
	
	@TestConfiguration
    static class NodeServiceImplTestContextConfiguration {
        @Bean
        public NodeService nodeService() {
            return new NodeServiceImpl();
        }
    }
	
	@Autowired
	NodeService nodeService;
	
	@Autowired
	NodeRepository nodeRepository;
	
	@Before
	public void setUp() {
		Node root = new Node();
		root.setId(1L);
		root.setValue("Root");
	    Mockito.when(nodeRepository.save(Mockito.any())).thenReturn(root);
	}
	
	@Test
	public void add() {
		Node root = new Node();
		root.setId(1L);
		root.setValue("Root");
		assertThat( nodeService.add(root) ).isEqualToComparingFieldByField(root);
	}
}
