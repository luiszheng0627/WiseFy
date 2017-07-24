package com.isupatches.wisefy;


import android.net.wifi.ScanResult;
import com.isupatches.wisefy.constants.Capabilities;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;


public class IsNetworkWPA2Test extends BaseAndroidJUnit4TestClass {

    @Test
    public void failure_differentCapability() {
        ScanResult scanResult = mock(ScanResult.class);
        scanResult.capabilities = "Other";
        assertEquals(false, mWiseFy.isNetworkWPA2(scanResult));
    }

    @Test
    public void failure_emptyCapabilities() {
        ScanResult scanResult = mock(ScanResult.class);
        scanResult.capabilities = "";
        assertEquals(false, mWiseFy.isNetworkWPA2(scanResult));
    }

    @Test
    public void failure_nullCapabilities() {
        ScanResult scanResult = mock(ScanResult.class);
        assertEquals(false, mWiseFy.isNetworkWPA2(scanResult));
    }

    @Test
    public void failure_nullScanResult() {
        assertEquals(false, mWiseFy.isNetworkWPA2(null));
    }

    @Test
    public void success() {
        ScanResult scanResult = mock(ScanResult.class);
        scanResult.capabilities = Capabilities.WPA2;
        assertEquals(true, mWiseFy.isNetworkWPA2(scanResult));
    }
}
