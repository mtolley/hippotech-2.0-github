package com.hippotech.api.data;

import com.hippotech.api.model.ApprovalRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApprovalRepository extends JpaRepository<ApprovalRequest, Long> {

}