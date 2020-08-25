package tq.arxsoft.metalmaths.domain.limiter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tq.arxsoft.metalmaths.domain.limiter.entity.LimitEntity;
import tq.arxsoft.metalmaths.domain.limiter.repo.LimitRepo;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Date;

@Component
@Transactional
public class RequestLimiterDerby implements RequestLimiter {

    private LimitRepo limitRepo;
    private int maxAllowedRequest;
    private LocalDate date;

    @Autowired
    public RequestLimiterDerby(LimitRepo limitRepo) {
        this.limitRepo = limitRepo;
        this.maxAllowedRequest = maxAllowedRequest;
        date = LocalDate.now();

        maxAllowedRequest = 1000;
    }

    @Override
    public boolean isLimitReached() {

        LimitEntity limitEntity = limitRepo.findLimitEntityByDate(date);
        if( limitEntity == null ) {
            limitEntity = new LimitEntity();// .builder().date(date).maxAllowed(maxAllowedRequest).build();
            limitEntity.setDate(date);
            limitEntity.setMaxAllowed(maxAllowedRequest);
            limitRepo.save(limitEntity);
        }

        if( limitEntity.getCount() >= limitEntity.getMaxAllowed() ) {
            return true;
        }
        limitEntity.setCount(limitEntity.getCount() + 1);
        limitRepo.save(limitEntity);
        return false;
    }

    int getMaxAllowedRequest() {
        return maxAllowedRequest;
    }

    void setMaxAllowedRequest(int maxAllowedRequest) {
        this.maxAllowedRequest = maxAllowedRequest;
    }

    void setDate(LocalDate date) {
        this.date = date;
    }
}
