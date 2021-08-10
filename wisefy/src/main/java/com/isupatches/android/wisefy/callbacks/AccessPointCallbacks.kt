/*
 * Copyright 2021 Patches Klinefelter
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.isupatches.android.wisefy.callbacks

import com.isupatches.android.wisefy.accesspoints.entities.AccessPointData

interface GetNearbyAccessPointCallbacks : BaseWisefyCallbacks {
    fun onNearbyAccessPointsRetrieved(accessPoints: List<AccessPointData>)
    fun onNoNearbyAccessPoints()
}

interface GetRSSICallbacks : BaseWisefyCallbacks {
    fun onRSSIRetrieved(rssi: Int)
    fun onNoNetworkToRetrieveRSSI()
}

interface SearchForAccessPointCallbacks : BaseWisefyCallbacks {
    fun onAccessPointFound(accessPoint: AccessPointData)
    fun onNoAccessPointFound()
}

interface SearchForAccessPointsCallbacks : BaseWisefyCallbacks {
    fun onAccessPointsFound(accessPoints: List<AccessPointData>)
    fun onNoAccessPointsFound()
}

interface SearchForSSIDCallbacks : BaseWisefyCallbacks {
    fun onSSIDFound(ssid: String)
    fun onSSIDNotFound()
}

interface SearchForSSIDsCallbacks : BaseWisefyCallbacks {
    fun onSSIDsFound(ssids: List<String>)
    fun onNoSSIDsFound()
}
