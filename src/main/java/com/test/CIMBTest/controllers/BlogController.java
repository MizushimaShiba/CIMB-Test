package com.test.CIMBTest.controllers;


import com.test.CIMBTest.models.BlogEntity;
import com.test.CIMBTest.repositories.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class BlogController {

    @Autowired
    BlogRepository blogRepository;

    @GetMapping("/getAllBlogs")
    public ResponseEntity<List<BlogEntity>> getAllBlogs() {
        try {
            List<BlogEntity> blogEntityList = new ArrayList<>();
            blogRepository.findAll().forEach(blogEntityList::add);

            if (blogEntityList.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(blogEntityList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getBlogsById/{id}")
    public ResponseEntity<BlogEntity> getBlogsById(@PathVariable Long id) {
        try {
            Optional<BlogEntity> blogObject = blogRepository.findById(id);

            if (blogObject.isPresent())
                return new ResponseEntity<>(blogObject.get(), HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addBlog")
    public ResponseEntity<BlogEntity> addBlog(@RequestBody BlogEntity blogEntity) {
        try {
            BlogEntity blogObj = blogRepository.save(blogEntity);
            return new ResponseEntity<>(blogObj, HttpStatus.CREATED);
        } catch (Exception e) {return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);}
    }

    @PostMapping("/editBlogs/{id}")
    public ResponseEntity<BlogEntity> editBlog(@PathVariable Long id, @RequestBody BlogEntity blogEntity) {
        try {
            Optional<BlogEntity> blogData = blogRepository.findById(id);

            if (blogData.isPresent()) {
                BlogEntity updatedBlog = blogData.get();
                updatedBlog.setAuthor(blogEntity.getAuthor());
                updatedBlog.setBody(blogEntity.getBody());
                updatedBlog.setTitle(blogEntity.getTitle());

                BlogEntity blogObj = blogRepository.save(updatedBlog);
                return new ResponseEntity<>(blogObj, HttpStatus.CREATED);
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);}
    }

    @DeleteMapping("/deleteBlogById/{id}")
    public ResponseEntity<BlogEntity> deleteBlog(@PathVariable Long id) {
        try {
            blogRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);}
    }
}
