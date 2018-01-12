package com.dxc.payroll.persistence.jpa.repositories;

import javax.persistence.EntityManager;

import com.dxc.payroll.persistence.domain.Position;
import com.dxc.payroll.persistence.repositories.PositionRepository;

public class JpaPositionRepository implements PositionRepository {

	 private final EntityManager entityManager;
	
	 public JpaPositionRepository(final EntityManager entityManager) {
	        assert entityManager != null;
	        this.entityManager = entityManager;
	    }

	@Override
	public Position getPositionByUCN(String UCN) {
		// TODO Auto-generated method stub
		return null;
	}

}
