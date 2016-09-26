package com.sample.flow.repository;

import com.sample.flow.model.ReqRspFlow;
import com.sample.flow.model.ReqPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * Created by andongxu on 9/20/16.
 */
@Component
public interface ReqRspFlowRepository extends JpaRepository<ReqRspFlow, ReqPK> {


}
