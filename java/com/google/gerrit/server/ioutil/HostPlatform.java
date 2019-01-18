// Copyright (C) 2009 The Android Open Source Project
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.gerrit.server.ioutil;

import java.security.AccessController;
import java.security.PrivilegedAction;

public final class HostPlatform {
  private static final boolean win32 = compute("windows");
  private static final boolean mac = compute("mac");

  /** @return true if this JVM is running on a Windows platform. */
  public static boolean isWin32() {
    return win32;
  }

  public static boolean isMac() {
    return mac;
  }

  private static boolean compute(String platform) {
    final String osDotName =
        AccessController.doPrivileged(
            new PrivilegedAction<String>() {
              @Override
              public String run() {
                return System.getProperty("os.name");
              }
            });
    return osDotName != null && osDotName.toLowerCase().contains(platform);
  }

  private HostPlatform() {}
}
