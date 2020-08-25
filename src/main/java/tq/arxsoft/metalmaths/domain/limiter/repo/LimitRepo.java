package tq.arxsoft.metalmaths.domain.limiter.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import tq.arxsoft.metalmaths.domain.limiter.entity.LimitEntity;

import java.time.LocalDate;

public interface LimitRepo extends JpaRepository<LimitEntity, Long> {
    public LimitEntity findLimitEntityByDate(LocalDate date);
}
