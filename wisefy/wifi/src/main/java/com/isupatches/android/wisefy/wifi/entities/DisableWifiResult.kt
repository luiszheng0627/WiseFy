/*
 * Copyright 2022 Patches Barrett
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
package com.isupatches.android.wisefy.wifi.entities

/**
 * A set of classes and objects that are data representations of a result when disabling Wifi.
 *
 * @author Patches Barrett
 * @since 12/2022, version 5.0.0
 */
sealed class DisableWifiResult {

    /**
     * A data representation for when there is a success disabling Wifi.
     *
     * @see DisableWifiResult
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    sealed class Success : DisableWifiResult() {

        /**
         * A representation of when wifi is successfully disabled on pre-Android Q / SDK 29 devices.
         *
         * @see Success
         *
         * @author Patches Barrett
         * @since 12/2022, version 5.0.0
         */
        object Disabled : Success()

        /**
         * A representation of when the wifi settings screen is opened on Android Q / SDK 29 or higher devices for
         * the user to manually disable wifi.
         *
         * @see Success
         *
         * @author Patches Barrett
         * @since 12/2022, version 5.0.0
         */
        object WifiSettingScreenOpened : Success()
    }

    /**
     * A set of classes and objects that are data representations of a failure when disabling Wifi.
     *
     * @see DisableWifiResult
     *
     * @author Patches Barrett
     * @since 12/2022, version 5.0.0
     */
    sealed class Failure : DisableWifiResult() {

        /**
         * A representation of when there is a failure disabling wifi on pre-Android Q / SDK 29 devices.
         *
         * @see Failure
         *
         * @author Patches Barrett
         * @since 12/2022, version 5.0.0
         */
        object UnableToDisable : Failure()

        /**
         * A representation of a failure disabling wifi due to hitting an unexpected path causing an assertion.
         *
         * *NOTE* This is for developer specific feedback and should NEVER actually be hit unless there is a bug.
         *
         * @property message A text description describing the assertion error hit
         *
         * @see Failure
         *
         * @author Patches Barrett
         * @since 12/2022, version 5.0.0
         */
        data class Assertion(val message: String) : Failure()
    }
}
