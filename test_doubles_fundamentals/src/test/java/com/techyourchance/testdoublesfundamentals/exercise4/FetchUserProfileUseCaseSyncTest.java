package com.techyourchance.testdoublesfundamentals.exercise4;

import com.techyourchance.testdoublesfundamentals.example4.networking.NetworkErrorException;
import com.techyourchance.testdoublesfundamentals.exercise4.networking.UserProfileHttpEndpointSync;
import com.techyourchance.testdoublesfundamentals.exercise4.users.User;
import com.techyourchance.testdoublesfundamentals.exercise4.users.UsersCache;

import org.jetbrains.annotations.Nullable;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class FetchUserProfileUseCaseSyncTest {

    FetchUserProfileUseCaseSync SUT;
    UserProfileHttpEndpointSync userProfileHttpEndpointSync;
    UsersCache usersCache;

    @Before
    public void setUp() throws Exception {

        userProfileHttpEndpointSync = new UserProfileHttpEndpointSyncTd();
        usersCache = new UsersCacheTd();
        SUT = new FetchUserProfileUseCaseSync(userProfileHttpEndpointSync, usersCache);


    }

    @Test
    public void name() {

        User user = new User()
        SUT.fetchUserProfileSync()
        userProfileHttpEndpointSync.getUserProfile()
        usersCache.cacheUser();
    }

    private static class UserProfileHttpEndpointSyncTd implements UserProfileHttpEndpointSync{

        @Override
        public EndpointResult getUserProfile(String userId) throws NetworkErrorException {
            return new EndpointResult(EndpointResultStatus.SUCCESS, userId, "", "");
        }
    }

    private static class UsersCacheTd implements UsersCache {

        List<User> users = new ArrayList<>();

        @Override
        public void cacheUser(User user) {
            users.add(user);
        }

        @Nullable
        @Override
        public User getUser(String userId) {
            for (User user : users) {
                if (user.getUserId().equals(userId)) {
                    return user;
                }
            }
            return null;
        }
    }
}