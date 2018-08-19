package com.isupatches.wisefy.main

import android.net.wifi.WifiConfiguration
import com.isupatches.wisefy.BaseAndroidJUnit4TestClass
import com.isupatches.wisefy.VERIFICATION_SUCCESS_TIMEOUT
import com.isupatches.wisefy.WEP_NETWORK_PASSWORD
import com.isupatches.wisefy.WEP_NETWORK_SSID
import com.isupatches.wisefy.WiseFy.Companion.WIFI_MANAGER_FAILURE
import com.isupatches.wisefy.callbacks.AddNetworkCallbacks
import com.isupatches.wisefy.constants.MISSING_PARAMETER
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mockito.mock
import org.mockito.Mockito.timeout
import org.mockito.Mockito.verify

/**
 * Tests the ability to add WEP networks.
 *
 * @author Patches
 */
internal class AddWEPNetworkTests : BaseAndroidJUnit4TestClass() {

    @Test fun sync_failure_prechecks() {
        mockWiseFyPrechecksUtil.addNetwork_failure()
        assertEquals(MISSING_PARAMETER, wiseFy.addWEPNetwork(WEP_NETWORK_SSID, WEP_NETWORK_PASSWORD))
        verificationUtil.didNoTryToAddNetwork()
    }

    @Test fun sync_failure() {
        mockWiseFyPrechecksUtil.addNetwork_success()
        mockNetworkUtil.addNetwork_failure()
        assertEquals(WIFI_MANAGER_FAILURE, wiseFy.addWEPNetwork(WEP_NETWORK_SSID, WEP_NETWORK_PASSWORD))
        verificationUtil.triedToAddNetwork()
    }

    @Test fun sync_success() {
        mockWiseFyPrechecksUtil.addNetwork_success()
        mockNetworkUtil.addNetwork_success()
        assertNotEquals(1, wiseFy.addWEPNetwork(WEP_NETWORK_SSID, WEP_NETWORK_PASSWORD))
        verificationUtil.triedToAddNetwork()
    }

    @Test fun async_failure_prechecks() {
        mockWiseFyPrechecksUtil.addNetwork_failure()
        val mockCallbacks = mock(AddNetworkCallbacks::class.java)
        wiseFy.addWEPNetwork(WEP_NETWORK_SSID, WEP_NETWORK_PASSWORD, mockCallbacks)
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).wisefyFailure(MISSING_PARAMETER)
        verificationUtil.didNoTryToAddNetwork()
    }

    @Test fun async_failure_prechecks_nullCallback() {
        mockWiseFyPrechecksUtil.addNetwork_failure()
        nullCallbackUtil.callAddWEPNetwork(WEP_NETWORK_SSID, WEP_NETWORK_PASSWORD)
        verificationUtil.didNoTryToAddNetwork()
    }

    @Test fun async_failure() {
        mockWiseFyPrechecksUtil.addNetwork_success()
        mockNetworkUtil.addNetwork_failure()
        val mockCallbacks = mock(AddNetworkCallbacks::class.java)
        wiseFy.addWEPNetwork(WEP_NETWORK_SSID, WEP_NETWORK_PASSWORD, mockCallbacks)
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).failureAddingNetwork(WIFI_MANAGER_FAILURE)
        verificationUtil.triedToAddNetwork()
    }

    @Test fun async_failure_nullCallback() {
        mockWiseFyPrechecksUtil.addNetwork_success()
        mockNetworkUtil.addNetwork_failure()
        nullCallbackUtil.callAddWEPNetwork(WEP_NETWORK_SSID, WEP_NETWORK_PASSWORD)
        verificationUtil.triedToAddNetwork()
    }

    @Test fun async_success() {
        mockWiseFyPrechecksUtil.addNetwork_success()
        mockNetworkUtil.addNetwork_success()
        val mockCallbacks = mock(AddNetworkCallbacks::class.java)
        wiseFy.addWEPNetwork(WEP_NETWORK_SSID, WEP_NETWORK_PASSWORD, mockCallbacks)
        verify(mockCallbacks, timeout(VERIFICATION_SUCCESS_TIMEOUT)).networkAdded(anyInt(), any(WifiConfiguration::class.java))
        verificationUtil.triedToAddNetwork()
    }

    @Test fun async_success_nullCallback() {
        mockWiseFyPrechecksUtil.addNetwork_success()
        mockNetworkUtil.addNetwork_success()
        nullCallbackUtil.callAddWEPNetwork(WEP_NETWORK_SSID, WEP_NETWORK_PASSWORD)
        verificationUtil.triedToAddNetwork()
    }
}
