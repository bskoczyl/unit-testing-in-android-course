package com.techyourchance.testdrivendevelopment.exercise8;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;

import com.techyourchance.testdrivendevelopment.example11.networking.CartItemSchema;
import com.techyourchance.testdrivendevelopment.example11.networking.GetCartItemsHttpEndpoint;
import com.techyourchance.testdrivendevelopment.exercise8.networking.ContactSchema;
import com.techyourchance.testdrivendevelopment.exercise8.networking.GetContactsHttpEndpoint;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class FetchContactsUseCaseTest {

    FetchContactsUseCase SUT;
    @Mock GetContactsHttpEndpoint getContactsHttpEndpointMock;

    @Before
    public void setUp() throws Exception {
        SUT = new FetchContactsUseCase(getContactsHttpEndpointMock);
        success();
    }

    private void success() {
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                GetContactsHttpEndpoint.Callback callback = (GetContactsHttpEndpoint.Callback) args[1];
                callback.onGetContactsSucceeded(getContacts());
                return null;
            }
        }).when(getContactsHttpEndpointMock).getContacts(anyString(), any(GetContactsHttpEndpoint.Callback.class));
    }

    private List<ContactSchema> getContacts() {
        List<ContactSchema> schemas = new ArrayList<>();
        schemas.add(new ContactSchema("id", "fullName", "fullPhoneNumber", "imageUrl", 20));
        return schemas;
    }

    @Test
    public void FetchContacts_correctParamPassedToEndpoint() {
        ArgumentCaptor<String> acString = ArgumentCaptor.forClass(String.class);

        SUT.FetchContactsAndNotify("filter");

        verify(getContactsHttpEndpointMock).getContacts(acString.capture(), any(GetContactsHttpEndpoint.Callback.class));
        assertThat(acString.getValue(), is("filter"));
    }

    
}