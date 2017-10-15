package org.vini.diff;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.vini.diff.controller.DiffController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DiffApiAppTest {
	
	@Autowired
	private DiffController diffController;
	
    @Test
    public void contextLoads() throws Exception {
    	assertThat(diffController).isNotNull();
    }
    
}
