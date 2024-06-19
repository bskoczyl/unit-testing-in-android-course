package com.techyourchance.testdrivendevelopment.exercise8;

import com.techyourchance.testdrivendevelopment.example11.FetchCartItemsUseCase;
import com.techyourchance.testdrivendevelopment.exercise8.networking.ContactSchema;
import com.techyourchance.testdrivendevelopment.exercise8.networking.GetContactsHttpEndpoint;

import java.util.List;

public class FetchContactsUseCase {

    interface Listener {
        void onContactsFetched(List<ContactSchema> contacts);
        void onContactsFetchFailure();
    }

    GetContactsHttpEndpoint getContactsHttpEndpoint;

    public FetchContactsUseCase(GetContactsHttpEndpoint getContactsHttpEndpoint) {
        this.getContactsHttpEndpoint = getContactsHttpEndpoint;
    }

    public void FetchContactsAndNotify(String filter) {
        getContactsHttpEndpoint.getContacts(filter, new GetContactsHttpEndpoint.Callback() {
            @Override
            public void onGetContactsSucceeded(List<ContactSchema> cartItems) {

            }

            @Override
            public void onGetContactsFailed(GetContactsHttpEndpoint.FailReason failReason) {

            }
        });
    }

    public void registerListener(FetchCartItemsUseCase.Listener listener) {
        mListeners.add(listener);
    }

    public void unregisterListener(FetchCartItemsUseCase.Listener listener) {
        mListeners.remove(listener);
    }
}
