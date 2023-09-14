package com.test.CIMBTest.repositories;


import com.test.CIMBTest.models.BlogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends JpaRepository<BlogEntity, Long> {
    
}
