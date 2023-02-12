package com.hippotech.api.data;

import com.hippotech.api.model.BlogPost;
import com.hippotech.api.model.HippoUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<BlogPost, Long> {

}