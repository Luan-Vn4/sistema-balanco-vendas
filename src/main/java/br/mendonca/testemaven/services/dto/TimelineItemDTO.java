package br.mendonca.testemaven.services.dto;

import java.util.UUID;

public class TimelineItemDTO {
    private UUID entityId;
    private String entityType;
    private String displayName;
    private long creationOrder;

    public UUID getEntityId() {
        return entityId;
    }

    public void setEntityId(UUID entityId) {
        this.entityId = entityId;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public long getCreationOrder() {
        return creationOrder;
    }

    public void setCreationOrder(long creationOrder) {
        this.creationOrder = creationOrder;
    }
}
