package com.hippotech.api.data;

import com.hippotech.api.model.BlogPost;
import com.hippotech.api.model.BlogSubscriber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogSubscriberRepository extends JpaRepository<BlogSubscriber, String> {

}