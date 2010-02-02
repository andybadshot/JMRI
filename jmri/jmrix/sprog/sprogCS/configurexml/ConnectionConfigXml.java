package jmri.jmrix.sprog.sprogCS.configurexml;

import jmri.InstanceManager;
import jmri.jmrix.configurexml.AbstractConnectionConfigXml;
import jmri.jmrix.sprog.sprogCS.ConnectionConfig;
import jmri.jmrix.sprog.sprogCS.SprogCSSerialDriverAdapter;

/**
 * Handle XML persistance of layout connections by persistening
 * the SerialDriverAdapter (and connections). Note this is
 * named as the XML version of a ConnectionConfig object,
 * but it's actually persisting the SerialDriverAdapter.
 * <P>
 * This class is invoked from jmrix.JmrixConfigPaneXml on write,
 * as that class is the one actually registered. Reads are brought
 * here directly via the class attribute in the XML.
 *
 * @author Andrew Crosland Copyright: Copyright (c) 2006
 * @version $Revision: 1.3 $
 */
public class ConnectionConfigXml extends AbstractConnectionConfigXml {

    public ConnectionConfigXml() {
        super();
    }

    protected void getInstance() {
        adapter = SprogCSSerialDriverAdapter.instance();
    }

    protected void register() {
        InstanceManager.configureManagerInstance().registerPref(new ConnectionConfig(adapter));
    }

    // initialize logging
    static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(ConnectionConfigXml.class.getName());

}
