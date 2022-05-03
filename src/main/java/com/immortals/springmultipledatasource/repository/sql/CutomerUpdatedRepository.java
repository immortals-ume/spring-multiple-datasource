package com.immortals.springmultipledatasource.repository.sql;

import com.immortals.springmultipledatasource.model.sql.CustomerDetailsUpdated;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CutomerUpdatedRepository extends JpaRepository< CustomerDetailsUpdated,Long>{
}
