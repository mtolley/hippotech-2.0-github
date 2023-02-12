package com.hippotech.approval;

public class ApprovalRequestNotFoundException extends RuntimeException {
    public ApprovalRequestNotFoundException(Long id) {
        super("Could not find Approval Request: " + id);
    }
}
