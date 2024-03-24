/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *	  https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.restservice; //declaração de pacote em que a classe reside

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class GreetingControllerTests {

	@Autowired
	private MockMvc mockMvc; // declaração de variável mock  que será injetada pelo spring

	@Test
	public void noParamGreetingShouldReturnDefaultMessage() throws Exception //método verifica se a requisição get retorna a mensagem padrão hello world/
	 {

		this.mockMvc.perform(get("/greeting")).andDo(print()).andExpect(status().isOk()) // Simula a requisição get para greeting, andDo()(print()) imprime os detalhes da execução da request, andExpect(status().isOk()) verifica se o status da resposta é ok(200)
				.andExpect(jsonPath("$.content").value("Hello, World!")); // verifica se o conteúdo é a mensagem personalizada esperada
	}

	@Test
	public void paramGreetingShouldReturnTailoredMessage() throws Exception //Este método sde teste verifica se a requisição GET para /greeting com um parâmetro name retorna uma mensagem personalizada.
	
	{

		this.mockMvc.perform(get("/greeting").param("name", "Spring Community")) // Simula uma requisição GET para /greeting com o parâmetro name definido como "Spring Community".
				.andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.content").value("Hello, Spring Community!")); // Verifica se o conteúdo da resposta contém a mensagem personalizada esperada.
	}

}
