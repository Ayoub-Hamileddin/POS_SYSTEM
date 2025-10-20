package com.eyuup.domain;

import java.nio.file.AccessDeniedException;

import com.eyuup.modal.Store;
import com.eyuup.modal.User;

public class CheckAuthority {
   
    public static void isAuthorized(User user,Store store) throws Exception{
        
            boolean isAdmin=user.getRole().equals(UserRole.ROLE_ADMIN);

            boolean isManager=user.getRole().equals(UserRole.ROLE_STORE_MANAGER);

            boolean isSameSite=user.getId().equals(store.getStoreAdmin().getId());

            boolean isAuthorized=isAdmin || (isManager && isSameSite);

            if (!isAuthorized) {
                throw new AccessDeniedException("you are not allowed to modify this item");
            }
    }
}
