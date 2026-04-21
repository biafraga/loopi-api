package com.senac.loopi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {"MAPBOX_TOKEN=token_falso_so_para_o_teste_nao_dar_erro"})
class LoopiApplicationTests {

	@Test
	void contextLoads() {
	}

}
