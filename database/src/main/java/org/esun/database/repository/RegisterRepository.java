package org.esun.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.esun.database.custom.RegisterRepositoryCustom;
import org.esun.database.entity.User;

public interface RegisterRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User>, RegisterRepositoryCustom {
}
