package com.isep.acme.domain.model;

public enum ApprovalStatus {

    PENDING("pending"),
    APPROVED("approved"),
    REJECTED("rejected");

    private String description;

    private ApprovalStatus(String description){
        this.description = description;
    }

    public String getDescription(){
        return description;
    }
}
