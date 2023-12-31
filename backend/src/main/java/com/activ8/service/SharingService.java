package com.activ8.service;

import java.util.List;

public interface SharingService {
    void shareEntity(String objId, String userId);
    void unshareEntity(String objId, String userId);
    List<?> getSharedEntities(String userId);
}
