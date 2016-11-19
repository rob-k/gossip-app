package de.uulm.vs.gossip;

import android.app.Application;

public class GossipApplication extends Application {

    @Override
    public void onCreate() {

        // TODO Setup framework, connection handler and start a scheduled discovery

        super.onCreate();
    }

    /**
     * Registers the broadcast receivers with the intent values to be matched.
     * Can be called by activities as default implementation for
     * {@link android.app.Activity#onResume()}.
     */
    public void onResume() {

        // TODO Make the framework register its receivers

    }

    /**
     * Unregisters the broadcast receivers and makes the CRDTController save its data to the device storage.
     * Can be called by activities as default implementation for {@link android.app.Activity#onPause()}.
     */
    public void onPause() {

        // TODO Make the framework unregister its receivers and make it save its data to the device storage

    }

}
