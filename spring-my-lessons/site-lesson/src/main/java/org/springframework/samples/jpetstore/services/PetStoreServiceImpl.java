package org.springframework.samples.jpetstore.services;

import org.springframework.samples.jpetstore.dao.jpa.JpaAccountDao;
import org.springframework.samples.jpetstore.dao.jpa.JpaItemDao;

public class PetStoreServiceImpl {
    JpaAccountDao accountDao;
    JpaItemDao itemDao;

    public JpaAccountDao getAccountDao() {
        return accountDao;
    }

    public void setAccountDao(JpaAccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public JpaItemDao getItemDao() {
        return itemDao;
    }

    public void setItemDao(JpaItemDao itemDao) {
        this.itemDao = itemDao;
    }
}