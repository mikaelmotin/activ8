package com.activ8.security.service;

import java.util.List;

public interface SharingService {
    void shareEntity(String objId, String userId);
    void unshareEntity(String objId, String userId);
    List<?> getSharedEntities(String userId);
}
