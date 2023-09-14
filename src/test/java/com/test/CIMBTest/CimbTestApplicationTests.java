package com.test.CIMBTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.CIMBTest.controllers.BlogController;
import com.test.CIMBTest.models.BlogEntity;
import com.test.CIMBTest.repositories.BlogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

//@WebMvcTest(controllers = BlogController.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CimbTestApplicationTests {

	@Autowired
	private BlogController blogController;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private BlogRepository blogRepository;


	@BeforeEach
	void setUp() {

		MockitoAnnotations.initMocks(this);
		BlogEntity blogEntity = new BlogEntity();
		blogEntity.setId(1L);
		blogEntity.setTitle("Sample Title");
		blogEntity.setBody("Sample Body");
		blogEntity.setAuthor("Sample Author");


	}

	@Test
	public void testCreateBlogPost() throws Exception {
		BlogEntity blogEntity = new BlogEntity();
		blogEntity.setTitle("Sample Title");
		blogEntity.setBody("Sample Body");
		blogEntity.setAuthor("Sample Author");

		mockMvc.perform(MockMvcRequestBuilders.post("/api/addBlog")
						.contentType(MediaType.APPLICATION_JSON)
						.content(asJsonString(blogEntity)))
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andDo(print());
	}

	@Test
	public void testGetBlogPostById() throws Exception {
		BlogEntity blogEntity = new BlogEntity();
		blogEntity.setId(1L);
		blogEntity.setTitle("Sample Title");
		blogEntity.setBody("Sample Body");
		blogEntity.setAuthor("Sample Author");
		blogRepository.save(blogEntity);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/getBlogsById/1"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Sample Title"))
				.andDo(print());
	}

	@Test
	public void testRetrieveAllBlogPosts() throws Exception {
		List<BlogEntity> blogEntityList = new ArrayList<>();
		BlogEntity post1 = new BlogEntity();
		post1.setId(1L);
		post1.setTitle("Post 1");
		post1.setBody("Body of Post 1");
		post1.setAuthor("Author 1");
		blogRepository.save(post1);
		blogEntityList.add(post1);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/getAllBlogs"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("Post 1"))
				.andDo(print());
	}

	@Test
	public void testUpdateBlogPost() throws Exception {
		BlogEntity updatedBlog = new BlogEntity();
		updatedBlog.setId(1L);
		updatedBlog.setTitle("Updated Title");
		updatedBlog.setBody("Updated Body");
		updatedBlog.setAuthor("Updated Author");
		blogRepository.save(updatedBlog);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/editBlogs/1")
						.contentType(MediaType.APPLICATION_JSON)
						.content(asJsonString(updatedBlog)))
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Updated Title"))
				.andDo(print());
	}

	@Test
	public void testDeleteBlogPost() throws Exception {
		BlogEntity blogEntity = new BlogEntity();
		blogEntity.setId(1L);
		blogEntity.setTitle("Sample Title");
		blogEntity.setBody("Sample Body");
		blogEntity.setAuthor("Sample Author");
		blogRepository.save(blogEntity);

		mockMvc.perform(MockMvcRequestBuilders.delete("/api/deleteBlogById/1"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(print());
	}

	// Helper method to convert objects to JSON
	private String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}

