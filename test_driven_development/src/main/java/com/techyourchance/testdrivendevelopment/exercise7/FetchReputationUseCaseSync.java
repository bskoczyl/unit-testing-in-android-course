package com.techyourchance.testdrivendevelopment.exercise7;

import com.techyourchance.testdrivendevelopment.exercise6.networking.FetchUserHttpEndpointSync;
import com.techyourchance.testdrivendevelopment.exercise7.networking.GetReputationHttpEndpointSync;

public class FetchReputationUseCaseSync {

    public static class UseCaseResult {
        public GetReputationHttpEndpointSync.EndpointStatus status;
        public int rep;

        public UseCaseResult(GetReputationHttpEndpointSync.EndpointStatus status, int rep) {
            this.status = status;
            this.rep = rep;
        }
    }

    GetReputationHttpEndpointSync getReputationHttpEndpointSync;


    public FetchReputationUseCaseSync(GetReputationHttpEndpointSync getReputationHttpEndpointSync) {
        this.getReputationHttpEndpointSync = getReputationHttpEndpointSync;
    }

    public UseCaseResult getReputation() {

        UseCaseResult useCaseResult = new UseCaseResult(getReputationHttpEndpointSync.getReputationSync().getStatus(), getReputationHttpEndpointSync.getReputationSync().getReputation());

        return useCaseResult;
    }
}
