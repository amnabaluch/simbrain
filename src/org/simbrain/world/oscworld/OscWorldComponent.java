package org.simbrain.world.oscworld;

import java.io.OutputStream;

import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.illposed.osc.OSCPortOut;

import org.simbrain.workspace.Consumer;
import org.simbrain.workspace.WorkspaceComponent;
import org.simbrain.workspace.WorkspaceComponentListener;

/**
 * OSC world component.
 */
public final class OscWorldComponent
    extends WorkspaceComponent<WorkspaceComponentListener> {

    /** OSC port out. */
    private final OSCPortOut oscPortOut;

    /** List of OSC consumers. */
    private final List<OscMessageConsumer> consumers;

    /** Default OSC receiver host. */
    //private static final InetAddress DEFAULT_RECEIVER_HOST = InetAddress.getLocalHost();

    /** Default OSC receiver port. */
    private static final int DEFAULT_RECEIVER_PORT = 9999;


    /**
     * Create a new OSC world component with the specified name.
     *
     * @param name name of this OSC world component
     */
    public OscWorldComponent(final String name) {
        super(name);
        try {
            oscPortOut = new OSCPortOut(InetAddress.getLocalHost(), DEFAULT_RECEIVER_PORT);
        }
        catch (UnknownHostException e) {
            throw new RuntimeException("could not create OSC port out", e);
        }
        catch (SocketException e) {
            throw new RuntimeException("could not create OSC port out", e);
        }
        consumers = new ArrayList<OscMessageConsumer>();
        // TODO: just as an example for now...
        addMessage("/test");
    }


    /** {@inheritDoc} */
    public void close() {
        oscPortOut.close();
    }

    /** {@inheritDoc} */
    public void save(final OutputStream outputStream, final String format) {
        // empty
    }

    /** {@inheritDoc} */
    public void update() {
        // empty
    }

    /** {@inheritDoc} */
    public Collection<? extends Consumer> getConsumers() {
        return Collections.unmodifiableList(consumers);
    }

    /**
     * Return the OSC port out for this OSC world component.
     *
     * @return the OSC port out for this OSC world component
     */
    OSCPortOut getOscPortOut() {
        return oscPortOut;
    }

    /**
     * Add a new OSC message with the specified address.
     *
     * @param address OSC message address, must not be null and must start with <code>'/'</code> character
     */
    void addMessage(final String address) {
        OscMessageConsumer consumer = new OscMessageConsumer(address, this);
        consumers.add(consumer);
        // fire event?
    }
}