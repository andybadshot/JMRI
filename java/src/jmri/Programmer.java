package jmri;

import java.beans.PropertyChangeListener;
import java.util.List;

/**
 * Provide access to the hardware DCC decoder programming capability.
 * <P>
 * Programmers come in multiple types:
 * <UL>
 * <LI>Global, previously Service Mode, e.g. on a programming track
 * <LI>Addressed, previously Ops Mode, e.g. "programming on the main"
 * </UL>
 * Different equipment may also require different programmers:
 * <ul>
 * <LI>DCC CV programming, on service mode track or on the main
 * <LI>CBUS Node Variable programmers
 * <LI>LocoNet System Variable programmers
 * <LI>LocoNet Op Switch programmers
 * <li>etc
 * </UL>
 * Depending on which type you have, only certain modes can be set. Valid modes
 * are specified by the class static constants.
 * <P>
 * You get a Programmer object from a {@link ProgrammerManager}, which in turn
 * can be located from the {@link InstanceManager}.
 * <p>
 * Starting in JMRI 3.5.5, the CV addresses are Strings for generality. The
 * methods that use ints for CV addresses will later be deprecated.
 * <hr>
 * This file is part of JMRI.
 * <P>
 * JMRI is free software; you can redistribute it and/or modify it under the
 * terms of version 2 of the GNU General Public License as published by the Free
 * Software Foundation. See the "COPYING" file for a copy of this license.
 * <P>
 * JMRI is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * <P>
 * @see jmri.ProgrammerManager
 * @author	Bob Jacobsen Copyright (C) 2001, 2008, 2013
 */
public interface Programmer {

    /**
     * Perform a CV write in the system-specific manner, and using the specified
     * programming mode.
     * <P>
     * Handles the legacy DCC case of a single-number address space.
     * <P>
     * Note that this returns before the write is complete; you have to provide
     * a ProgListener to hear about completion. The exceptions will only be
     * thrown at the start, not during the actual programming sequence. A
     * typical exception would be due to an invalid mode (though that should be
     * prevented earlier)
     * @deprecated As of 4.1.1, use #writeCV(java.lang.String, int, jmri.ProgListener)
     */
    @Deprecated
    public void writeCV(int CV, int val, ProgListener p) throws ProgrammerException;

    /**
     * Perform a CV write in the system-specific manner, and using the specified
     * programming mode.
     * <P>
     * Handles a general address space through a String address. Each programmer
     * defines the acceptable formats.
     * <P>
     * Note that this returns before the write is complete; you have to provide
     * a ProgListener to hear about completion. The exceptions will only be
     * thrown at the start, not during the actual programming sequence. A
     * typical exception would be due to an invalid mode (though that should be
     * prevented earlier)
     */
    public void writeCV(String CV, int val, ProgListener p) throws ProgrammerException;

    /**
     * Perform a CV read in the system-specific manner, and using the specified
     * programming mode.
     * <P>
     * Handles the legacy DCC case of a single-number address space.
     * <P>
     * Note that this returns before the read is complete; you have to provide a
     * ProgListener to hear about completion. The exceptions will only be thrown
     * at the start, not during the actual programming sequence. A typical
     * exception would be due to an invalid mode (though that should be
     * prevented earlier)
     * @deprecated As of 4.1.1, use #readCV(java.lang.String, int, jmri.ProgListener)
     */
    @Deprecated
    public void readCV(int CV, ProgListener p) throws ProgrammerException;

    /**
     * Perform a CV read in the system-specific manner, and using the specified
     * programming mode.
     * <P>
     * Handles a general address space through a String address. Each programmer
     * defines the acceptable formats.
     * <P>
     * Note that this returns before the read is complete; you have to provide a
     * ProgListener to hear about completion. The exceptions will only be thrown
     * at the start, not during the actual programming sequence. A typical
     * exception would be due to an invalid mode (though that should be
     * prevented earlier)
     */
    public void readCV(String CV, ProgListener p) throws ProgrammerException;

    /**
     * Confirm the value of a CV using the specified programming mode. On some
     * systems, this is faster than a read.
     * <P>
     * Handles the legacy DCC case of a single-number address space.
     * <P>
     * Note that this returns before the confirm is complete; you have to
     * provide a ProgListener to hear about completion. The exceptions will only
     * be thrown at the start, not during the actual programming sequence. A
     * typical exception would be due to an invalid mode (though that should be
     * prevented earlier)
     * @deprecated As of 4.1.1, use #confirmCV(java.lang.String, int, jmri.ProgListener)
     */
    @Deprecated
    public void confirmCV(int CV, int val, ProgListener p) throws ProgrammerException;

    /**
     * Confirm the value of a CV using the specified programming mode. On some
     * systems, this is faster than a read.
     * <P>
     * Handles a general address space through a String address. Each programmer
     * defines the acceptable formats.
     * <P>
     * Note that this returns before the confirm is complete; you have to
     * provide a ProgListener to hear about completion. The exceptions will only
     * be thrown at the start, not during the actual programming sequence. A
     * typical exception would be due to an invalid mode (though that should be
     * prevented earlier)
     */
    public void confirmCV(String CV, int val, ProgListener p) throws ProgrammerException;

    /**
     * Get the list of {@link ProgrammingMode} supported by this Programmer. If
     * the order is significant, earlier modes are better.
     */
    public List<ProgrammingMode> getSupportedModes();

    /**
     * Set the programmer to a particular mode.
     * <p>
     * Mode is a bound parameter; mode changes fire listeneres.
     * <p>
     * Only modes returned by {@link #getSupportedModes} are supported. If an
     * invalid mode is requested, the active mode is unchanged.
     */
    public void setMode(ProgrammingMode p);

    /**
     * Get the current programming mode
     */
    public ProgrammingMode getMode();

    /**
     * Checks the general read capability, regardless of mode
     */
    public boolean getCanRead();

    /**
     * Checks the general read capability, regardless of mode, for a specific
     * address
     */
    public boolean getCanRead(String addr);

    /**
     * Checks the general write capability, regardless of mode
     */
    public boolean getCanWrite();

    /**
     * Checks the general write capability, regardless of mode, for a specific
     * address
     */
    public boolean getCanWrite(String addr);

    public void addPropertyChangeListener(PropertyChangeListener p);

    public void removePropertyChangeListener(PropertyChangeListener p);

    // error handling on request is via exceptions
    // results are returned via the ProgListener callback
    public String decodeErrorCode(int i);

}
