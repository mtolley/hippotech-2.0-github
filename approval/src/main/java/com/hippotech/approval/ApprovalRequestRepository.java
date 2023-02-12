package com.hippotech.approval;

import org.springframework.data.jpa.repository.JpaRepository;

interface ApprovalRequestRepository extends JpaRepository<ApprovalRequest, Long> {

}