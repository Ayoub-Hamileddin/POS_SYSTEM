package com.eyuup.domain;

import com.eyuup.modal.Store;
import com.eyuup.modal.User;

public class CheckAuthority {
   
    public static void isAuthorized(User user,Store store){
        
            boolean isAdmin=user.getRole().equals(UserRole.ROLE_ADMIN);

            boolean isManager=user.getRole().equals(UserRole.ROLE_STORE_MANAGER);

            boolean isSameSite=user.equals(store.getStoreAdmin());

            boolean isAuthorized=isSameSite &&(isAdmin || isManager);

            if (!isAuthorized) {
                throw new SecurityException("you are not allowed to modify this item");
            }
    }
}
