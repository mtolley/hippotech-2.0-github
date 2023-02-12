package com.hippotech.api.data;

import com.hippotech.api.model.ApprovalRequest;
import com.hippotech.api.model.FraudCheck;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FraudCheckRepository extends JpaRepository<FraudCheck, Long> {

}