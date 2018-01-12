package com.dxc.payroll.persistence.repositories;

import com.dxc.payroll.persistence.domain.Position;

public interface PositionRepository {
	
	Position getPositionByUCN(String UCN);
}
