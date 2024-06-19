package com.techyourchance.testdrivendevelopment.exercise6;

import com.techyourchance.testdrivendevelopment.exercise6.networking.FetchUserHttpEndpointSync;
import com.techyourchance.testdrivendevelopment.exercise6.networking.NetworkErrorException;
import com.techyourchance.testdrivendevelopment.exercise6.users.User;
import com.techyourchance.testdrivendevelopment.exercise6.users.UsersCache;

public class FetchUserUseCaseSyncImpl implements FetchUserUseCaseSync {

    UsersCache usersCache;
    FetchUserHttpEndpointSync fetchUserHttpEndpointSync;

    public FetchUserUseCaseSyncImpl(FetchUserHttpEndpointSync mFetchUserHttpEndpointSyncTestDouble, UsersCache mUsersCacheMock) {
        usersCache = mUsersCacheMock;
        fetchUserHttpEndpointSync = mFetchUserHttpEndpointSyncTestDouble;
    }

    @Override
    public UseCaseResult fetchUserSync(String userId) {
        User user = usersCache.getUser(userId);

        if (user != null) {
            return new UseCaseResult(Status.SUCCESS, user);
        } else {
            try {
                FetchUserHttpEndpointSync.EndpointResult epr = fetchUserHttpEndpointSync.fetchUserSync(userId);
                switch (epr.getStatus()) {
                    case SUCCESS: {
                        usersCache.cacheUser(new User(epr.getUserId(), epr.getUsername()));
                        return new UseCaseResult(Status.SUCCESS, new User(epr.getUserId(), epr.getUsername()));
                    }
                    case GENERAL_ERROR:
                    case AUTH_ERROR: {
                        return new UseCaseResult(Status.FAILURE, null);
                    }
                }

            } catch (NetworkErrorException e) {
                return new UseCaseResult(Status.NETWORK_ERROR, null);
            }
        }
        return new UseCaseResult(Status.NETWORK_ERROR, null);
    }
}
