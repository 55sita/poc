package com.handicraftsnepal.shecrafts.services;

import com.handicraftsnepal.shecrafts.entities.HandicraftBy;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional
public class HandicraftByServicesImpl implements HandicraftByServices {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public void insert(HandicraftBy handicraftBy) {
        entityManager.persist(handicraftBy);
    }
}
