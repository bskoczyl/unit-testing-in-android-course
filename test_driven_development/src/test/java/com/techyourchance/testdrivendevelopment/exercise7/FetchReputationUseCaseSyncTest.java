package com.techyourchance.testdrivendevelopment.exercise7;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.techyourchance.testdrivendevelopment.example10.networking.PingServerHttpEndpointSync;
import com.techyourchance.testdrivendevelopment.exercise7.networking.GetReputationHttpEndpointSync;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class FetchReputationUseCaseSyncTest {
    
    FetchReputationUseCaseSync SUT;
    
    @Mock
    GetReputationHttpEndpointSync getReputationHttpEndpointSyncMock;

    @Before
    public void setUp() throws Exception {
        
        getReputationHttpEndpointSyncMock = mock(GetReputationHttpEndpointSync.class);
        
        SUT = new FetchReputationUseCaseSync(getReputationHttpEndpointSyncMock);
        success();
    }



    @Test
    public void fetchReputation_success_successReturned() {
        FetchReputationUseCaseSync.UseCaseResult result = SUT.getReputation();
        Assert.assertEquals(1, result.rep);
    }

    @Test
    public void fetchReputation_failure_failureReturned() throws Exception {
        failure();
        FetchReputationUseCaseSync.UseCaseResult result = SUT.getReputation();
        Assert.assertEquals(0, result.rep);
    }

    private void failure() throws Exception {
        when(getReputationHttpEndpointSyncMock.getReputationSync()).thenReturn(new GetReputationHttpEndpointSync.EndpointResult(GetReputationHttpEndpointSync.EndpointStatus.GENERAL_ERROR, 0));
    }

    private void success() throws Exception {
        when(getReputationHttpEndpointSyncMock.getReputationSync()).thenReturn(new GetReputationHttpEndpointSync.EndpointResult(GetReputationHttpEndpointSync.EndpointStatus.SUCCESS, 1));
    }
}