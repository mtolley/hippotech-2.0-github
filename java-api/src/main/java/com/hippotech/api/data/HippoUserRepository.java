package com.hippotech.api.data;

import org.springframework.data.jpa.repository.JpaRepository;
import javax.persistence.*;
import com.hippotech.api.model.HippoUser;


public interface HippoUserRepository extends JpaRepository<HippoUser, Long> {
    public HippoUser findFirstByEmail(String email);
}